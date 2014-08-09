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
	public Map<String, String> currentUser = new HashMap<String, String>();

	public ClientController(DBController db) {
		initMainScreen();
//		displayMainScreen();
//		this.db = db;
		if(db.setUp() && User.count() > 0) {
			initLoginScreen();
			displayLoginScreen();
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
//		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setResizable(true);
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

	public List<String> unapprovedSales() {
		List<String> unapprovedSales = new ArrayList<String>();
		for(Map<String, String> sale : SalesTransaction.where("approved", "NO")) {
			String sales = "";
			for(Map<String, String> ss : SalesTransaction.sales(sale.get("id"))) {
				sales += Stock.find(ss.get("stock_id")).get("name") + "(" + ss.get("quantity") + ")" + " - ";
			}
			unapprovedSales.add("#" + sale.get("id") + " - " + sales);
		}
		return unapprovedSales;
	}

	@Override
	public void run() {
//		Login l = new Login(this);
		List<String> activities = new ArrayList<String>();
		try {
			for (; ; ) {
//	        	 String stock = Stock.showAll().get(i).get("name");
//	        	 activities.add(stock);
//	            setPassLbl(i);
	        	 if (currentUser.get("user_type").equalsIgnoreCase("admin")) {
	        		 populateList(unapprovedSales());
				 }
//	        	 populateList(unapprovedSales());
	            Thread.sleep(960);
	         }
	     } catch (InterruptedException e) {
	         System.out.println("Thread  interrupted.");
	     }
	}

	private void setPassLbl(int i) {
//		loginScreen.passLabel.setText(String.valueOf(i));
		CardLayout card = new CardLayout();
		ViewHelpers.switchPanels(card, mainWindow.cardPanel, String.valueOf(i));
	}

	public static void main(String[] args) {
		db = new DBController();
		new ClientController(db);
		
	}
	
}
