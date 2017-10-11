/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore.FarmClasses;

import java.util.*;

/**
 *
 * @author acer
 */
public class Farm implements Comparable<Farm>{
    
    private static int id = 0;
    private String ident;
    private String farmName;
    private String location;
    private Collection<Herd> herds;
    
    /**
     *
     * @param name
     * @param location
     */
    public Farm(String name, String location) {
        id++;
        ident = IntToLetters(id);
        farmName = name;
        this.location = location;
        herds = new HashSet();
    }
    
    public Farm()
    {
        ident = null;
    }
    
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
     *
     * @return
     */
    public String getFarmId() {
        return ident;
    }
    
    /**
     *
     * @return
     */
    public String getFarmName() {
        return farmName;
    }
    
    /**
     *
     * @return
     */
    public String getFarmLocation() {
        return location;
    }
    
    /**
     *
     * @return
     */
    public Collection<Herd> getHerds() {
        return herds;
    }
    
    public void addHerd(Herd aHerd) {
        herds.add(aHerd);
    }
    
    @Override
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

    public void deleteHerd(Herd aHerd) {
        herds.remove(aHerd);
    }
}
