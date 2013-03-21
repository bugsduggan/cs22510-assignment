package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

	public static List<String> readLines(String filename)
			throws FileNotFoundException {
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
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static void appendToFile(String filename, String text) {
		File f = new File(filename);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			FileLock fl = fos.getChannel().tryLock();
			while (fl == null) {
				fl = fos.getChannel().tryLock();
			}
			FileWriter out = new FileWriter(fos.getFD());
			out.write(text + "\n");

			fl.release();
			fos.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
