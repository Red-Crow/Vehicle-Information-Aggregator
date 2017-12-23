public class Car 
{  
  private String make;	
  private String model;
  private String year;
  private String color;
  private String rawLicense;
  private String equivLicense;
  
  public Car(String [] data)
  {
	this.make = data[0];
    this.model = data[1];
    this.year = data[2];
    this.color = data[3];
    this.rawLicense = data[4];
    this.equivLicense = equateLicense(rawLicense);
  }
  
  public Car() // Used in cloning
  { 
  }
  
//*****************************************************************************
  // Converts input of any license information into an normalized form
  private String equateLicense(String license)
  {
	StringBuilder eqLicense = new StringBuilder();
    
	for(int i = 0; i < license.length(); i++)
    {
      if (Character.isDigit(license.charAt(i)) 
    		  || Character.isLetter(license.charAt(i)))
      {
        eqLicense.append(Character.toUpperCase(license.charAt(i)));	  
      } 
    }   
    return eqLicense.toString();
  }
  
//*****************************************************************************  
  // returns a copy of car's data
  public String printCar()
  {
    return ("\n" + make + ", " + model + ", " + year + ", " + color 
    		+ ", " + rawLicense);	  
  }
  
//*****************************************************************************
  // creates and returns a copy of itself (much of this method is for the 
  // sake of clarity rather than effeciency)
  public Car getCar()
  {
    Car clone = new Car();
    clone.make = make;
    clone.model = model;
    clone.year = year;
    clone.color = color;
    clone.rawLicense = rawLicense;
    clone.equivLicense = equivLicense;
    return clone;
  }
  
//*****************************************************************************
  // Compare search input data fields
  public boolean compareInput(String data, int value)
  {
    switch (value)
    {
      case 1:
      {
        if (data.matches(model))
        {
          return true;
          
        }
        else
        {
          return false;
        }
      }
      case 2:
      {
        if (data.matches(year))
        {
          return true;	
        }
        else
        {
          return false;	
        }
      }
      case 3:
      {
        if (data.matches(color))
        {
          return true;  	
        }
        else
        {
          return false;	
        }
      }
      case 4:
      {
        if (equateLicense(data).matches(equivLicense))
        {
          return true;	
        }
        else
        {
          return false; 	
        }
      }
      case 5:
      {
        if (equivLicense.contains(equateLicense(data))) 
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
  
    return false;
  }

}
