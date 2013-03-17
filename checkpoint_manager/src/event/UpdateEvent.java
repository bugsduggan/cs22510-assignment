package event;

public class UpdateEvent {

  private char type;
  private Node node;
  private Entrant entrant;
  private int hrs;
  private int mins;
  
  public UpdateEvent(char type, Node node, Entrant entrant, int hrs, int mins) {
    setType(type);
    setNode(node);
    setEntrant(entrant);
    setHrs(hrs);
    setMins(mins);
  }
  
  public char getType() {
    return type;
  }
  
  private void setType(char type) {
    this.type = type;
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

}
