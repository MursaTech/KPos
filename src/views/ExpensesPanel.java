package views;

import helpers.ViewHelpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import models.Category;
import models.Expense;
import models.ExpenseTransaction;
import models.SalesTransaction;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.ClientController;

//import pos.SalesPanel.PopupListener;

/**
 *
 * @author Muaad
 */
public class ExpensesPanel {
	
	public ExpensesPanel(ClientController controller) {
		this.controller = controller;
	}
	
		JPanel expensePanel;
	     JButton buttonAddExpense;
	     JButton buttonSaveExpense;
	     JComboBox comboReason;
	     JPanel panelXp1;
	     JPanel panelXp2;
	     JPanel panelXp3;
	     JScrollPane scrollXp;
	     JLabel lblBalanceXp;
	     JLabel lblXpensePaid;
	     JLabel lblPaidXp;
	     JLabel lblReason;
	     JLabel lblShowBalanceXp;
	     JLabel lblShowTotalXp;
	     JLabel lblTotalXp;
	     JTable tblItemsXp;
	     JTextField txtPaidXp, txtXpensePaid;
		 DefaultTableModel tblModelXp;
		 JMenuItem menuRemove, menuEdit, menuCancel;
		 JPopupMenu popup;
		 ClientController controller;
		 List<Map<String, String>> row = new ArrayList<Map<String,String>>();
		 private String expenseTransactionId;
		 TreeMap<String, String> record;
		 Map<String, String> params;
		 
		 Vector<String> elements = null;
		 int xpense = 0;
		 
		 EventHandler eHandler = new EventHandler();
		 
	public JPanel createExpensePanel() {
    	expensePanel = new JPanel();
        panelXp1 = new JPanel();
        lblXpensePaid = new JLabel();
        txtXpensePaid = new JTextField();
        lblReason = new JLabel();
        comboReason = new JComboBox();
        buttonAddExpense = new JButton();
        panelXp2 = new JPanel();
        scrollXp = new JScrollPane();
        tblItemsXp = new JTable();
        panelXp3 = new JPanel();
        lblTotalXp = new JLabel();
        lblShowTotalXp = new JLabel();
        lblPaidXp = new JLabel();
        txtPaidXp = new JTextField();
        lblBalanceXp = new JLabel();
        lblShowBalanceXp = new JLabel();
        buttonSaveExpense = new JButton();

		popup = new JPopupMenu();
		menuEdit = new JMenuItem(" Edit record ");
	    menuEdit.addActionListener(eHandler);
	    popup.add(menuEdit);
	    popup.addSeparator();
		menuRemove = new JMenuItem(" Remove record ");
	    menuRemove.addActionListener(eHandler);
	    popup.add(menuRemove);
	    popup.addSeparator();
		menuCancel = new JMenuItem(" Cancel transaction ");
		menuCancel.addActionListener(eHandler);
	    popup.add(menuCancel); 
	    

        expensePanel.setBackground(new java.awt.Color(153, 204, 255));

        panelXp1.setBackground(new java.awt.Color(0, 51, 102));
        panelXp1.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        lblXpensePaid.setBackground(new java.awt.Color(153, 153, 255));
        lblXpensePaid.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblXpensePaid.setForeground(new java.awt.Color(0, 0, 204));
        lblXpensePaid.setText("Enter amount paid");
        lblXpensePaid.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblXpensePaid.setFocusable(false);
        lblXpensePaid.setOpaque(true);

        //txtXpensePaid.setBackground(new java.awt.Color(153, 153, 255));
        txtXpensePaid.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtPaidXp.addActionListener(eHandler);

        lblReason.setBackground(new java.awt.Color(153, 153, 255));
        lblReason.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblReason.setForeground(new java.awt.Color(0, 0, 204));
        lblReason.setText("As payment for");
        lblReason.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblReason.setFocusable(false);
        lblReason.setOpaque(true);

        comboReason.setBackground(new java.awt.Color(153, 153, 255));
        comboReason.setEditable(true);
        comboReason.setModel(new DefaultComboBoxModel(controller.expenseCategories().toArray(new String[controller.expenseCategories().size()])));

        buttonAddExpense.setBackground(new java.awt.Color(255, 153, 51));
        buttonAddExpense.setFont(new java.awt.Font("Tahoma", 1, 12));
        buttonAddExpense.setText("Add");
        buttonAddExpense.addActionListener(eHandler);
        
        GroupLayout jPanel3Layout = new GroupLayout(panelXp1);
        panelXp1.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
            		.addGap(20, 20, 20)
                    .addComponent(lblXpensePaid, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtXpensePaid, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(lblReason, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(comboReason, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonAddExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXpensePaid)
                    .addComponent(txtXpensePaid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReason)
                    .addComponent(comboReason, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddExpense))
                .addContainerGap())
        );

        panelXp2.setBackground(new java.awt.Color(0, 51, 102));
        panelXp2.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        /*tblItems.setAutoCreateRowSorter(true);
        tblItems.setBackground(new java.awt.Color(153, 153, 255));
        tblItems.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 3, true));
        tblItems.setFont(new java.awt.Font("Tahoma", 0, 14));*/
        
