package event;

public abstract class UpdateEvent {

  private Node node;
  private Entrant entrant;
	private Time time;
  
  public UpdateEvent(Node node, Entrant entrant, Time time) {
    setNode(node);
    setEntrant(entrant);
		setTime(time);
  }
  
  public Node getNode() {
    return node;
  }
  
  private void setNode(Node node) {
    this.node = node;
  }
  
  public Entrant getEntrant() {
    return entrant;
  }
  
  private void setEntrant(Entrant entrant) {
    this.entrant = entrant;
  }
  
  public Time getTime() {
    return time;
  }
  
  private void setTime(Time time) {
		this.time = time;
  }

	public abstract void execute();

}
