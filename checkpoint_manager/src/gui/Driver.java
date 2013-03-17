package gui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    boolean initTimes = false;
    
    if (args.length < 5) {
      System.out.println(
          "USAGE: java Driver <event_file> <courses_file> " +
          "<tracks_file> <nodes_file> <entrants_file> [times_file]");
      System.exit(1);
      
    } else if (args.length == 5) {
      System.out.println("No times file specified");
      System.out.println("Would you like to create one now?");
      System.out.println("(warning: could lead to loss of data if event has already started!)");
      System.out.print("y/n: ");
      Scanner in = new Scanner(System.in);
      String input = in.nextLine();
      if (input.equalsIgnoreCase("y")) {
        initTimes = true;
      } else if (input.equalsIgnoreCase("n")) {
        System.out.println("No times file, quitting");
        System.exit(1);
      } else {
        System.err.println("Undefined input");
        System.exit(1);
      }
    }
    
    String eventFile = args[0];
    String coursesFile = args[1];
    String tracksFile = args[2];
    String nodesFile = args[3];
    String entrantsFile = args[4];
    String timesFile;
    if (initTimes)
      timesFile = "times.txt";
    else
      timesFile = args[5];
      
    List<String> lines = FileIO.readLines(eventFile);
    String name = lines.get(0);
    Calendar c = Parser.parseDate(lines.get(1), lines.get(2));
    Event event = new Event(name, c);
    
    NodeList nodes = Parser.parseNodes(nodesFile);
    TrackList tracks = Parser.parseTracks(tracksFile, nodes);
    CourseList courses = Parser.parseCourses(coursesFile, nodes, tracks);
    List<Entrant> entrants = Parser.parseEntrants(entrantsFile, courses);
      
    event.addEntrants(entrants);
      
    if (!initTimes) {
      lines = FileIO.readLines(timesFile);
      for (String line : lines) {
        String[] tokens = line.split(" ");
        char type = tokens[0].charAt(0);
        Node node = nodes.getNodeById(Integer.parseInt(tokens[1]));
        int entrantId = Integer.parseInt(tokens[2]);
        int tHours = Integer.parseInt(tokens[3].split(":")[0]);
        int tMins = Integer.parseInt(tokens[3].split(":")[1]);
        event.update(type, node, entrantId, tHours, tMins);
      }
    }
    
    // that's all the parsing done
    // now start the gui
  }
  
}
