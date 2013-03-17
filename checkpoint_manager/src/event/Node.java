package event;

public abstract class Node {

  private int id;
  
  public Node(int id) {
    setId(id);
  }
  
  public int getId() {
    return id;
  }
  
  private void setId(int id) {
    this.id = id;
  }
  
}
