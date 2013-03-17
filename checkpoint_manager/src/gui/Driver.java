package gui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import event.Event;

import util.FileIO;
import util.Parser;

public class Driver {

  public static void main(String[] args) {
    if (args.length < 5) {
      System.out.println(
          "USAGE: java Driver <event_file> <courses_file> " +
          "<tracks_file> <nodes_file> <entrants_file>");
      System.exit(1);
    } else {
      List<String> lines = FileIO.readLines(args[0]);
      String name = lines.get(0);
      Calendar c = Parser.parseDate(lines.get(1), lines.get(2));
      Event event = new Event(name, c);
    }
  }
  
}
