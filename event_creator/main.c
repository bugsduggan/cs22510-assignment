/*
 * main.c
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <stdlib.h>

#include "data.h"
#include "dbg.h"
#include "util.h"
#include "vector.h"

static const char DEFAULT_ROOT[] = "data";

/*
 * checks that root ends with a trailing slash and adds one if it doesn't
 */
char* check_root(char* root) {
  char* temp;

  if (root[strlen(root) - 1] != '/') {
    temp = malloc(sizeof(char) * (strlen(root) + 2));
    check_mem(temp);
    strcpy(temp, root);
    strcat(temp, "/");
    free(root);
    return temp;
  }
  return root;

error:
  exit(EXIT_FAILURE);
}

char* prompt() {
  char* result;

  printf(">> ");
  result = readline();

  return result;
}

int prompt_int() {
  char* line;
  char* token;
  int result;

  line = prompt();
  token = strtok(line, "\n");
  result = atoi(token);
  free(line);

  return result;
}

Event* create_event(const char* root) {
  char* line;
  char* token;
  char* path;
  Event* event = Event_new();

  printf("Please enter event name\n");
  line = prompt();
  event->name = strdup(line);
  free(line);

  printf("Please enter event date\n");
  line = prompt();
  event->date = strdup(line);
  free(line);

  printf("Please enter event time\n");
  line = prompt();
  token = strtok(line, ":");
  event->start_hrs = atoi(token);
  token = strtok(NULL, "\n");
  event->start_mins = atoi(token);
  free(line);

  path = make_event_dir(root, event->name);
  Event_write(event, path);

  return event;
}

Event* load_event(Event* current, Vector* events) {
  Event* result = current;
  Event* event;
  int input;
  int i;

  /* show options */
  printf("\nEvents:\n");
  for (i = 0; i < Vector_size(events); i++) {
    Vector_get(events, i, &event);
    printf("\t%d - %s\n", (i+1), event->name);
  }
  printf("\n");

  /* process selection */
  input = prompt_int();
  if (input < 0 || input > Vector_size(events)) {
    printf("\nSelection out of range\n");
  } else {
    Vector_get(events, (input-1), &result);
  }

  return result;
}

void add_competitor(Event* current, const char* root) {
  char* path = make_event_dir(root, current->name);
  char* full_path;
  Vector* lines;
  char* line;
  char* token;
  char* name;
  char* course_id;
  int next_id;

  full_path = malloc(sizeof(char) * (strlen(path) + 1) +
      sizeof(COMPETITOR_FILENAME));
  check_mem(full_path);
  strcpy(full_path, path);
  strcat(full_path, COMPETITOR_FILENAME);

  if (valid_filename(full_path)) {
    /* file exists, we need to find the next comp_id */
    lines = read_file(full_path);
    Vector_get(lines, Vector_size(lines) - 1, &line);
    token = strtok(line, " ");
    next_id = atoi(token) + 1;
    Vector_dispose(lines);
  } else {
    next_id = 1;
  }

  /* then grab course and name from user */
  printf("Please enter competitor name\n");
  name = readline();
  printf("Please enter course id\n");
  course_id = readline();

  write_competitor(full_path, next_id, course_id[0], name);

  return;
error:
  exit(EXIT_FAILURE);
}

void add_course(Event* current, const char* root) {
  char* path = make_event_dir(root, current->name);
  char* full_path;
  char* course_id;
  char* line;
  char* token;
  Vector* nodes = Vector_new(sizeof(int), NULL);
  int node_id;
  int num_nodes;
  int i;

  full_path = malloc(sizeof(char) * (strlen(path) + 1) +
      sizeof(COURSE_FILENAME));
  check_mem(full_path);
  strcpy(full_path, path);
  strcat(full_path, COURSE_FILENAME);

  printf("Please enter course id\n");
  course_id = readline();
  printf("Please enter the number of nodes in the course\n");
  line = readline();
  token = strtok(line, "\n");
  num_nodes = atoi(token);
  free(line);
  /* now we read in the node ids */
  for (i = 0; i < num_nodes; i++) {
    printf("Please enter node id for node #%d\n", i+1);
    line = readline();
    token = strtok(line, "\n");
    node_id = atoi(token);
    Vector_add(nodes, &node_id);
    free(line);
  }

  write_course(full_path, course_id[0], num_nodes, nodes);

  return;
error:
  exit(EXIT_FAILURE);
}

void print_menu(Event* current) {
  printf("\n");
  printf("Main menu");
  if (current) printf(" - %s", current->name);
  printf("\n\n");
  printf("\t1. Create event\n");
  printf("\t2. Load event\n");
  printf("\t3. Add competitor\n");
  printf("\t4. Add course\n");
  printf("\t5. Quit\n");
  printf("\n");
}

int main(int argc, char* argv[]) {
  char* root;
  Event* event;
  Event* current = NULL;
  Vector* events;
  int running = 1;
  int input;
  int i;

  /* setup data directory */
  if (argc < 2) {
    root = strdup(DEFAULT_ROOT);
  } else {
    root = argv[1];
  }
  root = check_root(root);

  debug("Root path set to %s", root);
  root = make_event_dir(root, NULL);
  events = find_events(root);
  debug("Found %d events:", Vector_size(events));
  for (i = 0; i < Vector_size(events); i++) {
    Vector_get(events, i, &event);
    debug("\t%s", event->name);
  }

  /* main loop */
  while (running) {
    print_menu(current);
    input = prompt_int();
    switch(input) {
      case 1:
        /* create event */
        event = create_event(root);
        current = event;
        Vector_add(events, &event);
        break;
      case 2:
        /* load event */
        if (Vector_size(events) < 1) {
          printf("No events in %s!\n", root);
        } else {
          current = load_event(current, events);
        }
        break;
      case 3:
        /* add competitor */
        if (current) {
          add_competitor(current, root);
        } else {
          printf("No event selected!\n");
        }
        break;
      case 4:
        /* add course */
        if (current) {
          add_course(current, root);
        } else {
          printf("No event selected!\n");
        }
        break;
      case 5:
        /* quit */
        running = 0;
        break;
      default:
        /* invalid choice, do nothing */
        break;
    }
  }

  /* clean up */
  debug("Cleaning resources");
  if (root) free(root);
  Vector_dispose(events);

  return EXIT_SUCCESS;
}
