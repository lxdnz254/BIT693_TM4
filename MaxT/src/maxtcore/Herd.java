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
public class Herd implements Comparable<Herd>{
    
    private static int id = 0;
    private String ident;
    private String name;
    private MilkInterval milkingInterval;
    private Collection<Cow> cows;
    
    public Herd(String aName, MilkInterval interval)
    {
        id++;
        ident = "0" + Integer.toString(id);
        name = aName;
        milkingInterval = interval;
        cows = new HashSet();
    }
    
    public String getHerdId()
    {
        return ident;
    }
    
    public String getHerdName()
    {
        return name;
    }
    
    public MilkInterval getHerdInterval()
    {
        return milkingInterval;
    }
    
    public Collection<Cow> getCows()
    {
        return cows;
    }
    
    void addCow(Cow aCow)
    {
        cows.add(aCow);
    }
    
    public void addHerdToFarm(Farm aFarm) {
        aFarm.addHerd(this);
    }
    
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Herd herd = (Herd) obj ;
        return this.ident.equals(herd.getHerdId());
    }

    @Override
    public int compareTo(Herd o) {
         // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int last = this.ident.length() - o.ident.length();
        return last == 0 ? this.ident.compareTo(o.ident) : last;
    }
}
