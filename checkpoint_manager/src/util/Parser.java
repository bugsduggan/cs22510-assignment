package util;

import event.Entrant;
import event.Event;
import event.node.CheckpointNode;
import event.node.JunctionNode;
import event.node.MedicalCheckpointNode;
import event.node.Node;
import event.update.ArrivalUpdate;
import event.update.TimeUpdate;
import event.update.Update;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	public static List<Node> parseNodes(String filename) {
		List<Node> nodes = new ArrayList<Node>();
		List<String> lines = new ArrayList<String>();

		try {
			lines = FileIO.readLines(filename);
		} catch (FileNotFoundException e) {
			System.err.println(filename + " not found");
			System.exit(1);
		}

		for (String line : lines) {
			String[] tokens = line.split(" ");
			int id = Integer.parseInt(tokens[0]);

			switch (tokens[1]) {
				case "JN":
					nodes.add(new JunctionNode(id));
					break;
				case "CP":
					nodes.add(new CheckpointNode(id));
					break;
				case "MC":
					nodes.add(new MedicalCheckpointNode(id));
					break;
				default:
					System.err.println("Failed to parse node type " + tokens[1]);
					System.exit(1);
					break;
			}
		}

		return nodes;
	}

	public static List<Update> parseUpdates(String filename, Event event) {
		List<Update> updates = new ArrayList<Update>();
		List<String> lines = new ArrayList<String>();

		try {
			lines = FileIO.readLines(filename);
		} catch (FileNotFoundException e) {
			System.err.println(filename + " not found");
			System.exit(1);
		}

		for (String line : lines) {
			String[] tokens = line.split(" ");
			
			char type = tokens[0].charAt(0); // should be 1 char
			Node node = event.getNode(Integer.parseInt(tokens[1]));
			Entrant entrant = event.getEntrant(Integer.parseInt(tokens[2]));
			Time time = new Time(Integer.parseInt(tokens[3].split(":")[0]),
						Integer.parseInt(tokens[3].split(":")[1]));

			switch (type) {
				case 'T':
					updates.add(new TimeUpdate(node, entrant, time));
					break;
				case 'A':
					updates.add(new ArrivalUpdate(node, entrant, time));
					break;
				case 'D':
					break;
				case 'I':
					break;
				case 'E':
					break;
				default:
					System.err.println("Failed to parse update type " + type);
					System.exit(1);
					break;
			}
		}

		return updates;
	}

}
