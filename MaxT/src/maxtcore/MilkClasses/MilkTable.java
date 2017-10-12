/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.MilkClasses;

import java.util.*;

/**
 * The Class to hold the approximate morning and evening yields for a 
 * daily yield of cows milk. Different yields are held held for different
 * milk intervals.
 * @author acer
 */
public class MilkTable {
    // The reference to a MilkInterval enum   
    private MilkInterval milkInterval;
    // The references to the maps
    private Map<Integer, Integer[]> dailyMap;
    private Integer[] yieldArray;

    /**
     * Blank constructor to fill in comparison methods
     */
    public MilkTable()
    {

    }
    /**
     * Constructor for a MilkTable
     * @param interval the milk interval for the MilkTable object
     */
    public MilkTable(MilkInterval interval)
    {
        milkInterval = interval;
        dailyMap = new HashMap<>();
    }

    /**
     * Populates a row in a MilkTable object
     * @param daily The Key for the milk table, which represents the daily milk 
     * yield of a cow/herd
     * @param am The morning milk yield
     * @param pm The evening milk yield
     */
    public void populateRow(Integer daily, Integer am, Integer pm)
    {
        yieldArray = new Integer[]{am,pm};
        dailyMap.put(daily, yieldArray);
    }

    /**
     * Returns the Integer array associated with the key give
     * @param dailyRow The key to search the MilkTable with.
     * @return Integer[] an array of Integer objects
     */
    public Integer[] getRow(int dailyRow)
    {
        return dailyMap.get(dailyRow);
    }

    /**
     * Returns the MilkInterval associated with the MilkTable object
     * @return the milk interval.
     */
    public MilkInterval getMilkInterval()
    {
        return this.milkInterval;
    }

    @Override
    /**
     * Overrides the equals() method from Object class and compares on MilkInterval
     */
    public boolean equals(Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        MilkTable table = (MilkTable) obj ;
        return this.milkInterval.equals(table.getMilkInterval());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.milkInterval);
        return hash;
    }
}
