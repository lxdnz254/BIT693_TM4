/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

import java.util.*;
/**
 *
 * @author acer
 */
public class Cow implements Comparable<Cow>{
    
    private static int id = 99;
    private String ident;
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
    
    /**
     *
     * @return
     */
    public String getCowId()
    {
        return ident;
    }
    
    /**
     *
     * @return
     */
    public MilkYield getMilkYield()
    {
        return milkYield;
    }
    
    /**
     * 
     * @return 
     */
    public boolean hasValidMilkYield()
    {
        return hasValidMilkYield;
    }
    
    
    void setValidMilkYield(boolean b) {
        hasValidMilkYield = b;
    }
    
    void addCowToHerd(Herd aHerd)
    {
        aHerd.addCow(this);
    }
    
    @Override
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
        return this.getCowId();
    }
}
