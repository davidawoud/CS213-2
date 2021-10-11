package unitTesting;
import static org.junit.Assert.*;
import students.Profile;
import students.Student;
import students.Resident;
import students.NonResident;
import students.Tristate;
import students.International;
import students.Roster;

/**
This class tests the add and remove methods in the roster class.
@author Stephen Juan, David Halim
*/
public class RosterTest 
{
    /**
    This method tests the add method of the roster. 
    @throws Exception if any constructor throws an Exception.
    */
    @org.junit.Test
    public void addTest() throws Exception
    {
        Student student1 = new Student(new Profile("Student Test1", "CS"), 12);
        Student duplicate_student1 = new Student(new Profile("Student Test1", "CS"), 12);
        Resident student2 = new Resident(new Profile("Student Test2", "EE"), 12);
        NonResident student3 = new NonResident(new Profile("Student Test3", "EE"), 9);
        Tristate student4 = new Tristate(new Profile("Student Test4", "EE"), 20, "NY");
        Tristate duplicate_student4 = new Tristate(new Profile("Student Test4", "EE"), 20, "NY");
        Tristate student5 = new Tristate(new Profile("Student Test5", "ME"), 19, "ct");
        International student6 = new International(new Profile("Student Test6", "BA"), 13, false);
        International duplicate_student6 = new International(new Profile("Student Test6", "BA"), 13, false);
        International student7 = new International(new Profile("Student Test7", "BA"), 13, true);
        
        Roster roster = new Roster();
        
        assertTrue(roster.add(student1));
        assertFalse(roster.add(duplicate_student1)); // cannot add duplicate
        assertTrue(roster.add(student2));
        assertTrue(roster.add(student3));
        assertTrue(roster.add(student4));
        assertFalse(roster.add(duplicate_student4)); // cannot add duplicate
        assertTrue(roster.add(student5));
        assertTrue(roster.add(student6));
        assertFalse(roster.add(duplicate_student6)); // cannot add duplicate
        assertTrue(roster.add(student7));
    }
    
    /**
    This method tests the remove class of roster.
    @throws Exception if any constructor throws an Exception.
    */
    @org.junit.Test
    public void removeTest() throws Exception
    {
    	Student student1 = new Student(new Profile("Student Test1", "CS"), 12);
        Resident student2 = new Resident(new Profile("Student Test2", "EE"), 12);
        NonResident student3 = new NonResident(new Profile("Student Test3", "EE"), 9);
        Tristate student4 = new Tristate(new Profile("Student Test4", "EE"), 20, "NY");
        Tristate student5 = new Tristate(new Profile("Student Test5", "ME"), 19, "ct");
        International student6 = new International(new Profile("Student Test6", "BA"), 13, false);
        International student7 = new International(new Profile("Student Test7", "BA"), 13, true);
        
        Roster roster = new Roster();
        
        assertFalse(roster.remove(student1));
        
        roster.add(student1);
        roster.add(student2);
        roster.add(student3);
        roster.add(student4);
        roster.add(student5);
        
        assertTrue(roster.remove(student1));
        assertTrue(roster.remove(student2));
        assertTrue(roster.remove(student3));
        assertTrue(roster.remove(student4));
        assertFalse(roster.remove(student1)); // only student 5 left in the lsit
        
        roster.add(student6);
        roster.add(student7); // 5,6,7 left
        
        assertTrue(roster.remove(student6));
        assertFalse(roster.remove(student6)); // 5, 7 left
        assertTrue(roster.remove(student5));
        assertTrue(roster.remove(student7)); 
        
    }
}
