import java.util.ArrayList;

public class DBTNode 
{
  CarList list; // Cannot be private, as Tree Class needs access to adjoin
  				// Answer Linked List
  DBTNode leftChild;
  DBTNode rightChild;
  String make;
  
  public DBTNode(String make, ArrayList<String []> data)
  {
    this.make = make;
    list = new CarList(data);
  }
  
//*****************************************************************************
  // Simply returns the results gathered from the CarList class
  public CarList searchCars(String data, int field)
  {
    CarList temp = list.searchCars(data, field);	  
    return temp;
  }
  
//*****************************************************************************
  
  public int getListSize()
  {
    return list.countList();  
  }
}
