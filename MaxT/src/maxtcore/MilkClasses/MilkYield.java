/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.MilkClasses;
import maxtcore.FarmClasses.Cow;

/**
 * Class to store the milk yields associated with a Cow object
 * @author acer
 */
public class MilkYield {
    
    private int amYield;
    private int pmYield;
    private Cow aCow;
    
    /**
     * Constructor not associated with a Cow object that sets the yield values to zero.
     */
    public MilkYield()
    {
        amYield = 0;
        pmYield = 0;
    }
    
    /**
     * Initial constructor,sets yields to 0
     * @param theCow the Cow object the milkYield is associated with
     */
    public MilkYield(Cow theCow)
    {
        amYield = 0;
        pmYield = 0;
        aCow = theCow;
        aCow.setValidMilkYield(false);
    }
    
    /**
     * Constructor to create a MilkYield object with values not associated with
     * a Cow object
     * @param am the morning milk value
     * @param pm the evening milk value
     */
    public MilkYield(int am, int pm)
    {
        amYield = am;
        pmYield = pm;
    }
    
    /**
     * Constructor for a MilkYield object that initializes values
     * @param am the morning milk yield value
     * @param pm the evening milk yield value.
     * @param theCow the Cow Object the MilkYield is associated with.
     */
    public MilkYield(int am, int pm, Cow theCow)
    {
        amYield = am;
        pmYield = pm;
        aCow = theCow;
        if (am + pm < 1) 
        {
            aCow.setValidMilkYield(false);
        }
        else 
        {
            aCow.setValidMilkYield(true);
        }
    }
    
    /**
     * Returns the morning milk yield
     * @return an integer representing the morning milk yield.
     */
    public int getAmYield() {
        return amYield;
    }
    
    /**
     * Returns the evening milk yield
     * @return an integer representing the evening milk yield.
     */
    public int getPmYield() {
        return pmYield;
    }
    
    /**
     * Sets the milk yields for a MilkYield object
     * @param am the morning milk yield.
     * @param pm the evening milk yield.
     */
    public void setYield(int am, int pm)
    {
        amYield = am;
        pmYield = pm;
        if (am + pm < 1){aCow.setValidMilkYield(false);}
        else {aCow.setValidMilkYield(true);}
    }    
}
