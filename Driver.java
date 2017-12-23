import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver 
{
  static ArrayList<String []> data = new ArrayList<>();
  
  public static void main(String [] args) throws FileNotFoundException
  {  
    collectTheData(); 
    
    PrintWriter outFile = new PrintWriter(JOptionPane.showInputDialog
    		("Please input the name of the output file"));
    DatabaseBST carBST = new DatabaseBST(data, outFile);
    carBST.searchTheDatabase();
    
    outFile.close();
  }
  
  public static void collectTheData() throws FileNotFoundException
  {
    File inFile = new File(JOptionPane.showInputDialog
    		("Please input the name of the input file"));
    
    Scanner input = new Scanner(inFile);
    Scanner line;
    String [] tempInput;
    
    while(input.hasNext())
    {
      tempInput = new String[5];	
      line = new Scanner(input.nextLine()); 
      line.useDelimiter(",\\s");
      
      for (int t = 0; t < 5; t++)
      {
        tempInput[t] = line.next(); 	  
      }
      data.add(tempInput);
    } 
    
    input.close();	
  }
}
