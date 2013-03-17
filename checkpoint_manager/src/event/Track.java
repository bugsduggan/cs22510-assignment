package event;

public class Track {

  private int id;
  private Node start;
  private Node end;
  private int safeTime;
  
  public Track(int id, Node start, Node end, int safeTime) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.safeTime = safeTime;
  }
  
}
