import java.util.ArrayList;

public class CarList 
{
  CarNode head;
  private ArrayList<String []> data;  // Contains Cars for given Make
  
  public CarList(ArrayList<String []> data)
  {
    this.data = data;
    generateTheList();
  }
  
//*****************************************************************************
  // Used in initializing/reseting AnswerList
  public CarList()
  {
  }
  
//*****************************************************************************  
  // Returns cars of this List
  public String scanTheList()
  { 
    String outPut = "";	  
    if (head == null)
    {
      return "\nNot Found";
    }
    else
    {
      CarNode current = head;
      
      while (current != null)
      {
        outPut += current.printCar();
        current = current.next;
      }
      
      return outPut;
    }
  }
  
//*****************************************************************************
  
  private void generateTheList()
  {
	int numCars = data.size();
	
    head = new CarNode(data.get(0));
    CarNode current = head;
    
    for (int i = 1; i < numCars; i++)
    {
      current.next = new CarNode(data.get(i));
      current = current.next;
    }
    
  }
  
//*****************************************************************************
  
  public void addNode(Car carData)
  {
    if (head == null)
    {
      head = new CarNode(carData);
    }
    else
    {
      CarNode current = head;
      while (current.next != null)
      {
        current = current.next;
      }
      
      current.next = new CarNode(carData);
    }
  }
   
//*****************************************************************************  
  //  Traverses list, generating a new CarList of cars that match the search
  public CarList searchCars(String data, int field)
  {
    CarList answers = new CarList();
    CarNode current = head;
    
    while (current != null)
    {
      if (current.compare(data, field))
      {
        answers.addNode(current.getCar());
      }
      current = current.next;
    }
    
    return answers;
  }
  
//*****************************************************************************  
  
  public int countList()
  {
    CarNode current = head;
    int count = 0;
    while (current != null)
    {
      current = current.next;
      count++;
    }
    
    return count;  
  } 
}
