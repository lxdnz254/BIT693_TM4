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
public class CalculateMaxT {
    
    Collection<MilkTable> milkTables;
    MaxTimeTable maxTTime;
    MilkTable milkTable8;
    MilkTable milkTable9;
    
    /**
     * Set up the milk table values;
     */
    public CalculateMaxT()
    {
        milkTable8 = new MilkTable(MilkInterval.EIGHT_16);
        milkTable9 = new MilkTable(MilkInterval.NINE_15);
        maxTTime = new MaxTimeTable();
        milkTables = new HashSet<>();
        milkTables.add(milkTable8);
        milkTables.add(milkTable9);
    }
    /**
     * Checks that all cows in a Herd object have valid Milk Yields
     * @param aHerd
     * @return 
     */
    public boolean allCowsHaveMilk(Herd aHerd)
    {
        return aHerd.getCows().stream().noneMatch((testCow) -> (!testCow.hasValidMilkYield()));
    }
    
    /**
     * Calculates the average Milk Yield for a Herd of Cows
     * @param aHerd
     * @return 
     */
    public int herdMilkAverage(Herd aHerd)
    {
        int totalMilkYield = 0; // running total of milk for the herd
        
        for(Cow testCow: aHerd.getCows())
        {
            MilkYield testMilk = testCow.getMilkYield();
            totalMilkYield += testMilk.getAmYield();
            totalMilkYield += testMilk.getPmYield();
        }
        
        return totalMilkYield/aHerd.getCows().size();
    }
    
    public String amMaxTTime(Herd aHerd)
    {
        maxTTime = new MaxTimeTable();
        
        MilkTable foundMilkT = new MilkTable();
        System.out.println("milkTables size: " + milkTables.size());
        for (MilkTable milkT: milkTables)
        {
            
            System.out.println("Interval: " + milkT.getMilkInterval());
            System.out.println("Herd Interval: " + aHerd.getHerdInterval());
            //System.out.println("Rows: " + milkT.getRow(20)[0]);
            if(milkT.getMilkInterval() == aHerd.getHerdInterval())
            {
                foundMilkT = milkT;
                System.out.println("Objects match: " + foundMilkT + " -> " + milkT);
            }
        }
        System.out.println("Herd Average: " + herdMilkAverage(aHerd));
        System.out.println(foundMilkT.getRow(20)[0]);
        Integer[] times = foundMilkT.getRow(herdMilkAverage(aHerd));
        System.out.println("Got the am time: " + times[0]);
        return maxTTime.getTime(times[0]);
    }
    
    public String pmMaxTTime(Herd aHerd)
    {
        maxTTime = new MaxTimeTable();
        
        MilkTable foundMilkT = new MilkTable();
        for (MilkTable milkT: milkTables)
        {
            if(aHerd.getHerdInterval().equals(milkT.getMilkInterval()))
            {
                foundMilkT = milkT;
            }
        }
        Integer[] times = foundMilkT.getRow(herdMilkAverage(aHerd));
      
        return maxTTime.getTime(times[1]);
    }
    
    public Collection<MilkTable> getMilkTables()
    {
        return milkTables;
    }
}