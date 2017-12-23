public class CarNode
{
  private Car car; 
  CarNode next;

  
  public CarNode(String [] data)
  {
    this.car = new Car(data); 
  }
  
//*****************************************************************************  
  
  public CarNode(Car car)
  {
    this.car = car;
  }
  
//*****************************************************************************
  
  public Car getCar()
  {
    return car.getCar();	  
  }
  
//*****************************************************************************
  
  public String printCar()
  {
    return car.printCar();	  
  }
  
//*****************************************************************************
  
  public boolean compare(String data, int value)
  {
    return car.compareInput(data, value);	  
  }
}
