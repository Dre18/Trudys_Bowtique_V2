package APP.OrderManagement;
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
import APP.StockManagement.Stock;

import javax.swing.*;

// package APP.StockManagement;
import java.awt.*;    
import java.awt.event.*;

public class Orders extends JFrame implements ActionListener{
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
    public Orders() {
        

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
			sortByOrdNum.addActionListener(this);
            sortByDeadline = new JMenuItem("Deadline");
			sortByDeadline.addActionListener(this);
            sortByCompleted = new JMenuItem("Completed Order(s)");
			sortByCompleted.addActionListener(this);
            sortByIncomplete = new JMenuItem("Incomplete Order(s)");
			sortByIncomplete.addActionListener(this);

            savetable = new JMenuItem("Save table changes");
			savetable.addActionListener(this);
			editDescrp = new JMenuItem("Edit Order Description");
			editDescrp.addActionListener(this);
			editPhonenum = new JMenuItem("Edit Phone number");
			editPhonenum.addActionListener(this);
			editCost = new JMenuItem("Edit the cost of an order");
			editCost.addActionListener(this);


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

			
            addRecord.addActionListener(this);
			editRecord.addActionListener(this);
			delRecord.addActionListener(this);
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
  
	
    /** 
     * @param pfile
     * @param val
     * @return String
     */
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

    
    /** 
     * @param pfile
     * @return ArrayList<OrdItem>
     */
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

    
    /** 
     * @param i
     */
    public void addToTable(OrdItem i) {
        String[] item = { ""+i.getOrdnum(),  i.getName(), i.getStatus_2(), i.getDeadline()};
        model.addRow(item);
    }

    
    /** 
     * @param orderList
     */
    private void showTable(ArrayList<OrdItem> orderList) {
        if (orderList.size() > 0) {
            for (OrdItem i : orderList) {
                {
                    addToTable(i);
                }
            }
        }

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

/** 
 * @param str
 * @return int
 */
public int createJOptionpane(String str){
	int n = JOptionPane.showConfirmDialog(this, str,"Confirmation",JOptionPane.YES_NO_OPTION);
	return n;
}


/** 
 * @param val
 */
public void removeRecord(String val){
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
		File temp = new File(file);
		newfile.renameTo(temp);
	}

	catch (IOException IO) {
	}
}
private class Orderpanel extends JFrame implements ActionListener{
     
    // Components of the Form
    private Container a;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel mobile;
    private JTextField t_mob;
    private JLabel Descrp;
    private JTextArea t_Descrp;
    private JLabel cost;
    private JTextField t_cost;
    private JLabel dline;
    private JTextField t_dline;
    private JLabel add;
    private JTextArea tadd;
    private JButton Done;
  
    public JTextArea resadd;
 

    public Orderpanel(){
        setTitle("New Order Form");
        setBounds(300, 10, 900, 900);
        setResizable(false);
        a = getContentPane();
        JPanel b = new JPanel();
        b.setLayout(new GridLayout(0,2));
        JPanel c = new JPanel();
        c.setLayout(null);
        JPanel d = new JPanel();
        d.setLayout(null);

        title = new JLabel("NEW ORDER");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(1200, 30);
        a.add(title, BorderLayout.NORTH);
		 
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(50, 100);
        c.add(name);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(150, 20);
        tname.setLocation(180, 100);
        c.add(tname);
 
        mobile = new JLabel("Mobile (xxx-xxx-xxxx)");
        mobile.setFont(new Font("Arial", Font.PLAIN, 18));
        mobile.setSize(200, 20);
        mobile.setLocation(50, 150);
        c.add(mobile);
 
        t_mob = new JTextField();
        t_mob.setFont(new Font("Arial", Font.PLAIN, 15));
        t_mob.setSize(150, 20);
        t_mob.setLocation(220, 150);
        c.add(t_mob);

        dline = new JLabel("Deadline (dd/mm/yyyy)");
        dline.setFont(new Font("Arial", Font.PLAIN, 18));
        dline.setSize(200, 20);
        dline.setLocation(50, 200);
        c.add(dline);
 
        t_dline = new JTextField();
        t_dline.setFont(new Font("Arial", Font.PLAIN, 15));
        t_dline.setSize(100, 20);
        t_dline.setLocation(250, 200);
        c.add(t_dline);
 
        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(50, 250);
        c.add(add);
 
        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(250, 50);
        tadd.setLocation(220, 250);
        tadd.setLineWrap(true);
        c.add(tadd);

        Descrp = new JLabel("Order Description");
        Descrp.setFont(new Font("Arial", Font.PLAIN, 18));
        Descrp.setSize(200, 40);
        Descrp.setLocation(50, 320);
        c.add(Descrp);
 
        t_Descrp = new JTextArea();
        t_Descrp.setFont(new Font("Arial", Font.PLAIN, 15));
        t_Descrp.setSize(250, 150);
        t_Descrp.setLocation(220, 320);
        t_Descrp.setLineWrap(true);
        c.add(t_Descrp);

        cost = new JLabel("Cost:");
        cost.setFont(new Font("Arial", Font.PLAIN, 20));
        cost.setSize(100, 20);
        cost.setLocation(150, 500);
        c.add(cost);
 
        t_cost = new JTextField();
        t_cost.setFont(new Font("Arial", Font.PLAIN, 15));
        t_cost.setSize(85, 30);
        t_cost.setLocation(220, 500);
        c.add(t_cost);
 
        Done = new JButton("Done");
        Done.setFont(new Font("Arial", Font.PLAIN, 15));
        Done.setSize(100, 20);
        Done.setLocation(270, 600);
        Done.addActionListener(this);
        c.add(Done);
    
        
        JLabel res  = new JLabel("reduce stock by");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(300, 25);
        res.setLocation(10, 100);
        d.add(res);

 
        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(250, 300);
		resadd.setMargin(new InsetsUIResource(10,10,10,10));
		resadd.setText("eg:\n Red_Polo_Shirts 5\n (Delete this example before typing)");
        resadd.setLocation(180, 100);
		
        d.add(resadd);
         
        b.add(c);
        b.add(d);
        a.add(b, BorderLayout.CENTER);
        setVisible(true);
		
    
    }
    public String getDescrp() {
    return t_Descrp.getText();
    }
    public String getRStock() {
        return resadd.getText();
        }

    

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Done){
			FileWriter f;
			try {
				f = new FileWriter(file, true);
			
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter w = new PrintWriter(b);
					OrdItem o = new OrdItem(tname.getText(), t_dline.getText(), tadd.getText(), getDescrp(), t_mob.getText(), t_cost.getText());
                    w.println(o.getOrdnum() + " " + o.getName().replace(" ", "_") + " " + o.getStatus_2() + " "
                            + o.getDeadline() + " " +o.getPhonenum()+" "+ o.getAddr().replace(" ", "_").replace("\n", "~") + " " + o.getOrdDescrip().replace(" ","_").replace("\n", "~") + " " + o.getCost());
                            // System.out.println("Description: " + o.getOrdDescrip());
                            // String reduceStockContent = getRStock();
                            System.out.println("Content of 'reduce stock by': ");
                            // System.out.println(reduceStockContent);

					w.flush();
					w.close();
					b.close();
					f.close();
                    orderList.add(o);
					this.setVisible(false);

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Something went wrong");;
				}
				model.setRowCount(0);
                orderList=loadItems(file);
                showTable(orderList);
		}
		
	}
   
	
}



/** 
 * @param e
 */
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource()==addRecord){
		Orderpanel o =new Orderpanel();
	}
	if (e.getSource()==delRecord){
		int row = table.getSelectedRow();
		String val ="";
    

	   if (createJOptionpane("Are you sure you want to delete this item")==0){
			for (OrdItem i : orderList) {
				if (i.getOrdnum()==(Integer.parseInt(table.getValueAt(row, 0).toString()))){
					val=""+i.getOrdnum();
					removeRecord(val);
					orderList.remove(i);
					model.setRowCount(0);
					showTable(orderList);
					break;
		
				}   
			}
		}

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
	


	
	if (e.getSource()==sortByOrdNum){

	Collections.sort(orderList, new Comp());
	model.setRowCount(0);
	showTable(orderList);
}  

    if (e.getSource()==sortByDeadline){
		Collections.sort(orderList, new CompD3());
		model.setRowCount(0);
		showTable(orderList);
		

    }
    if (e.getSource()==sortByCompleted){
		Collections.sort(orderList, new CompD2());
		model.setRowCount(0);
		showTable(orderList);
    }
	
    if (e.getSource()==sortByIncomplete){
		Collections.reverse(orderList);
		model.setRowCount(0);
		showTable(orderList);
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






}

