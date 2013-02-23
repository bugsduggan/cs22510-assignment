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
 * 'Borrowed' functions
 */

/*
 * This function was taken from
 * http://cboard.cprogramming.com/c-programming/
 * 95462-compiler-error-warning-implicit-declaration-function-strdup.html
 * as it's not part of the c89 standard but it is really *really* useful
 */
char* strdup(const char* str);

/*
 * My functions
 */

/*
 * This is going to make sure there is a directory available for event data
 *
 * root_dir should end with a trailing slash
 *
 * e.g.
 * make_event_dir("./resources/", "The Big Race");
 * will create a directory at
 * ./resources/the_big_race/
 * and return the path to that dir including the trailing slash
 */
char* make_event_dir(const char* root_dir, const char* event_name);

#endif
