package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public abstract class Update {

	private Node node;
	private Entrant entrant;
	private Time time;

	public Update(Node node, Entrant entrant, Time time) {
		this.node = node;
		this.entrant = entrant;
		this.time = time;
	}

	public abstract char getType();

	public Node getNode() {
		return node;
	}

	public Entrant getEntrant() {
		return entrant;
	}

	public Time getTime() {
		return time;
	}

	public abstract void execute();

}
