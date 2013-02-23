/*
 * event.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdlib.h>
#include <stdio.h>

#include "dbg.h"
#include "data.h"
#include "util.h"

Event* Event_new(const char* name, const char* date, const int hrs, const int mins) {
  Event* event = malloc(sizeof(Event));
  check_mem(event);

  event->name = strdup(name);
  event->date = strdup(date);
  event->start_hrs = hrs;
  event->start_mins = mins;

  return event;
error:
  exit(EXIT_FAILURE);
}

void Event_destroy(Event* event) {
  if (event) {
    if (event->name) free(event->name);
    if (event->date) free(event->date);
    free(event);
  }
}

int Event_write(Event* event, const char* filename) {
  FILE* fp;

  fp = fopen(filename, "w");
  check(fp != NULL, "Could not open %s for writing", filename);
  fprintf(fp, "%s\n", event->name);
  fprintf(fp, "%s\n", event->date);
  fprintf(fp, "%i:%i\n", event->start_hrs, event->start_mins);
  fclose(fp);

  return 0;
error:
  exit(EXIT_FAILURE);
}
