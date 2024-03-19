package APP.StockManagement;

public class Item{
        private String name;
        private int quantity;
    
        
        public Item(String name, int quantity) {
            
    
            this.name = name;
            this.quantity = quantity;
    
        }
    
        
        /** 
         * @return String
         */
        public String getItemName() {
            return name;
        }
    
        
        /** 
         * @return int
         */
        public int getItemQuantity() {
            return quantity;
        }

        
        /** 
         * @param x
         */
        public void changeQuantity(int x){
                this.quantity -= x;
       
    
        }

        public void setQuantity(int newQuantity) {
            if (newQuantity >= 0) { // Validate non-negative quantity
              quantity = newQuantity;
            } else {
              System.out.println("Error: Quantity cannot be negative. Setting quantity to 0.");
              quantity = 0;
            }
          }
        

        
    }
       
