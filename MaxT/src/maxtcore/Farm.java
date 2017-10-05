/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

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
    
    public Farm(String name, String location) {
        id++;
        ident = IntToLetters(id);
        farmName = name;
        this.location = location;
        herds = new HashSet();
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
    
    public String getFarmId() {
        return ident;
    }
    
    public String getFarmName() {
        return farmName;
    }
    
    public String getFarmLocation() {
        return location;
    }
    
    public Collection<Herd> getHerds() {
        return herds;
    }
    
    void addHerd(Herd aHerd) {
        herds.add(aHerd);
    }
    
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Farm farm = (Farm) obj ;
        return this.ident.equals(farm.getFarmId());
    }

    @Override
    public int compareTo(Farm o) {
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int last = this.ident.length() - o.ident.length();
        return last == 0 ? this.ident.compareTo(o.ident) : last;
    }
}
