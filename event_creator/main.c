/*
 * main.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdlib.h>

#include "dbg.h"
#include "util.h"

static const char DEFAULT_ROOT[] = "data";

/*
 * checks that root ends with a trailing slash and adds one if it doesn't
 */
char* check_root(char* root) {
  char* temp;

  if (root[strlen(root) - 1] != '/') {
    temp = malloc(sizeof(char) * strlen(root) + 2);
    check_mem(temp);
    strcpy(temp, root);
    strcat(temp, "/");
    return temp;
  }
  return root;

error:
  exit(EXIT_FAILURE);
}

int main(int argc, char* argv[]) {
  char* root;

  /* setup data directory */
  if (argc < 2) {
    root = strdup(DEFAULT_ROOT);
  } else {
    root = argv[1];
  }
  root = check_root(root);

  debug("Root path set to %s", root);
  root = make_event_dir(root, NULL);

  return EXIT_SUCCESS;
}
