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
    public void runBeforeEachTest()
    {
        coord = new MaxTCoord();
        farm = new Farm("TestFarm", "Waikato");
        herd = new Herd("TestHerd", EIGHT_16);
    }
     
    @Test
    public void test_1_AddFarm()
    {
        assertTrue(coord.addFarm("NewFarm", "Waikato"));
        farms = new ArrayList<>(coord.getFarms());
        assertTrue(farms.size() == 2);
        assertTrue(farms.get(0).getFarmName().equals("NewFarm"));
    }
    
    @Test
    public void test_2_AddHerdToFarm()
    {
        assertTrue(coord.addHerd("Herd1", EIGHT_16, farm));
        herds = new ArrayList<>(coord.getHerds(farm));
        assertTrue(herds.size() == 1);
        assertTrue(herds.get(0).getHerdName().equals("Herd1"));
    }
    
    @Test
    public void test_3_AddCowToHerd()
    {
        assertTrue(coord.addCow(herd, farm));
        cows = new ArrayList<>(coord.getCows(herd));
        assertTrue(cows.size() == 1);
        assertTrue(cows.get(0).getCowId().equals(farm.getFarmId()+"-"+herd.getHerdId()+"-103"));
    }
    
    @Test
    public void test_4_FarmIDGoesDouble()
    {
        for (int i = 1; i < 27; i++)
        {
            coord.addFarm("Farm" + i, "Waikato");
        }
        farms = new ArrayList<>(coord.getFarms());
        Collections.sort(farms);
        assertTrue(farms.get(farms.size()-1).getFarmId().length() == 2);
    }
}
