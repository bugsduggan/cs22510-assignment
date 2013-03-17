package gui;

import util.Parser;
import event.Event;

public class Driver {

  public static void main(String[] args) {
    if (args.length < 6) {
      System.out.println("Usage:");
      System.out.println("    java Driver " +
          "<event_file> <nodes_file> <tracks_file> " +
          "<courses_file> <entrants_file> <times_file>");
      System.exit(1);
    }
    
    String eventFile = args[0];
    String nodesFile = args[1];
    String tracksFile = args[2];
    String coursesFile = args[3];
    String entrantsFile = args[4];
    String timesFile = args[5];
    
    Event event = Parser.parseEvent(eventFile);
    event.addNodes(Parser.parseNodes(nodesFile));
    event.addTracks(Parser.parseTracks(tracksFile, event));
  }
  
}
