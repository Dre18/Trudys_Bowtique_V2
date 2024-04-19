// package APP.NotificationsandEvents;
//  public static void main(String[] args) {

//      StockAlert sAlert = new StockAlert();

// //     Notification
// // it needs to check if stock levels are low.
// // if stock levels are low then it prints out a message 
// // will have to use flags/interrups to prevent unending loop


// // Notification class
// // contains events based on salert
//  }
//      System.out.println(Game.running);
//      do  {
//              Notification.printStockAlert();
//      }while(StockAlert.status());


 
//  public class StockAlert {
    
//      int critlvl;
//      int intStock;
//      List<Stock> restock;
//      List<Stock> Slist;
//   /*  public class stockAlert2(critlvl,cur_level){
//         if(critlvl < cur_level){
//             System.out.println("Quantiy is low. Restock soon")
//         }
//         else{
//             System.out.println("Quanity is above critical level")
//         } */
//       }

//      //Constructor
//      public StockAlert(int crtlvl,int intStock, List<Stock> Slist){
//         this.critlvl=0.50 * intStock; // temporary until the intial stock is found
//         this.intStock= intStock;
//         this.Slist= Slist;

// //      //empty for now
//      }

//      void check_stock(){
//          for (Stock p: Slist){
//              if (p.val <= critlvl)
//                  restock.add(p);
//          }
//      }

//      void update_restock(){
//          for (Stock r: restock){
//              if (r.val > critlvl)
//              restock.remove(r);
//          }
//      }
//      void Alert_message(){
//          if (restock.length()>0){
 
//              for (Stock r: restock){
//                  str= r.name + " quantity is low. Restock soon";
//                  System.out.println(str);
//              }
//          }
//      }
 
