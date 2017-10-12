/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.FarmClasses;

import maxtcore.MilkClasses.MilkInterval;
import java.util.*;

/**
 * The Herd class for the maxT core system
 * @author acer
 */
public class Herd implements Comparable<Herd>{
    
    private static int id = 0;
    private final String ident;
    private String name;
    private MilkInterval milkingInterval;
    private Collection<Cow> cows;
    
    /**
     * Constructor for a Herd object
     * @param aName a String representation of the Herd name
     * @param interval The time interval A Herd is milked 
     */
    public Herd(String aName, MilkInterval interval)
    {
        id++;
        ident = "0" + Integer.toString(id);
        name = aName;
        milkingInterval = interval;
        cows = new HashSet();
    }
    
    /**
     * An empty constructor for comparison testing
     */
    public Herd()
    {
        ident = null;
    }
    
    /**
     * Returns the Herd objects unique identifier
     * @return String of the ident variable.
     */
    public String getHerdId()
    {
        return ident;
    }
    
    /**
     * Returns the Herd objects name
     * @return String of the herd name.
     */
    public String getHerdName()
    {
        return name;
    }
    
    /**
     * Returns the MilkInterval a Herd object has
     * @return the milk interval of the Herd object.
     */
    public MilkInterval getHerdInterval()
    {
        return milkingInterval;
    }
    
    /**
     * Returns the Collection of Cow objects associated with the Herd object.
     * @return a collection of Cow objects
     */
    public Collection<Cow> getCows()
    {
        return cows;
    }
    
    /**
     * Adds a Cow object to the Collection of Cows associated with the Herd object
     * @param aCow The Cow object to be added to the Collection.
     */
    public void addCow(Cow aCow)
    {
        cows.add(aCow);
    }
    
    /**
     * Adds the Herd object to a Farm objects Collection of Herd objects
     * @param aFarm the Farm object to add a Herd object to.
     */
    public void addHerdToFarm(Farm aFarm) {
        aFarm.addHerd(this);
    }
    
    /**
     * Removes a Cow object from the Collection of Cow objects associated with
     * the Herd object
     * @param aCow The Cow object to be removed
     */
    public void deleteCow(Cow aCow)
    {
        cows.remove(aCow);
    }
    
    @Override
    /**
     * Overrides the equals() method from the object class.
     * Compares on the Herd ident variable.
     */
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Herd herd = (Herd) obj ;
        return this.ident.equals(herd.getHerdId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.ident);
        return hash;
    }

    @Override
    public int compareTo(Herd o) {
         // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int last = this.ident.length() - o.ident.length();
        return last == 0 ? this.ident.compareTo(o.ident) : last;
    }
    
    @Override
    public String toString()
    {
        return this.getHerdName();
    }
}
