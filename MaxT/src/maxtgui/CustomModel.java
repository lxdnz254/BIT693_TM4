/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import maxtcore.MilkClasses.MaxTimeTable;

/**
 * A Custom TableModel Class for adding to a jTable in a Swing User Interface
 * @author acer
 */
public class CustomModel extends AbstractTableModel{
    
    private final Object[] columns = {"Daily Yield", "8 & 16 am", "8 & 16 pm", "9 & 15 am", "9 & 15 pm"};
    ArrayList<Integer[]> data;
    CustomClass customC;
    Integer[][] table;
    MaxTimeTable maxTime;
    
    /**
    * Constructor for the Custom Model
    */
   public CustomModel() {
        maxTime = new MaxTimeTable();
        this.data = getDataForDropList();
   }

   @Override
   public int getRowCount() {
   return data.size();
   }

   @Override
   public int getColumnCount() {
   return columns.length;
   }

   @Override
   public String getColumnName(int columnIndex) {
       return (String) columns[columnIndex];
   }

   @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       // Set Values here;
       Integer theValue = (Integer)aValue;
       Integer lowest = minimumMaxTimeKey();
       if (maxTime.getMaxTimeTable().containsKey(theValue)){ // check the value is valid for MaxT time
           table[rowIndex][columnIndex] = theValue;
           fireTableCellUpdated(rowIndex, columnIndex);
           switch(columnIndex)
           {
               case 1:{
                   if((theValue + table[rowIndex][2])!= table[rowIndex][0])
                   {
                       table[rowIndex][2] = table[rowIndex][0] - theValue;
                       if(table[rowIndex][2]< lowest){
                           setValueAt(lowest, rowIndex, 2);
                       }
                   } 
                   break;
               }
               case 2:{
                   if((theValue + table[rowIndex][1]) != table[rowIndex][0])
                   {
                       table[rowIndex][1] = table[rowIndex][0] - theValue;
                       if(table[rowIndex][1] < lowest){
                           setValueAt(lowest, rowIndex, 1);
                       }
                   }
                   break;
               }
               case 3:{
                   if((theValue + table[rowIndex][4]) != table[rowIndex][0])
                   {
                       table[rowIndex][4] = table[rowIndex][0] - theValue;
                       if(table[rowIndex][4] < lowest) {
                           setValueAt(lowest, rowIndex, 4);
                       }
                   }
                   break;
               }
               case 4:{
                   if((theValue + table[rowIndex][3]) != table[rowIndex][0])
                   {
                       table[rowIndex][3] = table[rowIndex][0] - theValue;
                       if(table[rowIndex][3] < lowest){
                           setValueAt(lowest, rowIndex, 3);
                       }
                   }
               }
           }
       } 
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex) {
   return data.get(rowIndex)[columnIndex];
   }

   @Override
   public boolean isCellEditable(int rowIndex, int columnIndex) {
           return !(columnIndex == 0);
   }

   @Override
   public Class<?> getColumnClass(int columnIndex) {
       // Depending on the type of the column. Return data type;
       return getValueAt(0, columnIndex).getClass();
   }


   /**
    * Populate the data from here.
    * @return ArrayList<Integer[]>
    */
    private ArrayList<Integer[]> getDataForDropList() {
        ArrayList<Integer[]> modelList = new  ArrayList<>();
        // Create custom Object and add it to the ArrayList.
        customC = new CustomClass();
        // Create a CustomClass object and add it to the ArrayList
        table = customC.getTable();
        for(int i = 0; i< 6; i++) 
        {
            modelList.add(table[i]);
        }
            // Finally return the ArrayList
        return modelList;
   }
    
    private Integer minimumMaxTimeKey()
    {
        Integer lowest = 100;
        for (Integer key : maxTime.getMaxTimeTable().keySet())
        {
            if (key < lowest) {lowest = key;}
        }
        return lowest;
    }
}