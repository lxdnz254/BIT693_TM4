/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.MilkClasses;

/**
 * Class to store the milk yields associated with a Cow object
 * @author acer
 */
public class MilkYield {
    
    private int amYield;
    private int pmYield;
    
    /**
     * Initial constructor,sets yields to 0
     */
    public MilkYield()
    {
        amYield = 0;
        pmYield = 0;
    }
    
    /**
     * Constructor for a MilkYield object that initializes values
     * @param am the morning milk yield value
     * @param pm the evening milk yield value.
     */
    public MilkYield(int am, int pm)
    {
        amYield = am;
        pmYield = pm;
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
    }    
}
