package APP.NotificationsandEvents;
import java.util.*;



public class DLineAlert{
   ArrayList<Integer>values = new ArrayList<>();
   ArrayList<Integer>date = new ArrayList<>();

  // Constructor
public DLineAlert(){
  
}
  
  /** 
   * @return int
   */
  // Return the day value from ArrayList
public int getdate(){
  return values.get(0);
}
  
  /** 
   * @param a
   * @param b
   * @param c
   */
  // Insert the current date value into an Array
public void setDate(int a, int b, int c){
values.add(a);
values.add(b);
values.add(c); 
  
  }

/** 
 * @param ArrayListdate
 * @return String
 */
// This method will compare the dates from the order file and the current date.
 public String CompareDate(ArrayList<Integer>date){
  int sum1=date.get(0);
  int sum2=values.get(0);
  int sum3=sum1-sum2;
  if (sum3 > 2){ 
  return"NO DEADLINE" ;
  }
  else if (sum3<=2){
    return "DEADLINE APPROACHING";
  }
  else{
    return "ERROR";
  }
}
  
  /** 
   * @param a
   * @param b
   * @param c
   */
  // Will  add the date to the ArrayList.
  public void addDate(int a,int b, int c){
    date.add(a);
    date.add(b);
    date.add(c);
  }
  
  /** 
   * @return int
   */
  // Will return the date from Array List.
  public int getdate1(){
  return date.get(0);
}
}
