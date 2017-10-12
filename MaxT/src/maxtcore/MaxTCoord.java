/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtcore;

import maxtcore.FarmClasses.Herd;
import maxtcore.FarmClasses.Cow;
import maxtcore.FarmClasses.Farm;
import maxtcore.MilkClasses.MilkYield;
import maxtcore.MilkClasses.MilkInterval;
import maxtcore.MilkClasses.MilkTable;
import java.util.*;


/**
 *
 * @author acer
 */
public class MaxTCoord {
    
    private Collection<Farm> farms;
    private MilkInterval milkInterval;
    private ArrayList<String> errors;

    /**
     * CustomClass calls this object to obtain
     */
    public CalculateMaxT calcMaxT;
    
    /**
     * The main coordinating class for the maxtcore system
     */
    public MaxTCoord()
    {
        errors = new ArrayList<>();
        farms = new HashSet();
        calcMaxT = new CalculateMaxT();
        initialiseMilkTable();
        // Some initial data commented out after testing
        
        // <editor-fold defaultstate="collapsed">
        /*
        Acceptance test initial data when testing gui builds.
        
        
        if (addFarm("NewFarm", "Waikato"))
        {
            System.out.println("Added NewFarm!");
            ArrayList<Farm> arrayFarms = new ArrayList(farms);
            if(addHerd("NewHerd", milkInterval.EIGHT_16, arrayFarms.get(0)))
            {
                System.out.println("Added NewHerd to " + arrayFarms.get(0).getFarmName());
                ArrayList<Herd> arrayHerd = new ArrayList(getHerds(arrayFarms.get(0)));
                if(addCow(arrayHerd.get(0), arrayFarms.get(0)))
                {
                    System.out.println("Added Cow to " + arrayHerd.get(0).getHerdName());
                }
                else
                {
                    System.out.println(GetLastError());
                }
            }
            else
            {
                System.out.println(GetLastError());
            }
        }
        else
        {
            System.out.println(GetLastError());
        }
        */
        
        // </editor-fold>
    }
    
    /**
     * Gets the Collection of Farm objects stored in the coordinating object
     * @return
     */
    public Collection<Farm> getFarms()
    {
        return farms;
    }
    
    /**
     * Gets the Collection of Herd objects associated with a Farm object
     * @param aFarm
     * @return
     */
    public Collection<Herd> getHerds(Farm aFarm)
    {
        return aFarm.getHerds();
    }
 
    /**
     * Returns a Collection of Cow objects associated with a Herd object
     * @param aHerd
     * @return
     */
    public Collection<Cow> getCows(Herd aHerd)
    {
        return aHerd.getCows();
    }

//******************************************************************************
    // This section deals with adding objects to the system
    
    // <editor-fold defaultstate="collapsed">
    
