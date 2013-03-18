package event;

public class TimeUpdate extends UpdateEvent {

	public TimeUpdate(Node node, Entrant entrant, int hrs, int mins) {
		super(node, entrant, hrs, mins);
	}

	@Override
	public void execute() {
		// TODO
	}

}
