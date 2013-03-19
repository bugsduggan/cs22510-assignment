package event;

import event.node.Node;
import event.node.JunctionNode;

public class Entrant {

	public static final Status NOT_STARTED = Status.NOT_STARTED;
	public static final Status RUNNING = Status.RUNNING;
	public static final Status STOPPED = Status.STOPPED;
	public static final Status FINISHED = Status.FINISHED;
	public static final Status DISQUALIFIED = Status.DISQUALIFIED;

	private int id;
	private Course course;
	private String name;

	private Node currentNode;
	private Status status;

	public Entrant(int id, Course course, String name) {
		this.id = id;
		this.course = course;
		this.name = name;

		currentNode = null;
		status = NOT_STARTED;
	}

	public int getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 *	returns the next !junction node or null if one cannot be found
	 */
	public Node getNextCheckpoint() {
		int currI = course.getNodes().indexOf(currentNode);
		if (currI == -1) return null; // something went wrong, oh well
		for (int i = currI + 1; i < course.getNodes().size(); i++) {
			if (!(course.getNodes().get(i) instanceof JunctionNode))
				return course.getNodes().get(i);
		}
		return null;
	}

	public void updateLocation(Node node) {
		currentNode = node;
	}

	private enum Status {
		NOT_STARTED,
		RUNNING,
		STOPPED,
		FINISHED,
		DISQUALIFIED
	}

}
