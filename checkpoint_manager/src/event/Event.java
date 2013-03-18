package event;

import event.node.Node;

import java.util.List;

public class Event {

	private List<Node> nodes;

	public Event(List<Node> nodes) {
		this.nodes = nodes;
	}

	public Node getNode(int id) {
		for (Node n : nodes) {
			if (n.getId() == id) return n;
		}
		return null;
	}

}
