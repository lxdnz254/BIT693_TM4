/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

/**
 *
 * @author acer
 */
public class MilkTaking {
    
    private int amYield;
    private int pmYield;
    
    /**
     *
     */
    public MilkTaking()
    {
        amYield = 0;
        pmYield = 0;
    }
    
    /**
     *
     * @param am
     * @param pm
     */
    public MilkTaking(int am, int pm)
    {
        amYield = am;
        pmYield = pm;
    }
    
    /**
     *
     * @return
     */
    public int getAmYield() {
        return amYield;
    }
    
    /**
     *
     * @return
     */
    public int getPmYield() {
        return pmYield;
    }
    
    /**
     *
     * @param am
     * @param pm
     */
    public void setYield(int am, int pm)
    {
        amYield = am;
        pmYield = pm;
    }
    
}
