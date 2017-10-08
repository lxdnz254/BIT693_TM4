/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

/**
 *
 * @author acer
 */
public enum MilkInterval {

    /**
     *
     */
    EIGHT_16,

    /**
     *
     */
    NINE_15;
     
     public String toString() {
         
         switch(this) 
         {
             case EIGHT_16: return "8 and 16 hour intervals";
             default: return "9 and 15 hour intervals";
         }
     }
}
