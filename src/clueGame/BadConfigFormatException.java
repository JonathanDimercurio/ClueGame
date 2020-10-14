/**
 * BadConfigurationException - Skeleton version
 * 
 * Purpose: Currently a Skeleton class, next implementation will actual data file errors.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */


package clueGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@SuppressWarnings("serial")
public class BadConfigFormatException extends Exception{
	
	public BadConfigFormatException (String message) {
		super(message);
		try {
			Date date = new Date();
			message = System.lineSeparator() + message + date;
		    File myObj = new File("errorLog.txt");
		    if (myObj.createNewFile()) {
		    	System.out.println("File created: " + myObj.getName());
		    	Files.write(Paths.get("errorLog.txt"), message.getBytes(), StandardOpenOption.APPEND);
		    } else {
		    	Files.write(Paths.get("errorLog.txt"), message.getBytes(), StandardOpenOption.APPEND);
		      }
		    } catch (IOException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
	}
}
