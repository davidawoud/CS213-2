import java.text.DecimalFormat;

/**
This class is the superclass Student and contains the profile and
the credit hours that the student is taking. It is extended by 
NonResident and Resident. 
@author Stephen Juan, David Halim
*/
public class Student 
{
    private Profile profile;
    private int creditHours; 
    
    public static final int universityFee = 3268;
    public static final int minFulltime = 12;
    public static final int fulltimeThreshold = 16; 
    
    private String lastPaymentDate = "--/--/--";
    private float totalPayment = 0; 
    private float tuitionDue; 
    
    // WE WILL NEED TO CHECK FOR SOMETHING WHEN PAYMENT > COST OF TUITION
    
    /**
    This is a default constructor that sets the a default profile
    with name 'Default Name' and a default major of CS and zero credit hours.
    It calls the overloaded constructor to pass on the values.  
    */
    public Student()
    {
        this(new Profile("Default Name","CS"), 0);
    }
    
    /**
    This is an overloaded constructor that initializes the profile and 
    the number of credit the student is taking. 
    @param profile     - profile (including name and major) of the student
    @param creditHours - number of credits the student is taking 
    */
    public Student(Profile profile, int creditHours)
    {
        this.profile = profile;
        this.creditHours = creditHours;
    }
    
    /**
    This method is a method that is called when anything is being paid. 
    It adds the total payment to the last payment amount and saves the last payment date. 
    @param input   - the date object 
    @param payment - last payment amount
    */
    public void setLastPaymentDateAmount(Date input, float payment)
    {
        lastPaymentDate = input.toString();
        totalPayment += payment;
    }
    
    /**
    This method returns the total payment that the student has made.
    @return total payment the student made
    */
    public float getTotalPayment() // use this method in the sub classes
    {
        return totalPayment; 
    }
    
    /**
    This method sets the remaining tuition due to the parameter that it takes in. 
    It is called once in every one of the subclasses. 
    @param amount - amount of money that is due
    */
    public void setTuitionDue(float amount)
    {
        tuitionDue = amount;
    }
    
    /**
    This is do-nothing method that is to be overloaded in the classes that
    extend the student class. 
     * @return 
    */
    
    public void tuitionDue() // <- total cost of tuition minus total payment
    {
    }
    
    /**
    This method returns the number of credits the student is taking. 
    @return number of credits the student is taking
    */
    public int getCreditHours()
    {
        return creditHours; 
    }
    
    /**
    This method returns the profile of the student. It's used in the compare
    method of Profile.
    @return profile of the student. 
    */
    public Profile getProfile()
    {
        return profile;
    }
    
    /**
    Checks the validity of credit hours (between 3 and 24 inclusive). 
    @return true if the credit hours is 3-24, false if otherwise
    */
    public boolean isValidCreditHours()
    {
        if (creditHours >= 3 && creditHours <= 24)
            return true;
        else
            return false; 
    }
    
    /**
    This method returns a string representation of the student in the format
    "name:major:".
    @return string representation of a student as "name:major:credit hours"
    */
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        return profile.toString() + ":" + getCreditHours() + " credit hours:tuition due:" + df.format(tuitionDue) + 
               ":totalPayment:" + df.format(totalPayment) + ":last payment date:" + lastPaymentDate;
    }
}