import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.PrintWriter;

/*  This class handles the generation of the tree, as well as all of the
 *  searching above the level individual lists (although it does adjoin
 *  the resulting answer Linked Lists)
 */
public class DatabaseBST 
{
  private  DBTNode root;
  private ArrayList<String []> carData; // All of the Cars from the inFile
  private CarList answerList;  // A linkedList composed of results
  							   // from multiple LinkedLists
  private PrintWriter outFile; 
  private String searchType;   // user-selected search 
  private String inputData;    
  private String inputData2;
  private int numCars = 0;     // utilized when printing entire DB
  
  public DatabaseBST(ArrayList<String []> input, PrintWriter outFile)
  { 
    carData = input;
    createTheTree();
    this.outFile = outFile;
    answerList = new CarList();
  }
  
//*****************************************************************************
  // Cycles through list of cars, attempting to generate a Node for each
  public void createTheTree()
  {
    for (int i = 0; i < carData.size(); i++)
    {	
      addNodeToTree(carData.get(i)[0]);	   
    }
  }
  
//*****************************************************************************
 
  private void addNodeToTree(String makeInput)
  {
	if (root == null) 
	{
	  root = new DBTNode(makeInput, generateListData(makeInput)); 
	}
		
    addNodeToTree(root, makeInput);
  }
  
//*****************************************************************************
  
  // Finds (as is determined alphabetically) the appropriate location for
  // a new Tree Node, and constructs the Node
  private void addNodeToTree(DBTNode current, String makeInput)
  {
	if (compareMakes(current.make, makeInput) == 0) // make already has Node
	{
	  return; // if duplicate	
	}
    else if (compareMakes(current.make, makeInput) == - 1) // input > current
    {
      if (current.rightChild == null) // generates a new leaf at right child
      {
        current.rightChild = new DBTNode
        		(makeInput, generateListData(makeInput));	
      }
      else
      {
        addNodeToTree(current.rightChild, makeInput);  // continues searching
      }
    }
    else if (compareMakes(current.make, makeInput) == 1)
    {
      if (current.leftChild == null)
      {
        current.leftChild = new DBTNode
        		(makeInput, generateListData(makeInput));	
      }
      else
      {
        addNodeToTree(current.leftChild, makeInput);  
      }
    }
  }
    
  
//*****************************************************************************
  // Compares two Strings to determine which is greater
  private int compareMakes(String st1, String st2)
  {
    for (int i = 0; ((i < st1.length() && i < st2.length())); i++)    
    {  
      if (st1.charAt(i) < st2.charAt(i))
        return -1;
      else if (st1.charAt(i) > st2.charAt(i))
        return 1;
    }
    
    return 0;
  }
  
//*****************************************************************************
  // Collects and returns all cars of given Make, leading to the generation 
  // of a Tree Nodes' LinkedList 
  private ArrayList<String []> generateListData(String make)
  {
    ArrayList<String []> newCarList = new ArrayList<>();
    
    for (int i = 0; i < carData.size(); i++)  
    {
      if (carData.get(i)[0].matches(make))
      {
        newCarList.add(carData.get(i));	  
      }
    }
      
    return newCarList;
  }
  
//****************************************************************************
  //  Presents the user with a simple GUI in which they determine what they
  //  want to search by, and enter the appropriate searching data
  public void searchTheDatabase()
  {
    boolean proceed = true;
    
    do
    {
	  int search = Integer.parseInt(JOptionPane.showInputDialog
			("Select the corresponding number to the search"
			+ "you would like to perform. \nSearch for all cars with "
			+ "specified:"
			+ "\n0: Make"
			+ "\n1: Model"
			+ "\n2: Year"
			+ "\n3: Color"
			+ "\n4: Full License"
			+ "\n5: Partial License"
			+ "\n6: Make and Color"
			+ "\n7: Color and Partial License"
			+ "\n8: Complete Searching!"
			+ "\n9: Print entire DataBase"));
	
	  switch (search)
	  {
	    case 0: 
	    {  
	      searchType = "make";	    		  
	      searchForMake();
	      break;
	    }
	    
	    case 1:
	    case 2:
	    case 3:
	    case 4:
	    case 5:
	    {
	      searchForField(search); // Searches for these data-types are similar
	      break;
	    }
	    case 6:
	    {
	      searchForMake();
	      inputData2 = JOptionPane.showInputDialog
	    		  ("Enter the color to search for: ");
	      //  Searches results from previous search, minimizing redundancy
	      answerList = answerList.searchCars(inputData2, 3); 
	      break;
	    }
	    case 7:
	    {
	      searchForField(3);
	      inputData2 = JOptionPane.showInputDialog
		    		("Enter the partial license plate to search for");
	      //  Also searches results from previous search (color)
	      answerList = answerList.searchCars(inputData2, 5);
	      break;
	    }
	    case 8:
	    {
	      proceed = false;	 //  Stop searching
	      break;
	    }
	    case 9:
	    { 
	      outFile.println("All Cars within the Database:");
	      printDB(root);
	      outFile.println("\nTotal number of cars in DB:" + numCars);
	      break;
	    }
	  }
	  
	  if (search < 8)  
	    processResults(search);
	  
	  answerList = new CarList(); // resets the answers
    } while(proceed);
  }
  
//*****************************************************************************
  
