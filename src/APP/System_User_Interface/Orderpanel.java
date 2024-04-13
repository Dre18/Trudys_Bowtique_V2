package APP.System_User_Interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;

import org.w3c.dom.events.MouseEvent;

import APP.OrderManagement.OrdItem;
import APP.System_User_Interface.Order_GUI;
import APP.OrderManagement.Order;



class Orderpanel extends JFrame {
     
    // Components of the Form
    private Container a;
    private JLabel title;
    private JLabel name;
    public static JTextField tname;
    private JLabel mobile;
    public static JTextField t_mob;
    private JLabel Descrp;
    private static JTextArea t_Descrp;
    private JLabel cost;
    public static JTextField t_cost;
    private JLabel dline;
    public static JTextField t_dline;
    private JLabel add;
    public static JTextArea tadd;
    public static JButton Done;
  
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
        Done.addActionListener(new DoneButton());
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
		resadd.setText("eg:\n Red_Polo_Shirts 5\n (Delete this example before typing)  ");
        resadd.setLocation(180, 100);

        // APP.System_User_Interface.Order_GUI.sortRecord.addActionListener(new sortRecord());
		
        d.add(resadd);
         
        b.add(c);
        b.add(d);
        a.add(b, BorderLayout.CENTER);
        setVisible(true);
		

        
    
    }


    private class DoneButton implements ActionListener
    {
        @Override 

    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Done){
			FileWriter f;
			try {
				f = new FileWriter(APP.System_User_Interface.Order_GUI.file, true);
			
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter w = new PrintWriter(b);
					OrdItem o = new OrdItem(tname.getText(), t_dline.getText(), tadd.getText(), t_Descrp.getText(), t_mob.getText(), t_cost.getText());
                    w.println(o.getOrdnum() + " " + o.getName().replace(" ", "_") + " " + o.getStatus_2() + " "
                            + o.getDeadline() + " " +o.getPhonenum()+" "+ o.getAddr().replace(" ", "_").replace("\n", "~") + " " + o.getOrdDescrip().replace(" ","_").replace("\n", "~") + " " + o.getCost());
                    
					w.flush();
					w.close();
					b.close();
					f.close();
                    APP.OrderManagement.Order.getOrderList().add(o);
                    // APP.System_User_Interface.Order_GUI.table.setVisible(false);
                    dispose();
                    // tname.setText("");
                    // t_mob.setText("");
                    // t_dline.setText("");
                    // tadd.setText("");
                    // t_Descrp.setText(""); 
                    // t_cost.setText("");
                    

                    // resadd.setText("eg:\n Red_Polo_Shirts 5\n (Delete this example before typing)  ");
                    // setVisible(true);



				} catch (IOException e1) {
                    Order_GUI panel = new Order_GUI();
					JOptionPane.showMessageDialog(panel,"Something went wrong");
				}
				APP.System_User_Interface.Order_GUI.model.setRowCount(0);
                APP.OrderManagement.Order.orderList=APP.OrderManagement.Order.loadItems(APP.System_User_Interface.Order_GUI.file);
                APP.System_User_Interface.Order_GUI.showTable( APP.OrderManagement.Order.orderList);
                
		}
  

    
}




}
}