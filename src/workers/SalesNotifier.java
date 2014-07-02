package workers;

import helpers.ViewHelpers;

import java.awt.CardLayout;

import controllers.ClientController;

import views.Login;
import views.MainWindow;

public class SalesNotifier implements Runnable {
	private ClientController controller;
	public SalesNotifier(ClientController controller) {
		this.controller = controller;
	}
	@Override
	public void run() {
		Login l = new Login(controller);
		try {
	         for(int i = 4; i > 0; i--) {
	            setPassLbl(i);
	            Thread.sleep(960);
	         }
	     } catch (InterruptedException e) {
	         System.out.println("Thread  interrupted.");
	     }
	}

	private void setPassLbl(int i) {
//		loginScreen.passLabel.setText(String.valueOf(i));
//		controller.initMainScreen();
		MainWindow mainWindow = new MainWindow(controller);
		CardLayout card = new CardLayout();
		ViewHelpers.switchPanels(card, mainWindow.cardPanel, String.valueOf(i));
	}
}
