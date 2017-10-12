/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.FarmClasses;

import java.util.*;

/**
 * The Farm class for the core system.
 * @author acer
 */
public class Farm implements Comparable<Farm>{
    
    private static int id = 0;
    private final String ident;
    private String farmName;
    private String location;
    private Collection<Herd> herds;
    
    /**
     * Constructor for a Farm object
     * @param name A String representing a unique name for the Farm
     * @param location A String representing the location of the Farm object
     */
    public Farm(String name, String location) {
        id++;
        ident = IntToLetters(id);
        farmName = name;
        this.location = location;
        herds = new HashSet();
    }
    
    /**
     * A Constructor for a empty Farm object
     */
    public Farm()
    {
        ident = null;
    }
    
    /**
     * A method to convert an integer to a String that iterates 
     * A-Z, AA-ZZ, AAA-ZZZ etc
     * @param value the integer to create the String object
     * @return 
     */
    static String IntToLetters(int value)
    {
        String result = "";
        while (--value >= 0)
        {
            result = (char)('A' + value % 26 ) + result;
            value /= 26;
        }
        return result;
    }
    
    /**
     * Returns the Farm objects unique identifier
     * @return
     */
    public String getFarmId() {
        return ident;
    }
    
    /**
     * Returns the Farm object name
     * @return
     */
    public String getFarmName() {
        return farmName;
    }
    
    /**
     * Returns the location of a Farm object
     * @return
     */
    public String getFarmLocation() {
        return location;
    }
    
    /**
     * Returns the Collection of Herd objects associated with the Farm object
     * @return
     */
    public Collection<Herd> getHerds() {
        return herds;
    }
    
    /**
     * Add a herd object to the Collection of Herd objects associated with the
     * Farm object
     * @param aHerd The Herd object to be added to the Collection
     */
    public void addHerd(Herd aHerd) {
        herds.add(aHerd);
    }
    
    /**
     * Removes a Herd object from the Collection of Herd objects associated with
     * the Farm object
     * @param aHerd
     */
    public void deleteHerd(Herd aHerd) {
        herds.remove(aHerd);
    }
    
    @Override
    /**
     * Overrides the equals() method from Object class and checks for equality
     * on the Farm object ident variable.
     */
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Farm farm = (Farm) obj ;
        return this.ident.equals(farm.getFarmId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ident);
        return hash;
    }

    @Override
    public int compareTo(Farm o) {
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int last = this.ident.length() - o.ident.length();
        return last == 0 ? this.ident.compareTo(o.ident) : last;
    }
    
    @Override
    public String toString()
    {
        return this.getFarmName();
    }
}
