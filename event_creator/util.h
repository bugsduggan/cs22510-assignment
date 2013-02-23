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

#define MAX_LINE_LENGTH 80

/*
 * Some constants relating to filenames
 */
static const char EVENT_FILENAME[] = "name.txt";
static const char COMPETITOR_FILENAME[] = "entrants.txt";
static const char COURSE_FILENAME[] = "courses.txt";

/*
 * 'Borrowed' functions
 */

/*
 * This function was taken from
 * http://stackoverflow.com/questions/314401/how-to-read-a-line-from-the-console-in-c
 * I could have used scanf, but I think just reading in a whole line each time and
 * then parsing it makes everything easier to read in the rest of the source
 */
char* readline();

/*
 * My functions
 */

/*
 * This is going to make sure there is a directory available for event data
 *
 * root_dir should end with a trailing slash
 *
 * passing NULL as the second arg will just create a directory specified by root
 *
 * e.g.
 * make_event_dir("./resources/", "The Big Race");
 * will create a directory at
 * ./resources/the_big_race/
 * and return the path to that dir including the trailing slash
 */
char* make_event_dir(const char* root_dir, const char* event_name);

Vector* read_file(const char* path);

int valid_filename(const char* filename);

Vector* find_events(const char* root);

int write_competitor(const char* path, const int id, const char course, const char* name);
int write_course(const char* path, const char id, const int num_nodes, Vector* nodes);

#endif
