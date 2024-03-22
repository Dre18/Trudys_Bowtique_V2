package APP.OrderManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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

private class Orderpanel extends JFrame{
     
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
        // Done.addActionListener(this);
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

    public String getDescrp() 
    {
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
