/*
 * data.h
 *
 * Contains structures and methods wrt Events, Competitors and Courses
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#ifndef DATA_THL_H
#define DATA_THL_H

#include "vector.h"

typedef struct Event {
  char* name;
  char* date;
  int start_hrs;
  int start_mins;
} Event;

Event* Event_new();
void Event_destroy(Event* event);
int Event_write(Event* event, const char* root);

#endif
