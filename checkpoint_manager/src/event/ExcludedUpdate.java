package event;

public class ExcludedUpdate extends UpdateEvent {

	public ExcludedUpdate(Node node, Entrant entrant, int hrs, int mins) {
		super(node, entrant, hrs, mins);
	}

	@Override
	public void execute() {
		// TODO
	}

}
