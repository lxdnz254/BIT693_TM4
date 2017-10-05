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
/**
 *
 * @author acer
 */
public class HerdTest {
    private Herd herd;
    private Farm farm;
    
    @Before
    public void runBeforeEachTest()
    {
        herd = new Herd ("Herd1", EIGHT_16);
        farm = new Farm ("NewFarm", "Waikato");
    }
    
    @Test
    public void testCreate()
    {
        String name = herd.getHerdName();
        String milkingInterval = herd.getHerdInterval().toString();
        assertEquals("Herd1", name);
        assertEquals("8 and 16 hour intervals", milkingInterval);
        assertTrue(herd.equals(herd));
    }
    
    @Test
    public void testAddHerdToFarm() {
        herd.addHerdToFarm(farm);
        ArrayList<Herd> herds = new ArrayList<>(farm.getHerds());
        assertEquals(1, herds.size());
        assertEquals("Herd1", herds.get(0).getHerdName());
    }
}
