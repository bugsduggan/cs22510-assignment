package event;

public class TimeUpdate extends UpdateEvent {

	public TimeUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		// TODO
	}

}
