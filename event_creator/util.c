/*
 * util.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <ctype.h>
#include <dirent.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>

#include "data.h"
#include "dbg.h"
#include "util.h"
#include "vector.h"

/*
 * This function was taken from
 * http://stackoverflow.com/questions/314401/how-to-read-a-line-from-the-console-in-c
 * I could have used scanf, but I think just reading in a whole line each time and
 * then parsing it makes everything easier to read in the rest of the source
 */
char* readline() {
  char* line = malloc(100), *linep = line;
  size_t lenmax = 100, len = lenmax;
  int c;

  if(line == NULL)
    return NULL;

  for(;;) {
    c = fgetc(stdin);
    if(c == EOF)
      break;

    if(--len == 0) {
      len = lenmax;
      char * linen = realloc(linep, lenmax *= 2);

      if(linen == NULL) {
        free(linep);
        return NULL;
      }
      line = linen + (line - linep);
      linep = linen;
    }

    if((*line++ = c) == '\n')
      break;
  }
  *line = '\0';
  strtok(linep, "\n"); /* this line is mine, just to strip the newline */
  return linep;
}

char* make_event_dir(const char* root_dir, const char* event_name) {
  char* new_dir;
  char* tmp_dir;
  int i;

  /*
   * first, we're going to sanitize the event name by turning all the spaces into
   * underscores and lower casing the whole thing
   */
  if (event_name) {
    tmp_dir = strdup(event_name);
    for (i = 0; tmp_dir[i]; i++) {
      if (tmp_dir[i] == ' ') tmp_dir[i] = '_';
      else tmp_dir[i] = tolower(tmp_dir[i]);
    }
  }

  if (event_name) {
    /* grab enough memory to contain the whole path */
    /* that is, length(root) + length(dir) + '/' + null */
    new_dir = malloc(sizeof(char) * (strlen(root_dir) + strlen(tmp_dir) + 2));
    check_mem(new_dir);
    strcpy(new_dir, root_dir);
    strcat(new_dir, tmp_dir);
    strcat(new_dir, "/");
  } else {
    new_dir = strdup(root_dir);
  }

  /*
   * then we're going to either create the directory or establish that it already
   * exists
   */
  if (mkdir(new_dir, S_IRWXU|S_IRGRP|S_IXGRP) != 0) {
    check(errno == EEXIST, "Could not create directory %s", new_dir);
    debug("%s already exists", new_dir);
  } else {
    debug("%s created", new_dir);
  }

  if (event_name) free(tmp_dir); /* this looks a little clunky but if there's no
                                    event name, there's no tmp_dir */
  return new_dir;
error:
  exit(EXIT_FAILURE);
}

void dispose_event(void* event_ptr) {
  Event* event = *(Event**) event_ptr;
  Event_destroy(event);
}

void dispose_str(void* str_ptr) {
  char* str = *(char**) str_ptr;
  if (str) free(str);
}

/*
 * This function was pinched from http://ragingbit.com/blog/list-dirs-c.html
 */
int filter(const struct dirent * dire){

  /* Discard . and .. */
  if( strncmp(dire->d_name, ".", 2) == 0
      || strncmp(dire->d_name, "..", 3) == 0 )
    return 0;

  /* Check whether it is a DIR or not.
   * Some FS doesn't handle d_type, so we check UNKNOWN as well */
  if( dire->d_type != DT_UNKNOWN
      && dire->d_type != DT_DIR )
    return 0;

  /* We've nothing against it. Accept */
  return 1;
}

/*
 * This function makes use of ideas from
 * http://ragingbit.com/blog/list-dirs-c.html
 */
Vector* find_dirs(const char* root) {
  int i;
  struct dirent** filelist = NULL;
  int ndirs = scandir(root, &filelist, filter, alphasort);
  char* dname;
  Vector* dirs = Vector_new(sizeof(char**), dispose_str);

  check(ndirs >= 0, "Failed to read data from %s", root);

  for (i = 0; i < ndirs; i++) {
    dname = strdup(filelist[i]->d_name);
    Vector_add(dirs, &dname);
  }

  /* clean up */
  if (filelist) {
    for (i = 0; i < ndirs; i++) {
      free(filelist[i]);
    }
    free(filelist);
  }

  return dirs;
error:
  exit(EXIT_FAILURE);
}

/* this reads a whole file into a vector */
Vector* read_file(const char* filename) {
  char line[MAX_LINE_LENGTH];
  char* str;
  FILE* fp;
  Vector* lines = Vector_new(sizeof(char**), dispose_str);

  fp = fopen(filename, "r");
  while (fgets(line, MAX_LINE_LENGTH, fp) != NULL) {
    strtok(line, "\n");      /* strip newline */
    str = strdup(line);      /* make a new pointer */
    Vector_add(lines, &str); /* pass it to the vector */
  }
  fclose(fp);

  return lines;
}

/* check that a filename is valid (1 = true, 0 = false) */
int valid_filename(const char* filename) {
  FILE* fp;
  int ret_val = 0;

  fp = fopen(filename, "r");
  if (fp) {
    ret_val = 1;
    fclose(fp);
  }

  return ret_val;
}

Vector* find_events(const char* root) {
  Vector* dirs = find_dirs(root);
  Vector* events = Vector_new(sizeof(Event*), dispose_event);
  Event* event;
  char* dirname;
  char* path;
  char* token;
  char* line;
  int i;

  for (i = 0; i < Vector_size(dirs); i++) {
    Vector_get(dirs, i, &dirname);
    /* +2 for slash & null */
    path = malloc(sizeof(char) * (strlen(root) + strlen(dirname) + 2) +
        sizeof(EVENT_FILENAME));
    check_mem(path);
    strcpy(path, root);
    strcat(path, dirname);
    strcat(path, "/");
    strcat(path, EVENT_FILENAME);

    if (valid_filename(path)) {
      Vector* lines = read_file(path);
      event = malloc(sizeof(Event));
      check_mem(event);

      Vector_get(lines, 0, &line);
      token = strtok(line, "\n");
      event->name = strdup(token);

      Vector_get(lines, 1, &line);
      token = strtok(line, "\n");
      event->date = strdup(token);

      Vector_get(lines, 2, &line);
      token = strtok(line, ":");
      event->start_hrs = atoi(token);
      token = strtok(NULL, "\n");
      event->start_mins = atoi(token);

      Vector_add(events, &event);
      Vector_dispose(lines);
    }

    free(path);
  }
  Vector_dispose(dirs);

  return events;
error:
  exit(EXIT_FAILURE);
}

int write_competitor(const char* path, const int id, const char course, const char* name) {
  FILE* fp;

  fp = fopen(path, "a");
  check(fp != NULL, "Could not open %s for writing", path);
  fprintf(fp, "%d %c %s\n", id, course, name);
  fclose(fp);

  return 0;
error:
  exit(EXIT_FAILURE);
}

int write_course(const char* path, const char id, const int num_nodes, Vector* nodes) {
  FILE* fp;
  int i;
  int node;

  fp = fopen(path, "a");
  check(fp != NULL, "Could not open %s for writing", path);
  fprintf(fp, "%c %d", id, num_nodes);
  for (i = 0; i < Vector_size(nodes); i++) {
    Vector_get(nodes, i, &node);
    fprintf(fp, " %d", node);
  }
  fprintf(fp, "\n");
  fclose(fp);

  return 0;
error:
  exit(EXIT_FAILURE);
}
