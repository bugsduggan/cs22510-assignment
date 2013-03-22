package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public class ExcludedUpdate extends Update {

	public ExcludedUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public char getType() {
		return 'E';
	}

	@Override
	public void execute() {
		getEntrant().setStatus(Entrant.DISQUALIFIED);
	}

}
