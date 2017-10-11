/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import maxtcore.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author acer
 */
public class MaxTGUI extends javax.swing.JFrame {

    // The coordinator object for communicating with the core system
    private final MaxTCoord maxTCoord;
    // ArraysLists for populating farm, herd and cow lists
    private ArrayList<Farm> farms;
    private ArrayList<Herd> herds;
    private ArrayList<Cow> cows;
    // The MilkInterval class
    private MilkInterval milkInterval;
    // True or false? has the user edited a field.
    private boolean farmNameEdit;
    private boolean farmLocationEdit;
    private boolean herdNameEdit;
    // Error reporters
    final String[] noHerds = {"No herds currently available"};
    final String[] noFarms = {"No farms currently available"};
    final String[] noCows = {"No cows currently available"};
    /**
     * Creates new form MaxTGUI
     */
    public MaxTGUI() {
        initComponents();
        systemInfoTextArea.setText("System Information: This is only a prototype application. \r\n\r\n" +
                "Functionality is limited to navigation and examples of system functions, " +
                "which will be displayed in this area.");
        maxTCoord = new MaxTCoord();
        displayFarmList();
        displayHerdList();
        displayCowList();
    }
    
//**************************************************************************
    // The methods in this section are concerned with the main screen

    // <editor-fold defaultstate="collapsed"> 
    private void displayHerdStatistics()
    {
        if (selectHerdComboBox.isEnabled() && cows != null) 
        {
            Herd theHerd = herds.get(selectHerdComboBox.getSelectedIndex());
            if(maxTCoord.checkHerd(theHerd) && cows.size() > 0)
            {
                statisticsTextArea.setText("For the selected Herd: " 
                + herds.get(selectHerdComboBox.getSelectedIndex()) + "\r\n\r\n"
                + "Number of Cows: " + cows.size() + "\r\n\r\n"
                + "Average Milk Yield: " + maxTCoord.getAverage(theHerd) + "\r\n\r\n"
                + "MaxT Value - AM milking: " + maxTCoord.getMaxTAmTime(theHerd) + "\r\n"
                + "               - PM milking: " + maxTCoord.getMaxTPmTime(theHerd));
            }
            else
            {
                statisticsTextArea.setText("For the selected Herd: " 
                + herds.get(selectHerdComboBox.getSelectedIndex()) + "\r\n\r\n"
                + "Number of Cows: " + cows.size() + "\r\n\r\n"
                + "Average Milk Yield: " + "\r\n\r\n"
                + "MaxT Value: ");
            }
        
        }
        else
        {
            statisticsTextArea.setText("No herd data currently available");
        }
    }


    private void displayFarmList()
    {
        //selectFarmComboBox.removeAllItems();
        farms = new ArrayList<>(maxTCoord.getFarms());
        if (farms.size() > 0)
        {
            Collections.sort(farms);
            selectFarmComboBox.setModel(new DefaultComboBoxModel(farms.toArray()));
            selectFarmComboBox.setEnabled(true);
            addHerdButton.setEnabled(true);
            jTabbedPane1.setEnabledAt(2, true);
        }
        else
        {
            selectFarmComboBox.addItem(noFarms);
            selectFarmComboBox.setEnabled(false);
            addHerdButton.setEnabled(false);
            jTabbedPane1.setEnabledAt(2, false);
        }
    }

    private void displayHerdList()
    {
        //selectHerdComboBox.removeAllItems();

        if (selectFarmComboBox.isEnabled())
        {
            Farm theFarm = farms.get(selectFarmComboBox.getSelectedIndex());
            herds = new ArrayList<>(maxTCoord.getHerds(theFarm));
            if (herds.size() > 0)
            {
                Collections.sort(herds);
                selectHerdComboBox.setModel(new DefaultComboBoxModel(herds.toArray()));              
                selectHerdComboBox.setEnabled(true);
            }
            else
            {
                selectHerdComboBox.addItem(noHerds);
                selectHerdComboBox.setEnabled(false);
                addCowButton.setEnabled(false);
            }
        }
        else
        {
            selectHerdComboBox.addItem(noHerds);
            selectHerdComboBox.setEnabled(false);
            addCowButton.setEnabled(false);
        }
    }

    private void displayCowList()
    {
        if (selectHerdComboBox.isEnabled())
        {
            addCowButton.setEnabled(true);
            Herd theHerd = herds.get(selectHerdComboBox.getSelectedIndex());
            cows = new ArrayList<>(maxTCoord.getCows(theHerd));
            if (cows.size() > 0)
            {
                Collections.sort(cows);
                selectCowList.setListData(cows.toArray());
                selectCowList.setEnabled(true);
            }
            else
            {
                selectCowList.setListData(noCows);
                selectCowList.setEnabled(false);
                milkTakingButton.setEnabled(false);
                jTabbedPane1.setEnabledAt(3, false);
            }
        }
        else
        {
            selectCowList.setListData(noCows);
            selectCowList.setEnabled(false);
            milkTakingButton.setEnabled(false);
            jTabbedPane1.setEnabledAt(3, false);
        }
        displayHerdStatistics();
    }

    // </editor-fold>
     
//***************************************************************************
    // The methods in this section are concerned with the Add Farm page

    // <editor-fold defaultstate="collapsed"> 

