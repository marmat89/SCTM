
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ashraf
 * 
 */
public class CSVfileManager {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "lowpulseoccupancy,concentration";

	public static void writeCsvFile(String fileName, DustMeasure measure) {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(df.format(date));
		//Create a new list of student objects

	
		
		FileWriter fileWriter = null;		
		try {
			fileWriter = new FileWriter(fileName,true);

			//Write the CSV file header
			//fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			//fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file

				fileWriter.append(df.format(date));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(measure.lowpulseoccupancy);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(measure.concentration);
				fileWriter.append(NEW_LINE_SEPARATOR);
			

			
			
			System.out.println("CSV file was update successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) throws Exception {
		CSVfileManager main = new CSVfileManager();
		DustMeasure measure = new DustMeasure("test","test");
		main.writeCsvFile("measure.csv",measure);
		   // Wait 5 seconds then shutdown
	   
	}
}