package event;

import java.util.Calendar;

public class Event {

  private String name;
  private Calendar date;
  
  public Event(String name, Calendar date) {
    setName(name);
    setDate(date);
  }
  
  public String getName() {
    return name;
  }
  
  private void setName(String name) {
    this.name = name;
  }
  
  private void setDate(Calendar date) {
    this.date = date;
  }
  
}