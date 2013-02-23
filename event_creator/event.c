/*
 * event.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdio.h>
#include <stdlib.h>

#include "data.h"
#include "dbg.h"
#include "util.h"

Event* Event_new() {
  Event* event = malloc(sizeof(Event));
  check_mem(event);

  event->name = NULL;
  event->date = NULL;
  event->start_hrs = 0;
  event->start_mins = 0;

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

int Event_write(Event* event, const char* root) {
  char* file_path;
  FILE* fp;

  file_path = malloc(sizeof(char) * (strlen(root) + 1) +
      sizeof(EVENT_FILENAME));
  check_mem(file_path);
  strcpy(file_path, root);
  strcat(file_path, EVENT_FILENAME);

  fp = fopen(file_path, "w");
  check(fp != NULL, "Could not open %s for writing", file_path);
  fprintf(fp, "%s\n", event->name);
  fprintf(fp, "%s\n", event->date);
  fprintf(fp, "%02d:%02d\n", event->start_hrs, event->start_mins);
  fclose(fp);

  free(file_path);
  return 0;
error:
  exit(EXIT_FAILURE);
}
