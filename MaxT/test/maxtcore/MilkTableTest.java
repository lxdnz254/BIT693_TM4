/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static maxtcore.MilkInterval.*;
import java.util.*;

/**
 *
 * @author acer
 */
public class MilkTableTest {
    
    public MilkTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of populateRow method, of class MilkTable.
     */
    @Test
    public void testPopulateRow() {
        System.out.println("populateRow");
        int daily = 20;
        int am = 13;
        int pm = 7;
        MilkTable instance = new MilkTable(MilkInterval.EIGHT_16);
        instance.populateRow(daily, am, pm);
        // TODO review the generated test code and remove the default call to fail.
        Integer[] yields = instance.getRow(daily);
        assertTrue(yields[0]== 13);
        assertTrue(yields[1] == 7);
    }

    /**
     * Test of getRow method, of class MilkTable.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        int dailyRow = 21;
        int am = 13;
        int pm = 8;
        MilkInterval interval = MilkInterval.EIGHT_16;
        MilkTable instance = new MilkTable(interval);
        instance.populateRow(dailyRow, am, pm);
        Integer[] expResult = {13,8};
        
        Integer[] result = instance.getRow(dailyRow);
        
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getMilkInterval method, of class MilkTable.
     */
    @Test
    public void testGetMilkInterval() {
        System.out.println("getMilkInterval");
        MilkInterval interval = MilkInterval.EIGHT_16;
        MilkTable instance = new MilkTable(interval);
        MilkInterval expResult = interval;
        MilkInterval result = instance.getMilkInterval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

}
