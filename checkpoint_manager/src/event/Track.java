package event;

public class Track {

  private char id;
  
  public Track(char id) {
    setId(id);
  }
  
  public char getId() {
    return id;
  }
  
  private void setId(char id) {
    this.id = id;
  }
  
}
