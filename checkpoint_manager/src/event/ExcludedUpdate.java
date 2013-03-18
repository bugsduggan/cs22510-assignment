package event;

public class ExcludedUpdate extends UpdateEvent {

	public ExcludedUpdate(Node node, Entrant entrant, Time time) {
		super(node, entrant, time);
	}

	@Override
	public void execute() {
		// TODO
	}

}
