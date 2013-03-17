package event;

import java.util.List;

public class Course {

  private char id;
  private List<Node> nodes;
  private List<Track> tracks;
  
  public Course(char id, List<Node> nodes, List<Track> tracks) {
    setId(id);
    setNodes(nodes);
    setTracks(tracks);
  }
  
  public char getId() {
    return id;
  }
  
  private void setId(char id) {
    this.id = id;
  }
  
  public List<Node> getNodes() {
    return nodes;
  }
  
  private void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }
  
  public List<Track> getTracks() {
    return tracks;
  }
  
  private void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }
  
}
