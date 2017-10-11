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
public class MilkTable {
        
        MilkInterval milkInterval;
        int dailyTaking;
        int amTaking;
        int pmTaking;
        
        SortedMap<Integer, Integer[]> dailyMap;
        Integer[] yieldArray;
        
        
        /**
         * Blank constructor
         */
        public MilkTable()
        {
            
        }
        /**
         * Constructor for a MilkTable
         * @param interval 
         */
        public MilkTable(MilkInterval interval)
        {
            milkInterval = interval;
            dailyMap = new TreeMap();
            yieldArray = new Integer[2];
        }
        
        void populateRow(int daily, int am, int pm)
        {
            yieldArray[0] = am;
            yieldArray[1] = pm;
            dailyMap.put(daily, yieldArray);
        }
        
        Integer[] getRow(int dailyRow)
        {
            return dailyMap.get(dailyRow);
        }
        
        MilkInterval getMilkInterval()
        {
            return this.milkInterval;
        }
        
        @Override
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
