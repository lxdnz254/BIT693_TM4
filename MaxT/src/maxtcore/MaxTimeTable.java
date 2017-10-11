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
public class MaxTimeTable
    {
        HashMap<Integer, String> timeTable;
        
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
        
        String getTime(Integer value)
        {
            return timeTable.get(value);
        }
    }