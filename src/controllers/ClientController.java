package controllers;

import helpers.ViewHelpers;

import java.awt.CardLayout;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import models.Sale;
import models.SalesTransaction;
import models.Session;
import models.Stock;
import models.User;

import views.*;

public class ClientController implements Runnable {

	private Login loginScreen;
	private SignUp registerScreen;
	private SetUp setUpScreen;
	private static DBController db;
	private MainWindow mainWindow;
	private PostPaidDialog postPaid;
	private UnapprovedSales unapproved;
	public Map<String, String> currentUser = new HashMap<String, String>();

	public ClientController(DBController db) {
		initMainScreen();
//		displayMainScreen();
//		this.db = db;
		if(db.setUp() && User.count() > 0) {
			initLoginScreen();
			displayLoginScreen();
			initUnapprovedDialog();
//			new Thread (this, "").start();
		}
		else {
			initSetUpScreen();
			displaySetUpScreen();
		}
//		initPostPaidDialog();
//		SalesPanel sp = new SalesPanel(this);
//		if (Session.where("user_id", "1").get(0).get("Logged_in").equals("YES")) {
//			sp.buttonAdd.setEnabled(false);
//		}
	}

	public void displayLoginScreen() {
		loginScreen.setVisible(true);
	}

	public void hideLoginScreen() {
		loginScreen.setVisible(false);
	}

	public void destroyLoginScreen() {
		loginScreen.dispose();
	}

	public void displaySetUpScreen() {
		setUpScreen.setVisible(true);
	}

	public void initSetUpScreen() {
		setUpScreen = new SetUp(this);
	}

	public void hideSetUpScreen() {
		setUpScreen.setVisible(false);
	}

	public void destroySetUpScreen() {
		setUpScreen.dispose();
	}

	public void displayRegisterScreen() {
		registerScreen.setVisible(true);
	}

	public void hideRegisterScreen() {
		registerScreen.setVisible(false);
	}

	public void destroyRegisterScreen() {
		registerScreen.dispose();
	}
	
	public void displayMainScreen() {
		mainWindow.setVisible(true);
	}

	public void initMainScreen() {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
		mainWindow = new MainWindow(this);
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setResizable(false);
	}

	public void hideMainScreen() {
		mainWindow.setVisible(false);
	}

	public void destroyMainScreen() {
		mainWindow.dispose();
	}

	private void initLoginScreen() {
		loginScreen = new Login(this);
	}
	
	private void initPostPaidDialog() {
		postPaid = new PostPaidDialog(this);
	}

	public void displayPostPaidDialog() {
		postPaid.createGUI();
	}
	
	private void populateUnapprovedTable(String id) {
		ViewHelpers.clearJTable(unapproved.tblModel);
		List<Map<String, String>> sale = SalesTransaction.sales(id);
		unapproved.tableData = new ArrayList<Map<String, String>>();
		for(Map<String, String> record : sale) {
			Vector<String> row = new Vector<String>();
			Map<String, String> stock = Stock.find(record.get("stock_id"));
			row.add(stock.get("name"));
			row.add(record.get("quantity"));
			row.add(stock.get("buying_price"));
			row.add(record.get("price"));
			row.add(String.valueOf(Double.parseDouble(record.get("price")) - Double.parseDouble(stock.get("buying_price"))));
			row.add(record.get("VAT"));
			row.add(record.get("total_amount"));
			unapproved.tblModel.addRow(row);
			
			unapproved.row = new HashMap<String, String>();
			unapproved.row.put("sale_id", record.get("id"));
			unapproved.row.put("stock_id", stock.get("id"));
			unapproved.row.put("quantity", record.get("quantity"));
			unapproved.row.put("price", record.get("price"));
			unapproved.row.put("total_amount", record.get("total_amount"));
			unapproved.row.put("VAT", record.get("VAT"));
			unapproved.tableData.add(unapproved.row);
		}
		unapproved.showOldTotalLabel.setText(SalesTransaction.find(id).get("total_amount"));
		unapproved.lblHeader.setText("Unapproved Sales - Sale #"+id);
//		double total = 0;
//		for (int i = 0; i < unapproved.tblModel.getRowCount(); i++) {
//			total += Double.parseDouble(unapproved.tblModel.getValueAt(i, 6).toString());
//			if (total != Double.parseDouble(SalesTransaction.find(id).get("total_amount"))) {
//				unapproved.showNewTotalLabel.setText(String.valueOf(total));
//			}
//			else {
//				unapproved.showNewTotalLabel.setText("0");
//			}
//		}
	}

	private void initUnapprovedDialog() {
		unapproved = new UnapprovedSales(this);
	}

	public void displayUnapprovedDialog(String id) {
		populateUnapprovedTable(id);
        unapproved.setVisible(true);
	}
	
	public void destroyUnapprovedDialog() {
		unapproved.dispose();
	}
	
	public void switchToSales() {
		ViewHelpers.switchPanels(new CardLayout(), mainWindow.cardPanel, "sales");
	}

	public void switchToExpenses() {
		ViewHelpers.switchPanels(new CardLayout(), mainWindow.cardPanel, "expense");
	}

	public void switchToStats() {
		ViewHelpers.switchPanels(new CardLayout(), mainWindow.cardPanel, "stats");
	}

	public void switchToStock() {
		ViewHelpers.switchPanels(new CardLayout(), mainWindow.cardPanel, "stock");
	}

	public void populateList(List<String> activities) {
		mainWindow.populateList(activities);
	}
	
	public boolean isAdmin() {
		return currentUser.get("user_type").equalsIgnoreCase("Admin");
	}
	
	public List<String> products() {
		List<String> products = new ArrayList<String>();
		for(TreeMap<String, String> product : Stock.showAll()) {
			products.add(product.get("name"));
		}
		return products;
	}

	@Override
	public void run() {
		List<String> activities = new ArrayList<String>();
		try {
			for (; ; ) {
	        	 if (isAdmin()) {
	        		 populateList(SalesTransaction.unapprovedSales());
				 }
	            Thread.sleep(960);
	         }
	     } catch (InterruptedException e) {
	         System.out.println("Thread  interrupted.");
	     }
	}

	public static void main(String[] args) {
		db = new DBController();
		new ClientController(db);		
	}
	
}
