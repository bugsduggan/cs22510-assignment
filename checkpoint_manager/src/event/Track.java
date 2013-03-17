package event;

public class Track {

  private int id;
  private Node start;
  private Node end;
  private int safeTime;
  
  public Track(int id, Node start, Node end, int safeTime) {
    setId(id);
    setStartNode(start);
    setEndNode(end);
    setSafeTime(safeTime);
  }
  
  public int getId() {
    return id;
  }
  
  private void setId(int id) {
    this.id = id;
  }
  
  public Node getStartNode() {
    return start;
  }
  
  private void setStartNode(Node start) {
    this.start = start;
  }
  
  public Node getEndNode() {
    return end;
  }
  
  private void setEndNode(Node end) {
    this.end = end;
  }
  
  public int getSafeTime() {
    return safeTime;
  }
  
  private void setSafeTime(int safeTime) {
    this.safeTime = safeTime;
  }
  
}
