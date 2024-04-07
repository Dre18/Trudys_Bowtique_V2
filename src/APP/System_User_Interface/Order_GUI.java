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




    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel toppanel;
    private JFrame ordWindow;
    private JTextArea detailspanel;
    private JPanel bottompanel;
    private static final String file = "OrderList.dat";
    private ArrayList<OrdItem> orderList;
    private JMenuBar optionBar;
	private String details;
    private Orderpanel newOrder;
	JMenuItem addRecord;
	JMenu editRecord;
	JMenuItem savetable;
	JMenuItem editDescrp;
	JMenuItem editCost;
	JMenuItem editPhonenum;
	JMenuItem delRecord;
	JMenu Options;
    JMenu sortRecord;
    JMenuItem sortByLastName;
	JMenuItem sortByOrdNum;
	JMenuItem sortByDeadline;
	JMenuItem sortByCompleted;
	JMenuItem sortByIncomplete;

    public Order_GUI() {
        


// 


        toppanel=new JPanel();  
        ordWindow = new JFrame();
       	// ordWindow.setBackground(Color.pink);

        toppanel.setBounds(0,0,1500,800);    
        
        toppanel.setLayout(new GridLayout(0,2));
        detailspanel = new JTextArea();
        detailspanel.setLayout(new FlowLayout());
        detailspanel.setBackground(Color.yellow); 
        optionBar = new JMenuBar();   
        
        	sortRecord = new JMenu("Sort By ");
            sortByOrdNum = new JMenuItem("Order Number");
			// sortByOrdNum.addActionListener(this);
            sortByDeadline = new JMenuItem("Deadline");
			// sortByDeadline.addActionListener(this);
            sortByCompleted = new JMenuItem("Completed Order(s)");
			// sortByCompleted.addActionListener(this);
            sortByIncomplete = new JMenuItem("Incomplete Order(s)");
			// sortByIncomplete.addActionListener(this);

            savetable = new JMenuItem("Save table changes");
			// savetable.addActionListener(this);
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
			// delRecord.addActionListener(this);
            this.setJMenuBar(optionBar);
            this.add(toppanel, BorderLayout.CENTER); 
               
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            int width = size.width;
            int height = size.height;
            this.setSize(width, height);
            this.setLocationRelativeTo(null);  
            this.setVisible(true);
            orderList = loadItems(file);
            String[] columnNames = { "Order No.", "Customer's Name", "Status of Order", "DeadLine" };
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
            detailspanel.setBackground(Color.WHITE);
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


        // Secure_viewGUI gui = new Secure_viewGUI();
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
          

        private void showTable(ArrayList<OrdItem> orderList) {
            if (orderList.size() > 0) {
                for (OrdItem i : orderList) {
                    {
                        addToTable(i);
                    }
                }
            }
    
        }

        public void addToTable(OrdItem i) {
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




      


        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==addRecord){
                Orderpanel o =new Orderpanel();
            }
         
            if (e.getSource()==savetable){
                if  ( table.isEditing() )
                {
                    String val;
                    int row = table.getEditingRow();
                    int col = table.getEditingColumn();
                    table.getCellEditor(row, col).stopCellEditing();
                    int count= table.getRowCount();
                    int num=0;
                    for (OrdItem i: orderList){
                    
                        val= table.getValueAt(row,col).toString();
                        String tempfile = "temp.dat";
                        String currentline;
                        File oldfile= new File(file);
                        File newfile = new File(tempfile);
                        try {
                            FileWriter fw = new FileWriter(tempfile, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter pw = new PrintWriter(bw);
            
                            FileReader fr = new FileReader(file);
                            BufferedReader br = new BufferedReader(fr);
                            
                            while ((currentline =br.readLine()) != null) {
                                String[] data = currentline.split(" ");
                                
                                if (!(num==row)) {
                                     pw.println(currentline);
                                }
                                else{
                                    data[col]=val;
                                    String txt= data[0]+" "+data[1].replace(" ", "_")+ " "+data[2]+ " "+data[3]+" "+data[4]+" "+data[5]+" "+ data[6]+" "+data[7];
                                    pw.println(txt);
                                }
                                num++;
                                
                            }
                            pw.flush();
                            pw.close();
                            br.close();
                            fr.close();
                            fw.close();
                            bw.close();
            
                            oldfile.delete();
                            File temp = new File(file);
                            newfile.renameTo(temp);
                        }
            
                        catch (IOException IO) {
                        }
                    }
                    
                }
            }
            
        
        
            
        //     if (e.getSource()==sortByOrdNum){
        
        //     Collections.sort(orderList, new Comp());
        //     model.setRowCount(0);
        //     showTable(orderList);
        // }  
        
        //     if (e.getSource()==sortByDeadline){
        //         Collections.sort(orderList, new CompD3());
        //         model.setRowCount(0);
        //         showTable(orderList);
                
        
        //     }
        //     if (e.getSource()==sortByCompleted){
        //         Collections.sort(orderList, new CompD2());
        //         model.setRowCount(0);
        //         showTable(orderList);
        //     }
            
        //     if (e.getSource()==sortByIncomplete){
        //         Collections.reverse(orderList);
        //         model.setRowCount(0);
        //         showTable(orderList);
        //     }
            
        }


    //     private class DeleteButtonListener implements ActionListener {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
        
    //         if (e.getSource() == APP.System_User_Interface.Orderpanel.Done){
    //             Orderpanel newOrd =new Orderpanel();
    //             FileWriter f;
    //             try {
    //                 f = new FileWriter(file, true);
                
    //                     BufferedWriter b = new BufferedWriter(f);
    //                     PrintWriter w = new PrintWriter(b);
    //                     OrdItem o = new OrdItem(APP.System_User_Interface.Orderpanel.tname.getText(), APP.System_User_Interface.Orderpanel.t_dline.getText(), APP.System_User_Interface.Orderpanel.tadd.getText(), getDescrp(), APP.System_User_Interface.Orderpanel.t_mob.getText(), APP.System_User_Interface.Orderpanel.t_cost.getText());
    //                     w.println(o.getOrdnum() + " " + o.getName().replace(" ", "_") + " " + o.getStatus_2() + " "
    //                             + o.getDeadline() + " " +o.getPhonenum()+" "+ o.getAddr().replace(" ", "_").replace("\n", "~") + " " + o.getOrdDescrip().replace(" ","_").replace("\n", "~") + " " + o.getCost());
    //                             // System.out.println("Description: " + o.getOrdDescrip());
    //                             // String reduceStockContent = getRStock();
    //                             System.out.println("Content of 'reduce stock by': ");
    //                             // System.out.println(reduceStockContent);
    
    //                     w.flush();
    //                     w.close();
    //                     b.close();
    //                     f.close();
    //                     APP.OrderManagement.Order.getOrderList().add(o);
    //                     // .setVisible(false);
    
    //                 } catch (IOException e1) {
    //                     JOptionPane.showMessageDialog(newOrd, "Something went wrong");;
    //                 }
    //                 model.setRowCount(0);
    //                 orderList=loadItems(file);
    //                 showTable(orderList);
    //         }
            
    //     }
       
         
   
        
        private class addNewRecord implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
               APP.System_User_Interface.Orderpanel newOrder  = new Orderpanel();
          
            // Login successful, show secure view or perform other actions
            // APP.System_User_Interface.Stock_GUI stock_GUI  = new Stock_GUI(stockManagement);
            // newOrder.createAndShowGUI();
            }
        }


}

  