    /**
     * Adds a Farm object to the system, returns true if successful, or produces
     * error report and returns false.
     * @param name A String identifying the name of the Farm object
     * @param location A String identifying the location of the Farm object
     * @return
     */
    public boolean addFarm(String name, String location)
    {
        try
        {
            // validates the Farm name is not blank
            if (name.trim().isEmpty() || name.trim().matches("[a-zA-Z0-9 ]"))
            {
                AddErrors("Farm not saved, Farm name is empty");
                return false;
            }
            // validates the location is not blank
            if (location.trim().isEmpty() || location.trim().equals("[a-zA-Z0-9 ]"))
            {
                AddErrors("Farm not saved, Farm location is empty");
            }
            // validation for equal farm names in the system
            for (Farm checkFarm: farms)
            {
                if (checkFarm.getFarmName().equals(name.trim()))
                {
                    AddErrors("Farm not saved, Farm name matches another.");
                    return false;
                }
            }
            // store the Farm object in the system
            Farm farm = new Farm(name.trim(), location);
            farms.add(farm);
            return true;
        }
        catch(Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
        
    }
    
    /**
     * Adds a Herd object to the system and associates it with the given Farm
     * object, returns true if successful, or produces error report and 
     * returns false
     * @param name a String identifying the name of the Herd object
     * @param interval A MilkInterval object
     * @param aFarm The Farm object the Herd will be associated with.
     * @return
     */
    public boolean addHerd(String name, MilkInterval interval, Farm aFarm)
    {
        try
        {
            // validates the name is legitimate
            if (name.trim().isEmpty() || name.trim().matches("[^a-zA-Z0-9 ]"))
            {
                AddErrors("Herd not saved, Herd name is empty or contains special charcters");
                return false;
            }
            // validation for equal names within the system
            for (Herd checkHerd : getHerds(aFarm))
            {
                if (checkHerd.getHerdName().equals(name.trim()))
                {
                    AddErrors("Herd not saved, a herd matching this name on "+ aFarm 
                            + " already exists.");
                    return false;
                }
            }
            // stores the Herd object to the system.
            Herd herd = new Herd(name.trim(), interval);
            aFarm.addHerd(herd);
            return true;
        }
        catch(Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
    }
    
    /**
     * Adds a Cow object to the system, returns true if successful, or produces
     * error report and returns false.
     * @param aHerd The Herd object the Cow will be associated with.
     * @param aFarm The Farm object the Herd is associated with, used to create
     *              Cows unique identifier.
     * @return
     */
    public boolean addCow(Herd aHerd, Farm aFarm)
    {
        try
        {
            // Adds a cow to the system, no validation is needed as no Cow will be 
            // the same
            Cow cow = new Cow(aFarm, aHerd);
            aHerd.addCow(cow);
            return true;
        }
        catch(Exception e)
        {
            AddErrors("Error: "  + e);
            return false;
        }   
    }

    /**
     * Adds a MilkYield object to a Cow object, returns true if successful, or 
     * produces an error report and returns false
     * @param aCow The Cow object the milk Yield is being added/updated to.
     * @param am an integer representing the morning milk volume
     * @param pm an integer representing the evening milk volume
     * @return 
     */
    public boolean addMilkYield(Cow aCow, int am, int pm)
    {
        try
        {
            // Get the MilkYiled object associated with aCow
            MilkYield milkYield = aCow.getMilkYield();
            milkYield.setYield(am, pm);
            aCow.setValidMilkYield(true);
            return true;
        }
        catch (Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
    }
    
    // </editor-fold>
    
//******************************************************************************
    // This section deals with deleting objects from the system
    
    // <editor-fold defaultstate="collapsed">
    
    /**
     * Deletes the MilkYield object values held by a Cow object
     * @param aCow
     * @return true or false that the deletion has taken place
     */
    public boolean deleteAMilkYield(Cow aCow)
    {
        try
        {
            if(aCow.hasValidMilkYield())
            {
                aCow.deleteMilkYield();
                return true;
            }
            else
            {
                AddErrors("The Cow " + aCow.getCowId() + " has no milk yield to delete!");
                return false;
            }
        }
        catch (Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
    }
    
    /**
     * Deletes a Cow object from the Collection of Cow objects held by a Herd object 
     * @param aCow
     * @param aHerd
     * @return true or false that the deletion has taken place
     */
    public boolean deleteACow(Cow aCow, Herd aHerd)
    {
        try
        {
            Collection<Cow> cows = getCows(aHerd);
            
            if (!cows.isEmpty()){
                Cow deleteCow = new Cow();
                for (Cow theCow: cows)
                {
                    if (theCow.equals(aCow))
                    {
                        deleteCow = theCow;
                    }
                }
                if (deleteCow.toString() != null)
                {
                    aHerd.deleteCow(aCow);
                    return true;
                }
                else
                {
                    AddErrors("The system could not find the matching cow to delete");
                    return false;
                }
            }
            else
            {
                AddErrors("The herd has no cows to delete");
                return false;
            }
            
        }
        catch (Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        } 
    }
    
    /**
     * Deletes a Herd object from the Collection of Herd objects held by a Farm object.
     * @param aHerd
     * @param aFarm
     * @return true or false that the deletion has taken place
     */
    public boolean deleteAHerd(Herd aHerd, Farm aFarm)
    {
        try
        {
            Collection<Herd> herds = aFarm.getHerds();
            
            if (!herds.isEmpty()) {
                Herd deleteHerd = new Herd();
                
                for (Herd theHerd: herds)
                {
                    if (theHerd.equals(aHerd))
                    {
                        deleteHerd = theHerd;
                    }                       
                }
                if (deleteHerd.toString() != null)
                {
                    Collection<Cow> cows = deleteHerd.getCows();
                    if (cows.isEmpty())
                    {
                        aFarm.deleteHerd(deleteHerd);
                        return true;
                    }
                    else
                    {
                        AddErrors("The herd cannot be deleted, cows are assigned to the herd.");
                        return false;
                    }
                }
                else
                {
                    AddErrors("No herd could be found matching on this Farm.");
                    return false;
                }
            }
            else
            {
                AddErrors("There are no herds found with this farm.");
                return false;
            }
        }
        catch (Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
    }
    
    /**
     * Deletes a Farm object from the Collection of Farm objects held by the Coordinating class
     * @param aFarm
     * @return true or false that the deletion has taken palce
     */
    public boolean deleteAFarm(Farm aFarm)
    {
        try
        {
            if (!farms.isEmpty())
            {
                Farm deleteFarm = new Farm();
                for (Farm theFarm: farms)
                {
                    if (theFarm.equals(aFarm))
                    {
                        deleteFarm = theFarm;
                    }
                }
                if (deleteFarm.toString() != null)
                {
                    Collection<Herd> herds = deleteFarm.getHerds();
                    if (herds.isEmpty())
                    {
                        farms.remove(deleteFarm);
                        return true;
                    }
                    else
                    {
                        AddErrors("Farm not deleted, herds still exist on this Farm!");
                        return false;
                    }   
                }
                else
                {
                    AddErrors("No matching Farms found!");
                    return false;
                }
            }
            else
            {
                AddErrors("There are no farms to delete!");
                return false;
            }
        }
        catch (Exception e)
        {
            AddErrors("Error: " + e);
            return false;
        }
    }
    // </editor-fold>
    
//******************************************************************************
    // This section deals with error reporting to the user interface
    
    // <editor-fold defaultstate="collapsed">
    
    /**
     * Adds an error report to the Collection of Strings used for storing errors
     * @param e a String object representing the error.
     */
        
    public void AddErrors(String e)
    {
        errors.add(e);
    }
    
    /**
     * Returns the entire collection of errors created while system has been running
     * @return
     */
    public ArrayList<String> GetAllErrors()
    {
        return errors;
    }
    
    /**
     * Returns the most recent error put in the Collection of errors.
     * @return
     */
    public String GetLastError()
    {
        return errors.get(errors.size()-1);
    }
    
    // </editor-fold>
    
//******************************************************************************
    /* This section deals with getting the calculated times and populating the
        tables stored in the core system
    */
    
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Checks if the Herd objects associated Cow objects all have valid MilkYields
     * @param aHerd the herd Object being queried.
     * @return 
     */
    public boolean checkHerd(Herd aHerd)
    {
        return calcMaxT.allCowsHaveMilk(aHerd);
    }
    
    /**
     * Returns the average for a Herd
     * @param aHerd the Herd object being queried.
     * @return 
     */
    public int getAverage(Herd aHerd)
    {
        return calcMaxT.herdMilkAverage(aHerd);
    }
    
    /**
     * Returns the String representation of the morning milk Yield for a Herd object
     * @param aHerd the Herd object being queried.
     * @return 
     */
    public String getMaxTAmTime(Herd aHerd)
    {
        return calcMaxT.amMaxTTime(aHerd);
    }
    
    /**
     * Returns the String representation of the evening milk Yield for a Herd object
     * @param aHerd the Herd object being queried.
     * @return 
     */
    public String getMaxTPmTime(Herd aHerd)
    {
        return calcMaxT.pmMaxTTime(aHerd);
    }
    
    /**
     * Adds a Row of milk volume takings to the MilkTable object associated with
     * the MilkInterval object
     * @param interval The identifier for the MilkTable
     * @param daily The daily amount of milk, this is the Key in the MilkTable
     * @param am The AM milk volume.
     * @param pm The PM milk volume.
     */
    public void addRow(MilkInterval interval, int daily, int am, int pm)
    {
        MilkTable addRowTable = new MilkTable(); 
        for (MilkTable table: calcMaxT.getMilkTables())
        {
            if(interval == table.getMilkInterval())
            {
                addRowTable = table;
            }
        }
        addRowTable.populateRow(daily, am, pm);
    }
    
    /**
     * Returns the MilkYield values from the MilkTable object associated with the
     * MilkInterval and has the daily key
     * @param interval The MilkInterval to select the right MilkTable 
     * @param daily The Key in the MilkTable representing the daily milk volume
     * @return 
     */
    public Integer[] getRow(MilkInterval interval, int daily)
    {
        MilkTable getRowTable = new MilkTable();
        for (MilkTable table: calcMaxT.getMilkTables())
        {
            if(interval == table.getMilkInterval())
            {
                getRowTable = table;
            }
        }    
        return getRowTable.getRow(daily);
    }
    
    /**
     * Returns the CalculateMaxT object associated with MaxTCoord object.
     * @return
     */
    public CalculateMaxT getCalcMaxT()
    {
        return calcMaxT;
    }
    
    /**
     * Set up the MilkTables on first run of application.
     */
    private void initialiseMilkTable()
    {
        addRow(MilkInterval.EIGHT_16, 20, 13, 7);
        
        addRow(MilkInterval.EIGHT_16, 21, 14, 7);
        addRow(MilkInterval.EIGHT_16, 22, 15, 7);
        addRow(MilkInterval.EIGHT_16, 23, 15, 8);
        addRow(MilkInterval.EIGHT_16, 24, 16, 8);
        addRow(MilkInterval.EIGHT_16, 25, 17, 8);
        addRow(MilkInterval.NINE_15, 20, 12, 8);
        addRow(MilkInterval.NINE_15, 21, 13, 8);
        addRow(MilkInterval.NINE_15, 22, 14, 8);
        addRow(MilkInterval.NINE_15, 23, 14, 9);
        addRow(MilkInterval.NINE_15, 24, 15, 9);
        addRow(MilkInterval.NINE_15, 25, 16, 9);
                
    }
    //</editor-fold>
}
