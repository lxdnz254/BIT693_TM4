/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

import maxtcore.FarmClasses.Farm;
import maxtcore.FarmClasses.Cow;
import maxtcore.FarmClasses.Herd;
import maxtcore.MilkClasses.MilkTable;
import maxtcore.MilkClasses.MilkYield;
import java.lang.reflect.Method;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static maxtcore.MilkClasses.MilkInterval.*;

/**
 *
 * @author acer
 */
public class CalculateMaxTTest {
    
    Farm aFarm;
    Herd aHerd;
    Cow cow1;
    Cow cow2;
    MilkYield milkYield;
    MilkTable milkTable;
    Collection<MilkTable> milkTables;
    CalculateMaxT calcMaxT;
    
    public CalculateMaxTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        aFarm = new Farm("New Farm", "Waikato");
        aHerd = new Herd("NewHerd", EIGHT_16);
        // add cow1 to the test system
        cow1 = new Cow(aFarm, aHerd);
        aHerd.addCow(cow1);
        milkYield = cow1.getMilkYield();
        milkYield.setYield(13, 7);
        cow1.setValidMilkYield(true);
        // add cow2 to the test system
        cow2 = new Cow(aFarm, aHerd);
        aHerd.addCow(cow2);
        milkYield = cow2.getMilkYield();
        milkYield.setYield(14, 8);
        cow2.setValidMilkYield(true);
        
        calcMaxT = new CalculateMaxT();
        milkTables = calcMaxT.getMilkTables();
        // selects the MilkTable referenced by the MilkInterval.EIGHT_16
        milkTables.stream().filter((mt) -> (mt.getMilkInterval().equals(EIGHT_16))).forEach((mt) -> {
            milkTable = mt;
        });
        setMilkTable();  
    }
    
    private void setMilkTable()
    {
        int daily = 21;
        int am = 14;
        int pm = 7;
        milkTable.populateRow(daily, am, pm);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of allCowsHaveMilk method, of class CalculateMaxT.
     */
    @Test
    public void testAllCowsHaveMilk() {
        System.out.println("allCowsHaveMilk");
        Herd testHerd = aHerd;
        CalculateMaxT instance = new CalculateMaxT();
        boolean expResult = true;
        boolean result = instance.allCowsHaveMilk(testHerd);
        assertEquals(expResult, result);
    }

    /**
     * Test of herdMilkAverage method, of class CalculateMaxT.
     */
    @Test
    public void testHerdMilkAverage() {
        System.out.println("herdMilkAverage");
        CalculateMaxT instance = new CalculateMaxT();
        int expResult = 21;
        int result = instance.herdMilkAverage(aHerd);
        assertEquals(expResult, result);
    }

    /**
     * Test of amMaxTTime method, of class CalculateMaxT.
     */
    @Test
    public void testAmMaxTTime() {
        System.out.println("amMaxTTime");
        CalculateMaxT instance = calcMaxT;
        String expResult = "07:57";
        String result = instance.amMaxTTime(aHerd);
        assertEquals(expResult, result);
    }

    /**
     * Test of pmMaxTTime method, of class CalculateMaxT.
     */
    @Test
    public void testPmMaxTTime() {
        System.out.println("pmMaxTTime");
        CalculateMaxT instance = calcMaxT;
        String expResult = "04:51";
        String result = instance.pmMaxTTime(aHerd);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMilkTables method, of class CalculateMaxT.
     */
    @Test
    public void testGetMilkTables() {
        System.out.println("getMilkTables");
        CalculateMaxT instance = new CalculateMaxT();
        Collection<MilkTable> expResult = milkTables;
        Collection<MilkTable> result = instance.getMilkTables();
        assertEquals(expResult, result);
    }
    
}
