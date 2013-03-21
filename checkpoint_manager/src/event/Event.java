package event;

import event.node.Node;

import java.util.List;

public class Event {

	private List<Node> nodes;
	private List<Entrant> entrants;

	public Event(List<Node> nodes, List<Entrant> entrants) {
		this.nodes = nodes;
		this.entrants = entrants;
	}

	public Node getNode(int id) {
		for (Node n : nodes) {
			if (n.getId() == id) return n;
		}
		return null;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Entrant getEntrant(int id) {
		for (Entrant e : entrants) {
			if (e.getId() == id) return e;
		}
		return null;
	}

	public List<Entrant> getEntrants() {
		return entrants;
	}

}
