package controllers;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import views.*;

public class ClientController {
	private Socket socket;

	private String username;

	private Login loginScreen;
	private SignUp registerScreen;
	private SetUp setUpScreen;
	private static DBController db;
	private MainWindow mainWindow;
	public Map<String, String> sessionMap = new HashMap<String, String>();

	public ClientController(DBController db) {
		initMainScreen();
		this.db = db;
		if(db.setUp()) {
			initLoginScreen();
			displayLoginScreen();
		}
		else {
			initSetUpScreen();
			displaySetUpScreen();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public String getUsername() {
		return username;
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

	public static void main(String[] args) {
		db = new DBController();
		new ClientController(db);

	}

}
