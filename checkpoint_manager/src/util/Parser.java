package util;

import java.util.Calendar;
import java.util.List;

import event.Event;

public class Parser {

  public static Event parseEvent(String eventFile) {
    List<String> lines = FileIO.readLines(eventFile);
    
    String name = lines.get(0);
    
    Calendar date = Calendar.getInstance();
    date.set(Calendar.DST_OFFSET, 0);
    String[] tokens = lines.get(1).split(" ");
    
    int day = Integer.parseInt(tokens[0].split("[snr]")[0]);
    date.set(Calendar.DATE, day);
    
    switch (tokens[1]) {
      case "January":
        date.set(Calendar.MONTH, Calendar.JANUARY);
        break;
      case "February":
        date.set(Calendar.MONTH, Calendar.FEBRUARY);
        break;
      case "March":
        date.set(Calendar.MONTH, Calendar.MARCH);
        break;
      case "April":
        date.set(Calendar.MONTH, Calendar.APRIL);
        break;
      case "May":
        date.set(Calendar.MONTH, Calendar.MAY);
        break;
      case "June":
        date.set(Calendar.MONTH, Calendar.JUNE);
        break;
      case "July":
        date.set(Calendar.MONTH, Calendar.JULY);
        break;
      case "August":
        date.set(Calendar.MONTH, Calendar.AUGUST);
        break;
      case "September":
        date.set(Calendar.MONTH, Calendar.SEPTEMBER);
        break;
      case "October":
        date.set(Calendar.MONTH, Calendar.OCTOBER);
        break;
      case "November":
        date.set(Calendar.MONTH, Calendar.NOVEMBER);
        break;
      case "December":
        date.set(Calendar.MONTH, Calendar.DECEMBER);
        break;
      default:
        System.err.println("Error parsing month " + tokens[1]);
        System.exit(1);
        break;
    }
    
    int year = Integer.parseInt(tokens[2]);
    date.set(Calendar.YEAR, year);
    
    tokens = lines.get(2).split(":");
    int hours = Integer.parseInt(tokens[0]);
    date.set(Calendar.HOUR, hours);
    int mins = Integer.parseInt(tokens[1]);
    date.set(Calendar.MINUTE, mins);
    date.set(Calendar.SECOND, 0);
    
    return new Event(name, date);
  }

}
