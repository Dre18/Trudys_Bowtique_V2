package APP.OrderManagement;

import java.awt.event.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.w3c.dom.events.MouseEvent;

public class Order implements ActionListener{
    private static final String FILE_NAME = "OrderList.dat";
    public static ArrayList<OrdItem> orderList;
     private JTable table;

    public Order() {
        orderList = loadItems(FILE_NAME);
         
    }
         
    // }

    public static ArrayList<OrdItem> getOrderList() {
        return orderList;
    }


    private void writeToFile(ArrayList<OrdItem> orderList) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(FILE_NAME));
            for (OrdItem item : orderList) {
                writer.println(item.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }


    public String getDetails(String orderNum) {
        return DisplayDetails(FILE_NAME, orderNum);
    }

    public void addOrder(OrdItem order) {
        orderList.add(order);
        writeToFile(orderList);
    }

    public void removeOrder(String orderNum) {
        for (OrdItem item : orderList) {
            if (item.getOrdnum() == Integer.parseInt(orderNum)) {
                orderList.remove(item);
                writeToFile(orderList);
                return;
            }
        }
    }

    // public void updateOrderStatus(int row, String status) {
    //     orderList.get(row).setStatus(status);
    //     writeToFile(orderList);
    // }

    public void updateOrder(int row, String name, String address, String deadline, String description, String phoneNum, String cost) {
        OrdItem updatedOrder = new OrdItem(orderList.get(row).getOrdnum(), name, address, deadline,description, phoneNum, cost, cost);
        orderList.set(row, updatedOrder);
        writeToFile(orderList);
    }

    public static void removeRecord(String val){
        String tempfile = "temp.dat";
        String currentline;
        File oldfile= new File(FILE_NAME);
        File newfile = new File(tempfile);
        try {
            FileWriter fw = new FileWriter(tempfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
    
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            
            while ((currentline =br.readLine()) != null) {
                String[] data = currentline.split(" ");
                if (!(data[0].equals(val))) {
                     pw.println(currentline);
                }
                
            }
            pw.flush();
            pw.close();
            br.close();
            fr.close();
            fw.close();
            bw.close();
    
            oldfile.delete();
            File temp = new File(FILE_NAME);
            newfile.renameTo(temp);
        }
    
        catch (IOException IO) {
        }
    }

    public static ArrayList<OrdItem> loadItems(String fileName) {
        Scanner pscan = null;
        ArrayList<OrdItem> orderList = new ArrayList<OrdItem>();

        try {
            pscan = new Scanner(new File(fileName));
            while (pscan.hasNext()) {
                String data = pscan.nextLine();
                String[] nextLine = data.split(" ");
                int ordnum = Integer.parseInt(nextLine[0]);
                String name = nextLine[1].replace("_", " ");
                String status = nextLine[2];
                String date = nextLine[3];
                String phonenum = nextLine[4];
                String addr = nextLine[5].replace("_", " ").replace("~", "\n\t  ");
                String descrip = nextLine[6].replace("_", " ").replace("~", "\n\t  ");
                String cost = nextLine[7];
                OrdItem O = new OrdItem(ordnum, name, addr, date, status, descrip, phonenum, cost);
                orderList.add(O);
            }

            pscan.close();
        } catch (IOException e) {
            System.out.println("System Error Restart application");
        }
        return orderList;
    }

    public String DisplayDetails(String pfile, String val){

		Scanner pscan = null;
        String str="";

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String data=pscan.nextLine();
                String[] nextLine = data.split(" ");
                String ordnum = nextLine[0];
				if (ordnum.equals(val)){
					String name = nextLine[1].replace("_"," ");
					String status = nextLine[2];
					String date = nextLine[3];
					String phonenum=nextLine[4];
					String addr = nextLine[5].replace("_"," ").replace("~","\n\t    ");
					String descrip = nextLine[6].replace("_"," ").replace("~","\n\t    ");
					String cost = nextLine[7]; 
				             
				str = "Order No. : " + ordnum +"\n\nCustomer:  "+ name+ "           Phone number: " +phonenum+"\n\n Address: "+ addr+ "\n\n Deadline: " +date +"          Status of Order: "+ status +
				"\n\n Order Description: "+descrip +"\n\n Total Cost: "+ cost ;
				
			}
          
            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, this, "System Error Restart application", 0);
        }
        return str;
    }

    
    
// }




private class Comp implements Comparator<OrdItem>
{
    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        return o1.getOrdnum()- (o2.getOrdnum());
    }
}
private class CompD2 implements Comparator<OrdItem>
{

    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        return o1.getStatus_2().compareTo(o2.getStatus_2());
    }
}
private class CompD3 implements Comparator<OrdItem> 
{
@Override
public int compare(OrdItem o1, OrdItem o2) {
    
    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    Date date1;
    Date date2;
    int n=0;
    
    try {
            date1= f.parse(o1.getDeadline());
    
            date2=f.parse(o2.getDeadline());
            n = (date1.compareTo((date2)));
        
    } 
 
    catch (ParseException e) {
        e.printStackTrace();
    }	
    
    return n;
}

}
private class sortRecord implements ActionListener
{
@Override 
public void actionPerformed(ActionEvent e) {

    if (e.getSource()==APP.System_User_Interface.Order_GUI.sortByOrdNum){

        Collections.sort(APP.OrderManagement.Order.orderList, new Comp());
        APP.OrderManagement.Orders.model.setRowCount(0);
        APP.System_User_Interface.Order_GUI.showTable(APP.OrderManagement.Order.orderList);
    }  
    
        if (e.getSource()==APP.System_User_Interface.Order_GUI.sortByDeadline){
            Collections.sort(APP.System_User_Interface.Order_GUI.orderList, new CompD3());
            APP.System_User_Interface.Order_GUI.model.setRowCount(0);
            APP.System_User_Interface.Order_GUI.showTable(APP.System_User_Interface.Order_GUI.orderList);
            
    
        }
        if (e.getSource()==APP.System_User_Interface.Order_GUI.sortByCompleted){
            Collections.sort(APP.System_User_Interface.Order_GUI.orderList, new CompD2());
            APP.System_User_Interface.Order_GUI.model.setRowCount(0);
            APP.System_User_Interface.Order_GUI.showTable(APP.System_User_Interface.Order_GUI.orderList);
        }
        
        if (e.getSource()==APP.System_User_Interface.Order_GUI.sortByIncomplete){
            Collections.reverse(APP.System_User_Interface.Order_GUI.orderList);
            APP.System_User_Interface.Order_GUI.model.setRowCount(0);
            APP.System_User_Interface.Order_GUI.showTable(APP.System_User_Interface.Order_GUI.orderList);
        }

    }  
}
@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
} 
}

