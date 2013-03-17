package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import event.Checkpoint;
import event.Course;
import event.CourseList;
import event.Entrant;
import event.Junction;
import event.MedicalCheckpoint;
import event.Node;
import event.NodeList;
import event.Track;
import event.TrackList;

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

  public static NodeList parseNodes(String nodesFile) {
    NodeList nodes = new NodeList();
    List<String> lines = FileIO.readLines(nodesFile);
    
    for (String line : lines) {
      String[] tokens = line.split(" ");
      int nodeId = Integer.parseInt(tokens[0]);
      switch (tokens[1]) {
      case "JN":
        nodes.add(new Junction(nodeId));
        break;
      case "CP":
        nodes.add(new Checkpoint(nodeId));
        break;
      case "MC":
        nodes.add(new MedicalCheckpoint(nodeId));
        break;
      default:
        System.err.println("Error parsing node type " + tokens[1]);
        System.exit(1);
        break;
      }
    }
    
    return nodes;
  }

  public static TrackList parseTracks(String tracksFile, NodeList nodes) {
    TrackList tracks = new TrackList();
    List<String> lines = FileIO.readLines(tracksFile);
    
    for (String line : lines) {
      String[] tokens = line.split(" ");
      int id = Integer.parseInt(tokens[0]);
      Node start = nodes.getNodeById(Integer.parseInt(tokens[1]));
      Node end = nodes.getNodeById(Integer.parseInt(tokens[2]));
      int safeTime = Integer.parseInt(tokens[3]);
      tracks.add(new Track(id, start, end, safeTime));
    }
    
    return tracks;
  }

  public static CourseList parseCourses(String coursesFile, NodeList nodes, TrackList tracks) {
    CourseList courses = new CourseList();
    List<String> lines = FileIO.readLines(coursesFile);
    
    for (String line : lines) {
      String[] tokens = line.split(" ");
      char id = tokens[0].charAt(0); // should be 1 char
      int numNodes = Integer.parseInt(tokens[1]);
      List<Track> courseTracks = new ArrayList<Track>();
      for (int i = 2; i < tokens.length - 1; i++) {
        int startId = Integer.parseInt(tokens[i]);
        int endId = Integer.parseInt(tokens[i+1]);
        Node start = nodes.getNodeById(startId);
        Node end = nodes.getNodeById(endId);
        courseTracks.add(tracks.getTrackFromNodes(start, end));
      }
      courses.add(new Course(id, numNodes, courseTracks));
    }
    
    return courses;
  }

  public static List<Entrant> parseEntrants(String entrantsFile,
      CourseList courses) {
    List<Entrant> entrants = new ArrayList<Entrant>();
    List<String> lines = FileIO.readLines(entrantsFile);
    
    return entrants;
  }
  
}
