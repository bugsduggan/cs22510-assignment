package event.node;

public abstract class Node {

	private int id;

	public Node(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}