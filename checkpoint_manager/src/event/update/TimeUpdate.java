package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

public class TimeUpdate extends Update {

	public TimeUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		getEntrant().updateLocation(getNode());
	}

}
