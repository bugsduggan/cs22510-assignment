package gui;

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

		// Process any times already in the file

		// Start the gui
		CheckpointFrame top = new CheckpointFrame();
		top.setVisible(true);
	}

}
