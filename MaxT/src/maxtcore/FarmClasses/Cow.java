/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.FarmClasses;

import maxtcore.MilkClasses.MilkYield;
import java.util.*;
/**
 *
 * @author acer
 */
public class Cow implements Comparable<Cow>{
    
    private static int id = 99;
    private final String ident;
    private MilkYield milkYield;
    private boolean hasValidMilkYield;
    
    /**
     *
     * @param aFarm
     * @param aHerd
     */
    public Cow(Farm aFarm, Herd aHerd)
    {
        id++;
        ident = aFarm.getFarmId()+"-"+aHerd.getHerdId()+"-"+Integer.toString(id);
        milkYield = new MilkYield();
        hasValidMilkYield = false;
    }
    
    public Cow()
    {
        ident = null;
    }
    
    /**
     *
     * @return
     */
    public String getCowId()
    {
        return ident;
    }
    
    /**
     *  Returns the MilkYield object associated with this Cow object
     * @return
     */
    public MilkYield getMilkYield()
    {
        return this.milkYield;
    }
    
    /**
     * Returns the state of hasValidMilkYield
     * @return 
     */
    public boolean hasValidMilkYield()
    {
        return this.hasValidMilkYield;
    }
    
    /**
     * "Deletes" the current milkYield and instantiates a null copy.
     */
    public void deleteMilkYield()
    {
        this.milkYield = new MilkYield();
        setValidMilkYield(false);
    }
    
    
    public void setValidMilkYield(boolean b) {
        this.hasValidMilkYield = b;
    }
    
    public void addCowToHerd(Herd aHerd)
    {
        aHerd.addCow(this);
    }
    
    @Override
    /**
     * Overrides the equals() method and checks the "ident" variable for a match
     */
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Cow cow = (Cow) obj ;
        return this.ident.equals(cow.getCowId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ident);
        return hash;
    }

    @Override
    public int compareTo(Cow o) {
         // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int last = this.ident.length() - o.ident.length();
        return last == 0 ? this.ident.compareTo(o.ident) : last;
    }
    
    @Override
    public String toString()
    {
        if (this.hasValidMilkYield())
        {
            return this.getCowId().concat(" *");
        }
        return this.getCowId();
    }
}
