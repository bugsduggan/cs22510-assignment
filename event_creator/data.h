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

typedef struct Competitor {
  int id;
  char course_id;
  char* name;
} Competitor;

Competitor* Competitor_new(const int id, const char course_id, const char* name);
void Competitor_destroy(Competitor* competitor);
int Competitor_write(Competitor* competitor, const char* path, const char* filename);

typedef struct Course {
  char id;
  int num_nodes;
  Vector* nodes;
} Course;

Course* Course_new(const char id);
void Course_destroy(Course* course);
void Course_add_node(Course* course, int node_id);
int Course_write(Course* course, const char* path, const char* filename);

#endif
