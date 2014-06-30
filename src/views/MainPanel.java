package views;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
package mursal;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

*//**
 *
 * @author Muaad
 *//*
public class MainPanel {
	
	CalculatorApplet calc = new CalculatorApplet();
    StatsPanel stPanel = new StatsPanel();
    SalesPanel sp = new SalesPanel();
    ExpensesPanel xp = new ExpensesPanel();
    
    private JPanel contentPanel, placeHolderPanel, mainPanel, toolsPanel;
    private JButton salesButton, expensesButton, statsButton, inventoryButton;
    
    
    public void createMainPanel() {
    	
    	sp.createSalesPanel();
    	xp.createExpensePanel();
    	stPanel.createStatsPanel();
    	calc.buildGUI();
    	
        mainPanel = new JPanel();
        toolsPanel = new JPanel();
        salesButton = new JButton();
        statsButton = new JButton();
        expensesButton = new JButton();
        inventoryButton = new JButton();
        contentPanel = new JPanel();
        placeHolderPanel = new JPanel();
        placeHolderPanel.setBackground(new java.awt.Color(153, 204, 255));

       // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        

        mainPanel.setBackground(new java.awt.Color(153, 204, 255));
        mainPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new java.awt.Color(255, 51, 102), 2, true), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), new java.awt.Color(255, 51, 51)));

        toolsPanel.setBackground(new java.awt.Color(204, 204, 255));
        toolsPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        salesButton.setBackground(new java.awt.Color(255, 153, 51));
        salesButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        salesButton.setIcon(new ImageIcon("C:\\Users\\Muaad\\Desktop\\Workspace\\Mursal\\sale.PNG"));
        salesButton.setToolTipText("Sales");
        salesButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        salesButton.addActionListener(eHandler);

        statsButton.setBackground(new java.awt.Color(255, 153, 51));
        statsButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        statsButton.setIcon(new ImageIcon("C:\\Users\\Muaad\\Desktop\\Workspace\\Mursal\\stats.PNG"));
        statsButton.setText("Statistics");
        statsButton.setToolTipText("Statistics");
        statsButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        statsButton.addActionListener(eHandler);

        expensesButton.setBackground(new java.awt.Color(255, 153, 51));
        expensesButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        expensesButton.setIcon(new ImageIcon("C:\\Users\\Muaad\\Desktop\\Workspace\\Mursal\\expenses.PNG"));
        expensesButton.setText("Expenses");
        expensesButton.setToolTipText("Expenses");
        expensesButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        expensesButton.addActionListener(eHandler);

        inventoryButton.setBackground(new java.awt.Color(255, 153, 51));
        inventoryButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        inventoryButton.setIcon(new ImageIcon("C:\\Users\\Muaad\\Desktop\\Workspace\\Mursal\\inventory.PNG"));
        inventoryButton.setText("Inventory Management");
        inventoryButton.setToolTipText("Inventory Management");
        inventoryButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        inventoryButton.addActionListener(eHandler);

        GroupLayout toolsPanelLayout = new GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
            toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(toolsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesButton, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expensesButton, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statsButton, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventoryButton, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        toolsPanelLayout.setVerticalGroup(
            toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(toolsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(salesButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                    .addComponent(statsButton)
                    .addComponent(expensesButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryButton))
                .addContainerGap())
        );

        toolsPanelLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {expensesButton, inventoryButton, salesButton, statsButton});

        contentPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        contentPanel.setLayout(new java.awt.CardLayout());

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentPanel.add(placeHolderPanel, "trial");
        contentPanel.add(sp.SalesPanel,"sales");
        contentPanel.add(xp.expensePanel,"xpense");
        contentPanel.add(calc.cp, "calc");
        contentPanel.add(stPanel.statsPanel, "stats");
    }
    
    class eventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cancelButton) {
				System.exit(1);
			}
			
			if(e.getSource() == salesButton) {
				swithToSales(card, contentPanel);
				frame.setTitle(title + " | Sales");
				try {
					db.readRecords();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				barcodes = db.barcodes.toArray(new String[db.barcodes.size()]);
		        sp.comboName.setModel(new DefaultComboBoxModel(barcodes));
			}
			
			if(e.getSource() == expensesButton) {
				swithToXpense(card, contentPanel);
				frame.setTitle(title + " | Expenses");
				xp.txtXpensePaid.requestFocus();
				try {
					db.loadReasons();
					xp.comboReason.setModel(new DefaultComboBoxModel(db.reasons.toArray(new String[db.reasons.size()])));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == statsButton) {
				swithToStats(card, contentPanel);
				frame.setTitle(title + " | Stats");
			}
			
			if(e.getSource() == stPanel.xpenseButton) {
				JOptionPane.showMessageDialog(null, "h");
			}
 			
			if(!(userTxt.getText().equals("") || passTxt.getPassword().equals(""))) {
				loginButton.setEnabled(true);
			}
			else {
				loginButton.setEnabled(false);
			}
			
			if(e.getSource() == exitMenu) {
				System.exit(1);
			}
			
			if(e.getSource() == logoutMenu) {
				logout(card, overallPanel);
				frame.setTitle(title);
				userTxt.setText("");
				passTxt.setText("");
				userTxt.requestFocus();
			}
			
			if(e.getSource() == calcMenu) {
				swithToCalc(card, contentPanel);
				frame.setTitle(title + " | Calculator");
			}
			
			if(e.getSource() == loginButton) {
				db.user = sp.user = xp.user = userTxt.getText();
				char [] p = passTxt.getPassword();
				db.pass = sp.pass = xp.pass = new String(p);
				db.connect();
				if(db.success) {
					login(card, overallPanel);
				}
				else {
					lblError.setFont(new java.awt.Font("Tahoma", 1, 14));
			        lblError.setForeground(new java.awt.Color(255, 0, 51));
			        lblError.setHorizontalAlignment(SwingConstants.CENTER);
			        //lblError.setBorder(new LineBorder(new java.awt.Color(255, 0, 51), 1, true));
			        lblError.setText("Invalid username or password!! Try again.");
					userTxt.requestFocus();
				}
			}
			
			if(e.getSource() == passTxt) {
				db.user = sp.user = xp.user = userTxt.getText();
				char [] p = passTxt.getPassword();
				db.pass = sp.pass = xp.pass = new String(p);
				db.connect();
				if(db.success) {
					login(card, overallPanel);
				}
				else {
					lblError.setFont(new java.awt.Font("Tahoma", 1, 14));
			        lblError.setForeground(new java.awt.Color(255, 0, 51));
			        lblError.setHorizontalAlignment(SwingConstants.CENTER);
			        //lblError.setBorder(new LineBorder(new java.awt.Color(255, 0, 51), 1, true));
			        lblError.setText("Invalid username or password!! Try again.");
					userTxt.requestFocus();
				}
			}
			
		}
    	
    }
    
    void resetForNewSession(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "trial");
		sp.txtPaid.setText("");
		sp.lblShowBalance.setText("");
		sp.lblShowTotal.setText("");
		for (int i = sp.tblModel.getRowCount() - 1; i >= 0; i--) {
			sp.tblModel.removeRow(i);
		}
		
		xp.txtPaidXp.setText("");
		xp.txtXpensePaid.setText("");
		xp.lblShowBalanceXp.setText("");
		xp.lblShowTotalXp.setText("");
		for (int i = xp.tblModelXp.getRowCount() - 1; i >= 0; i--) {
			xp.tblModelXp.removeRow(i);
		}
		
		calc.display.setText("");
	}
    
    void swithToSales(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "sales");
	}
    
    void swithToCalc(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "calc");
	}
    
    void swithToStats(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "stats");
	}
    
    void swithToXpense(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "xpense");
	}
    
    void login(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "panel");
		resetForNewSession(card, contentPanel);
	}
    
    void logout(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "login");
	}
}*/