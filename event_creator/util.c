/*
 * util.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdlib.h>
#include <string.h>

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