        tblModelXp = new DefaultTableModel();
		tblItemsXp = new JTable(tblModelXp);
		tblModelXp.addColumn("Amount");
		tblModelXp.addColumn("Reason");
	    MouseListener popupListener = new PopupListener(popup);
	    tblItemsXp.addMouseListener(popupListener);
        
        scrollXp.setViewportView(tblItemsXp);

        panelXp3.setBackground(new java.awt.Color(0, 51, 102));
        panelXp3.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        lblTotalXp.setBackground(new java.awt.Color(153, 153, 255));
        lblTotalXp.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTotalXp.setForeground(new java.awt.Color(0, 0, 204));
        lblTotalXp.setText("Total amount");
        lblTotalXp.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblTotalXp.setFocusable(false);
        lblTotalXp.setOpaque(true);

        lblShowTotalXp.setBackground(new java.awt.Color(153, 153, 255));
        lblShowTotalXp.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblShowTotalXp.setForeground(new java.awt.Color(0, 0, 204));
        lblShowTotalXp.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblShowTotalXp.setFocusable(false);
        lblShowTotalXp.setOpaque(true);

        lblPaidXp.setBackground(new java.awt.Color(153, 153, 255));
        lblPaidXp.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPaidXp.setForeground(new java.awt.Color(0, 0, 204));
        lblPaidXp.setText("Amount paid");
        lblPaidXp.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblPaidXp.setFocusable(false);
        lblPaidXp.setOpaque(true);

        lblBalanceXp.setBackground(new java.awt.Color(153, 153, 255));
        lblBalanceXp.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblBalanceXp.setForeground(new java.awt.Color(0, 0, 204));
        lblBalanceXp.setText("Balance");
        lblBalanceXp.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblBalanceXp.setFocusable(false);
        lblBalanceXp.setOpaque(true);

        lblShowBalanceXp.setBackground(new java.awt.Color(153, 153, 255));
        lblShowBalanceXp.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblShowBalanceXp.setForeground(new java.awt.Color(0, 0, 204));
        lblShowBalanceXp.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblShowBalanceXp.setFocusable(false);
        lblShowBalanceXp.setOpaque(true);

        buttonSaveExpense.setBackground(new java.awt.Color(255, 153, 51));
        buttonSaveExpense.setFont(new java.awt.Font("Tahoma", 1, 12));
        buttonSaveExpense.setText("Save Transaction");
        buttonSaveExpense.addActionListener(eHandler);

        GroupLayout jPanel6Layout = new GroupLayout(panelXp3);
        panelXp3.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalXp, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblShowTotalXp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPaidXp, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPaidXp, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBalanceXp, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblShowBalanceXp, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSaveExpense, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lblShowBalanceXp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveExpense)
                    .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblShowTotalXp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBalanceXp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaidXp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaidXp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblTotalXp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {lblPaidXp, lblShowTotalXp, txtPaidXp});

        jPanel6Layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {buttonSaveExpense, lblShowBalanceXp});
        
