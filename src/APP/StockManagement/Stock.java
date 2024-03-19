package APP.StockManagement;
// import APP.OrderManagement.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import APP.OrderManagement.Order;



public class Stock extends JPanel {
    
    private JButton update;
    private JButton addItem;
    private JButton delete;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private static ArrayList<Item> ilist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField item;
    private JTextField Quantity;
    private JPanel pnl;
    private static final String file= "StockList.dat";
    private String resadd;
 
  
    public Stock() {
        super(new GridLayout(2, 1));
        pnl=this;
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        ilist = loadStock(file);
        String[] columnNames = { "Items", "Quantity"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(ilist);
        table.setPreferredScrollableViewportSize(new Dimension(50, ilist.size() * 15 + 50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        item = new JTextField(10);
        Quantity = new JTextField(10);
        pnlDisplay.setLayout(new GridLayout(4, 0));
        pnlDisplay.add(new JLabel("New Item:"));
        pnlDisplay.add(item);
        pnlDisplay.add(new JLabel("Amount:"));
        pnlDisplay.add(Quantity);
        addItem = new JButton("Add Item");
        addItem.addActionListener(new AddItemButtonListener());
        addItem.setBackground(Color.GREEN);
        pnlDisplay.add(new JLabel(" "));
        pnlDisplay.add(addItem);

        delete = new JButton("Delete Item");
        update = new JButton("Update Item");

        update.addActionListener(new UpdateButtonListener());
        delete.addActionListener(new DeleteButtonListener());
        update.setBackground(Color.ORANGE);
        delete.setBackground(Color.red);
        pnlCommand.add(update, BorderLayout.CENTER);
        pnlCommand.add(delete, BorderLayout.CENTER);
        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);
        resadd = "";
        

    

}
    
    /** 
     * @param pfile
     * @return ArrayList<Item>
     */
    @SuppressWarnings("null")
    private ArrayList<Item> loadStock(String pfile){
        Scanner pscan = null;
        ArrayList<Item> ilist = new ArrayList<Item>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                if (nextLine[0].equals("resadd")) {
                    resadd = nextLine[1];
                } else if (!nextLine[0].isEmpty()) {
                    String name = nextLine[0];
                    int quantity = Integer.parseInt(nextLine[1]);
                    Item item = new Item(name, quantity);
                    ilist.add(item);
                }
            }
            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error Unable to load items");
        }
        return ilist;
    }

   

    public void reduceStock(String item_name, int c) {
        for (Item i : ilist) {
            if (item_name.equals(i.getItemName())) {
                int reduction = (resadd.isEmpty()) ? 0 : Integer.parseInt(resadd);
                int newQuantity = i.getItemQuantity() - Math.max(c - reduction, 0);
                i.changeQuantity(newQuantity);
                break;
            }
        }
    }
/** 
    * @param I
    */
   public void addItem(Item I) {
        ilist.add(I);
        addToTable(I);

    }
    
    /** 
     * @return ArrayList<Item>
     */
    public static ArrayList<Item> getItems(){
        return ilist;
    }
    
    /** 
     * @param item_name
     * @param c
     */
    // public void reduceStock(String item_name, int c){
    //     //reduces the stock qauntity based on order made
    //     if (!resadd.isEmpty()) {
    //         c -= Integer.parseInt(resadd);
    //     }
    //     for (Item i: ilist){
    //         if (item_name==i.getItemName() ){
    //         int retval= i.getItemQuantity() - c;
    //         i.changeQuantity(retval);
    //         }
    //     }
    // }
    
    
    /** 
     * @param i
     */
    public void addToTable(Item i) {
        String[] item = { i.getItemName(), "" + i.getItemQuantity()};
        model.addRow(item);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Stock");
        Stock newContentPane = new Stock();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }
    
    /** 
     * @param str
     * @return int
     */
    public int createJOptionpane(String str){
        int n = JOptionPane.showConfirmDialog(pnl, str,"Confirmation",JOptionPane.YES_NO_OPTION);
        return n;
    }


    
    
    /** 
     * @param ilist
     */
    private void showTable(ArrayList<Item> ilist) {
        if (ilist.size() > 0) {
            for (Item i : ilist) {
                {
                    addToTable(i);
                }
            }
        }
    }




    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == update){
                if  ( table.isEditing() )
				{
                    String val;
					int row = table.getEditingRow();
					int col = table.getEditingColumn();
					table.getCellEditor(row, col).stopCellEditing();
				    int count= table.getRowCount();
                    int num=0;
				    for (Item i: ilist){
                    
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
                                    String txt= data[0].replace(" ", "_")+" "+data[1];
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
            
                        catch (IOException IO) 
                        {
                            JOptionPane.showInputDialog(this, "Incorrect fields");
                        }
                    }
                    
                }
            }
     
                        
        }
                

    }             
                
                  

    
    private class AddItemButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==addItem){
                try{
                if (item.getText().isEmpty()){
                JOptionPane.showMessageDialog(pnl, "Incomplete Fields");
                }
                else{
                    String new_item = item.getText().trim().replace(" ", "_");
                    item.setText("");
                    int item_quantity = Integer.parseInt(Quantity.getText().trim());
                    Quantity.setText("");
                    Item I = new Item(new_item, item_quantity);
                    
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println(I.getItemName() + " " + I.getItemQuantity());
                        pw.close();
                        bw.close();
                        fw.close();
                    }
                }
                catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(pnl, "Quantity Invalid");
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(pnl, "Something Went Wrong");
                }
                catch (Exception l) {
                    JOptionPane.showMessageDialog(pnl,"Please Close Application\nIf problem persists");
                }
                model.setRowCount(0);
                ilist=loadStock(file);
                showTable(ilist);
                
            }
        }

    }
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            if (a.getSource()==delete){
                int row = table.getSelectedRow();
                String val ="";


               if (createJOptionpane("Are you sure you want to delete this item")==0){
                    for (Item i : ilist) {
                        if (i.getItemName().equals(table.getValueAt(row, 0))){
                            val=i.getItemName();
                            removeRecord(val);
                            ilist.remove(i);
                            model.setRowCount(0);
                            showTable(ilist);
                            break;
                
                        }   
                    }
                }

            }
        }
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
    }
   
   
}


