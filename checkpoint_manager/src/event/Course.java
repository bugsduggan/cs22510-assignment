package event;

import event.node.Node;

import java.util.List;

public class Course {

	private char id;
	private List<Node> nodes;

	public Course(char id, List<Node> nodes) {
		this.id = id;
		this.nodes = nodes;
	}

	public char getId() {
		return id;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Node getLastNode() {
		return nodes.get(nodes.size() - 1);
	}

}
