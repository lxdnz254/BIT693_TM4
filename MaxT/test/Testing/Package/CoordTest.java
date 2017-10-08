/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Package;

import maxtcore.*;
import static maxtcore.MilkInterval.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
/**
 *
 * @author acer
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoordTest {
    
    private MaxTCoord coord;
    private Farm farm;
    private Herd herd;
    private ArrayList<Farm> farms;
    private ArrayList<Herd> herds;
    private ArrayList<Cow> cows;
    
    @Before
    /**
     * Instantiate some test objects for each test
     */
    public void runBeforeEachTest()
    {
        coord = new MaxTCoord();
        farm = new Farm("TestFarm", "Waikato");
        herd = new Herd("TestHerd", EIGHT_16);
    }
     
    @Test
    /**
     * Testing adding a Farm to the system
     */
    public void test_1_AddFarm()
    {
        assertTrue(coord.addFarm("NewFarm", "Waikato"));
        farms = new ArrayList<>(coord.getFarms());
        assertTrue(farms.size() == 1);
        assertTrue(farms.get(0).getFarmName().equals("NewFarm"));
    }
    
    @Test
    /**
     * Testing adding a Herd to a Farm
     */
    public void test_2_AddHerdToFarm()
    {
        assertTrue(coord.addHerd("Herd1", EIGHT_16, farm));
        herds = new ArrayList<>(coord.getHerds(farm));
        assertTrue(herds.size() == 1);
        assertTrue(herds.get(0).getHerdName().equals("Herd1"));
    }
    
    @Test
    /**
     * Test adding a Cow to a Herd
     */
    public void test_3_AddCowToHerd()
    {
        assertTrue(coord.addCow(herd, farm));
        cows = new ArrayList<>(coord.getCows(herd));
        assertTrue(cows.size() == 1);
        assertTrue(cows.get(0).getCowId().equals(farm.getFarmId()+"-"+herd.getHerdId()+"-100"));
    }
    
    @Test
    /**
     * Testing the unique identifier 
     */
    public void test_4_FarmIDGoesDouble()
    {
        // add multiple farms
        for (int i = 1; i < 27; i++)
        {
            coord.addFarm("Farm" + i, "Waikato");
        }
        // get list of farms in the system and sort
        farms = new ArrayList<>(coord.getFarms());
        Collections.sort(farms);
        // test the last farm id is of length 2 "AA or AB or AC or ..."
        assertTrue(farms.get(farms.size()-1).getFarmId().length() == 2);
    }
    
    @Test
    /**
     * Tests for the return of an empty herd collection
     */
    public void test_5_GetHerds()
    {
        // add test farm
        assertTrue(coord.addFarm("EmptyHerdFarm", "Waikato"));
        Farm testFarm = new Farm();
        // find the test farm
        farms = new ArrayList<>(coord.getFarms());
        for(Farm theFarm: farms)
        {
            if (theFarm.getFarmName().equals("EmptyHerdFarm"))
            {
                testFarm = theFarm;
            }
        }
        // test the test farm has no herds associated.
        assertTrue(coord.getHerds(testFarm).isEmpty());
    }
    
    @Test
    /**
     * Test that a farm is deleted from the system
     */
    public void test_6_DeleteAFarm()
    {
        // add the test farm
        assertTrue(coord.addFarm("DeleteFarm", "Waikato"));
        Farm testFarm = new Farm();
        // find the test farm
        farms = new ArrayList<>(coord.getFarms());
        for(Farm theFarm: farms)
        {
            if (theFarm.getFarmName().equals("DeleteFarm"))
            {
                testFarm = theFarm;
            }
        }
        // delete the test farm
        assertTrue(coord.deleteAFarm(testFarm));
        // get new farms list
        farms = new ArrayList(coord.getFarms());
        // check the test farm is not in the system
        assertFalse(farms.contains(testFarm));
    }
}
