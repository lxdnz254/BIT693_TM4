/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.MilkClasses;

import java.util.*;

/**
 * The class holding the references to the MaxT times associated to a milk Yield
 * @author acer
 */
public class MaxTimeTable
{
    private HashMap<Integer, String> timeTable;
        
    /**
     * Constructor for MaxTimeTable that generates the time references.
     */
    public MaxTimeTable()
    {
        timeTable = new HashMap<>();
        timeTable.put(7, "04:51");
        timeTable.put(8, "05:20");
        timeTable.put(9, "05:48");
        timeTable.put(10, "06:15");
        timeTable.put(11, "06:42");
        timeTable.put(12, "07:07");
        timeTable.put(13, "07:32");
        timeTable.put(14, "07:57");
        timeTable.put(15, "08:21");
        timeTable.put(16, "08:44");
        timeTable.put(17, "09:13");
    }
        
    /**
     * Returns the String value from the HashTable given by the key
     * @param key The key to look up the HashTable
     * @return String
     */
    public String getTime(Integer key)
    {
        return timeTable.get(key);
    }
    
    /**
     * Returns the HashMap that is the maxT times table
     * @return HashMap of the maxT times table
     */
    public HashMap<Integer, String> getMaxTimeTable()
    {
        return timeTable;
    }
}
