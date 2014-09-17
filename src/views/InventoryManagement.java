/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.ViewHelpers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import com.toedter.calendar.JDateChooser;

import models.Stock;

import controllers.ClientController;

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
    public DefaultTableModel tblModel;
    
    ClientController controller;
	List<Map<String, String>> row = new ArrayList<Map<String,String>>();
	TreeMap<String, String> record;
	Map<String, String> params;
	EventHandler eHandler;
	 
	 Vector<String> elements = null;
	
    public InventoryManagement(ClientController controller) {
    	this.controller = controller;
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
        tblModel = new DefaultTableModel();
        tblInventory = new JTable(tblModel);
        bDelete = new JButton();
        bEdit = new JButton();
        eHandler = new EventHandler();

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
        
        txtSell.addActionListener(eHandler);

        bAdd.setBackground(new Color(255, 153, 51));
        bAdd.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bAdd.setText("Add Inventory");
        bAdd.addActionListener(eHandler);

        lblUnits.setBackground(new Color(153, 153, 255));
        lblUnits.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        lblUnits.setForeground(new Color(0, 0, 204));
        lblUnits.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnits.setText("Units");
        lblUnits.setBorder(new LineBorder(new Color(102, 102, 255), 2, true));
        lblUnits.setOpaque(true);

        comboUnits.setModel(new DefaultComboBoxModel(new String[] { "Kilograms", "Grams", "Litre", "Bags", "Bottles", "Packets" }));
        comboUnits.setEditable(true);
        
        tblModel.addColumn("Product");
        tblModel.addColumn("Quantity");
        tblModel.addColumn("Units");
        tblModel.addColumn("Buying Price");
        tblModel.addColumn("Selling Price");
        tblModel.addColumn("Date");

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
        bDelete.addActionListener(eHandler);

        bEdit.setBackground(new Color(255, 153, 51));
        bEdit.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bEdit.setText("Edit");
        bEdit.addActionListener(eHandler);

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
    
    class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bAdd || e.getSource() == txtSell) {
				if (Stock.where("name", txtName.getText()).size() == 0) {
					params = ViewHelpers.constructParamsMap("name", txtName.getText(), "quantity", txtQuantity.getText(),
							"units", comboUnits.getSelectedItem().toString(), "buying_price", txtBuy.getText(),
							"selling_price", txtSell.getText());
					record = new TreeMap<String, String>(Stock.create(new TreeMap<String, String>(params)));
					elements = new Vector<String>();
					elements.add(record.get("name"));
					elements.add(record.get("quantity"));
					elements.add(record.get("units"));
					elements.add(record.get("buying_price"));
					elements.add(record.get("selling_price"));
					elements.add(record.get("created_at"));
					tblModel.addRow(elements);
				}
				else {
//					TODO: Add to StockReserve
					String newQuantity = String.valueOf(Integer.parseInt(txtQuantity.getText()) + 
							Integer.parseInt(Stock.findBy("name", txtName.getText()).get("quantity")));
					params = ViewHelpers.constructParamsMap("name", txtName.getText(), "quantity", newQuantity,
							"units", comboUnits.getSelectedItem().toString(), "buying_price", txtBuy.getText(),
							"selling_price", txtSell.getText());
					Stock.findOrCreateBy("name", params, true);
					ViewHelpers.clearJTable(tblModel);
					controller.populateStocksTable(tblModel);
				}
				
			}
			if(e.getSource() == bDelete) {
				if (tblInventory.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select the item(s) to remove from inventory");
				}
				else {
					int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove selected item(s) from inventory?",
									"Remove", JOptionPane.YES_NO_OPTION);
					//System.out.println(choice);
					if (choice == 0) {
						for (int i = 0; i < tblInventory.getSelectedRows().length; i++) {
							String createdAt = (String) tblModel.getValueAt(tblInventory.getSelectedRows()[i], 5);
							Stock.delete(Stock.findBy("created_at", createdAt).get("id"));
						}
					}
				}
				txtName.requestFocus();
				ViewHelpers.clearJTable(tblModel);
				controller.populateStocksTable(tblModel);
			}
			
			if(e.getSource() == bEdit) {
				if(tblInventory.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select which row to edit.");
				}
				else {
					int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to edit selected item?",
							"Edit", JOptionPane.YES_NO_OPTION);
					if (choice == 0) {
						String name = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 0);
						String quantity = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 1);
						String units = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 2);
						String buying_price = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 3);
						String selling_price = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 4);
						String createdAt = (String) tblModel.getValueAt(tblInventory.getSelectedRow(), 5);
						params = ViewHelpers.constructParamsMap("name", name, "quantity", quantity, "units", units,
								"buying_price", buying_price, "selling_price", selling_price, "created_at", createdAt);
						Stock.findOrCreateBy("created_at", params, true);
					}
				}
				txtName.requestFocus();
				ViewHelpers.clearJTable(tblModel);
				controller.populateStocksTable(tblModel);
			}
		}
    	
    }
    
//    public static void main(String args[]) {
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InventoryManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InventoryManagement().setVisible(true);
//            }
//        });
//    }
    
}
