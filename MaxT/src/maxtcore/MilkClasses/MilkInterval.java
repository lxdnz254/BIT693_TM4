/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.MilkClasses;

/**
 * An Enum class to determine the interval a herd of cows is milked by
 * @author acer
 */
public enum MilkInterval {

    /**
     * The milking interval is 8 hours and 16 hours
     */
    EIGHT_16,

    /**
     * The milking interval is 9 hours and 15 hours
     */
    NINE_15;
     
    @Override
     public String toString() {
         
         switch(this) 
         {
             case EIGHT_16: return "8 and 16 hour intervals";
             default: return "9 and 15 hour intervals";
         }
     }
}
