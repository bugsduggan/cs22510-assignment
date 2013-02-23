/*
 * util.h
 *
 * This contains a whole load of useful functions including some that became part
 * of standards after C89. Some of these have been poached from the web. Those that have,
 * have a comment stating where they were found.
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#ifndef UTIL_THL_H
#define UTIL_THL_H

/*
 * This function was taken from
 * http://cboard.cprogramming.com/c-programming/
 * 95462-compiler-error-warning-implicit-declaration-function-strdup.html
 * as it's not part of the c89 standard but it is really *really* useful
 */
char* strdup(const char* str);

#endif
