package event.node;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public abstract class Node {

	private int id;

	public Node(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
