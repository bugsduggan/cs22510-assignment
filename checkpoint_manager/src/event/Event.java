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

  public void update(char type, Node node, int entrantId, int tHours, int tMins) {
    // type may be:
    // T - normal time check
    // A - arrival at medical checkpoint
    // D - departure from medical checkpoint
    // I - invalid route
    // E - excluded for safety
  }

  private void addEntrant(Entrant e) {
    entrants.add(e);
  }
  
}
