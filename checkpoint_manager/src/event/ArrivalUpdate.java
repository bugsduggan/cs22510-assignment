package event;

public class ArrivalUpdate extends UpdateEvent {

	public ArrivalUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		// TODO
	}

}
