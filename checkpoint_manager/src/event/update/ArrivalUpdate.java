package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

public class ArrivalUpdate extends Update {

	public ArrivalUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		getEntrant().updateLocation(getNode());
		getEntrant().setStatus(Entrant.STOPPED);
	}

}
