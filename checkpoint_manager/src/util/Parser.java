package util;

import event.node.CheckpointNode;
import event.node.JunctionNode;
import event.node.MedicalCheckpointNode;
import event.node.Node;

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

}
