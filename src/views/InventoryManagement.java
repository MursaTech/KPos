/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

/**
 *
 * @author Muaad
 */
public class InventoryManagement extends JFrame {

    JPanel addPanel;
    JButton bAdd;
    JComboBox comboUnits;
    JPanel containerPanel;
    JPanel inventoryPanel;
    JButton bDelete;
    JButton bEdit;
    JScrollPane jScrollPane1;
    JTable tblInventory;
    JLabel lblBuy;
    JLabel lblName;
    JLabel lblQuantity;
    JLabel lblSell;
    JLabel lblUnits;
    JTextField txtBuy;
    JTextField txtName;
    JTextField txtQuantity;
    JTextField txtSell;
    DefaultTableModel tblModel = new DefaultTableModel();
	
    public InventoryManagement() {
        createInventoryPanel();
    }
    
    public JPanel createInventoryPanel() {

        inventoryPanel = new JPanel();
        addPanel = new JPanel();
        lblName = new JLabel();
        txtName = new JTextField();
        txtQuantity = new JTextField();
        lblQuantity = new JLabel();
        txtBuy = new JTextField();
        lblBuy = new JLabel();
        txtSell = new JTextField();
        lblSell = new JLabel();
        bAdd = new JButton();
        lblUnits = new JLabel();
        comboUnits = new JComboBox();
        containerPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        tblInventory = new JTable(tblModel);
        bDelete = new JButton();
        bEdit = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inventoryPanel.setBackground(new Color(153, 204, 255));
        tblInventory.setBackground(new Color(153, 204, 255));

        addPanel.setBackground(new Color(0, 51, 102));
        addPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

        lblName.setBackground(new Color(153, 153, 255));
        lblName.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblName.setForeground(new Color(0, 0, 204));
        lblName.setText("Enter Item Name");
        lblName.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblName.setOpaque(true);

        lblQuantity.setBackground(new Color(153, 153, 255));
        lblQuantity.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblQuantity.setForeground(new Color(0, 0, 204));
        lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantity.setText("Quantity");
        lblQuantity.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblQuantity.setOpaque(true);

        lblBuy.setBackground(new Color(153, 153, 255));
        lblBuy.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblBuy.setForeground(new Color(0, 0, 204));
        lblBuy.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuy.setText("Buying Price");
        lblBuy.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblBuy.setOpaque(true);

        lblSell.setBackground(new Color(153, 153, 255));
        lblSell.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblSell.setForeground(new Color(0, 0, 204));
        lblSell.setHorizontalAlignment(SwingConstants.CENTER);
        lblSell.setText("Selling Price");
        lblSell.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblSell.setOpaque(true);

        bAdd.setBackground(new Color(255, 153, 51));
        bAdd.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bAdd.setText("Add Inventory");

        lblUnits.setBackground(new Color(153, 153, 255));
        lblUnits.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblUnits.setForeground(new Color(0, 0, 204));
        lblUnits.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnits.setText("Units");
        lblUnits.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblUnits.setOpaque(true);

        comboUnits.setModel(new DefaultComboBoxModel(new String[] { "Kilograms", "Grams", "Litre", "Bags", "Bottles", "Packets" }));
        comboUnits.setEditable(true);

        GroupLayout addPanelLayout = new GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUnits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboUnits, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addGap(32,32,32)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuy, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuy, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblSell, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSell, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(bAdd)
                .addContainerGap())
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(comboUnits, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(lblUnits, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(lblSell, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSell, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(lblBuy, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBuy, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(bAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        containerPanel.setBackground(new Color(0, 51, 102));
        containerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        
        jScrollPane1.setViewportView(tblInventory);

        //tblInventory.setFillsViewportHeight(true);
        //tblInventory.setShowGrid(true);
        tblInventory.setRowSelectionAllowed(true);
        tblInventory.setAutoCreateRowSorter(true);

        bDelete.setBackground(new Color(255, 153, 51));
        bDelete.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bDelete.setText("Delete");

        bEdit.setBackground(new Color(255, 153, 51));
        bEdit.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bEdit.setText("Edit");

        GroupLayout containerPanelLayout = new GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(containerPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bEdit, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDelete, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bDelete)
                    .addComponent(bEdit))
                .addContainerGap())
        );

        GroupLayout inventoryPanelLayout = new GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(containerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(containerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
		return inventoryPanel;
    }
    
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagement().setVisible(true);
            }
        });
    }
    
}
