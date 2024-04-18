package APP.OrderManagement;
import APP.StockManagement.*;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
public class OrdItem{
        private String name;
        private String[] status = {"Incomplete", "Completed"};
        private String addr;
        private String ordDescrip;
        private String t_Descrip;
        private String cost;
        private static int count;
        private int ordnum;
        private String phonenum;
       private Date date;
       private String deadline;
        private String status_2;
        private String stat;
        
        

        public OrdItem(int ordnum, String name, String addr, String date, String status_2, String ordDescrip, String phonenum, String cost) {
            this.ordnum=ordnum;
            this.name = name;
            if (status_2.equals(status[0]) ||status_2.equals(status[1]) ){
                this.status_2 = status_2;
            }
            this.deadline= date;
            this.addr=addr;
            this.ordDescrip=ordDescrip;
            this.phonenum=phonenum;
            this.cost=cost;
    
        }
        public OrdItem(String name, String deadline, String addr, String ordDescrip, String phonenum, String cost) {
            
    
            this.date= new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.deadline= deadline;
            this.ordnum=1 + calcOrdernum("OrderList.csv");
            this.name = name;
            this.addr=addr;
            this.ordDescrip=ordDescrip;
            this.phonenum=phonenum;
            this.cost=cost;
            this.status_2=status[0];
            
        

    
        }


        public void setStatus(String stat) {
            
            this.status_2 = stat;
        }


        public String getDescrp()
        {
            return t_Descrip;
        }

        


        /** 
         * @param pfile
         * @return int
         */
        public int calcOrdernum(String pfile){
            java.util.Scanner pscan = null; 
            int i =0;
            try {
                pscan = new java.util.Scanner(new File(pfile));
                while (pscan.hasNext()) {
                    String[] nextLine = pscan.nextLine().split(" ");
                    int num = Integer.parseInt(nextLine[0]);
                    if (num>i){
                        i= num;
                    }
                }
                pscan.close();
             } catch (IOException e) {
                }
            
            return i;
        }
        public OrdItem(String name2, int stockID) {
        }
    
        
        /** 
         * @return String
         */
        public String getName() {
            return name;
        }

        
        /** 
         * @return String
         */
        public String getAddr() {
            return addr;
        }

        
        /** 
         * @return String
         */
        public String getOrdDescrip() {
            return ordDescrip;
        }

        
        /** 
         * @return String
         */
        public String getPhonenum() {
            return this.phonenum;
        }

        
        /** 
         * @return String
         */
        public String getDeadline() {
            return deadline;
        }

        
        /** 
         * @return String
         */
        public String getCost() {
            return cost;
        }
         
         /** 
          * @return int
          */
         public int getOrdnum() {
            return ordnum;
        }
        
        /** 
         * @return String
         */
        public String getStatus_2() {
            return status_2;
        }
     

        
        
    }


