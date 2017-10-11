/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import maxtcore.MilkClasses.MilkTable;

/**
 *
 * @author acer
 */
public class CustomModel {
    
    TableModel tableModel;
    List<String> columns;
    List<Integer[]> rows;
    CustomClass customC;
    Integer[][] table;
    
    public CustomModel()
    {
        customC = new CustomClass();
        columns = new ArrayList<>();
        columns.add("Daily Milk Yield");
        columns.add("8 & 16 morning");
        columns.add("8 & 16 evening");
        columns.add("9 & 15 morning");
        columns.add("9 & 15 evening");
        rows = new ArrayList<>();
        table = customC.getTable();
        for (int i=0;i<6;i++)
        {
            rows.add(new Integer[] {table[i][0], table[i][1],table[i][2],table[i][3],table[i][4]});
        }
        
        tableModel = new DefaultTableModel(rows.toArray(new Object[][]{}), columns.toArray());
    }
    
    public TableModel getTableModel()
    {
        return tableModel;
    }
}