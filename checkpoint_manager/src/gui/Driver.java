package gui;

import event.Course;
import event.Entrant;
import event.Event;
import event.node.Node;
import event.update.Update;
import util.Parser;

import java.util.List;

import javax.swing.JFrame;

public class Driver {
  
	public static void main(String[] args) {
		if (args.length < 7) {
			System.out.println("Usage:");
			System.out.println("java Driver <event_file> <node_file> <track_file> " +
					"<course_file> <entrant_file> <time_file> <log_file>");
			System.exit(1);
		}

		String eventFile = args[0];
		String nodeFile = args[1];
		String trackFile = args[2];
		String courseFile = args[3];
		String entrantFile = args[4];
		String timeFile = args[5];
		String logFile = args[6];

		// Now read everything in
		List<Node> nodes = Parser.parseNodes(nodeFile);
		List<Course> courses = Parser.parseCourses(courseFile, nodes);
		List<Entrant> entrants = Parser.parseEntrants(entrantFile, courses);
		Event event = new Event(nodes, entrants);

		// Process any times already in the file
		List<Update> updates = Parser.parseUpdates(timeFile, event);
		for (Update u : updates) {
			u.execute();
		}

		JFrame top = new JFrame("Checkpoint Manager");
    top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this breaks stuff somehow?
    //Dimension dim = new Dimension(800, 600);
    //top.setSize(dim);
    //top.setPreferredSize(dim);
    //top.setMinimumSize(dim);
    //top.setMaximumSize(dim);

		CheckpointPanel panel = new CheckpointPanel(event);
		top.setContentPane(panel);
		panel.setVisible(true);
    
		top.pack();
		top.setVisible(true);
	}

}
