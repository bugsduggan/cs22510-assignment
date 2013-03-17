package event;

import java.util.ArrayList;

public class TrackList {

  private ArrayList<Track> tracks;
  
  public TrackList() {
    tracks = new ArrayList<Track>();
  }
  
  public void add(Track t) {
    tracks.add(t);
  }

  public Track getTrackFromNodes(Node start, Node end) {
    for (Track t : tracks) {
      if (t.getStartNode() == start && t.getEndNode() == end) return t;
    }
    return null;
  }
  
}