    private void addFarm() 
    {
        farmNameEdit = false;
        farmLocationEdit = false;
        farmSaveButton.setEnabled(false);
        currentFarmList.removeAll();
        if (farms.size() > 0)
        {
            currentFarmList.setListData(farms.toArray());
            currentFarmList.setSelectedIndex(selectFarmComboBox.getSelectedIndex());
        }
        else
        {
            currentFarmList.setListData(noFarms);
        }
    }

    private void saveFarm() 
    {
        if(maxTCoord.addFarm(farmNameField.getText(), farmLocationField.getText()))
        {
            systemInfoTextArea.setText("System Information: A Farm has been added to the system");
            displayFarmList();
            addFarm();
        }
        else
        {
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
        
    }

    private void resetFarm() 
    {
        farmNameField.setText("User Input");
        farmNameEdit = false;
        farmLocationField.setText("User Input");
        farmLocationEdit = false;
        farmSaveButton.setEnabled(false);
        systemInfoTextArea.setText("System Information: Re-enter the Farm details.");
    }
    
    private void setFarmSaveButton() 
    {
        if (farmNameEdit && farmLocationEdit)
        {
            farmSaveButton.setEnabled(true);
        }
    }

    // </editor-fold>

//******************************************************************************
    // The methods in this section are concerned with the Add Herd page
    
    // <editor-fold defaultstate="collapsed"> 
    private void addHerd() {
        
        herdNameEdit = false;
        herdSaveButton.setEnabled(false);
        
        if (!farms.isEmpty())
        {
            currentSelectedFarm.setModel(new DefaultComboBoxModel(farms.toArray()));
            currentSelectedFarm.setSelectedIndex(selectFarmComboBox.getSelectedIndex());
            herds = new ArrayList<>(maxTCoord.getHerds(farms.get(currentSelectedFarm.getSelectedIndex())));
            if (!herds.isEmpty())
            {
                currentHerdList.setListData(herds.toArray());
                currentHerdList.setSelectedIndex(selectHerdComboBox.getSelectedIndex());
            }
            else
            {
                currentHerdList.setListData(noHerds);
            }
            
        }
        else
        {
            currentSelectedFarm.setModel(new DefaultComboBoxModel(noFarms));
            currentHerdList.setListData(noHerds);
        }
        
    }
    
    /**
     * Method to action storing a herd in the system
     */
    private void saveHerd() 
    {
        //TODO: validate fields or wait for error?
        if (maxTCoord.addHerd(herdNameField.getText(),
                milkInterval,
                farms.get(currentSelectedFarm.getSelectedIndex())))
        {
            systemInfoTextArea.setText("System Information: A Herd has been added to the Farm.");
            addHerd();
            displayHerdList();
        }
        else
        {
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
    }
    
    /**
     * 
     */
    private void resetHerd() 
    {
        herdNameField.setText("User input");
        herdNameEdit = false;
        eightHourRadioButton.setSelected(false);
        nineHourRadioButton.setSelected(false);
        herdSaveButton.setEnabled(false);
        systemInfoTextArea.setText("System Information: Re-enter the Herd details.");
    }
    
    private void selectEightHour() 
    {
        if (eightHourRadioButton.isSelected()) {
            systemInfoTextArea.setText("System Information: 8 and 16 hour milking interval selected.");
            milkInterval = MilkInterval.EIGHT_16;
            nineHourRadioButton.setSelected(false);
        } else {
            systemInfoTextArea.setText("System Information: Unselected 8 & 16 hour milk interval. " +
                    "9 & 15 hour interval selected instead");
            milkInterval = MilkInterval.NINE_15;
            nineHourRadioButton.setSelected(true);
        }
    }
    
    private void selectNineHour() 
    {
        if (nineHourRadioButton.isSelected()) {
            systemInfoTextArea.setText("System Information: 9 and 15 hour milking interval selected");
            milkInterval = MilkInterval.NINE_15;
            eightHourRadioButton.setSelected(false);
        } else {
            systemInfoTextArea.setText("System Information: Unselected 9 & 15 hour milking interval. " +
                    "8 & 16 hour interval selected instead.");
            milkInterval = MilkInterval.EIGHT_16;
            eightHourRadioButton.setSelected(true);
        }
    }
    
    // </editor-fold>
    
//******************************************************************************
    //The methods in this section are concerned with the Milk Taking Page
    
    // <editor-fold defaultstate="collapsed">
    private void addMilkTaking() 
    {
       currentCowList.removeAll();
       
       if(!herds.isEmpty())
       {
            currentHerdComboBox.setModel(new DefaultComboBoxModel(herds.toArray()));
            currentHerdComboBox.setSelectedIndex(selectHerdComboBox.getSelectedIndex());
            getCurrentCowList();
       }
       else
       {
           currentHerdComboBox.setModel(new DefaultComboBoxModel(noHerds));
       }
       
       
       if (!selectCowList.isSelectionEmpty())
       {
           Cow theCow = cows.get(selectCowList.getSelectedIndex());
           milkTakingSaveButton.setEnabled(true);
           currentCowField.setText(theCow.getCowId());
           cowMilkIntervalField.setText("Cow "+ theCow.getCowId() + " is milked on " 
                   + herds.get(currentHerdComboBox.getSelectedIndex()).getHerdInterval());
           // Add or Update milk Yields?
           if(theCow.hasValidMilkYield())
           {
               milkTakingSaveButton.setText("Update");
               MilkYield milkYield = theCow.getMilkYield();
               amMilkYieldSpinner.setValue(milkYield.getAmYield());
               pmMilkYieldSpinner.setValue(milkYield.getPmYield());
           }
           else
           {
               milkTakingSaveButton.setText("Add");
               amMilkYieldSpinner.setValue(7); // get minimum value from datatable
               pmMilkYieldSpinner.setValue(7); // get minimum value from datatable
           }
       }
       else
       {
           systemInfoTextArea.setText("System Information: No cow currently selected to add Milk Yield to.");
           milkTakingSaveButton.setEnabled(false);
       }
    }

    private void getCurrentCowList() {
        cows = new ArrayList<>(maxTCoord.getCows(herds.get(currentHerdComboBox.getSelectedIndex())));
        if (!cows.isEmpty())
        {
            Collections.sort(cows);
            currentCowList.setListData(cows.toArray());
            if(!selectCowList.isSelectionEmpty())
            {
                currentCowList.setSelectedIndex(selectCowList.getSelectedIndex());
            }
            else
            {
                currentCowList.setSelectedIndex(0);
            }
            
        }
        else
        {
            currentCowList.setListData(noCows);
        }
    }
    
    private void saveMilkTaking() 
    {
        Cow theCow = cows.get(selectCowList.getSelectedIndex());
        if(maxTCoord.addMilkTaking(theCow,
                    (int)amMilkYieldSpinner.getValue(),
                    (int)pmMilkYieldSpinner.getValue()))
        {
           systemInfoTextArea.setText("System Information: The milk taking for cow " 
                   + theCow.getCowId() + " has been added to the system."); 
           displayHerdStatistics();
           addMilkTaking();
        }
        else
        {
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
    }
    
    private void deleteMilkTaking() 
    {
        systemInfoTextArea.setText("System Information: The milk taking for the cow has been deleted from the system.");
        displayHerdStatistics();
        addMilkTaking();
    }
    
    private void resetMilkTaking() 
    {
        amMilkYieldSpinner.setValue(0);
        pmMilkYieldSpinner.setValue(0);
        systemInfoTextArea.setText("System Information: Re-enter the milk taking values.");
    }
    
    // </editor-fold>
    
//******************************************************************************
    //The methods in this section are concerned with the right-sidebar
    
    // <editor-fold defaultstate="collapsed">
    
    private void addCow() 
    {
        if (maxTCoord.addCow(herds.get(selectHerdComboBox.getSelectedIndex()),
                                farms.get(selectFarmComboBox.getSelectedIndex())))
        {
            systemInfoTextArea.setText("System Information: A Cow has been added to the Herd: " +
                    herds.get(selectHerdComboBox.getSelectedIndex()));
            displayCowList();
            if (jTabbedPane1.getSelectedIndex() == 3)
            {
                addMilkTaking();
            }
        }
        else
        {
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }      
    }
    
    private void deleteCow() 
    {
        try
        {
            if (maxTCoord.deleteACow(cows.get(selectCowList.getSelectedIndex()),
                herds.get(selectHerdComboBox.getSelectedIndex())))
            {
                systemInfoTextArea.setText("System Information: A Cow has been deleted from the Herd.");
                displayCowList();
                addMilkTaking();
            }
            else
            {
                systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
            }
        }
        catch(Exception e)
        {
            maxTCoord.AddErrors("Delete Error: " + e);
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
        
    }
    
    private void deleteHerd() 
    {
        try
        {
            if(maxTCoord.deleteAHerd(herds.get(selectHerdComboBox.getSelectedIndex()),
                    farms.get(selectFarmComboBox.getSelectedIndex())))
            {
                systemInfoTextArea.setText("System Information: A Herd has been deleted from the Farm.");
                displayHerdList();
                addHerd();
                displayCowList();
                addMilkTaking();
            }
            else
            {
                systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
            }
        }
        catch(Exception e)
        {
            maxTCoord.AddErrors("Delete Error: " + e);
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
        
    }
    
    private void deleteFarm() 
    {
        try
        {
            if (maxTCoord.deleteAFarm(farms.get(selectFarmComboBox.getSelectedIndex())))
            {
                systemInfoTextArea.setText("System Information: A Farm has been deleted from the system.");
                displayFarmList();
                addFarm();
                displayHerdList();
                addHerd();
                displayCowList();
                addMilkTaking();
            }
            else
            {
                systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
            }
        }
        catch(Exception e)
        {
            maxTCoord.AddErrors("Delete Error: " + e);
            systemInfoTextArea.setText("System Information: " + maxTCoord.GetLastError());
        }
        
        
    }
    
    // </editor-fold>
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainScreenPanel = new javax.swing.JPanel();
        selectFarmLabel = new javax.swing.JLabel();
        selectFarmComboBox = new javax.swing.JComboBox();
        selectHerdLabel = new javax.swing.JLabel();
        selectHerdComboBox = new javax.swing.JComboBox();
        selectCowLabel = new javax.swing.JLabel();
        selectCowScrollPane = new javax.swing.JScrollPane();
        selectCowList = new javax.swing.JList();
        statisticsLabel = new javax.swing.JLabel();
        statisticsScrollPane = new javax.swing.JScrollPane();
        statisticsTextArea = new javax.swing.JTextArea();
        addFarmPanel = new javax.swing.JPanel();
        addFarmLabel = new javax.swing.JLabel();
        farmNameLabel = new javax.swing.JLabel();
        farmNameField = new javax.swing.JTextField();
        farmLocationLabel = new javax.swing.JLabel();
        farmLocationField = new javax.swing.JTextField();
        currentFarmScrollPanel = new javax.swing.JScrollPane();
        currentFarmList = new javax.swing.JList();
        currentFarmLabel = new javax.swing.JLabel();
        farmSaveButton = new javax.swing.JButton();
        farmResetButton = new javax.swing.JButton();
        addHerdPanel = new javax.swing.JPanel();
        addHerdLabel = new javax.swing.JLabel();
        currentSelectedFarm = new javax.swing.JComboBox();
        herdNameLabel = new javax.swing.JLabel();
        herdNameField = new javax.swing.JTextField();
        milkingIntervalPanel = new javax.swing.JPanel();
        eightHourRadioButton = new javax.swing.JRadioButton();
        nineHourRadioButton = new javax.swing.JRadioButton();
        currentHerdPanel = new javax.swing.JScrollPane();
        currentHerdList = new javax.swing.JList();
        currentHerdLabel = new javax.swing.JLabel();
        herdSaveButton = new javax.swing.JButton();
        herdResetButton = new javax.swing.JButton();
        milkTakingsPanel = new javax.swing.JPanel();
        milkTakingsLabel = new javax.swing.JLabel();
        currentCowField = new javax.swing.JTextField();
        currentHerdComboBox = new javax.swing.JComboBox();
        currentCowPanel = new javax.swing.JScrollPane();
        currentCowList = new javax.swing.JList();
        currentCowLabel = new javax.swing.JLabel();
        cowMilkIntervalField = new javax.swing.JTextField();
        milkIntervalPanel = new javax.swing.JPanel();
        amMilkYieldLabel = new javax.swing.JLabel();
        amMilkYieldSpinner = new javax.swing.JSpinner();
        pmMilkYieldLabel = new javax.swing.JLabel();
        pmMilkYieldSpinner = new javax.swing.JSpinner();
        milkTakingSaveButton = new javax.swing.JButton();
        milkTakingResetButton = new javax.swing.JButton();
        milkTakingDeleteButton = new javax.swing.JButton();
        systemInfoTextArea = new javax.swing.JTextArea();
        exitButton = new javax.swing.JButton();
        milkTakingButton = new javax.swing.JButton();
        addCowButton = new javax.swing.JButton();
        addHerdButton = new javax.swing.JButton();
        addFarmButton = new javax.swing.JButton();
        deleteCowButton = new javax.swing.JButton();
        deleteHerdButton = new javax.swing.JButton();
        deleteFarmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculate MaxT");
        setPreferredSize(new java.awt.Dimension(820, 640));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(690, 460));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        selectFarmLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectFarmLabel.setText("Select A Farm");

        selectFarmComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Farm 1", "Farm 2", "Farm 3", "Farm 4" }));
        selectFarmComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectFarmComboBoxItemStateChanged(evt);
            }
        });

        selectHerdLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectHerdLabel.setText("Select A Herd");

        selectHerdComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Herd 1", "Herd 2", "Herd 3", "Herd 4" }));
        selectHerdComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectHerdComboBoxItemStateChanged(evt);
            }
        });

        selectCowLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectCowLabel.setText("Select A Cow");

        selectCowList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1001", "1002", "1003", "1004", "1005" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        selectCowList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectCowListMousePressed(evt);
            }
        });
        selectCowList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                selectCowListValueChanged(evt);
            }
        });
        selectCowScrollPane.setViewportView(selectCowList);

        statisticsLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        statisticsLabel.setText("Herd Statistics");

        statisticsTextArea.setEditable(false);
        statisticsTextArea.setColumns(20);
        statisticsTextArea.setRows(5);
        statisticsTextArea.setText("For the selected herd {herdName}\n\nNumber of Cows:\n\nAverage Milk Yield: \n\nMaxT Value:\n");
        statisticsScrollPane.setViewportView(statisticsTextArea);

        javax.swing.GroupLayout mainScreenPanelLayout = new javax.swing.GroupLayout(mainScreenPanel);
        mainScreenPanel.setLayout(mainScreenPanelLayout);
        mainScreenPanelLayout.setHorizontalGroup(
            mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainScreenPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainScreenPanelLayout.createSequentialGroup()
                        .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainScreenPanelLayout.createSequentialGroup()
                                .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectFarmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(mainScreenPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(selectFarmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectHerdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(mainScreenPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(selectHerdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mainScreenPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(statisticsLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainScreenPanelLayout.createSequentialGroup()
                                .addComponent(selectCowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addComponent(selectCowScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(statisticsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        mainScreenPanelLayout.setVerticalGroup(
            mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainScreenPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFarmLabel)
                    .addComponent(selectHerdLabel)
                    .addComponent(selectCowLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainScreenPanelLayout.createSequentialGroup()
                        .addGroup(mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectFarmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectHerdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statisticsLabel))
                    .addComponent(selectCowScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Main Screen", mainScreenPanel);

        addFarmLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addFarmLabel.setText("Complete the Farm information");

        farmNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        farmNameLabel.setText("Farm Name:");

        farmNameField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        farmNameField.setText("User input");
        farmNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                farmNameFieldKeyPressed(evt);
            }
        });

        farmLocationLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        farmLocationLabel.setText("Location:");

        farmLocationField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        farmLocationField.setText("User input");
        farmLocationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                farmLocationFieldKeyPressed(evt);
            }
        });

        currentFarmList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Farm 1", "Farm 2", "Farm 3", "Farm 4", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        currentFarmList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentFarmListMouseClicked(evt);
            }
        });
        currentFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                currentFarmListValueChanged(evt);
            }
        });
        currentFarmScrollPanel.setViewportView(currentFarmList);

        currentFarmLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        currentFarmLabel.setText("List of current Farms");

        farmSaveButton.setText("Save");
        farmSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                farmSaveButtonActionPerformed(evt);
            }
        });

        farmResetButton.setText("Reset");
        farmResetButton.setPreferredSize(new java.awt.Dimension(85, 60));
        farmResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                farmResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addFarmPanelLayout = new javax.swing.GroupLayout(addFarmPanel);
        addFarmPanel.setLayout(addFarmPanelLayout);
        addFarmPanelLayout.setHorizontalGroup(
            addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFarmPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addFarmLabel)
                    .addGroup(addFarmPanelLayout.createSequentialGroup()
                        .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmNameLabel)
                            .addComponent(farmLocationLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(farmNameField)
                            .addComponent(farmLocationField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentFarmScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFarmPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentFarmLabel)
                .addGap(25, 25, 25))
            .addGroup(addFarmPanelLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(farmSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(farmResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        addFarmPanelLayout.setVerticalGroup(
            addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFarmPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(addFarmLabel)
                .addGap(29, 29, 29)
                .addComponent(currentFarmLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addFarmPanelLayout.createSequentialGroup()
                        .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(farmNameLabel)
                            .addComponent(farmNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(farmLocationLabel)
                            .addComponent(farmLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(currentFarmScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(farmResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(farmSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        jTabbedPane1.addTab("Add Farm", addFarmPanel);

        addHerdLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addHerdLabel.setText("Complete the Herd information");

        currentSelectedFarm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Farm 1", "Farm 2", "Farm 3", "Farm 4" }));
        currentSelectedFarm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                currentSelectedFarmItemStateChanged(evt);
            }
        });

        herdNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        herdNameLabel.setText("Herd Name:");

        herdNameField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        herdNameField.setText("User input");
        herdNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                herdNameFieldKeyPressed(evt);
            }
        });

        milkingIntervalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Milking Interval", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        milkingIntervalPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        eightHourRadioButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        eightHourRadioButton.setText("8 and 16 hour");
        eightHourRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightHourRadioButtonActionPerformed(evt);
            }
        });

        nineHourRadioButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nineHourRadioButton.setText("9 and 15 hour");
        nineHourRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineHourRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout milkingIntervalPanelLayout = new javax.swing.GroupLayout(milkingIntervalPanel);
        milkingIntervalPanel.setLayout(milkingIntervalPanelLayout);
        milkingIntervalPanelLayout.setHorizontalGroup(
            milkingIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkingIntervalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(milkingIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eightHourRadioButton)
                    .addComponent(nineHourRadioButton))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        milkingIntervalPanelLayout.setVerticalGroup(
            milkingIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkingIntervalPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(eightHourRadioButton)
                .addGap(18, 18, 18)
                .addComponent(nineHourRadioButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        currentHerdList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Herd 1", "Herd 2", "Herd 3", "Herd 4" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        currentHerdList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentHerdListMouseClicked(evt);
            }
        });
        currentHerdList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                currentHerdListValueChanged(evt);
            }
        });
        currentHerdPanel.setViewportView(currentHerdList);

        currentHerdLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        currentHerdLabel.setText("List of current Herds");

        herdSaveButton.setText("Save");
        herdSaveButton.setPreferredSize(new java.awt.Dimension(85, 60));
        herdSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herdSaveButtonActionPerformed(evt);
            }
        });

        herdResetButton.setText("Reset");
        herdResetButton.setPreferredSize(new java.awt.Dimension(85, 60));
        herdResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herdResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addHerdPanelLayout = new javax.swing.GroupLayout(addHerdPanel);
        addHerdPanel.setLayout(addHerdPanelLayout);
        addHerdPanelLayout.setHorizontalGroup(
            addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addHerdPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addHerdPanelLayout.createSequentialGroup()
                        .addComponent(addHerdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentSelectedFarm, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addHerdPanelLayout.createSequentialGroup()
                        .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addHerdPanelLayout.createSequentialGroup()
                                .addComponent(herdNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(herdNameField))
                            .addComponent(milkingIntervalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(currentHerdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addHerdPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentHerdLabel)
                .addGap(27, 27, 27))
            .addGroup(addHerdPanelLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(herdSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(herdResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addHerdPanelLayout.setVerticalGroup(
            addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addHerdPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currentSelectedFarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addHerdLabel))
                .addGap(28, 28, 28)
                .addComponent(currentHerdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addHerdPanelLayout.createSequentialGroup()
                        .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(herdNameLabel)
                            .addComponent(herdNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(milkingIntervalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(currentHerdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(herdResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(herdSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        jTabbedPane1.addTab("Add Herd", addHerdPanel);

        milkTakingsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        milkTakingsLabel.setText("Complete the Milk Yield for Cow:");

        currentCowField.setEditable(false);
        currentCowField.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        currentCowField.setText("cowID");

        currentHerdComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Herd 1", "Herd 2", "Herd 3", "Herd 4" }));
        currentHerdComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                currentHerdComboBoxItemStateChanged(evt);
            }
        });

        currentCowList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1001", "1002", "1003", "1004", "1005" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        currentCowList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentCowListMouseClicked(evt);
            }
        });
        currentCowList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                currentCowListValueChanged(evt);
            }
        });
        currentCowPanel.setViewportView(currentCowList);

        currentCowLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        currentCowLabel.setText("Current List of Cows");

        cowMilkIntervalField.setEditable(false);
        cowMilkIntervalField.setText("Cow is milked on a {interval}");

        milkIntervalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Milkings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        amMilkYieldLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amMilkYieldLabel.setText("AM Milk Yield");

        amMilkYieldSpinner.setPreferredSize(new java.awt.Dimension(60, 25));

        pmMilkYieldLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pmMilkYieldLabel.setText("PM Milk Yield");

        pmMilkYieldSpinner.setPreferredSize(new java.awt.Dimension(60, 25));

        javax.swing.GroupLayout milkIntervalPanelLayout = new javax.swing.GroupLayout(milkIntervalPanel);
        milkIntervalPanel.setLayout(milkIntervalPanelLayout);
        milkIntervalPanelLayout.setHorizontalGroup(
            milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkIntervalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amMilkYieldLabel)
                    .addComponent(pmMilkYieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pmMilkYieldSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amMilkYieldSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 50, Short.MAX_VALUE))
        );
        milkIntervalPanelLayout.setVerticalGroup(
            milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkIntervalPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amMilkYieldLabel)
                    .addComponent(amMilkYieldSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(milkIntervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pmMilkYieldLabel)
                    .addComponent(pmMilkYieldSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        milkTakingSaveButton.setText("Add/Update");
        milkTakingSaveButton.setPreferredSize(new java.awt.Dimension(85, 60));
        milkTakingSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkTakingSaveButtonActionPerformed(evt);
            }
        });

        milkTakingResetButton.setText("Reset");
        milkTakingResetButton.setPreferredSize(new java.awt.Dimension(85, 60));
        milkTakingResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkTakingResetButtonActionPerformed(evt);
            }
        });

        milkTakingDeleteButton.setText("Delete");
        milkTakingDeleteButton.setPreferredSize(new java.awt.Dimension(85, 60));
        milkTakingDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkTakingDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout milkTakingsPanelLayout = new javax.swing.GroupLayout(milkTakingsPanel);
        milkTakingsPanel.setLayout(milkTakingsPanelLayout);
        milkTakingsPanelLayout.setHorizontalGroup(
            milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, milkTakingsPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(milkTakingsPanelLayout.createSequentialGroup()
                        .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cowMilkIntervalField)
                            .addComponent(milkIntervalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentCowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(milkTakingsPanelLayout.createSequentialGroup()
                        .addComponent(milkTakingsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentCowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addComponent(currentHerdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, milkTakingsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(currentCowLabel)
                .addGap(30, 30, 30))
            .addGroup(milkTakingsPanelLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(milkTakingSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(milkTakingDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(milkTakingResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        milkTakingsPanelLayout.setVerticalGroup(
            milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkTakingsPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(milkTakingsLabel)
                    .addComponent(currentCowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentHerdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(currentCowLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentCowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(milkTakingsPanelLayout.createSequentialGroup()
                        .addComponent(cowMilkIntervalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(milkIntervalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(milkTakingSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(milkTakingResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(milkTakingDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Milk Takings", milkTakingsPanel);

        systemInfoTextArea.setEditable(false);
        systemInfoTextArea.setColumns(20);
        systemInfoTextArea.setRows(5);
        systemInfoTextArea.setText("System Information:");
        systemInfoTextArea.setMargin(new java.awt.Insets(2, 10, 2, 2));
        systemInfoTextArea.setPreferredSize(new java.awt.Dimension(690, 80));

        exitButton.setText("Exit");
        exitButton.setPreferredSize(new java.awt.Dimension(85, 60));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        milkTakingButton.setText("Cows Milk");
        milkTakingButton.setPreferredSize(new java.awt.Dimension(85, 60));
        milkTakingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkTakingButtonActionPerformed(evt);
            }
        });

        addCowButton.setText("Add Cow");
        addCowButton.setPreferredSize(new java.awt.Dimension(85, 60));
        addCowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCowButtonActionPerformed(evt);
            }
        });

        addHerdButton.setText("Add Herd");
        addHerdButton.setPreferredSize(new java.awt.Dimension(85, 60));
        addHerdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHerdButtonActionPerformed(evt);
            }
        });

        addFarmButton.setText("Add Farm");
        addFarmButton.setPreferredSize(new java.awt.Dimension(85, 60));
        addFarmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFarmButtonActionPerformed(evt);
            }
        });

        deleteCowButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteCowButton.setText("Delete Cow");
        deleteCowButton.setPreferredSize(new java.awt.Dimension(85, 60));
        deleteCowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCowButtonActionPerformed(evt);
            }
        });

        deleteHerdButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteHerdButton.setText("Delete Herd");
        deleteHerdButton.setPreferredSize(new java.awt.Dimension(85, 60));
        deleteHerdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHerdButtonActionPerformed(evt);
            }
        });

        deleteFarmButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteFarmButton.setText("Delete Farm");
        deleteFarmButton.setPreferredSize(new java.awt.Dimension(85, 60));
        deleteFarmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFarmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(addHerdButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addFarmButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteCowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteHerdButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(addCowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(milkTakingButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(systemInfoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(milkTakingButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(addHerdButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteCowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteHerdButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(systemInfoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCowButtonActionPerformed
        // TODO add your handling code here:
        addCow();
    }//GEN-LAST:event_addCowButtonActionPerformed

    private void milkTakingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkTakingButtonActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_milkTakingButtonActionPerformed

    private void addHerdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHerdButtonActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_addHerdButtonActionPerformed

    private void addFarmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFarmButtonActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_addFarmButtonActionPerformed

    private void farmSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_farmSaveButtonActionPerformed
        // TODO add your handling code here:
        saveFarm();
    }//GEN-LAST:event_farmSaveButtonActionPerformed

    private void farmResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_farmResetButtonActionPerformed
        // TODO add your handling code here:
        resetFarm();
    }//GEN-LAST:event_farmResetButtonActionPerformed

    private void deleteFarmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFarmButtonActionPerformed
        // TODO add your handling code here:
        deleteFarm();
    }//GEN-LAST:event_deleteFarmButtonActionPerformed

    private void herdSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_herdSaveButtonActionPerformed
        // TODO add your handling code here:
        saveHerd();
    }//GEN-LAST:event_herdSaveButtonActionPerformed

    private void herdResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_herdResetButtonActionPerformed
        // TODO add your handling code here:
        resetHerd();
    }//GEN-LAST:event_herdResetButtonActionPerformed

    private void deleteHerdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHerdButtonActionPerformed
        // TODO add your handling code here:
        deleteHerd();
    }//GEN-LAST:event_deleteHerdButtonActionPerformed

    private void milkTakingSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkTakingSaveButtonActionPerformed
        // TODO add your handling code here:
        saveMilkTaking();
    }//GEN-LAST:event_milkTakingSaveButtonActionPerformed

    private void milkTakingDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkTakingDeleteButtonActionPerformed
        // TODO add your handling code here:
        deleteMilkTaking();
    }//GEN-LAST:event_milkTakingDeleteButtonActionPerformed

    private void milkTakingResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkTakingResetButtonActionPerformed
        // TODO add your handling code here:
        resetMilkTaking();
    }//GEN-LAST:event_milkTakingResetButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void deleteCowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCowButtonActionPerformed
        // TODO add your handling code here:
        deleteCow();
    }//GEN-LAST:event_deleteCowButtonActionPerformed

    private void eightHourRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightHourRadioButtonActionPerformed
        // TODO add your handling code here:
        selectEightHour();
        if (herdNameEdit && eightHourRadioButton.isSelected())
        {
            herdSaveButton.setEnabled(true);
        }
    }//GEN-LAST:event_eightHourRadioButtonActionPerformed

    private void nineHourRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineHourRadioButtonActionPerformed
        // TODO add your handling code here:
        selectNineHour();
        if (herdNameEdit && nineHourRadioButton.isSelected())
        {
            herdSaveButton.setEnabled(true);
        }
    }//GEN-LAST:event_nineHourRadioButtonActionPerformed

    private void selectFarmComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectFarmComboBoxItemStateChanged
        // TODO add your handling code here:
        displayHerdList();
        displayCowList();
    }//GEN-LAST:event_selectFarmComboBoxItemStateChanged

    private void selectHerdComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectHerdComboBoxItemStateChanged
        // TODO add your handling code here:
        displayCowList();
    }//GEN-LAST:event_selectHerdComboBoxItemStateChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        if(maxTCoord != null)
        {
            int index = jTabbedPane1.getSelectedIndex();
            switch(index)
            {
                case(0):
                {
                    displayFarmList();
                    displayHerdList();
                    displayCowList();
                    break;
                }
                case(1):
                {
                    addFarm();
                    break;
                }
                case(2):
                {
                    addHerd();
                    break;
                }
                case(3):
                {
                    addMilkTaking();
                }
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void farmNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_farmNameFieldKeyPressed
        // TODO add your handling code here:
        if (!(evt.getKeyChar()==27||evt.getKeyChar()==65535))
        {
            farmNameEdit = true;
        }
        setFarmSaveButton();
    }//GEN-LAST:event_farmNameFieldKeyPressed

    private void farmLocationFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_farmLocationFieldKeyPressed
        // TODO add your handling code here:
        if (!(evt.getKeyChar()==27||evt.getKeyChar()==65535))
        {
            farmLocationEdit = true;
        }
        setFarmSaveButton();
    }//GEN-LAST:event_farmLocationFieldKeyPressed

    private void herdNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_herdNameFieldKeyPressed
        // TODO add your handling code here:
        if (!(evt.getKeyChar()==27||evt.getKeyChar()==65535))
        {
            herdNameEdit = true;
        }
        if (herdNameEdit && (eightHourRadioButton.isSelected() || nineHourRadioButton.isSelected()))
        {
            herdSaveButton.setEnabled(true);
        }
    }//GEN-LAST:event_herdNameFieldKeyPressed

    private void selectCowListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectCowListMousePressed
        // TODO add your handling code here:
        // waits for a double click
        if (evt.getClickCount() == 2) 
        {
            systemInfoTextArea.setText("System Information: Double clicked on the list of cows.");
            jTabbedPane1.setSelectedIndex(3);
        }
    }//GEN-LAST:event_selectCowListMousePressed

    private void selectCowListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_selectCowListValueChanged
        // TODO add your handling code here:
        if (!selectCowList.isSelectionEmpty())
        {
            jTabbedPane1.setEnabledAt(3, true);
            milkTakingButton.setEnabled(true);
        }
        
    }//GEN-LAST:event_selectCowListValueChanged

    private void currentFarmListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_currentFarmListValueChanged
        // TODO add your handling code here:
        if (!currentFarmList.isSelectionEmpty()) {
            selectFarmComboBox.setSelectedIndex(currentFarmList.getSelectedIndex());
        }
        
    }//GEN-LAST:event_currentFarmListValueChanged

    private void currentFarmListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentFarmListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2)
        {
            jTabbedPane1.setSelectedIndex(2);
        }
    }//GEN-LAST:event_currentFarmListMouseClicked

    private void currentHerdListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_currentHerdListValueChanged
        // TODO add your handling code here:
        if(!currentHerdList.isSelectionEmpty())
        {
            addMilkTaking();
            displayHerdList();
            selectHerdComboBox.setSelectedIndex(currentHerdList.getSelectedIndex());
        }
        
        if (!herds.isEmpty())
        {
            addCowButton.setEnabled(true);
        }
    }//GEN-LAST:event_currentHerdListValueChanged

    private void currentHerdListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentHerdListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2)
        {
            selectHerdComboBox.setSelectedIndex(currentHerdList.getSelectedIndex());
            jTabbedPane1.setSelectedIndex(3);
        }
    }//GEN-LAST:event_currentHerdListMouseClicked

    private void currentCowListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_currentCowListValueChanged
        // TODO add your handling code here:
        
        if (!currentCowList.isSelectionEmpty())
        {
            selectCowList.setSelectedIndex(currentCowList.getSelectedIndex()); 
        }
     
    }//GEN-LAST:event_currentCowListValueChanged

    private void currentCowListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentCowListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            selectCowList.setSelectedIndex(currentCowList.getSelectedIndex());
            addMilkTaking();
        }
    }//GEN-LAST:event_currentCowListMouseClicked

    private void currentHerdComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currentHerdComboBoxItemStateChanged
        // TODO add your handling code here:
        selectHerdComboBox.setSelectedIndex(currentHerdComboBox.getSelectedIndex());
        getCurrentCowList();
    }//GEN-LAST:event_currentHerdComboBoxItemStateChanged

    private void currentSelectedFarmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currentSelectedFarmItemStateChanged
        // TODO add your handling code here:
        selectFarmComboBox.setSelectedIndex(currentSelectedFarm.getSelectedIndex());
    }//GEN-LAST:event_currentSelectedFarmItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MaxTGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaxTGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaxTGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaxTGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaxTGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCowButton;
    private javax.swing.JButton addFarmButton;
    private javax.swing.JLabel addFarmLabel;
    private javax.swing.JPanel addFarmPanel;
    private javax.swing.JButton addHerdButton;
    private javax.swing.JLabel addHerdLabel;
    private javax.swing.JPanel addHerdPanel;
    private javax.swing.JLabel amMilkYieldLabel;
    private javax.swing.JSpinner amMilkYieldSpinner;
    private javax.swing.JTextField cowMilkIntervalField;
    private javax.swing.JTextField currentCowField;
    private javax.swing.JLabel currentCowLabel;
    private javax.swing.JList currentCowList;
    private javax.swing.JScrollPane currentCowPanel;
    private javax.swing.JLabel currentFarmLabel;
    private javax.swing.JList currentFarmList;
    private javax.swing.JScrollPane currentFarmScrollPanel;
    private javax.swing.JComboBox currentHerdComboBox;
    private javax.swing.JLabel currentHerdLabel;
    private javax.swing.JList currentHerdList;
    private javax.swing.JScrollPane currentHerdPanel;
    private javax.swing.JComboBox currentSelectedFarm;
    private javax.swing.JButton deleteCowButton;
    private javax.swing.JButton deleteFarmButton;
    private javax.swing.JButton deleteHerdButton;
    private javax.swing.JRadioButton eightHourRadioButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField farmLocationField;
    private javax.swing.JLabel farmLocationLabel;
    private javax.swing.JTextField farmNameField;
    private javax.swing.JLabel farmNameLabel;
    private javax.swing.JButton farmResetButton;
    private javax.swing.JButton farmSaveButton;
    private javax.swing.JTextField herdNameField;
    private javax.swing.JLabel herdNameLabel;
    private javax.swing.JButton herdResetButton;
    private javax.swing.JButton herdSaveButton;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mainScreenPanel;
    private javax.swing.JPanel milkIntervalPanel;
    private javax.swing.JButton milkTakingButton;
    private javax.swing.JButton milkTakingDeleteButton;
    private javax.swing.JButton milkTakingResetButton;
    private javax.swing.JButton milkTakingSaveButton;
    private javax.swing.JLabel milkTakingsLabel;
    private javax.swing.JPanel milkTakingsPanel;
    private javax.swing.JPanel milkingIntervalPanel;
    private javax.swing.JRadioButton nineHourRadioButton;
    private javax.swing.JLabel pmMilkYieldLabel;
    private javax.swing.JSpinner pmMilkYieldSpinner;
    private javax.swing.JLabel selectCowLabel;
    private javax.swing.JList selectCowList;
    private javax.swing.JScrollPane selectCowScrollPane;
    private javax.swing.JComboBox selectFarmComboBox;
    private javax.swing.JLabel selectFarmLabel;
    private javax.swing.JComboBox selectHerdComboBox;
    private javax.swing.JLabel selectHerdLabel;
    private javax.swing.JLabel statisticsLabel;
    private javax.swing.JScrollPane statisticsScrollPane;
    private javax.swing.JTextArea statisticsTextArea;
    private javax.swing.JTextArea systemInfoTextArea;
    // End of variables declaration//GEN-END:variables
}
