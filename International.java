/**
This class contains information about international students
and if they are study abroad or not. Contains updated tuition amount.
@author Stephen Juan, David Halim
*/

public class International extends NonResident
{
    private boolean studyAbroad; 
    
    /**
    This is a constructor for the international students that is the same as NonResident except it included 
    whether or not the student is in the study abroad program or not. 
    @param profile         - profile of the student (from Student)
    @param creditHours     - number of credits that the student is taking (from Student)
    @param lastPayment     - the last payment that the student made (from NonResident)
    @param lastPaymentDate - the date of the last payment (from NonResident)
    @param studyAbroad     - if the student is in the study abroad program or not
    */
    public International(Profile profile, int creditHours, boolean studyAbroad)
    {
        super(profile, creditHours);
        this.studyAbroad = studyAbroad; 
    }
    
    /**
    This method overrides the isValidCreditHours from Student and includes extra restrictions for study abroad
    students. If they are study abroad, they are only allowed to take a maximum of 12 credits. 
    @return true, if it is study abroad and the credits are 3-12, false if otherwise
            true, if it is not study abroad and the credits are 3-24, false if otherwise
    */
    @Override
    public boolean isValidCreditHours()
    {
       if (studyAbroad)
       {
           if (getCreditHours() >= 3 && getCreditHours() <= 12)
               return true;
           else
               return false;
       }
       else
       {
           if (getCreditHours() >= 3 && getCreditHours() <= 24)
               return true;
           else
               return false; 
       }
    }
    
    /**
    This method gets the tuition for international students.
    @return the total cost of tuition for international students
    */
    @Override
    public float getTotalCost()
    {
    	float internationalTuition = 29737;
        float additionalFee = 2650;
        float totalAmount = 0; 
        
        if (studyAbroad)
            totalAmount = universityFee + additionalFee;
        else
        {
            if (getCreditHours() >= minFulltime) // fulltime students
            {
                totalAmount += internationalTuition; 
                if (this.getCreditHours() - fulltimeThreshold > 0) // extra credit over 16 fee 
                {
                    totalAmount += (nonResidentCreditHour * (getCreditHours() - fulltimeThreshold)); 
                }
                totalAmount += universityFee; // university fee
            }
            else
            {
                totalAmount += (this.getCreditHours() * nonResidentCreditHour); // cost of tuition
                totalAmount += (0.8 * universityFee);
            }
        }
        return totalAmount;
    }
    
    /**
    This method calculates the tuition due by subtracting the total cost of tuition minus the 
    money the student has already paid. 
    */
    @Override
    public void tuitionDue()
    {
        float tuitionDue = getTotalCost() - getTotalPayment();
        setTuitionDue(tuitionDue);
    }
    
    /**
    This method returns a string representation of the student in the format
    "name:major:credit hours:tuition due: last payment:payment date:non-resident (tri-state):state".
    @return 
    */
    @Override
    public String toString()
    {
        String finalString = super.toString() + ":international";
        if (studyAbroad)
            return finalString + ":study abroad";
        else 
            return finalString; 
    }
}