package event;

import event.node.Node;
import event.node.JunctionNode;

public class Entrant {

	private int id;
	private Course course;
	private String name;

	private Node currentNode;

	public Entrant(int id, Course course, String name) {
		this.id = id;
		this.course = course;
		this.name = name;

		currentNode = null;
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

}
