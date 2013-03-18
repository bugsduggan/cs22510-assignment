package event;

public class InvalidUpdate extends UpdateEvent {

	public InvalidUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		// TODO
	}

}
