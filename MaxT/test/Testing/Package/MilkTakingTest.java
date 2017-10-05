/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Package;

import maxtcore.*;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author acer
 */
public class MilkTakingTest {
    
    private MilkTaking milkYield;
    private MilkTaking anotherMilkYield;
    
    @Before
    public void runBeforeEachTest()
    {
        milkYield = new MilkTaking();
        anotherMilkYield = new MilkTaking(5,5);
    }
    
    @Test
    public void testCreate()
    {
        int am = milkYield.getAmYield();
        int pm = milkYield.getPmYield();
        int anotherAm = anotherMilkYield.getAmYield();
        int anotherPm = anotherMilkYield.getPmYield();
        
        assertTrue(am == 0);
        assertTrue(pm == 0);
        assertTrue(anotherAm == 5);
        assertTrue(anotherPm == 5);
    }
    
}
