package APP.System_User_Interface;

import java.awt.*;
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

import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableModel;

// import APP.NotificationsandEvents.DLineAlert;
// import APP.StockManagement.Stock;
import APP.OrderManagement.OrdItem;
import APP.OrderManagement.Order;
import APP.StockManagement.Stock;

import javax.swing.*;

// package APP.StockManagement;
import java.awt.*;    
import java.awt.event.*;

public class Order_GUI extends JFrame{

    public static DefaultTableModel model;
    public static JTable table;
    private JScrollPane scrollPane;
    private JPanel toppanel;
    private JFrame ordWindow;
    public static JTextArea detailspanel;
    // private JPanel bottompanel;
    public static final String file = "OrderList.csv";
    public static ArrayList<OrdItem> orderList;
    private JMenuBar optionBar;
	private String details;
    private Order_Table_GUI newOrder;
	JMenuItem addRecord;
	JMenu editRecord;
	JMenuItem savetable;
	JMenuItem editDescrp;
	JMenuItem editCost;
	JMenuItem editPhonenum;
	JMenuItem delRecord;
	JMenu Options;
    public static JMenu sortRecord;
    // public static JMenuItem sortByLastName;
	public static JMenuItem sortByOrdNum;
	public static JMenuItem sortByDeadline;
	public static JMenuItem sortByCompleted;
	public static JMenuItem sortByIncomplete;

