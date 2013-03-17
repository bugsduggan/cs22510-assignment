package event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Event {

  private String name;
  private Calendar cal;
  private ArrayList<Entrant> entrants;
  
  public Event(String name, Calendar cal) {
    this.name = name;
    this.cal = cal;
    entrants = new ArrayList<Entrant>();
  }

  public void addEntrants(List<Entrant> entrants) {
    for (Entrant e : entrants) addEntrant(e);
  }

  private void addEntrant(Entrant e) {
    entrants.add(e);
  }
  
}
