package gui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import event.Course;
import event.CourseList;
import event.Entrant;
import event.Event;
import event.Node;
import event.NodeList;
import event.Track;
import event.TrackList;

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
      String eventFile = args[0];
      String coursesFile = args[1];
      String tracksFile = args[2];
      String nodesFile = args[3];
      String entrantsFile = args[4];
      
      List<String> lines = FileIO.readLines(eventFile);
      String name = lines.get(0);
      Calendar c = Parser.parseDate(lines.get(1), lines.get(2));
      Event event = new Event(name, c);
      
      NodeList nodes = Parser.parseNodes(nodesFile);
      TrackList tracks = Parser.parseTracks(tracksFile, nodes);
      CourseList courses = Parser.parseCourses(coursesFile, nodes, tracks);
      List<Entrant> entrants = Parser.parseEntrants(entrantsFile, courses);
    }
  }
  
}
