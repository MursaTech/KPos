
package views;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import models.Sale;
import models.SalesTransaction;
import models.Stock;
import models.User;

import com.toedter.calendar.JDateChooser;

import controllers.ClientController;

/**
 *
 * @author Muaad
 */
public class SalesStatsPanel extends JFrame {
	
	TransactionStats ts = new TransactionStats();
    ItemsSoldPanel items = new ItemsSoldPanel();
    EventHandler eHandler = new EventHandler();
    CardLayout card = new CardLayout();
    List<String> columns = new LinkedList<String>();
    List<String> itemColumns = new LinkedList<String>();
    ClientController controller;
    Vector<String> row;
	List<Vector<String>> rows = new ArrayList<Vector<String>>();
	TreeMap<String, String> record;
	Map<String, String> params;
    MursalDB db = new MursalDB();
	
    public SalesStatsPanel(ClientController controller) {
    	this.controller = controller;
        createSalesStatsOverviewPanel();
    }
    
    public JPanel createSalesStatsOverviewPanel() {
    	
        statsPanel = new JPanel();
        jPanel3 = new JPanel();
        lblction = new JLabel();
        comboAction = new JComboBox();
        fromLbl = new JLabel();
        fromCombo = new JDateChooser();
        buttonSearch = new JButton();
        toLbl = new JLabel();
        toCombo = new JDateChooser();
        salesStatsPanel = new JPanel();
        salesOverviewPanel = new JPanel();
        lblOverview = new JLabel();
        lblSummary = new JLabel();
        
        lblSummary.setText("<html><body><table width=\"1000\" height=100% border=\"0\">"+
				"<tr valign=\"top\">"+
				"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
				"<b>Total Sales</b>" +
				"<td style=\"background-color:#eeeeee;height:90px;width:500px;text-align:top;\">"+
				"<ul><li>Today - <b> Kshs. "+SalesTransaction.total()+"</b></li><li>Yesterday -<b> Kshs. db.yesterdaySalesTotal"+
				"</b></li><li>This week -<b> Kshs. db.weekSalesTotal</b></li>" +
						"<li>This month -<b> Kshs. db.monthSalesTotal</b></li>" +
						"<li>This Year -<b> Kshs. db.yearSalesTotal</b></li><li>From the begining of time -<b> Kshs. "+SalesTransaction.total()+
						"</b></li></ul></td></tr>" +
				"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
				"<b>Items Sold</b>" +
				"<td style=\"background-color:#eeeeee;height:90px;width:500px;text-align:top;\">"+
				"<ul><li>Most Popular -<b> db.top1, db.top2, db.top3</b></li><li>Least Popular --<b> db.bottom1, db.bottom2, "+
				"db.bottom3</b></li><li>Most Valuable -<b> db.mostValuable1, db.mostValuable2, db.mostValuable3</b></li>" +
						"<li>Least Valuable -<b> db.leastValuable1, db.leastValuable2, db.leastValuable3</b></li></ul></td></tr>"+
				"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
				"<b>Time</b>" +
				"<td style=\"background-color:#eeeeee;height:100px;width:500px;text-align:top;\">"+
				"<ul><li>Peak times -</li><li>Down Times -</li><li>Busiest days -</li><li>Least Busy Days -</li></ul></td></tr>"+
				"</table></body></html>");
        
        ts.createTransactionPanel();
        items.createItemsPanel();
        
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        statsPanel.setBackground(new java.awt.Color(153, 204, 255));

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        lblction.setBackground(new java.awt.Color(153, 153, 255));
        lblction.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblction.setForeground(new java.awt.Color(0, 0, 204));
        lblction.setText("Select Action");
        lblction.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        lblction.setFocusable(false);
        lblction.setOpaque(true);

        comboAction.setBackground(new java.awt.Color(153, 153, 255));
        comboAction.setModel(new DefaultComboBoxModel(new String[] { "Summary", "Transactions", "Individual items sold", "Peek times" }));
        comboAction.addActionListener(eHandler);

        fromLbl.setBackground(new java.awt.Color(153, 153, 255));
        fromLbl.setFont(new java.awt.Font("Tahoma", 1, 14));
        fromLbl.setForeground(new java.awt.Color(0, 0, 204));
        fromLbl.setText("For the period");
        fromLbl.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        fromLbl.setFocusable(false);
        fromLbl.setOpaque(true);

        fromCombo.setBackground(new java.awt.Color(0, 51, 102));
//        fromCombo.setMaximumRowCount(5);
//        fromCombo.addActionListener(eHandler);
        //fromCombo.setEditable(true);

        buttonSearch.setBackground(new java.awt.Color(255, 153, 51));
        buttonSearch.setFont(new java.awt.Font("Tahoma", 1, 12));
        buttonSearch.setText("Search");
        buttonSearch.addActionListener(eHandler);

        toLbl.setBackground(new java.awt.Color(153, 153, 255));
        toLbl.setFont(new java.awt.Font("Tahoma", 1, 14));
        toLbl.setForeground(new java.awt.Color(0, 0, 204));
        toLbl.setText("To");
        toLbl.setBorder(new LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        toLbl.setFocusable(false);
        toLbl.setOpaque(true);

        toCombo.setBackground(new java.awt.Color(0, 51, 102));
//        toCombo.setMaximumRowCount(5);
//        toCombo.addActionListener(eHandler);
        //toCombo.setEditable(true);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblction, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboAction, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fromLbl)
                .addGap(18, 18, 18)
                .addComponent(fromCombo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toLbl, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(toCombo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblction)
                    .addComponent(comboAction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(toLbl)
                        .addComponent(toCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(fromLbl)
                            .addComponent(fromCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonSearch)))
                .addContainerGap())
        );

        salesStatsPanel.setBackground(new java.awt.Color(0, 51, 102));
        salesStatsPanel.setBorder(new LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        salesOverviewPanel.setBackground(new java.awt.Color(153, 204, 255));
        salesOverviewPanel.setBorder(new LineBorder(new java.awt.Color(0, 51, 102), 9));

        lblOverview.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblOverview.setText("Sales Overview");

        lblSummary.setVerticalAlignment(SwingConstants.TOP);
        lblSummary.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        
        GroupLayout salesOverviewPanelLayout = new GroupLayout(salesOverviewPanel);
        salesOverviewPanel.setLayout(salesOverviewPanelLayout);
        salesOverviewPanelLayout.setHorizontalGroup(
            salesOverviewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(salesOverviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(salesOverviewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblSummary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(salesOverviewPanelLayout.createSequentialGroup()
                        .addComponent(lblOverview, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        salesOverviewPanelLayout.setVerticalGroup(
            salesOverviewPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(salesOverviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOverview, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSummary, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );

        /*GroupLayout salesStatsPanelLayout = new GroupLayout(salesStatsPanel);
        salesStatsPanel.setLayout(salesStatsPanelLayout);
        salesStatsPanelLayout.setHorizontalGroup(
            salesStatsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(salesStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesOverviewPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        salesStatsPanelLayout.setVerticalGroup(
            salesStatsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(salesStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesOverviewPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );*/
        
//        columns.add("Transaction ID");
        columns.add("Total Amount");
        columns.add("Amount Paid");
        columns.add("Balance");
        columns.add("VAT");
        columns.add("Discount");
        columns.add("Approved");
        columns.add("Authorized By");
        columns.add("Time");
        
        itemColumns.add("Item Name");
        itemColumns.add("Number Sold");
        itemColumns.add("Total Amount (Kshs)");
        itemColumns.add("Profit Generated");
        
        salesStatsPanel.setLayout(new CardLayout());
        salesStatsPanel.add(salesOverviewPanel, "summary");
        salesStatsPanel.add(ts.transactionStatsPanel, "transaction");
        salesStatsPanel.add(items.itemStatsPanel, "items");

        GroupLayout statsPanelLayout = new GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesStatsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salesStatsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
		return statsPanel;
    }
    
    String user, pass;
    
    class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == comboAction) {

				if(comboAction.getSelectedItem().equals("Summary")) {
					swithToSummary(card, salesStatsPanel);
					columns.clear();
				}
				
				if(comboAction.getSelectedItem().equals("Transactions")) {
					swithToTransaction(card, salesStatsPanel);
					for (int i = 0; i < columns.size(); i++) {
						ts.tblModel.addColumn(columns.get(i));
						if (i == ((columns.size() - 1) / 2)) {
							columns.clear();
						}
					}
					columns.clear();
					loadTransactions();
				}
				
				if(comboAction.getSelectedItem().equals("Individual items sold")) {
					swithToItems(card, salesStatsPanel);
					for (int i = 0; i < itemColumns.size(); i++) {
						items.tblModel.addColumn(itemColumns.get(i));
						if (i == ((itemColumns.size() - 1) / 2)) {
							itemColumns.clear();
						}
					}
					loadItemsTable();
				}
			}
			
			if(e.getSource() == buttonSearch) {
				if (comboAction.getSelectedItem().equals("Transactions")) {
					loadVariableTransaction();
					db.listDates.clear();
				}
				if(comboAction.getSelectedItem().equals("Individual items sold")) {
					loadVariableItems();
				}
			}
		}
    	
    }
    void loadTransactions() {
		for (int i = ts.tblModel.getRowCount() - 1; i >= 0; i--) {
			ts.tblModel.removeRow(i);
		}
		
		for(TreeMap<String, String> record : SalesTransaction.showAll()) {
			row = new Vector<String>();
//			row.add(record.get("id"));
			row.add(record.get("total_amount"));
			row.add(record.get("amount_paid"));
			row.add(record.get("balance"));
			row.add(record.get("VAT"));
			row.add(record.get("discount"));
			row.add(record.get("approved"));
			row.add(User.fullName(record.get("user_id")));
			row.add(record.get("created_at"));
			
			ts.tblModel.addRow(row);
		}
		ts.lblTotal.setText(ts.total + SalesTransaction.total());
    }
    
    void loadVariableTransaction() {
    	try {
    		for (int i = ts.tblModel.getRowCount() - 1; i >= 0; i--) {
    			ts.tblModel.removeRow(i);
    		}
			db.user = user;
			db.pass = pass;
			db.connect();
//			db.fromDate = fromCombo.getSelectedItem().toString();
//			db.toDate = toCombo.getSelectedItem().toString();
			db.loadVariableTransactionTable();
			//System.out.println(db.fromDate+ " "+ db.toDate);
			for (int i = 0; i < db.listOfVectorsVarTrans.size(); i++) {
				ts.tblModel.addRow(db.listOfVectorsVarTrans.get(i));
			}
			ts.lblTotal.setText(ts.total + db.totalForPeriod);
			db.totalForPeriod = 0;
			db.listOfVectorsVarTrans.clear();
		} /*catch(IllegalArgumentException ie) {
			JOptionPane.showMessageDialog(null, "No transactions recorded for "+ db.toDate, "Error", JOptionPane.ERROR_MESSAGE);			
		}*/
    	catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    void loadItemsTable() {
    	int total = 0;
		for (int i = items.tblModel.getRowCount() - 1; i >= 0; i--) {
			items.tblModel.removeRow(i);
		}
		//db.columns.clear();
		
		for(TreeMap<String, String> record : Stock.showAll()) {
			row = new Vector<String>();
			row.add(record.get("name"));
			System.out.println(record.get("id"));
			row.add(Stock.quantitySold(record.get("id")) + "");
			row.add(Stock.amountSold(record.get("id")) + "");
			row.add(Stock.profit(record.get("id")) + "");
			
			items.tblModel.addRow(row);
		}
		for (int i = 0; i < items.tblModel.getRowCount(); i++) {
			total += Integer.parseInt(items.tblItems.getValueAt(i, 1).toString());
		}
		items.lblTotal.setText(items.total + total);
	}
    
    void loadVariableItems() {
    	int total = 0;
    	try {
    		for (int i = items.tblModel.getRowCount() - 1; i >= 0; i--) {
    			items.tblModel.removeRow(i);
    		}
			db.user = user;
			db.pass = pass;
			db.connect();
//			db.fromDate = fromCombo.getSelectedItem().toString();
//			db.toDate = toCombo.getSelectedItem().toString();
			db.loadVariableItemsTable();
			//System.out.println(db.fromDate+ " "+ db.toDate);
			for (int i = 0; i < db.listOfVectorsItemsVar.size(); i++) {
				items.tblModel.addRow(db.listOfVectorsItemsVar.get(i));
			}
			for (int i = 0; i < db.listOfVectorsItemsVar.size(); i++) {
				total = total + Integer.parseInt(db.listOfVectorsItemsVar.get(i).get(1));
			}
			items.lblTotal.setText(items.total + total);
			total = 0;
			db.listOfVectorsItemsVar.clear();
		} 
    	catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
    void swithToSummary(CardLayout card, JPanel cardpanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "summary");
	}
    
    void swithToTransaction(CardLayout card, JPanel cardpanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "transaction");
	}
    
    void swithToItems(CardLayout card, JPanel cardpanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "items");
	}
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SalesStatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SalesStatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SalesStatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SalesStatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SalesStatsPanel(controllers).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify
    JButton buttonSearch;
    JComboBox comboAction;
    JDateChooser fromCombo;
    JLabel fromLbl;
    JLabel lblOverview;
    JLabel lblSummary;
    JPanel jPanel3;
    JLabel lblction;
    JPanel salesOverviewPanel;
    JPanel salesStatsPanel;
    JPanel statsPanel;
    JDateChooser toCombo;
    JLabel toLbl;
    // End of variables declaration
}