//        panelXp2.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 0;
//		panelXp2.add(panelXp3, c);
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 1;
//		panelXp2.add(scrollXp, c);

        GroupLayout jPanel4Layout = new GroupLayout(panelXp2);
        panelXp2.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(panelXp3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollXp))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollXp, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelXp3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout SalesPanelLayout = new GroupLayout(expensePanel);
        expensePanel.setLayout(SalesPanelLayout);
        SalesPanelLayout.setHorizontalGroup(
            SalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(SalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelXp1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelXp2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SalesPanelLayout.setVerticalGroup(
            SalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(SalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXp1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelXp2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
		return expensePanel;
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
	
	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonAddExpense) {
				elements = new Vector<String>(3);
				record = new TreeMap<String, String>();
				
				Map<String, String> category = Category.findOrCreateBy("name", ViewHelpers.constructParamsMap("name", 
						comboReason.getSelectedItem().toString()), false);

				try {
					xpense = Integer.parseInt(txtXpensePaid.getText());
				} catch (Exception e2) {}
				
				elements.add(xpense + "");
				elements.add((String)comboReason.getSelectedItem());
				
				if (ExpenseTransaction.count() > 0) {
					expenseTransactionId = String.valueOf(Integer.parseInt(ExpenseTransaction.last().get("id")) + 1);
				} else {
					expenseTransactionId = "1";
				}
				record.put("category_id", category.get("id"));
				record.put("amount", String.valueOf(xpense));
				record.put("expense_transaction_id", expenseTransactionId);
				record.put("user_id", controller.currentUser.get("user_id"));
				if (controller.isAdmin()) {
					record.put("approved", "YES");
				}
				else {
					record.put("approved", "NO");
				}
				row.add(record);

				tblModelXp.addRow(elements);
				
				comboReason.setModel(new DefaultComboBoxModel(controller.expenseCategories().toArray(new String[controller.expenseCategories().size()])));

				//tblItemsXp.setFillsViewportHeight(true);
				//tblItemsXp.setShowGrid(true);
				tblItemsXp.setRowSelectionAllowed(true);
				tblItemsXp.setAutoCreateRowSorter(true);
				tblItemsXp.setBackground(new Color(153,204,255));
				//tblItemsXp.setBorder(new LineBorder(new Color(153, 153, 255),3, true));
				tblItemsXp.setFont(new Font("Tahoma", 0, 14));
				int grandTotal = 0;
				for (int i = tblModelXp.getRowCount() - 1; i >= 0; i--) {
					grandTotal += Integer.parseInt(tblModelXp.getValueAt(i,
							0).toString());
				}
				lblShowTotalXp.setText(String.valueOf(grandTotal));

				txtPaidXp.setText("");
				txtPaidXp.requestFocus();
				lblShowBalanceXp.setText("");
			}

			if (e.getSource() == buttonSaveExpense || e.getSource() == txtPaidXp) {				
				String approved = "";
				if (controller.isAdmin()) {
					approved = "YES";
				}
				else {
					approved = "NO";
				}
				
				params = ViewHelpers.constructParamsMap("total_amount", lblShowTotalXp.getText(), "amount_paid", txtPaidXp.getText(),
						"user_id", controller.currentUser.get("user_id"), "approved", approved, 
						"balance", String.valueOf(Integer.parseInt(txtPaidXp.getText()) - Integer.parseInt(lblShowTotalXp.getText())));
				
				expenseTransactionId = ExpenseTransaction.create(new TreeMap<String, String>(params)).get("id");

				for(Map<String, String> xp : row) {
					xp.put("expense_transaction_id", expenseTransactionId);
					Expense.create(new TreeMap<>(xp));
				}
				
				for (int i = tblModelXp.getRowCount() - 1; i >= 0; i--) {
					tblModelXp.removeRow(i);
				}
				row.clear();

				// OR.....

				/*
				 * while(tblModel.getRowCount() > 0) { tblModel.removeRow(0); }
				 */

				// OR..... though not working yet.

				// tblModel.getDataVector().removeAllElements();
			}
			
			if(e.getSource() == menuRemove) {
				if(tblItemsXp.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!!");
				}
				else {
					int grandTotal = 0;
					//System.out.println(grandTotal);
					int row = tblItemsXp.getSelectedRow();
					removeRow(row);
					try {
						for (int i = 0; i < tblModelXp.getRowCount(); i++) {
							grandTotal += Integer.parseInt(tblModelXp.getValueAt(i,0).toString());							
						}
						lblShowTotalXp.setText(grandTotal +"");
					} catch (ArrayIndexOutOfBoundsException e1) {}
					
				}
			}
			
			if(e.getSource() == menuEdit) {
				int row = tblItemsXp.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Double click the cell you want to edit, press ENTER after editing,\n" +
							"right click again and click EDIT. If you want to make wholesale changes to a row, \nconsider just removing it and adding a\n" +
							"new one.");
				}
				else {
					tblModelXp.setValueAt(tblModelXp.getValueAt(row, 0), row, 0);
					tblModelXp.setValueAt(tblModelXp.getValueAt(row, 1), row, 1);
					
					editRow(row, tblModelXp);
					
					int grandTotal = 0;
					for (int i = tblModelXp.getRowCount() - 1; i >= 0; i--) {
						grandTotal += Integer.parseInt(tblModelXp.getValueAt(i,
								0).toString());
					}
					lblShowTotalXp.setText(String.valueOf(grandTotal));
//					try {
//						db.editExpenseRecord(Integer.parseInt(tblModelXp.getValueAt(row, 0).toString()), tblModelXp.getValueAt(row, 1).toString(), db.xpDates.get(row));
//						//System.out.println(Integer.parseInt(tblModelXp.getValueAt(row, 0).toString())+" "+ tblModelXp.getValueAt(row, 1).toString()+" "+ db.xpDates.get(row));
//					} catch (NumberFormatException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
			}
			
			if (e.getSource() == menuCancel) {
				cancelTransaction();
			}
		}
	}
	
	private void removeRow(int selection) {
		tblModelXp.removeRow(selection);
		row.remove(selection);
	}
	
	private void editRow(int selection, DefaultTableModel tblModel) {
		row.get(selection).remove("amount");
		row.get(selection).put("amount", tblModel.getValueAt(selection, 0).toString());
		params = ViewHelpers.constructParamsMap("name", tblModel.getValueAt(selection, 1).toString().toString());
		Map<String, String> category = Category.findOrCreateBy("name", params, false);
		row.get(selection).put("category_id", category.get("id"));
		System.out.println(category);
	}
	
	private void cancelTransaction() {
		ViewHelpers.clearJTable(tblModelXp);
		row.clear();
		txtPaidXp.setText("");
		lblShowBalanceXp.setText("");
		lblShowTotalXp.setText("");
	}
}
