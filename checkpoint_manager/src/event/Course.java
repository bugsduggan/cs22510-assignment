package event;

import java.util.List;

public class Course {

  private char id;
  private int numNodes;
  private List<Track> tracks;
  
  public Course(char id, int numNodes, List<Track> tracks) {
    this.id = id;
    this.numNodes = numNodes;
    this.tracks = tracks;
  }

  public char getId() {
    return id;
  }

}
