package students;

/**
This class is the client driver class that takes in user-inputted commands and information
and does functions with them using the Roster class
@author Stephen Juan, David Halim
*/

import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManager
{
    private int numberOfTokens;
    
    /**
    This method creates a new Roster and repeatedly takes input with a while loop via Java Scanner and
    uses methods from other classes to add, delete, lend or return them. It also checks to see that the 
    input is in the correct format. 
    */
    public void run()
    {
        System.out.println("Tuition Manager starts running.");
        
        Roster roster = new Roster();
        Scanner keyboard = new Scanner(System.in);
        
        // loop that repeatly takes in inputs until Q is entered as a command
        while (keyboard.hasNext())
        {
            String userInput = keyboard.nextLine();
            String[] command = tokenizer(userInput);
            
            if (command.length == 0)
            {
                System.out.println();
                continue;
            }
            
            try
            {
            	missingDataSwitch(command, roster);
            	switchCommand(command, roster);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
            	//e.printStackTrace();
            	System.out.println("Missing data in command line.");
            }
            catch (NumberFormatException e)
            {
            	//e.printStackTrace();
            	System.out.println("Invalid credit hours.");
            }
            catch (Exception e)
            {
            	//e.printStackTrace();
            	System.out.println(e.getMessage());
            }
        }
    }
    
    
    /**
    This method splits up the user input as a string into separate tokens that represents command, title,
    artist, genre and date or title and artist. If there is only three tokens, it fill the rest of the array
    with empty string "". It returns null for invalid input format and an array of strings of the tokens.
    @param input - string that is to be split up using StringTokenizer
    @return null if it is in improper format, an array of five strings if otherwise
    */
    public String[] tokenizer(String input)
    {
        StringTokenizer inputString = new StringTokenizer(input,",");
        
        numberOfTokens = inputString.countTokens(); 
        
        String[] arrayOfTokens = new String[numberOfTokens];
        
        // fills in applicable values and counter the number of tokens
        for (int i = 0; i < numberOfTokens; i++) 
        {
            arrayOfTokens[i] = inputString.nextToken(); 
        }
        
        return arrayOfTokens;
    }

    /**
    This method utilizes a command that is identified in the user input string and performs the 
    functions that the user requests. It prints out whether or not the requested action was able
    to be performed. It terminates the entire program once the 'Q' is entered
    @param command    - the command the user inputs
    @param roster - the roster the user wants to modify
    */
    public void switchCommand(String[] command, Roster roster) throws Exception
    {
    	if (command[0].equals("AR"))
        {
            Resident student = new Resident(new Profile(command[1],command[2]),
                                                 Integer.parseInt(command[3]));
        	if (roster.add(student)) 
                System.out.println("Student added."); 
            else
                System.out.println("Student is already in the roster.");
        }
        
        else if (command[0].equals("AN"))
        {
        	NonResident student = new NonResident(new Profile(command[1],command[2]),
        	                                           Integer.parseInt(command[3]));
        	if (roster.add(student))
                System.out.println("Student added."); 
            else
                System.out.println("Student is already in the roster.");
        }
        
        else if (command[0].equals("AT"))
        {
        	Tristate student = new Tristate(new Profile(command[1],command[2]),
        	                         Integer.parseInt(command[3]), command[4]);
        	if (roster.add(student)) 
                System.out.println("Student added.");
            else
                System.out.println("Student is already in the roster.");
        }
        
        else if (command[0].equals("AI"))
        {
        	International student = new International(new Profile(command[1],command[2]),
        	             Integer.parseInt(command[3]), Boolean.parseBoolean(command[4]));
        	if (roster.add(student)) 
                System.out.println("Student added.");
            else
                System.out.println("Student is already in the roster.");
        }

        else if (command[0].equals("R"))
        {
        	Student student = new Student(new Profile(command[1],command[2]));
        	if (roster.remove(student))
                System.out.println("Student removed from the roster."); 
            else 
                System.out.println("Student is not in the roster."); 
        }
        
        else if (command[0].equals("C"))
        {
        	roster.calculateTuition();
        	System.out.println("Calculation completed.");
        }
        
        else if (command[0].equals("T"))
        {
        	Student student = new Student(new Profile(command[1],command[2]));
        	float payment = Float.parseFloat(command[3]);
        	if (payment <= 0)
        	    throw new Exception("Invalid amount.");
        	Date paymentDate = new Date(command[4]);

            roster.payTuition(student, paymentDate, payment);
            System.out.println("Payment applied.");
            
        }
        
        else if (command[0].equals("S"))
        {
        	Student student = new Student(new Profile(command[1],command[2]));
        	if (roster.setStudyAbroad(student, Boolean.parseBoolean(command[3])))
                System.out.println("Tuition updated."); 
            else 
                System.out.println("Couldn't find the international student."); 
            
        }
        
        else if (command[0].equals("F"))
        {
        	Student student = new Student(new Profile(command[1],command[2]));
        	if (roster.setFinancialAid(student, Float.parseFloat(command[3])))
                System.out.println("Tuition updated."); 
            else 
                System.out.println("Student not in the roster.");
        }
        
        else if (command[0].equals("P"))
        	roster.print();
        
        else if (command[0].equals("PN"))
        	roster.printByStudentNames();
        
        else if (command[0].equals("PT"))
        	roster.printByPaymentDate();
        
        else if (command[0].equals("Q"))
        {
            System.out.println("Tuition Manager terminated.");
            System.exit(0);
        }

        else
            System.out.println("Command '" + command[0] + "' not supported!"); 
    }
    
    /**
    An Exception thrower that checks for missing input.
    @param command    - the command the user inputs
    @param roster - the roster the user wants to modify
    */
    public void missingDataSwitch(String[] command, Roster roster) throws Exception
    {
        if ((command[0].equals("AR") || command[0].equals("AN")
             || command[0].equals("AI")) && command.length == 3)
        	throw new Exception("Credit hours missing.");
        if (command[0].equals("T") && command.length == 3)
           	throw new Exception("Payment amount missing.");
        if (command[0].equals("F") && command.length == 3)
           	throw new Exception("Missing the amount.");
    }
}
