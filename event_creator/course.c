/*
 * course.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdio.h>
#include <stdlib.h>

#include "data.h"
#include "dbg.h"
#include "util.h"

Course* Course_new(const char id) {
  Course* course = malloc(sizeof(Course));
  check_mem(course);

  course->id = id;
  course->num_nodes = 0;
  course->nodes = Vector_new(sizeof(int), NULL);

  return course;
error:
  exit(EXIT_FAILURE);
}

void Course_destroy(Course* course) {
  if (course) {
    if (course->nodes) Vector_destroy(course->nodes);
    free(course);
  }
}

void Course_add_node(Course* course, int node_id) {
  Vector_add(course->nodes, &node_id);
}

int Course_write(Course* course, const char* path, const char* filename) {
  char* full_path;
  FILE* fp;
  int node;
  int i;

  full_path = malloc(sizeof(char) * (strlen(path) + strlen(filename) + 1));
  check_mem(full_path);
  strcpy(full_path, path);
  strcat(full_path, filename);

  fp = fopen(full_path, "a");
  check(fp != NULL, "Could not open %s for writing", filename);
  fprintf(fp, "%c %i", course->id, course->num_nodes);
  for (i = 0; i < course->num_nodes; i++) {
    Vector_get(course->nodes, i, &node);
    fprintf(fp, " %i", node);
  }
  fprintf(fp, "\n");
  fclose(fp);

  free(full_path);
  return 0;
error:
  exit(EXIT_FAILURE);
}
