package notepadutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotePadFileWriter {

	public static boolean write(File file, String contentText) {
		if(file == null)
	    	return false;
	    try {
	    	String filePath = file.getCanonicalPath();
			BufferedWriter writer= new BufferedWriter(new FileWriter(filePath));
			writer.write(contentText);
			writer.close();
			InputStreamReader r = new InputStreamReader(new FileInputStream(filePath));
			System.out.println(r.getEncoding());
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
