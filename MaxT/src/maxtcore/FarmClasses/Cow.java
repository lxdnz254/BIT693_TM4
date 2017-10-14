/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.FarmClasses;

import maxtcore.MilkClasses.MilkYield;
import java.util.*;
/**
 * The Cow class for the MaxT system
 * @author acer
 */
public class Cow implements Comparable<Cow>{
    
    private static int id = 99;
    private final String ident;
    private MilkYield milkYield;
    private boolean hasValidMilkYield;
    
    /**
     * Constructor for a Cow object, Uses Farm object and Herd object to build
     * unique identifier
     * @param aFarm The Farm object.
     * @param aHerd The Herd object.
     */
    public Cow(Farm aFarm, Herd aHerd)
    {
        id++;
        ident = aFarm.getFarmId()+"-"+aHerd.getHerdId()+"-"+Integer.toString(id);
        milkYield = new MilkYield(this);
    }
    
    /**
     * A Constructor of an empty Cow object, for comparison testing 
     */
    public Cow()
    {
        ident = null;
    }
    
    /**
     *  Returns the unique identifier as a String object
     * @return String of the indent variable
     */
    public String getCowId()
    {
        return ident;
    }
    
    /**
     *  Returns the MilkYield object associated with this Cow object
     * @return the MilkYield object associated with the Cow object.
     */
    public MilkYield getMilkYield()
    {
        return this.milkYield;
    }
    
    /**
     * Setter for a MilkYield object to be associated with a Cow object
     * @param milkYield A milk yield object
     */
    public void setMilkYield(MilkYield milkYield)
    {
        this.milkYield = milkYield;
    }
    
    /**
     * "Deletes" the current milkYield and instantiates a null copy.
     */
    public void deleteMilkYield()
    {
        this.milkYield = new MilkYield();
        setValidMilkYield(false);
    }
    
    /**
     * Returns the state of hasValidMilkYield
     * @return true || false
     */
    public boolean hasValidMilkYield()
    {
        return this.hasValidMilkYield;
    }
       
    /**
     * Flags the Cow object as having or having not a valid MilkYield object
     * @param b a boolean value either true || false
     */
    public void setValidMilkYield(boolean b) {
        this.hasValidMilkYield = b;
    }
    
    /**
     * Associates the Cow object to a Herd objects Collection of Cow objects
     * @param aHerd the Herd object to associate a Cow object to
     */
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
            // Highlights on the display if a cow has a valid MilkYield object
            return this.getCowId().concat(" *");
        }
        return this.getCowId();
    }
}
