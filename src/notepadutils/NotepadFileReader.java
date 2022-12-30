package notepadutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NotepadFileReader {

	public static String read(File file) {
		try {
			String fileContent = "";
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine())!= null) {
				fileContent += line + "\n";
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
