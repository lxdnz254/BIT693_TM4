/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

import maxtcore.FarmClasses.Herd;
import maxtcore.FarmClasses.Cow;
import maxtcore.MilkClasses.MaxTimeTable;
import maxtcore.MilkClasses.MilkYield;
import maxtcore.MilkClasses.MilkInterval;
import maxtcore.MilkClasses.MilkTable;
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
        
        for (MilkTable milkT: milkTables)
        {
            if(milkT.getMilkInterval() == aHerd.getHerdInterval())
            {
                foundMilkT = milkT;
            }
        }
        
        Integer[] times = foundMilkT.getRow(herdMilkAverage(aHerd));
        
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