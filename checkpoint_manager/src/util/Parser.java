package util;

import java.util.Calendar;

public class Parser {

  public static Calendar parseDate(String lineOne, String lineTwo) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.DST_OFFSET, 0);
    
    String[] foo = lineOne.split(" ");
    int date = Integer.parseInt(foo[0].split("[tr]")[0]);
    c.set(Calendar.DATE, date);
    
    switch (foo[1]) {
    case "January":
      c.set(Calendar.MONTH, Calendar.JANUARY);
      break;
    case "Febuary":
      c.set(Calendar.MONTH, Calendar.FEBRUARY);
      break;
    case "March":
      c.set(Calendar.MONTH, Calendar.MARCH);
      break;
    case "April":
      c.set(Calendar.MONTH, Calendar.APRIL);
      break;
    case "May":
      c.set(Calendar.MONTH, Calendar.MAY);
      break;
    case "June":
      c.set(Calendar.MONTH, Calendar.JUNE);
      break;
    case "July":
      c.set(Calendar.MONTH, Calendar.JULY);
      break;
    case "August":
      c.set(Calendar.MONTH, Calendar.AUGUST);
      break;
    case "September":
      c.set(Calendar.MONTH, Calendar.SEPTEMBER);
      break;
    case "October":
      c.set(Calendar.MONTH, Calendar.OCTOBER);
      break;
    case "November":
      c.set(Calendar.MONTH, Calendar.NOVEMBER);
      break;
    case "December":
      c.set(Calendar.MONTH, Calendar.DECEMBER);
      break;
    default:
      System.err.println("Failed to parse month " + foo[1]);
      System.exit(1);
      break;
    }
    
    c.set(Calendar.YEAR, Integer.parseInt(foo[2]));
    
    c.set(Calendar.HOUR, Integer.parseInt(lineTwo.split(":")[0]));
    c.set(Calendar.MINUTE, Integer.parseInt(lineTwo.split(":")[1]));
    c.set(Calendar.SECOND, 0);
    
    return c;
  }
  
}
