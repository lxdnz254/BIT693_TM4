/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

/**
 *
 * @author acer
 */
public class MaxTGUI extends javax.swing.JFrame {

    /**
     * Creates new form MaxTGUI
     */
    public MaxTGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainScreenPanel = new javax.swing.JPanel();
        addFarmPanel = new javax.swing.JPanel();
        addHerdPanel = new javax.swing.JPanel();
        milkTakingsPanel = new javax.swing.JPanel();
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

        javax.swing.GroupLayout mainScreenPanelLayout = new javax.swing.GroupLayout(mainScreenPanel);
        mainScreenPanel.setLayout(mainScreenPanelLayout);
        mainScreenPanelLayout.setHorizontalGroup(
            mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        mainScreenPanelLayout.setVerticalGroup(
            mainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Main Screen", mainScreenPanel);

        javax.swing.GroupLayout addFarmPanelLayout = new javax.swing.GroupLayout(addFarmPanel);
        addFarmPanel.setLayout(addFarmPanelLayout);
        addFarmPanelLayout.setHorizontalGroup(
            addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        addFarmPanelLayout.setVerticalGroup(
            addFarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Add Farm", addFarmPanel);

        javax.swing.GroupLayout addHerdPanelLayout = new javax.swing.GroupLayout(addHerdPanel);
        addHerdPanel.setLayout(addHerdPanelLayout);
        addHerdPanelLayout.setHorizontalGroup(
            addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        addHerdPanelLayout.setVerticalGroup(
            addHerdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Add Herd", addHerdPanel);

        javax.swing.GroupLayout milkTakingsPanelLayout = new javax.swing.GroupLayout(milkTakingsPanel);
        milkTakingsPanel.setLayout(milkTakingsPanelLayout);
        milkTakingsPanelLayout.setHorizontalGroup(
            milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        milkTakingsPanelLayout.setVerticalGroup(
            milkTakingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
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

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, systemInfoTextArea, org.jdesktop.beansbinding.ObjectProperty.create(), exitButton, org.jdesktop.beansbinding.BeanProperty.create("alignmentY"));
        bindingGroup.addBinding(binding);

        milkTakingButton.setText("Cows Milk");
        milkTakingButton.setPreferredSize(new java.awt.Dimension(85, 60));

        addCowButton.setText("Add Cow");
        addCowButton.setPreferredSize(new java.awt.Dimension(85, 60));

        addHerdButton.setText("Add Herd");
        addHerdButton.setPreferredSize(new java.awt.Dimension(85, 60));

        addFarmButton.setText("Add Farm");
        addFarmButton.setPreferredSize(new java.awt.Dimension(85, 60));

        deleteCowButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteCowButton.setText("Delete Cow");
        deleteCowButton.setPreferredSize(new java.awt.Dimension(85, 60));

        deleteHerdButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteHerdButton.setText("Delete Herd");
        deleteHerdButton.setPreferredSize(new java.awt.Dimension(85, 60));

        deleteFarmButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteFarmButton.setText("Delete Farm");
        deleteFarmButton.setPreferredSize(new java.awt.Dimension(85, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(systemInfoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(milkTakingButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addCowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addHerdButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addFarmButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteCowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteHerdButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel addFarmPanel;
    private javax.swing.JButton addHerdButton;
    private javax.swing.JPanel addHerdPanel;
    private javax.swing.JButton deleteCowButton;
    private javax.swing.JButton deleteFarmButton;
    private javax.swing.JButton deleteHerdButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mainScreenPanel;
    private javax.swing.JButton milkTakingButton;
    private javax.swing.JPanel milkTakingsPanel;
    private javax.swing.JTextArea systemInfoTextArea;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
