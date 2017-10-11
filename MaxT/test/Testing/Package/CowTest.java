/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Package;

import maxtcore.FarmClasses.Farm;
import maxtcore.FarmClasses.Cow;
import maxtcore.FarmClasses.Herd;
import maxtcore.*;
import static maxtcore.MilkClasses.MilkInterval.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author acer
 */
public class CowTest {
    
    private Cow cow;
    private Herd herd;
    private Farm farm;
    
    @Before
    public void runBeforeEachTest()
    {
        
        herd = new Herd("Herd1", EIGHT_16);
        farm = new Farm("NewFarm", "Waikato");
        cow = new Cow(farm, herd);
    }
    
    @Test
    public void testCreate()
    {
        assertTrue(cow.equals(cow));
    }
    
}
