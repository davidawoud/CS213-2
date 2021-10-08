import static org.junit.Assert.*;

public class InternationalTest 
{
    @org.junit.Test
    public void internationalTest1() throws Exception
    {
        International student1 = new International(new Profile("Student Test1", "CS"), 12);
        student1.tuitionDue();
        assertTrue(student1.getTuitionDue() == 35655);
        // $35655
        
    }
    
    // study abroad, no change credits
    @org.junit.Test
    public void internationalTest2() throws Exception
    {
        International student2 = new International(new Profile("Student Test2", "CS"), 12);
        student2.setStudyAbroad();
        student2.tuitionDue();
        assertTrue(student2.getTuitionDue() == 5918);
        // $5918
    }
    
    @org.junit.Test
    public void internationalTest3() throws Exception
    {
        International student3 = new International(new Profile("Student Test3", "CS"), 16);
        student3.tuitionDue();
        assertTrue(student3.getTuitionDue() == 35655);
        // $35655
    }
    
    @org.junit.Test
    public void internationalTest4() throws Exception
    {
        International student4 = new International(new Profile("Student Test4", "CS"), 20);
        student4.tuitionDue();
        assertTrue(student4.getTuitionDue() == 39519);
        // 39519
    }
    
    // set study abroad, change credits
    @org.junit.Test
    public void internationalTest5() throws Exception
    {
        International student5 = new International(new Profile("Student Test5", "CS"), 16);
        student5.setStudyAbroad();
        student5.tuitionDue();
        assertTrue(student5.getTuitionDue() == 5918);
        // $5918
    }
}