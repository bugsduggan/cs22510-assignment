package event;

public class DepartureUpdate extends UpdateEvent {

	public DepartureUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		// TODO
	}

}
