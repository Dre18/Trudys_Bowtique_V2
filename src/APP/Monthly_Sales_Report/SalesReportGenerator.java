package APP.Monthly_Sales_Report;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SalesReportGenerator {

    private static final String REPORT_FILE_PREFIX = "SalesReport";
	// private static final long TIMESTAMP_UPDATE_INTERVAL = 10000; // Update timestamp every 10 seconds (in milliseconds)

    private static int numm = 0;
	static long timestamp = System.currentTimeMillis();


	private static String generateUniqueFilename() {
		return String.valueOf(timestamp);
	  }

	  public static String convertTimestampToDate(long timestamp) {
		// Create a Date object from the timestamp
		Date date = new Date(timestamp);
	  
		// Use a SimpleDateFormat to format the date in a desired format
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Adjust format as needed (e.g., "dd/MM/yyyy")
	  
		// Return the formatted date string
		return formatter.format(date);
	  }

    public static void getInfo() {
        int totalCost = 0;
		int count = 0;
		numm++;
		long lastTimestampUpdate = System.currentTimeMillis(); // Initialize timestamp

		try (Scanner myreader = new Scanner(new FileReader(APP.OrderManagement.Order.FILE_NAME));
		

             FileWriter mywriter = new FileWriter(REPORT_FILE_PREFIX + generateUniqueFilename() + ".doc")) {
				// mywriter.setEncryption(null,null,mywriter,FileWriter.ENCRYPTION_AES | FileWriter.STANDARD_ENCRYPTION_128); // encryption level
				String header = "                   ********TRUDY'S BOWTIQUE******* \n                     ********SALES REPORT******* \n";
				mywriter.write(header);
			  while (myreader.hasNextLine()) {
				  
				  String [] mdata = myreader.nextLine().split(" ");
				  String ordnum = mdata[0];
				  String name = mdata[1].replace("_"," ");
				  String status = mdata[2];
				  String date = mdata[3];
				  String phonenum = mdata[4];
				  String addr =mdata[5].replace("_"," ").replace("~","\n\t    ");
				  String descrip = mdata[6].replace("_"," ").replace("~","\n\t    ");
				  String cost = mdata[7]; 
				  
					  count++;
					  
					  String num=Integer.toString(count);
					  String txt = "Sales #: "+num+ "\n"+"Order #: "+ordnum+" by: "+ name + " " + "Purchase: "+ descrip +" Cost: " + cost +"\n\n";
					  int cost2 = Integer.parseInt(cost.replace("$", ""));
					  // status = status.strip();
					  // if(status.equals("Completed"))
					  // {
						  mywriter.write(txt);
  
					  // }
					  
					  // else{
						  totalCost += cost2;
					  // }
					  
					//   if (System.currentTimeMillis() - lastTimestampUpdate >= TIMESTAMP_UPDATE_INTERVAL) {
					// 	lastTimestampUpdate = System.currentTimeMillis();
					// 	mywriter.write("\n******** Updated: " + convertTimestampToDate(lastTimestampUpdate) + " ********\n");
					//   }
				  
				}
				
				mywriter.write("\nTotal Monthly Sale: " + totalCost + "\n" );
				  
			  mywriter.flush();
				myreader.close();
				mywriter.close();
				mywriter.close();
		  } catch (FileNotFoundException e) {
				System.out.println("File not found");
				e.printStackTrace();
		  }
		  catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
		  
		  }
		  catch (Exception o) {
			  System.out.println(o.getMessage());
		  
		
		}
	  }
  }
  