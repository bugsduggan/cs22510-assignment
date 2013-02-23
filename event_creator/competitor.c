/*
 * competitor.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdio.h>
#include <stdlib.h>

#include "data.h"
#include "dbg.h"
#include "util.h"

Competitor* Competitor_new(const int id, const char course_id, const char* name) {
  Competitor* competitor = malloc(sizeof(Competitor));
  check_mem(competitor);

  competitor->id = id;
  competitor->course_id = course_id;
  competitor->name = strdup(name);

  return competitor;
error:
  exit(EXIT_FAILURE);
}

void Competitor_destroy(Competitor* competitor) {
  if (competitor) {
    if (competitor->name) free(competitor->name);
    free(competitor);
  }
}

int Competitor_write(Competitor* competitor, const char* path, const char* filename) {
  char* full_path;
  FILE* fp;

  full_path = malloc(sizeof(char) * (strlen(path) + strlen(filename) + 1));
  check_mem(full_path);
  strcpy(full_path, path);
  strcat(full_path, filename);

  fp = fopen(full_path, "a");
  check(fp != NULL, "Could not open %s for writing", filename);
  fprintf(fp, "%i %c %s\n", competitor->id, competitor->course_id, competitor->name);
  fclose(fp);

  free(full_path);
  return 0;
error:
  exit(EXIT_FAILURE);
}
