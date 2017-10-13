/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import java.util.*;
import maxtcore.MaxTCoord;
import maxtcore.MilkClasses.MilkInterval;
import maxtcore.MilkClasses.MilkTable;

/**
 * A Utility class to populate the TableModel for the jTable in the GUI
 * @author acer
 */
public class CustomClass {
    
    // Instance variables for the Custom class
    private final ArrayList<MilkTable> milkTables;
    private final MaxTCoord maxtCoord;
    private final Map<Integer, Integer[]> milkMap1;
    private final Map<Integer, Integer[]> milkMap2;
   
    /**
     * Constructor for the customer class
     */
    public CustomClass()
    {
        maxtCoord = new MaxTCoord();
        milkTables = new ArrayList<>(maxtCoord.getCalcMaxT().getMilkTables());
        milkMap1 = new HashMap<>();
        milkMap2 = new HashMap<>();
    }
    
    /**
     * Private class to populate the TableModel
     */ 
    private Integer[][] populateTables(ArrayList<MilkTable> collection)
    {
        // create references to populate the MilkTables
        MilkTable table8 = new MilkTable();
        MilkTable table9 = new MilkTable();
        // Ensure the correct MilkTable is placed in correct position
        for (MilkTable checkTable: collection)
        {
            if (checkTable.getMilkInterval() == MilkInterval.EIGHT_16)
            {
                table8 = checkTable;
            }
            if (checkTable.getMilkInterval() == MilkInterval.NINE_15)
            {
                table9 = checkTable;
            }
        }
        // shape of the Table
        Integer[][] table = new Integer[6][5];
        // Insert the tables to the arrays
        for (int i = 20; i<=25; i++)
        {
        milkMap1.put(i, table8.getRow(i));
        milkMap2.put(i, table9.getRow(i));
        table[i-20][0] = i;
        table[i-20][1] = milkMap1.get(i)[0];
        table[i-20][2] = milkMap1.get(i)[1];
        table[i-20][3] = milkMap2.get(i)[0];
        table[i-20][4] = milkMap2.get(i)[1];
        }        
        return table;
    }
    
    /**
     * Returns the MilkTables to the TableModel in the form of a 2D Array
     * @return A 2D array of Integer objects
     */
    public Integer[][] getTable()
    {
        return populateTables(milkTables);
    }  
}
