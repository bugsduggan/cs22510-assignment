package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

  public static List<String> readLines(String filename) {
    List<String> lines = new ArrayList<String>();
    
    File f = new File(filename);
    try {
      BufferedReader in = new BufferedReader(new FileReader(f));
      String line = in.readLine();
      
      while (line != null) {
        lines.add(line);
        line = in.readLine();
      }
      
      in.close();
    } catch (FileNotFoundException e) {
      System.err.println(filename + " not found");
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return lines;
  }
  
}
