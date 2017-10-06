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
    private MilkInterval milkInterval;
    private ArrayList<String> errors;
    
    /**
     *
     */
    public MaxTCoord()
    {
        errors = new ArrayList<>();
        farms = new HashSet();
        
        /*
        Acceptance test initial data when testing gui builds.
        */
        if (addFarm("NewFarm", "Waikato"))
        {
            System.out.println("Added NewFarm!");
            ArrayList<Farm> arrayFarms = new ArrayList(farms);
            if(addHerd("NewHerd", milkInterval.EIGHT_16, arrayFarms.get(0)))
            {
                System.out.println("Added NewHerd to " + arrayFarms.get(0).getFarmName());
                ArrayList<Herd> arrayHerd = new ArrayList(getHerds(arrayFarms.get(0)));
                if(addCow(arrayHerd.get(0), arrayFarms.get(0)))
                {
                    System.out.println("Added Cow to " + arrayHerd.get(0).getHerdName());
                }
                else
                {
                    System.out.println(GetLastError());
                }
            }
            else
            {
                System.out.println(GetLastError());
            }
        }
        else
        {
            System.out.println(GetLastError());
        }
    }
    
    /**
     *
     * @return
     */
    public Collection<Farm> getFarms()
    {
        return farms;
    }
    
    /**
     *
     * @param aFarm
     * @return
     */
    public Collection<Herd> getHerds(Farm aFarm)
    {
        return aFarm.getHerds();
    }
 
    /**
     *
     * @param aHerd
     * @return
     */
    public Collection<Cow> getCows(Herd aHerd)
    {
        return aHerd.getCows();
    }
    
    /**
     *
     * @param name
     * @param location
     * @return
     */
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
            AddErrors("Error: " + e);
            return false;
        }
        
    }
    
    /**
     *
     * @param name
     * @param interval
     * @param aFarm
     * @return
     */
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
            AddErrors("Error: " + e);
            return false;
        }
       
    }
    
    /**
     *
     * @param aHerd
     * @param aFarm
     * @return
     */
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
            AddErrors("Error: "  + e);
            return false;
        }
        
    }
    
    private void AddErrors(String e)
    {
        errors.add(e);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> GetAllErrors()
    {
        return errors;
    }
    
    /**
     *
     * @return
     */
    public String GetLastError()
    {
        return errors.get(errors.size()-1);
    }
}
