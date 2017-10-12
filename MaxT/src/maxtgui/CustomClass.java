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
    
    Integer dailyValue;
    Integer amEight;
    Integer pmEight;
    Integer amNine;
    Integer pmNine;
    ArrayList<MilkTable> milkTables;
    MaxTCoord maxtCoord;
    Map<Integer, Integer[]> milkMap1;
    Map<Integer, Integer[]> milkMap2;
    Integer[] milkYields;
    Integer[][] populatedTable;
   
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
    
    // private class to populate the TableModel
    private Integer[][] populateTables(ArrayList<MilkTable> collection)
    {
        // shape of the Table
        Integer[][] table = new Integer[6][5];
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
     * @return
     */
    public Integer[][] getTable()
    {
        return populateTables(milkTables);
    }  
}
