/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Package;

import maxtcore.FarmClasses.Farm;
import maxtcore.*;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author acer
 */
public class FarmTest {
    
    private Farm farm;
    
    @Before
    public void runBeforeEachTest() {
        farm = new Farm("NewFarm", "Waikato");
    }
    
    @Test
    public void testCreate()
    {
        String name = farm.getFarmName();
        String location = farm.getFarmLocation();
        assertEquals("NewFarm", name);
        assertEquals("Waikato", location);
        assertTrue(farm.equals(farm));
    }
    
    @Test
    public void testCreateAnother() 
    {
        Farm farm2 = new Farm("AnotherFarm", "Waikato");
        assertFalse(farm.equals(farm2));
    }
}
