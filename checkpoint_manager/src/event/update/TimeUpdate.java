package event.update;

import event.Entrant;
import event.node.Node;
import util.Time;

public class TimeUpdate extends Update {

	public TimeUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public char getType() {
		return 'T';
	}

	@Override
	public void execute() {
		getEntrant().setStatus(Entrant.RUNNING);
		getEntrant().updateLocation(getNode());
		if (getNode() == getEntrant().getCourse().getLastNode())
			getEntrant().setStatus(Entrant.FINISHED);
	}

}
