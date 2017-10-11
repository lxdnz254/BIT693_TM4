/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import java.util.*;
import maxtcore.MaxTCoord;
import maxtcore.MilkClasses.MilkTable;

/**
 *
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
   
    
    public CustomClass()
    {
        maxtCoord = new MaxTCoord();
        milkTables = new ArrayList<>(maxtCoord.calcMaxT.getMilkTables());
        milkMap1 = new HashMap<>();
        milkMap2 = new HashMap<>();
    }
    
    Integer[][] populateTables(ArrayList<MilkTable> collection)
    {
        Integer[][] table = new Integer[6][5];
        for (int i = 20; i<=25; i++)
        {
        milkMap1.put(i, collection.get(0).getRow(i));
        milkMap2.put(i, collection.get(1).getRow(i));
        table[i-20][0] = i;
        table[i-20][1] = milkMap1.get(i)[0];
        table[i-20][2] = milkMap1.get(i)[1];
        table[i-20][3] = milkMap2.get(i)[0];
        table[i-20][4] = milkMap2.get(i)[1];
        }
        
        return table;
    }
    
    public Integer[][] getTable()
    {
        return populateTables(milkTables);
    }
    
    public Integer[] getRowValues()
    {
        return new Integer[]{dailyValue, amEight, pmEight, amNine, pmNine};
    }
    
    public void setRowValues(Integer[] values)
    {
        dailyValue = values[0];
        amEight = values[1];
        pmEight = values[2];
        amNine = values[3];
        pmNine = values[4];
    }
    
}
