package event;

public abstract class UpdateEvent {

  private Node node;
  private Entrant entrant;
  private int hrs;
  private int mins;
  
  public UpdateEvent(Node node, Entrant entrant, int hrs, int mins) {
    setNode(node);
    setEntrant(entrant);
    setHrs(hrs);
    setMins(mins);
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
  
  public int getHrs() {
    return hrs;
  }
  
  private void setHrs(int hrs) {
    this.hrs = hrs;
  }
  
  public int getMins() {
    return mins;
  }
  
  private void setMins(int mins) {
    this.mins = mins;
  }

	public abstract void execute();

}
