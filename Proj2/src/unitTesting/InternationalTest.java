package unitTesting;
import static org.junit.Assert.*;
import students.Profile;
import students.International;

/**
This method tests the tuitionDue class. It calls it and checks the total tuition
due to see if it matches up with the expected cost. 
@author Stephen Juan, David Halim
*/

public class InternationalTest 
{
    /**
    Tests a student with 12 credits, cost is $35655
    */
    @org.junit.Test
    public void internationalTest1() throws Exception
    {
        International student1 = new International(new Profile("Student Test1", "CS"), 12, false);
        student1.tuitionDue();
        assertTrue(student1.getTuitionDue() == 35655);
        // $35655
        
    }
    
    /**
    Tests a student with 12 credits, with study abroad, cost is $5918
    */
    // study abroad, no change credits
    @org.junit.Test
    public void internationalTest2() throws Exception
    {
        International student2 = new International(new Profile("Student Test2", "CS"), 12, true);
        student2.tuitionDue();
        assertTrue(student2.getTuitionDue() == 5918);
        // $5918
    }
    
    /**
    Tests a student with 16 credits, cost is $35655
    */
    @org.junit.Test
    public void internationalTest3() throws Exception
    {
        International student3 = new International(new Profile("Student Test3", "CS"), 16, false);
        student3.tuitionDue();
        assertTrue(student3.getTuitionDue() == 35655);
        // $35655
    }
    
    /**
    Tests a student with 20 credits, which requires extra money credit hours, cost is $39519
    */
    @org.junit.Test
    public void internationalTest4() throws Exception
    {
        International student4 = new International(new Profile("Student Test4", "CS"), 20, false);
        student4.tuitionDue();
        assertTrue(student4.getTuitionDue() == 39519);
        // 39519
    }
    
    /**
    Tests a student with 16 credits, with study abroad which changes credits to 12, cost is $5918
    */
    // set study abroad, change credits
    @org.junit.Test
    public void internationalTest5() throws Exception
    {
        International student5 = new International(new Profile("Student Test5", "CS"), 16, false);
        student5.setStudyAbroad();
        student5.tuitionDue();
        assertTrue(student5.getTuitionDue() == 5918);
        // $5918
    }
}