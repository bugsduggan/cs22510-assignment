package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

public class DepartureUpdate extends Update {

	public DepartureUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		
	}

}
