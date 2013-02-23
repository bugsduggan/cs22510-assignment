/*
 * util.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>

/*
 * This function was taken from
 * http://cboard.cprogramming.com/c-programming/
 * 95462-compiler-error-warning-implicit-declaration-function-strdup.html
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

char* make_event_dir(const char* root_dir, const char* event_name) {
  char* tmp_root;
  char* tmp_dir;
  int i;

  /*
   * first, we're going to sanitize the event name by turning all the spaces into
   * underscores and lower casing the whole thing
   */
  tmp_dir = strdup(event_name);
  for (i = 0; tmp_dir[i]; i++) {
    if (tmp_dir[i] == ' ') tmp_dir[i] = '_';
    else tmp_dir[i] = tolower(tmp_dir[i]);
  }

  /* grab enough memory to contain the whole path */
  /* that is, length(root) + length(dir) + '/' + null */
  tmp_root = malloc(sizeof(char) * (strlen(root_dir) + strlen(tmp_dir) + 2));
  check_mem(tmp_root);
  strcpy(tmp_root, root_dir);
  strcat(tmp_root, tmp_dir);
  strcat(tmp_root, "/");

  /*
   * then we're going to either create the directory or establish that it already
   * exists
   */
  if (mkdir(tmp_root, S_IRWXU|S_IRGRP|S_IXGRP) != 0) {
    check(errno == EEXIST, "Could not create directory %s", tmp_root);
    debug("%s already exists", tmp_root);
  } else {
    debug("%s created", tmp_root);
  }

  free(tmp_dir);
  return tmp_root;
error:
  exit(EXIT_FAILURE);
}
