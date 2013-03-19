package gui;

import event.Course;
import event.Entrant;
import event.Event;
import event.node.Node;
import event.update.Update;
import util.Parser;

import java.util.List;

public class Driver {

	public static void main(String[] args) {
		if (args.length < 6) {
			System.out.println("Usage:");
			System.out.println("java Driver <event_file> <node_file> <track_file> " +
					"<course_file> <entrant_file> <time_file>");
			System.exit(1);
		}

		String eventFile = args[0];
		String nodeFile = args[1];
		String trackFile = args[2];
		String courseFile = args[3];
		String entrantFile = args[4];
		String timeFile = args[5];

		// Now read everything in
		List<Node> nodes = Parser.parseNodes(nodeFile);
		List<Course> courses = Parser.parseCourses(courseFile, nodes);
		List<Entrant> entrants = Parser.parseEntrants(entrantFile, courses);
		Event event = new Event(nodes, entrants);

		// Process any times already in the file
		List<Update> updates = Parser.parseUpdates(timeFile, event);

		// Start the gui
	}

}
