package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import event.CheckpointNode;
import event.Course;
import event.Entrant;
import event.Event;
import event.JunctionNode;
import event.MedicalCheckpointNode;
import event.Node;
import event.Track;
import event.UpdateEvent;

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

  public static List<Node> parseNodes(String nodesFile) {
    List<Node> nodes = new ArrayList<Node>();
    
    for (String line : FileIO.readLines(nodesFile)) {
      String[] tokens = line.split(" ");
      int id = Integer.parseInt(tokens[0]);
      switch (tokens[1]) {
        case "JN":
          nodes.add(new JunctionNode(id));
          break;
        case "CP":
          nodes.add(new CheckpointNode(id));
          break;
        case "MC":
          nodes.add(new MedicalCheckpointNode(id));
          break;
        default:
          System.err.println("Error parsing node type " + tokens[1]);
          System.exit(1);
          break;
      }
    }
    
    return nodes;
  }

  public static List<Track> parseTracks(String tracksFile, Event event) {
    List<Track> tracks = new ArrayList<Track>();
    
    for (String line : FileIO.readLines(tracksFile)) {
      String[] tokens = line.split(" ");
      int id = Integer.parseInt(tokens[0]);
      Node start = event.getNode(Integer.parseInt(tokens[1]));
      Node end = event.getNode(Integer.parseInt(tokens[2]));
      int safeTime = Integer.parseInt(tokens[3]);
      tracks.add(new Track(id, start, end, safeTime));
    }
    
    return tracks;
  }

  public static List<Course> parseCourses(String coursesFile, Event event) {
    List<Course> courses = new ArrayList<Course>();
    
    for (String line : FileIO.readLines(coursesFile)) {
      String[] tokens = line.split(" ");
      char id = tokens[0].charAt(0); // should be 1 char
      // we can ignore the next token unless we want to check the
      // number of nodes
      
      List<Node> courseNodes = new ArrayList<Node>();
      List<Track> courseTracks = new ArrayList<Track>();
      // add the first node
      courseNodes.add(event.getNode(Integer.parseInt(tokens[2])));
      // then loop through and add nodes and tracks as needed
      for (int i = 2; i < tokens.length - 1; i++) {
        Node start = event.getNode(Integer.parseInt(tokens[i]));
        Node end = event.getNode(Integer.parseInt(tokens[i+1]));
        courseTracks.add(event.getTrack(start, end));
        courseNodes.add(end);
      }
      
      courses.add(new Course(id, courseNodes, courseTracks));
    }
    
    return courses;
  }

  public static List<Entrant> parseEntrants(String entrantsFile, Event event) {
    List<Entrant> entrants = new ArrayList<Entrant>();
    
    for (String line : FileIO.readLines(entrantsFile)) {
      String[] tokens = line.split(" ");
      int id = Integer.parseInt(tokens[0]);
      Course course = event.getCourse(tokens[1].charAt(0));
      String name = new String();
      for (int i = 2; i < tokens.length; i++) {
        name = name + tokens[i] + " ";
      }
      name = name.substring(0, name.length() - 1);
      entrants.add(new Entrant(id, course, name));
    }
    
    return entrants;
  }
  
  public static List<UpdateEvent> parseTimes(String timesFile, Event event) {
    List<UpdateEvent> updates = new ArrayList<UpdateEvent>();
    
    for (String line : FileIO.readLines(timesFile)) {
      String[] tokens = line.split(" ");
      char type = tokens[0].charAt(0);
      Node node = event.getNode(Integer.parseInt(tokens[1]));
      Entrant entrant = event.getEntrant(Integer.parseInt(tokens[2]));
      int hrs = Integer.parseInt(tokens[3].split(":")[0]);
      int mins = Integer.parseInt(tokens[3].split(":")[1]);
      updates.add(new UpdateEvent(type, node, entrant, hrs, mins));
    }
    
    return updates;
  }

}
