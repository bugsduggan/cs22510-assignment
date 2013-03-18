package event;

public class InvalidUpdate extends UpdateEvent {

	public InvalidUpdate(Node node, Entrant entrant, int hrs, int mins) {
		super(node, entrant, hrs, mins);
	}

	@Override
	public void execute() {
		// TODO
	}

}
