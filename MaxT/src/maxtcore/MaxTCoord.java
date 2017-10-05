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
public class MaxTCoord {
    
    private Collection<Farm> farms;
    
    public MaxTCoord()
    {
        farms = new HashSet();
    }
    
    public Collection<Farm> getFarms()
    {
        return farms;
    }
    
    public Collection<Herd> getHerds(Farm aFarm)
    {
        return aFarm.getHerds();
    }
 
    public Collection<Cow> getCows(Herd aHerd)
    {
        return aHerd.getCows();
    }
    
    public boolean addFarm(String name, String location)
    {
        try
        {
            Farm farm = new Farm(name, location);
            farms.add(farm);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
            return false;
        }
        
    }
    
    public boolean addHerd(String name, MilkInterval interval, Farm aFarm)
    {
        try
        {
            Herd herd = new Herd(name, interval);
            aFarm.addHerd(herd);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
            return false;
        }
       
    }
    
    public boolean addCow(Herd aHerd, Farm aFarm)
    {
        try
        {
            Cow cow = new Cow(aFarm, aHerd);
            aHerd.addCow(cow);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
            return false;
        }
        
    }
}
