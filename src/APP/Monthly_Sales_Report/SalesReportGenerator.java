package APP.Monthly_Sales_Report;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SalesReportGenerator {

    private static final String REPORT_FILE_PREFIX = "SalesReport";

    public static void getInfo() {
        int totalCost2 = 0;
        int count = 0;
        int numm = 0;

		try (Scanner myreader = new Scanner(new FileReader(APP.OrderManagement.Order.FILE_NAME));
             FileWriter mywriter = new FileWriter(REPORT_FILE_PREFIX + numm + ".doc")) {

				String l = "                   ********TRUDY'S BOWTIQUE******* \n                     ********SALES REPORT******* \n";
				mywriter.write(l);
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
						  totalCost2 += cost2;
					  // }
					  
				  
				  
				}
				
				mywriter.write("\nTotal Monthly Sale: " + totalCost2 + "\n" );
				  
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
  