package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public class DepartureUpdate extends Update {

	public DepartureUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public char getType() {
		return 'D';
	}

	@Override
	public void execute() {
		getEntrant().setStatus(Entrant.RUNNING);
	}

}
