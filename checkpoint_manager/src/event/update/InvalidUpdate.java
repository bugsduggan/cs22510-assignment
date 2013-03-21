package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

public class InvalidUpdate extends Update {

	public InvalidUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public char getType() {
		return 'I';
	}

	@Override
	public void execute() {
		getEntrant().updateLocation(getNode());
		getEntrant().setStatus(Entrant.DISQUALIFIED);
	}

}
