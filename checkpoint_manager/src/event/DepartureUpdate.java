package event;

public class DepartureUpdate extends UpdateEvent {

	public DepartureUpdate(Node node, Entrant entrant, int hrs, int mins) {
		super(node, entrant, hrs, mins);
	}

	@Override
	public void execute() {
		// TODO
	}

}