  private void searchForMake()
  {
    inputData = JOptionPane.showInputDialog("Enter the make to search "
    		+ "for: ");
    
    searchForMake(inputData, root);
  }
  
//*****************************************************************************
  
  private void searchForMake(String makeInput, DBTNode current)
  {
    if (current == null)
    {
      return;	
    }
    else if (current.make.matches(makeInput))
    {
      answerList = current.list;	
    }
    else if (compareMakes(current.make, makeInput) == -1)
    {
      searchForMake(makeInput, current.rightChild);
    }
    else if (compareMakes(current.make, makeInput) == 1)
    {
      searchForMake(makeInput, current.leftChild);
    }
  }
  
//*****************************************************************************
  
  private void searchForField(int search)
  {
    String questionData = "";
    switch(search)
    {
      case 1:
      {
        questionData = "model";
        break;
      }
      case 2:
      {
        questionData = "year";
        break;
      }
      case 3:
      {
        questionData = "color";
        break;
      } 
      case 4:
      {
        questionData = "full license plate";
        break;
      }
      case 5:
      {
        questionData = "partial license plate";	 
        break;
      }
    }
    
    searchType = questionData;
    inputData = JOptionPane.showInputDialog
    		("Enter the " + questionData + " to search for:");
    searchForField(inputData, search, root);
  }

//*****************************************************************************
  
  private void searchForField(String data, int field, DBTNode current)
  {
    concatAnswers(current.searchCars(data, field));
    
    if (current.leftChild != null)
    {
      searchForField(data, field, current.leftChild);	
    }
    if (current.rightChild != null)
    {
      searchForField(data, field, current.rightChild);
    }
  }

//*****************************************************************************
  // Adjoins the resulting answers to a growing Linked List of answers from
  // all makes
  private void concatAnswers(CarList tempAnswers)
  {
    if (tempAnswers == null)
    {
      return;	
    }
	if (answerList.head == null)
    {
      answerList = tempAnswers;	
      return;
    }
    else
    {
      CarNode current = answerList.head;
      while (current.next != null)
      {
        current = current.next; 	  
      }
    current.next = tempAnswers.head;
    
    return;
    }
  } 
  
//*****************************************************************************
  // Output to the user-specified output file
  private void processResults(int search)
  {
	if (search < 6)
	{
	  outFile.println("\n" + searchType + " search for: " + inputData);
	}
	else if (search == 6)
	{
      outFile.println("\nMake and color search for: " + inputData + " and " + 
    		  inputData2);
	}
	else if (search == 7)
	{
	  outFile.println("\nColor and partial license search for: " + 
			  inputData + " and " + inputData2);
	}
    outFile.println(answerList.scanTheList());
    if (answerList.head != null)
    {
      outFile.println("\n Count: " + answerList.countList());	
    }
  }
  
//*****************************************************************************
  // Traverses and prints Car Lists of entire tree
  private void printDB(DBTNode current)
  {
    outFile.println(current.list.scanTheList());
    numCars += current.list.countList();
    
    if (current.leftChild != null)
      printDB(current.leftChild);
    if (current.rightChild != null)
      printDB(current.rightChild);
  }
}
