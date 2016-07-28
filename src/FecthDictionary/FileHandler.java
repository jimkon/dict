package FecthDictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;

public class FileHandler {


	public static void main(String[] args) {
		final String dict_path = "res/en_US.dic";
		System.out.println(dict_path.substring(dict_path.length()).length());
		try {
			FileHandler fh = new FileHandler(dict_path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public FileHandler(String dict_path) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File(dict_path)));
		
		System.out.println(in.readLine());
		String s = in.readLine();
		System.out.println(s);
		
	}
}