    public Order_GUI() {

        toppanel=new JPanel();  
        ordWindow = new JFrame();
       	ordWindow.setBackground(Color.pink);

        toppanel.setBounds(0,0,1500,800);    
        
        toppanel.setLayout(new GridLayout(0,2));
        detailspanel = new JTextArea();
        detailspanel.setLayout(new FlowLayout());
        detailspanel.setBackground(Color.yellow); 
        optionBar = new JMenuBar();   
        
        sortRecord = new JMenu("Sort By ");
        sortByOrdNum = new JMenuItem("Order Number");
        sortByOrdNum.addActionListener(new sortByOrdNum()); // Added action listener
        sortByDeadline = new JMenuItem("Deadline");
        sortByDeadline.addActionListener(new sortByDeadline());
        sortByCompleted = new JMenuItem("Completed Order(s)");
        sortByCompleted.addActionListener(new sortByCompleted()); 
        sortByIncomplete = new JMenuItem("Incomplete Order(s)");
        sortByIncomplete.addActionListener(new sortByIncompleted());

        savetable = new JMenuItem("Save table changes");
        // savetable.addActionListener(new savetable());
        editDescrp = new JMenuItem("Edit Order Description");
        // editDescrp.addActionListener(this);
        editPhonenum = new JMenuItem("Edit Phone number");
        // editPhonenum.addActionListener(this);
        editCost = new JMenuItem("Edit the cost of an order");
        // editCost.addActionListener(this);


        sortRecord.add(sortByOrdNum);
        sortRecord.add(sortByDeadline);
        sortRecord.add(sortByCompleted);
        sortRecord.add(sortByIncomplete);
        Options = new JMenu("Option");
        addRecord = new JMenuItem("New Order");
        editRecord = new JMenu("Edit Order");
        editRecord.add(savetable);
        editRecord.add(editDescrp);
        editRecord.add(editPhonenum);
        editRecord.add(editCost);
        delRecord = new JMenuItem("Remove Order");
        Options.add(addRecord);
        Options.add(editRecord);
        Options.add(delRecord);
        optionBar.add(Options);
        optionBar.add(sortRecord);

        
        addRecord.addActionListener(new addNewRecord());
        // editRecord.addActionListener(new editRecord());
        delRecord.addActionListener(new deleteRecord());
        // savetable.addActionListener(new savetable());
        // sortRecord.addActionListener(new sortRecord());
        // sortByOrdNum.addActionListener(this);
        

        this.setJMenuBar(optionBar);
        this.add(toppanel, BorderLayout.CENTER); 
            
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = size.width;
        int height = size.height;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);  
        this.setVisible(true);
        orderList = loadItems(file);
        String[] columnNames = { "Order No.", "Item Name", "Status of Order", "DeadLine" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(orderList);
        table.setPreferredScrollableViewportSize(new Dimension(500, orderList.size() * 15 + 50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        toppanel.add(scrollPane);
        toppanel.add(detailspanel); 
        detailspanel.setMargin(new InsetsUIResource(20, 20, 20, 20));
        detailspanel.setFont(new Font("Arial", Font.PLAIN, 20));
        detailspanel.setBackground(Color.PINK);
        detailspanel.setText("Click on an order to see its details displayed here.");
        detailspanel.setEditable(false);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                String str = table.getValueAt(row, 0).toString();
                detailspanel.setText(DisplayDetails(file, str));

            }
        });
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
                JOptionPane.showMessageDialog(this, "System Error Restart application");
            }
            return str;
        }

    public void createAndShowGUI() {

        JFrame frame = new JFrame("Order");
      
    
        addRecord.setBounds(50,100,60,30);
        frame.pack(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = size.width;
        int height = size.height;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);  
        setLayout(null);  
     
        }

    public int createJOptionpane(String str){
        int n = JOptionPane.showConfirmDialog(this, str,"Confirmation",JOptionPane.YES_NO_OPTION);
        return n;
    }
        
    public static void showTable(ArrayList<OrdItem> orderList) {
        if (orderList.size() > 0) {
            for (OrdItem i : orderList) {
                {
                    addToTable(i);
                }
            }
        }

    }

    public static void addToTable(OrdItem i) {
        String[] item = { ""+i.getOrdnum(),  i.getName(), i.getStatus_2(), i.getDeadline()};
        model.addRow(item);
    }

    public ArrayList<OrdItem> loadItems(String pfile){
        Scanner pscan = null;
        ArrayList<OrdItem> orderList = new ArrayList<OrdItem>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String data=pscan.nextLine();
                String[] nextLine = data.split(" ");
                int ordnum = Integer.parseInt(nextLine[0]);
                String name = nextLine[1].replace("_"," ");
                String status = nextLine[2];
                String date = nextLine[3];
                String phonenum=nextLine[4];
                String addr = nextLine[5].replace("_"," ");
                String descrip = nextLine[6].replace("_"," ");
                String cost = nextLine[7];                
            OrdItem O = new OrdItem(ordnum, name, addr, date, status,descrip,  phonenum, cost) ;
            orderList.add(O);
                

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error Restart application");
        }
        return orderList;
    }

    private class sortByDeadline implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        Collections.sort(orderList, new Comparator<OrdItem>() {
            @Override
            public int compare(OrdItem o1, OrdItem o2) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d1 = sdf.parse(o1.getDeadline());
                Date d2 = sdf.parse(o2.getDeadline());
                return d1.compareTo(d2);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return 0; // Handle parsing exceptions gracefully
            }
            }
        });
        model.setRowCount(0);
        showTable(orderList);
        }
    }

    private class sortByOrdNum implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        Collections.sort(orderList, new Comp() {
        });
        model.setRowCount(0);
        showTable(orderList); // Update the table model with the sorted data
        }
    }

    private class sortByCompleted implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        Collections.sort(orderList, new CompD2() {
        });
        model.setRowCount(0);
        showTable(orderList); // Update the table model with the sorted data
        }
    }
    
    private class sortByIncompleted implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        Collections.sort(orderList, new CompD3());
        model.setRowCount(0);
        showTable(orderList); // Update the table model with the sorted data
        }
    }

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
                return o2.getStatus_2().compareTo(o1.getStatus_2());
            }
        }

    private class addNewRecord implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        APP.System_User_Interface.Order_Table_GUI newOrder  = new Order_Table_GUI();
    
    }    
}

    private class deleteRecord implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==delRecord){
                    int row = table.getSelectedRow();
                    String val ="";
                
            
                    if (createJOptionpane("Are you sure you want to delete this item")==0){
                        for (OrdItem i : orderList) {
                            if (i.getOrdnum()==(Integer.parseInt(table.getValueAt(row, 0).toString()))){
                                val=""+i.getOrdnum();
                                APP.OrderManagement.Order.removeRecord(val);
                                orderList.remove(i);
                                model.setRowCount(0);
                                showTable(orderList);
                                break;
                    
                            }   
                        }
                    }
            
                }
                
                
        
        
        }
        
    } 


}


  