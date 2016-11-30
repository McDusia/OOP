package konstytucja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constitution {
	public static void main (String[] args)
	{
		Path file = Paths.get(args[0]);
		Path file2 = Paths.get("C:\\Users\\Madzia\\konst\\wynik.txt");
		System.out.println(file.toString());

		try(OutputStream out = Files.newOutputStream(file2);BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out)))
		{		
			try (InputStream in = Files.newInputStream(file);BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			      
			       writer.write (line);
			        
			    }
			} catch (IOException x) {
			    System.err.println(x);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
