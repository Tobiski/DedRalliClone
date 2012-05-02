package rally;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FileLoader {
	public static String read(String fileName) {
		String str = "";
		
		try {
			FileInputStream fstream = new FileInputStream("res/" + fileName);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			 
			while ((strLine = br.readLine()) != null)   {
				str += strLine;
			  }
			  in.close();
		} catch (Exception e) {
		}
		
		return str;
	}
	
	public static void write(String str, String fileName) {
		try {
			FileWriter fstream = new FileWriter("res/" + fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(str);
			
			out.close();
		} catch (Exception e) {
		}
	}
}
