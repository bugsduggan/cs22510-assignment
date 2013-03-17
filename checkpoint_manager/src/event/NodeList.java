package event;

import java.util.ArrayList;

public class NodeList {

  private ArrayList<Node> nodes;
  
  public NodeList() {
    nodes = new ArrayList<Node>();
  }
  
  public void add(Node n) {
    nodes.add(n);
  }
  
  public Node getNodeById(int id) {
    for (Node n : nodes) {
      if (n.getId() == id) return n;
    }
    return null;
  }
  
}
