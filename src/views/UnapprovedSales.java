package views;

import helpers.ViewHelpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import models.Sale;
import models.SalesTransaction;
import models.Stock;

import views.SalesPanel.EventHandler;
import views.SalesPanel.PopupListener;

import controllers.ClientController;
import controllers.DBController;

/**
 *
 * @author Muaad
 */
public class UnapprovedSales extends javax.swing.JDialog {

    /**
     * Creates new form UnapprovedSales
     */
    public UnapprovedSales(ClientController controller) {
        super();
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        oldTotalLabel = new javax.swing.JLabel();
        showOldTotalLabel = new javax.swing.JLabel();
        newTotalLabel = new javax.swing.JLabel();
        showNewTotalLabel = new javax.swing.JLabel();
        approveButton = new javax.swing.JButton();
        printCheckBox = new javax.swing.JCheckBox();
        vatCheckBox = new javax.swing.JCheckBox();
        tblModel = new DefaultTableModel();
        
        popup = new JPopupMenu();
		menuEdit = new JMenuItem(" Edit record ");
	    menuEdit.addActionListener(eHandler);
		//menuEdit.setToolTipText("You can edit the quantity field only. Other fields will get adjusted accordingly.");
	    popup.add(menuEdit);
	    popup.addSeparator();
		menuRemove = new JMenuItem(" Remove record ");
	    menuRemove.addActionListener(eHandler);
	    popup.add(menuRemove);
	    popup.addSeparator();
		menuCancel = new JMenuItem(" Cancel transaction ");
		menuCancel.addActionListener(eHandler);
	    popup.add(menuCancel); 
	    
	    MouseListener popupListener = new PopupListener(popup);
	    tblSales.addMouseListener(popupListener);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        topPanel.setBackground(new java.awt.Color(153, 204, 255));

        lblHeader.setBackground(new java.awt.Color(153, 204, 255));
        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Unapproved Sales - Sale #3254");
        lblHeader.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));
        lblHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblHeader.setOpaque(true);

        contentPanel.setBackground(new java.awt.Color(0, 51, 102));
        
        tblModel.addColumn("Product");
        tblModel.addColumn("Quantity");
        tblModel.addColumn("Buying Price");
        tblModel.addColumn("Selling Price");
		tblModel.addColumn("Profit");
		tblModel.addColumn("VAT");
        tblModel.addColumn("Total");

        tblSales.setModel(tblModel);
        jScrollPane1.setViewportView(tblSales);

        jPanel1.setBackground(new java.awt.Color(0,51,102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        oldTotalLabel.setBackground(new java.awt.Color(153, 153, 255));
        oldTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oldTotalLabel.setForeground(new java.awt.Color(0, 0, 204));
        oldTotalLabel.setText("Old Total");
        oldTotalLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        oldTotalLabel.setOpaque(true);

        showOldTotalLabel.setBackground(new java.awt.Color(153, 153, 255));
        showOldTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        showOldTotalLabel.setForeground(new java.awt.Color(0, 0, 204));
        showOldTotalLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        showOldTotalLabel.setOpaque(true);

        newTotalLabel.setBackground(new java.awt.Color(153, 153, 255));
        newTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newTotalLabel.setForeground(new java.awt.Color(0, 0, 204));
        newTotalLabel.setText("New Total");
        newTotalLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        newTotalLabel.setOpaque(true);

        showNewTotalLabel.setBackground(new java.awt.Color(153, 153, 255));
        showNewTotalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        showNewTotalLabel.setForeground(new java.awt.Color(0, 0, 204));
        showNewTotalLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        showNewTotalLabel.setOpaque(true);

        approveButton.setBackground(new java.awt.Color(255, 153, 51));
        approveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        approveButton.setText("Approve");
        approveButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        approveButton.addActionListener(eHandler);

        printCheckBox.setBackground(new java.awt.Color(153, 153, 255));
        printCheckBox.setForeground(new java.awt.Color(0, 0, 204));
        printCheckBox.setText("Print");
        printCheckBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        printCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printCheckBoxActionPerformed(evt);
            }
        });

        vatCheckBox.setBackground(new java.awt.Color(153, 153, 255));
        vatCheckBox.setForeground(new java.awt.Color(0, 0, 204));
        vatCheckBox.setText("VAT");
        vatCheckBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        vatCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vatCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(oldTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showOldTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showNewTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printCheckBox)
                .addGap(17, 17, 17)
                .addComponent(vatCheckBox)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(printCheckBox)
                        .addComponent(vatCheckBox))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(showNewTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oldTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(showOldTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void printCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void vatCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == approveButton) {
				String id = lblHeader.getText().split("#")[1];
				double vat = 0.0;
				double total = 0.0;
				
				for (int i = 0; i < tblModel.getRowCount(); i++) {
					vat += Double.parseDouble(tblModel.getValueAt(i, 5).toString());
					total += Double.parseDouble(tblModel.getValueAt(i, 6).toString());
				}
				
				Map<String, String> params = ViewHelpers.constructParamsMap("approved", "YES", "total_amount", String.valueOf(total),
						"VAT", String.valueOf(vat));
				SalesTransaction.update(params, ViewHelpers.constructParamsMap("id", id));
				
				for(Map<String, String> sale : SalesTransaction.sales(id)) {
					Sale.update(ViewHelpers.constructParamsMap("approved", "YES"), ViewHelpers.constructParamsMap("id", sale.get("id")));
				}
				
				for(String key : updatedSales.keySet()) {
					System.out.println(updatedSales.get(key));
					updatedSales.get(key).put("approved", "YES");
					Sale.update(updatedSales.get(key), ViewHelpers.constructParamsMap("id", key));
				}

				for(String key : updatedStocks.keySet()) {
					String q = String.valueOf(Integer.parseInt(Stock.find(key).get("quantity")) - Integer.parseInt(updatedStocks.get(key).get("quantity")));
					updatedStocks.get(key).put("quantity", q);
					Stock.update(updatedStocks.get(key), ViewHelpers.constructParamsMap("id", key));
				}
				
				controller.destroyUnapprovedDialog();
			}
			
			if(e.getSource() == menuRemove) {
				if(tblSales.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!!", "Remove", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int grandTotal = 0;
					int selection = tblSales.getSelectedRow();
					try {
//						removeRow(selection);
					}
					catch (IndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					
					try {
						for (int i = 0; i < tblModel.getRowCount(); i++) {
							grandTotal += Integer.parseInt(tblModel.getValueAt(i,3).toString());							
						}
//						lblShowTotal.setText(grandTotal +"");
					} catch (ArrayIndexOutOfBoundsException e1) {}
					
				}
			}
			
			if(e.getSource() == menuEdit) {
				int row = tblSales.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Double click the cell you want to edit, press ENTER after editing,\n" +
							"right click again and click EDIT. If you want to make wholesale changes to a row, \nconsider just removing it and adding a\n" +
							"new one.");
				}
				else {
					System.out.println(tableData.get(row).get("stock_id"));
					double oldPrice = Double.parseDouble(tableData.get(row).get("price"));
					int oldQuantity = Integer.parseInt(tableData.get(row).get("quantity"));
					
					int newTotal = Integer.parseInt(tblModel.getValueAt(row, 3).toString()) * 
							Integer.parseInt(tblModel.getValueAt(row, 1).toString());
					if (vatCheckBox.isSelected()) {
						tblModel.setValueAt(0.16 * newTotal, row, 5);
					}
					else {
						tblModel.setValueAt(0.0, row, 5);
					}
					tblModel.setValueAt(newTotal + Double.parseDouble(tblModel.getValueAt(row, 5).toString()), row, 6);
					
					editRow(row, tblModel);
					
					double newPrice = Double.parseDouble(tableData.get(row).get("price"));
					int newQuantity = Integer.parseInt(tableData.get(row).get("quantity"));

					showNewTotalLabel.setText(tableData.get(row).get("total_amount"));
					
					stockChanged = newQuantity != oldQuantity;
					saleChanged = newQuantity != oldQuantity || newPrice != oldPrice;
					
					if (saleChanged) {
						saleParams = new HashMap<String, String>();
						saleParams.put("price", newPrice+"");
						saleParams.put("quantity", String.valueOf(newQuantity));
						saleParams.put("VAT", tblModel.getValueAt(row, 5).toString());
						saleParams.put("total_amount", tableData.get(row).get("total_amount"));
						
						updatedSales.put(tableData.get(row).get("sale_id"), saleParams);
						
						if (stockChanged) {
							stockParams = new HashMap<String, String>();
							stockParams.put("quantity", String.valueOf(newQuantity - oldQuantity));
							
							updatedStocks.put(tableData.get(row).get("stock_id"), stockParams);
						}
					}
//					Map<String, String> saleRecord = Sale.where("sales_transaction_id", salesTransactionId).get(row);
//					params = new HashMap<String, String>();
//					conditions = new HashMap<String, String>();
//					int newTotal = Integer.parseInt(tblModel.getValueAt(row, 3).toString()) * 
//							Integer.parseInt(tblModel.getValueAt(row, 1).toString());
//					if (vatCheckBox.isSelected()) {
//						tblModel.setValueAt(0.16 * newTotal, row, 5);
//					}
//					else {
//						tblModel.setValueAt(0.0, row, 5);
//					}
//					tblModel.setValueAt(newTotal + Double.parseDouble(tblModel.getValueAt(row, 5).toString()), row, 6);
//					editRow(row, tblModel);
//					int grandTotal = 0;
//					try {
//						for (int i = 0; i < tblModel.getRowCount(); i++) {
//							grandTotal += Double.parseDouble(tblModel.getValueAt(i,6).toString());							
//						}
//						lblShowTotal.setText(grandTotal +"");
//					} catch (ArrayIndexOutOfBoundsException e1) {}
//					params.put("quantity", tblModel.getValueAt(row, 1).toString());
//					params.put("total_amount", newTotal+"");
//					conditions.put("id", saleRecord.get("id"));
//					Sale.update(params, conditions);
//					
//					params = new HashMap<String, String>();
//					conditions = new HashMap<String, String>();
//					params.put("quantity", String.valueOf(Integer.parseInt(Stock.findBy("id", saleRecord.get("stock_id")).get("quantity"))
//							- (Integer.parseInt(tblModel.getValueAt(row, 1).toString()) - enteredQuantity)));
//					conditions.put("id", saleRecord.get("stock_id"));
//					Stock.update(params, conditions);
//					enteredQuantity = Integer.parseInt(tblModel.getValueAt(row, 1).toString());
//					TODO: Use created_at to get the right record
				}
			}
			
			if(e.getSource() == menuCancel) {
//				cancelTransaction();
			}
		}
    	
    }
    
    private void editRow(int selection, DefaultTableModel tblModel) {
		tableData.get(selection).remove("quantity");
		tableData.get(selection).put("quantity", tblModel.getValueAt(selection, 1).toString());
		tableData.get(selection).remove("price");
		tableData.get(selection).put("price", tblModel.getValueAt(selection, 3).toString());
		tableData.get(selection).remove("total_amount");
		tableData.get(selection).put("total_amount", tblModel.getValueAt(selection, 6).toString());
		tableData.get(selection).remove("VAT");
		tableData.get(selection).put("VAT", tblModel.getValueAt(selection, 5).toString());
	}
    
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UnapprovedSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UnapprovedSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UnapprovedSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UnapprovedSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                UnapprovedSales dialog = new UnapprovedSales(new ClientController(new DBController()));
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify
    private javax.swing.JButton approveButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblHeader;
    private javax.swing.JLabel newTotalLabel;
    private javax.swing.JLabel oldTotalLabel;
    private javax.swing.JCheckBox printCheckBox;
    public javax.swing.JLabel showNewTotalLabel;
    public javax.swing.JLabel showOldTotalLabel;
    private javax.swing.JTable tblSales;
    private javax.swing.JPanel topPanel;
    private javax.swing.JCheckBox vatCheckBox;
    private ClientController controller;
    public DefaultTableModel tblModel;
    JPopupMenu popup;
    JMenuItem menuEdit;
    JMenuItem menuRemove;
    JMenuItem menuCancel;
    EventHandler eHandler = new EventHandler();
    public Map<String, String> row;
    public List<Map<String, String>> tableData;
    Map<String, Map<String, String>> updatedSales = new HashMap<String, Map<String,String>>();
    Map<String, Map<String, String>> updatedStocks = new HashMap<String, Map<String,String>>();
    Map<String, String> stockParams;
    Map<String, String> saleParams;
    boolean saleChanged = false;
    boolean stockChanged = false;
    // End of variables declaration
}

