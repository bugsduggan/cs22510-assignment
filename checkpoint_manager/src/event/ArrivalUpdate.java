package event;

public class ArrivalUpdate extends UpdateEvent {

	public ArrivalUpdate(Node node, Entrant entrant, int hrs, int mins) {
		super(node, entrant, hrs, mins);
	}

	@Override
	public void execute() {
		// TODO
	}

}
