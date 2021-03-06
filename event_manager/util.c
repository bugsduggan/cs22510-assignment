/*
 * util
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "vector.h"
#include "util.h"

/*
 * private functions
 */

/* this gets passed into the vectors that are used to read file data
 * it means we can call Vector_dispose once we've read the data in */
void string_dispose(void* string) {
  /* this is a bit of a funky cast, we're casting it to a char** (which it is) */
  /* and then dereferencing that to produce our char* foo */
  char* foo = *(char**) string;
  if (foo) free(foo);
}

/*
 * functions declared in util.h
 */

/*
 * This function was taken from
 * http://cboard.cprogramming.com/c-programming/95462-compiler-error-warning-implicit-declaration-function-strdup.html
 * as it's not part of the c89 standard but it is really *really* useful
 */
char* strdup(const char* str) {
  int n = strlen(str) + 1;
  char* dup = malloc(n);
  if (dup) {
    strcpy(dup, str);
  }
  return dup;
}

/*
 * The following function was taken from
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

/* check that a filename is valid (1 = true, 0 = false) */
int valid_filename(char* filename) {
  FILE* fp;
  int ret_val = 0;

  fp = fopen(filename, "r");
  if (fp) {
    ret_val = 1;
    fclose(fp);
  }

  return ret_val;
}

/* this reads a whole file into a vector */
Vector* read_file(char* filename) {
  char line[MAX_LINE_LENGTH];
  char* str;
  FILE* fp;
  Vector* lines = Vector_new(sizeof(char**), string_dispose);

  fp = fopen(filename, "r");
	if (fp == NULL) {
		/* the file probably doesn't exist which could be the case for */
		/* the log and times files, let's create them with a call to fopen */
		fp = fopen(filename, "a+");
		if (fp == NULL) {
			/* bad times */
			printf("Error opening %s\n", filename);
			exit(1);
		}
		fclose(fp);
		fp = fopen(filename, "r");
	}
  while (fgets(line, MAX_LINE_LENGTH, fp) != NULL) {
    strtok(line, "\n");      /* strip newline */
    str = strdup(line);      /* make a new pointer */
    Vector_add(lines, &str); /* pass it to the vector */
  }
  fclose(fp);

  return lines;
}

/* turn a string (hh:mm) into a struct Time */
Time* str_to_time(char* str) {
  Time* time = malloc(sizeof(Time));
  char* token;

  token = strtok(str, ":");
  time->hours = atoi(token);
  token = strtok(NULL, "\n");
  time->minutes = atoi(token);

  return time;
}

/* return a new copy of a struct Time */
Time* timecpy(Time* time) {
  Time* copy = malloc(sizeof(Time));
  copy->hours = time->hours;
  copy->minutes = time->minutes;
  return copy;
}

/* return a duration from a time */
int time_to_duration(Time* time) {
  return time->minutes + (time->hours * 60);
}

/* copied this little fella from Neal's example */
struct flock* file_lock(short type, short whence) {
	static struct flock ret;
	ret.l_type = type;
	ret.l_start = 0;
	ret.l_whence = whence;
	ret.l_len = 0;
	ret.l_pid = getpid();
	return &ret;
}

/* also based on Neal's code */
void append_to_file(char* filename, char* line) {
	struct flock* fl;
	FILE* fp;
	int fd = open(filename, O_RDWR);

	if (fd == -1) {
		/* I'm going to assume that I can't get a descriptor because the file doesn't */
		/* exist yet, so let's create it with a call to fopen */
		fp = fopen(filename, "a+");
		fclose(fp);
		/* and then try again */
		fd = open(filename, O_RDWR);
		if (fd == -1) {
			/* bad times! */
			printf("Error getting file descriptor\n");
			exit(1);
		}
	}

	fl = file_lock(F_WRLCK, SEEK_SET);

	while (fcntl(fd, F_SETLK, fl) == -1) {
		if (errno != EACCES && errno != EAGAIN) {
			/* bad times! */
			printf("can't get lock\n");
			exit(1);
		}
	}

	/* so now we have a lock */

	fp = fopen(filename, "a+");
	fprintf(fp, "%s\n", line);

	fcntl(fd, F_SETLKW, file_lock(F_UNLCK, SEEK_SET));
	fclose(fp);
}
