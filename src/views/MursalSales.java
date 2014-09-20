package views;

import helpers.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import models.Stock;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 *
 * @author Muaad
 */
public class MursalSales {
	
   
    String title = "Store Management System";
    JFrame frame = new JFrame(title);
    private JMenu fileMenu, viewMenu, toolsMenu, helpMenu, budgetMenu, closeOutMenu, cancelMenu, postpaymentMenu, prepaymentMenu;
    private JMenuItem setUpMenu, managePostMenu, recordPostMenu, managePreMenu, recordPreMenu, logoutMenu, exitMenu, calcMenu, 
    				  sqlMenu, tablesMenu, targetMenu, budgetXpenseMenu, viewTargetsMenu, dayMenu, monthMenu, yearMenu, mpesaMenu, usageMenu, shortcutsMenu,
    				  creditsMenu, setMenu, cancelSalesMenu, cancelXpenseMenu, cancelPreMenu, cancelPostMenu;
    private JMenuBar menuBar;
    private JPanel loginPanel, overallPanel, jPanel3;
    private JButton loginButton, cancelButton, clickHereButton;
    private JLabel userLabel, passLabel, lblError, userVerifierLabel, passVerifierLabel, usingLbl;
    private JTextField userTxt;
    private JPasswordField passTxt;
    eventHandler eHandler = new eventHandler();
    MursalDB db = new MursalDB();
    CardLayout card = new CardLayout();
    
    DefaultTableModel tblModel = new DefaultTableModel();
    Vector<String> elements;
    List<String> columns = new LinkedList<String>();
	int grandTotal = 0;
    String [] barcodes = null;
    String [] tablesArray = {"items","sales","inventory","newer_inventory","transaction_history","expenses_history","expense_reasons","expenses"};    
    SalesPanel sp = new SalesPanel();
    ExpensesPanel xp = new ExpensesPanel();
    SetUpHelpPanel set = new SetUpHelpPanel();
    
    List<String> listOfTables = Arrays.asList(tablesArray);
    
    private JPanel contentPanel, homePanel, mainPanel, toolsPanel;
    private JButton salesButton, expensesButton, statsButton, inventoryButton;
    
    private void createGUI() {
    	
    	createMainPanel();
    	set.createSetUpHelpPanel();
    	
    	//createSalesPanel();
        overallPanel = new JPanel();
        loginPanel = new JPanel();
        jPanel3 = new JPanel();
        userLabel = new JLabel();
        passLabel = new JLabel();
        usingLbl = new JLabel();
        userVerifierLabel = new JLabel();
        passVerifierLabel = new JLabel();
        lblError = new JLabel();
        userTxt = new JTextField();
        passTxt = new JPasswordField();
        loginButton = new JButton();
        cancelButton = new JButton();
        clickHereButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        viewMenu = new JMenu();
        toolsMenu = new JMenu("Tools");
        helpMenu = new JMenu("Help & Support");
        usageMenu = new JMenuItem("Usage");
        shortcutsMenu = new JMenuItem("Shortcuts");
        setMenu = new JMenuItem("Set up Help");
        creditsMenu = new JMenuItem("Credits");
        budgetMenu = new JMenu("Budget");
        calcMenu = new JMenuItem("<html><body>Calculator <i><b>Beta!</b></i></body></html>");
        sqlMenu = new JMenuItem("<html><body>SQL Browser <i><b>Beta!</b></i></body></html>");
        setUpMenu = new JMenuItem("Set up project");
        cancelMenu = new JMenu("Cancel a transaction");
        targetMenu = new JMenuItem("Set Targets");
        budgetXpenseMenu = new JMenuItem("Budget Expenses");
        viewTargetsMenu = new JMenuItem("View Targets");
        tablesMenu = new JMenuItem("Project Tables");
        postpaymentMenu = new JMenu("Postpayment");
        managePostMenu = new JMenuItem("Manage postpaying customers");
        recordPostMenu = new JMenuItem("Record transaction");
        prepaymentMenu = new JMenu("Prepayment");
        managePreMenu = new JMenuItem("Manage prepaying customers");
        recordPreMenu = new JMenuItem("Record transaction");
        mpesaMenu = new JMenuItem("Mpesa Agent");
        closeOutMenu = new JMenu("Close Out");
        dayMenu = new JMenuItem("Day");
        monthMenu = new JMenuItem("Month");
        yearMenu = new JMenuItem("Year");
        logoutMenu = new JMenuItem("Logout");
        cancelSalesMenu = new JMenuItem("Sales");
        cancelXpenseMenu = new JMenuItem("Expenses");
        cancelPreMenu = new JMenuItem("Prepayment");
        cancelPostMenu = new JMenuItem("Postpayment");
        exitMenu = new JMenuItem("Exit");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        
        Random r = new Random();
        Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

        overallPanel.setBackground(new Color(102, 153, 255));
        overallPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.pink, Color.red));
        overallPanel.setLayout(new CardLayout());

        /*loginPanel.setBackground(new Color(153, 204, 255));
        loginPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(255, 51, 102), 2, true), "Login",
        		TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 0, 18), new Color(255, 51, 51)));

        userLabel.setBackground(new Color(255, 153, 51));
        userLabel.setFont(new Font("Tahoma", 1, 14));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setText("Username");
        userLabel.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));

        passLabel.setBackground(new Color(255, 153, 51));
        passLabel.setFont(new Font("Tahoma", 1, 14));
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passLabel.setText("Password");
        passLabel.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));

        userTxt.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        userTxt.setForeground(Color.blue);
        userTxt.setFont(new Font("Tahoma", 1, 14));
        
        passTxt.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        passTxt.setForeground(Color.blue);
        passTxt.setFont(new Font("Tahoma", 1, 14));
        passTxt.addActionListener(eHandler);

        loginButton.setBackground(new Color(255, 153, 51));
        loginButton.setFont(new Font("Tahoma", 1, 14));
        loginButton.setText("Login");
        loginButton.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        //loginButton.setEnabled(false);
        loginButton.addActionListener(eHandler);

        cancelButton.setBackground(new Color(255, 153, 51));
        cancelButton.setFont(new Font("Tahoma", 1, 14));
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        cancelButton.addActionListener(eHandler);
        
        lblError = new JLabel();
        
        GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(passLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                	.addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(302, Short.MAX_VALUE))
        );

        loginPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {passTxt, userTxt});

        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );*/

        loginPanel.setBackground(new java.awt.Color(153, 204, 255));
        loginPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 2, true), "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), java.awt.Color.red)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        passLabel.setBackground(new java.awt.Color(153, 153, 255));
        passLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passLabel.setForeground(new java.awt.Color(51, 0, 51));
        passLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passLabel.setText("Password");
        passLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));

        userTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        userTxt.setForeground(Color.blue);
        userTxt.setFont(new Font("Tahoma", 1, 14));
        passTxt.addActionListener(eHandler);

        passVerifierLabel.setForeground(new java.awt.Color(204, 0, 0));

        cancelButton.setBackground(new java.awt.Color(255, 153, 51));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        cancelButton.addActionListener(eHandler);

        lblError.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(204, 0, 0));

        usingLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usingLbl.setText("Using this program for the first time?");

        clickHereButton.setBackground(new java.awt.Color(255, 153, 51));
        clickHereButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clickHereButton.setText("Click Here");
        clickHereButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        clickHereButton.addActionListener(eHandler);

        userLabel.setBackground(new java.awt.Color(153, 153, 255));
        userLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        userLabel.setForeground(new java.awt.Color(51, 0, 51));
        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText("Username");
        userLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));

        userVerifierLabel.setForeground(new java.awt.Color(204, 0, 0));

        passTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        passTxt.setForeground(Color.blue);
        passTxt.setFont(new Font("Tahoma", 1, 14));

        loginButton.setBackground(new java.awt.Color(255, 153, 51));
        loginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        loginButton.addActionListener(eHandler);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userTxt)
                            .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userVerifierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passVerifierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(usingLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clickHereButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userVerifierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passVerifierLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usingLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clickHereButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(139, 139, 139))
        );


        overallPanel.add(loginPanel, "login");
        overallPanel.add(mainPanel, "panel");
        overallPanel.add(set.setUpHelpPanel, "setHelp");
        
        set.backButton.addActionListener(eHandler);
        set.setUpButton.addActionListener(eHandler);
        set.passTxt.addActionListener(eHandler);

        fileMenu.setText("File");
        logoutMenu.addActionListener(eHandler);
        exitMenu.addActionListener(eHandler);
        tablesMenu.addActionListener(eHandler);
        setUpMenu.addActionListener(eHandler);
        fileMenu.add(setUpMenu);
        fileMenu.addSeparator();
        fileMenu.add(cancelMenu);
        fileMenu.addSeparator();
        fileMenu.add(postpaymentMenu);
        fileMenu.addSeparator();
        fileMenu.add(prepaymentMenu);
        fileMenu.addSeparator();
        fileMenu.add(mpesaMenu);
        fileMenu.addSeparator();
        fileMenu.add(closeOutMenu);
        fileMenu.addSeparator();
        fileMenu.add(logoutMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);

        closeOutMenu.add(dayMenu);
        closeOutMenu.addSeparator();
        closeOutMenu.add(monthMenu);
        closeOutMenu.addSeparator();
        closeOutMenu.add(yearMenu);
        
        postpaymentMenu.add(managePostMenu);
        postpaymentMenu.addSeparator();
        postpaymentMenu.add(recordPostMenu);

        prepaymentMenu.add(managePreMenu);
        prepaymentMenu.addSeparator();
        prepaymentMenu.add(recordPreMenu);
        
        
        cancelMenu.add(cancelSalesMenu);
        cancelMenu.addSeparator();
        cancelMenu.add(cancelXpenseMenu);
        cancelMenu.addSeparator();
        cancelMenu.add(cancelPreMenu);
        cancelMenu.addSeparator();
        cancelMenu.add(cancelPostMenu);
        
        menuBar.add(fileMenu);
        
        
        calcMenu.addActionListener(eHandler);
        toolsMenu.add(calcMenu);
        toolsMenu.addSeparator();
        toolsMenu.add(sqlMenu);
        
        budgetMenu.add(targetMenu);
        budgetMenu.addSeparator();
        budgetMenu.add(budgetXpenseMenu);
        budgetMenu.addSeparator();
        budgetMenu.add(viewTargetsMenu);

        viewMenu.setText("View");
        viewMenu.add(tablesMenu);
        
        helpMenu.add(usageMenu);
        helpMenu.addSeparator();
        helpMenu.add(shortcutsMenu);
        helpMenu.addSeparator();
        helpMenu.add(setMenu);
        helpMenu.addSeparator();
        helpMenu.add(creditsMenu);
        
        menuBar.add(viewMenu);
        menuBar.add(toolsMenu);
        menuBar.add(budgetMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        
        ImageIcon icon = new ImageIcon("F:\\POSWorkspace\\POS\\sale.png");
        frame.setIconImage(icon.getImage());

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(overallPanel, GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(overallPanel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );
        
        /*frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if(choice == 0) {
                	System.exit(1);
                }
                else {
                	System.out.println("You chose not to exit.");
                }
            }
        });*/
        
//        center(frame);
//        frame.setResizable(false);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(1350,760));
        frame.setVisible(true);
        frame.pack();
    }
    
    CalculatorApplet calc = new CalculatorApplet();
    StatsPanel stPanel = new StatsPanel();
    StatsPanel ssPanel = new StatsPanel();
    ViewPanel vPanel = new ViewPanel();
    SetUpProject setUp = new SetUpProject();
    InventoryManagement im = new InventoryManagement();
    
    public void createMainPanel() {
    	
    	sp.createSalesPanel();
    	xp.createExpensePanel();
    	stPanel.createStatsPanel();
    	calc.buildGUI();
    	ssPanel.createSalesStatsOverviewPanel();
    	setUp.buildPanel();
    	im.createInventoryPanel();
    	
        mainPanel = new JPanel();
        toolsPanel = new JPanel();
        salesButton = new JButton();
        statsButton = new JButton();
        expensesButton = new JButton();
        inventoryButton = new JButton();
        contentPanel = new JPanel();
        homePanel = new JPanel();
        homePanel.setBackground(new java.awt.Color(153, 204, 255));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        

        mainPanel.setBackground(new java.awt.Color(153, 204, 255));
        mainPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new java.awt.Color(255, 51, 102), 2, true), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), new java.awt.Color(255, 51, 51)));

        toolsPanel.setBackground(new java.awt.Color(204, 204, 255));
        toolsPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        salesButton.setBackground(new java.awt.Color(255, 153, 51));
        salesButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        salesButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\sale.PNG"));
        salesButton.setToolTipText("Sales");
        salesButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        salesButton.addActionListener(eHandler);

        statsButton.setBackground(new java.awt.Color(255, 153, 51));
        statsButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        statsButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\stats.PNG"));
        statsButton.setText("Statistics");
        statsButton.setToolTipText("Statistics");
        statsButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        statsButton.addActionListener(eHandler);

        expensesButton.setBackground(new java.awt.Color(255, 153, 51));
        expensesButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        expensesButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\expenses.PNG"));
        expensesButton.setText("Expenses");
        expensesButton.setToolTipText("Expenses");
        expensesButton.setBorder(new LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        expensesButton.addActionListener(eHandler);

        inventoryButton.setBackground(new java.awt.Color(255, 153, 51));
        inventoryButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        inventoryButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\inventory.PNG"));
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

//        im.tblModel.addColumn("Product");
//        im.tblModel.addColumn("Quantity");
//        im.tblModel.addColumn("Units");
//        im.tblModel.addColumn("Buying Price");
//        im.tblModel.addColumn("Selling Price");
//        im.tblModel.addColumn("Date");
        for (int i = 0; i < columns.size(); i++) {
			vPanel.tblModel.addColumn(columns.get(i));
		}
        
        stPanel.xpenseButton.addActionListener(eHandler);
        stPanel.salesButton.addActionListener(eHandler);
        stPanel.profitNLossButton.addActionListener(eHandler);
        stPanel.inventoryButton.addActionListener(eHandler);
        setUp.buttonCheck.addActionListener(eHandler);
        setUp.buttonSetUp.addActionListener(eHandler);
        im.bAdd.addActionListener(eHandler);
        im.bDelete.addActionListener(eHandler);
        im.bEdit.addActionListener(eHandler);
        im.txtSell.addActionListener(eHandler);
        ssPanel.comboAction.addActionListener(eHandler);
        vPanel.listOfTables.addListSelectionListener(listSelection);
		contentPanel.add(homePanel, "home");
        contentPanel.add(sp.salesPanel,"sales");
        contentPanel.add(xp.expensePanel,"xpense");
        contentPanel.add(calc.cp, "calc");
        contentPanel.add(stPanel.statsPanel, "stats");
        contentPanel.add(ssPanel.statsPanel, "salesStats");
        contentPanel.add(vPanel.viewTablesPanel, "view");
        contentPanel.add(setUp.setUpPanel, "setup");
        contentPanel.add(im.inventoryPanel, "inventory");
    }
    
    ListSelection listSelection = new ListSelection();
    
    class ListSelection implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			db.user = userTxt.getText();;
			db.pass = new String(passTxt.getPassword());
			db.connect();
			db.table = vPanel.listOfTables.getSelectedValue().toString();
			/*for (int i = vPanel.tblModel.getRowCount() - 1; i >= 0; i--) {
    			vPanel.tblModel.removeRow(i);
    		}*/
			//System.out.println(db.table);
			try {
				db.loadTable();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ListIterator<String> iterator = db.columns.listIterator();
			vPanel.lblTableViewed.setText(vPanel.listOfTables.getSelectedValue().toString().toUpperCase().replace("_", " "));
			/*columns.addAll(db.columns);
			 
			while (iterator.hasNext()) {
				String string = (String) iterator.next();
				vPanel.tblModel.addColumn(string);
				iterator.remove();
			}*/
			
			columns.clear();
			for (int i = 0; i < db.columns.size(); i++) {
				vPanel.tblModel.addColumn(db.columns.get(i));
				if (i == ((columns.size() - 1) / 2)) {
					columns.clear();
				}
			}
			
			for (int i = 0; i < db.listOfVectorsProjTbls.size(); i++) {
				vPanel.tblModel.addRow(db.listOfVectorsProjTbls.get(i));
			}
		}
    	
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
				for(String s : db.barcodes) {
					sp.products.add(s);
				}
				barcodes = db.barcodes.toArray(new String[db.barcodes.size()]);
		        sp.comboName.setModel(new DefaultComboBoxModel(barcodes));
		        sp.comboName.setSelectedItem("");
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
			
			if(e.getSource() == inventoryButton) {
				swithToInventoryPanel(card, contentPanel);
				frame.setTitle(title + " | Inventory Management");
				im.txtName.requestFocus();
//				loadInventory();
				ViewHelpers.clearJTable(im.tblModel);
				ViewHelpers.loadTable(im.tblModel, Stock.showAll());
			}
			
			if(e.getSource() == stPanel.xpenseButton) {
				//swithToCalc(card, contentPanel);
			}
			
			if(e.getSource() == stPanel.salesButton) {
				swithToSalesStats(card, contentPanel);
				try {
			        db.user = userTxt.getText();
					db.pass = new String(passTxt.getPassword());
					db.connect();
					//db.getDates();
					//db.getCurrentDate();
					db.getSalesTotalForToday();
					db.getGrandSalesTotal();
					db.getSalesTotalForYesterday();
					db.getSalesTotalForMonth();
					db.getSalesTotalForWeek();
					db.getCountOfItemsSold();
					db.getValueOfItemsSold();
					db.getSalesTotalForYear();
					ssPanel.lblSummary.setText("<html><body><table width=\"930\" height=100% border=\"0\">"+
							"<tr valign=\"top\">"+
							"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
							"<b>Total Sales</b>" +
							"<td style=\"background-color:#eeeeee;height:90px;width:500px;text-align:top;\">"+
							"<ul><li>Today - <b> Kshs. "+db.salesTotal+"</b></li><li>Yesterday -<b> Kshs. "+db.yesterdaySalesTotal+
							"</b></li><li>This week -<b> Kshs. "+db.weekSalesTotal+"</b></li>" +
									"<li>This month -<b> Kshs. "+db.monthSalesTotal+"</b></li>" +
									"<li>This Year -<b> Kshs. "+db.yearSalesTotal+"</b></li><li>From the begining of time -<b> Kshs. " +
									db.grandSalesTotal+"</b></li></ul></td></tr>" +
							"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
							"<b>Items Sold</b>" +
							"<td style=\"background-color:#eeeeee;height:90px;width:500px;text-align:top;\">"+
							"<ul><li>Most Popular -<b> "+db.top1+", "+db.top2+", "+db.top3+"</b></li><li>Least Popular --<b> "+db.bottom1+", "+db.bottom2+", "+
							db.bottom3+"</b></li><li>Most Valuable -<b> "+db.mostValuable1+", "+db.mostValuable2+", "+db.mostValuable3+"</b></li>" +
									"<li>Least Valuable -<b> "+db.leastValuable1+", "+db.leastValuable2+", "+db.leastValuable3+"</b></li></ul></td></tr>"+
							"<td style=\"background-color:#eeeeee;width:30px;text-align:top;\">"+
							"<b>Time</b>" +
							"<td style=\"background-color:#eeeeee;height:100px;width:500px;text-align:top;\">"+
							"<ul><li>Peak times -</li><li>Down Times -</li><li>Busiest days -</li><li>Least Busy Days -</li></ul></td></tr>"+
							"</table></body></html>");
					db.salesTotal = 0;
					db.grandSalesTotal = 0;
					db.yesterdaySalesTotal = 0;
					db.monthSalesTotal = 0;
					db.weekSalesTotal = 0;
					db.yearSalesTotal = 0;

				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}
				//db.dates.add(db.currentDate);
				List<String> l = new LinkedList<String>(db.dates);
				Collections.sort(l, Collections.reverseOrder());
		        ssPanel.fromCombo.setModel(new DefaultComboBoxModel(db.dates.toArray(new String[db.dates.size()])));
		        ssPanel.toCombo.setModel(new DefaultComboBoxModel(l.toArray(new String[l.size()])));
			}
 			
			/*if(!(userTxt.getText().equals("") || passTxt.getPassword().equals(""))) {
				loginButton.setEnabled(true);
			}
			else {
				loginButton.setEnabled(false);
			}*/
			
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
			
			if(e.getSource() == tablesMenu) {
				swithToView(card, contentPanel);
				frame.setTitle(title + " | Project Tables");
				 db.user = userTxt.getText();;
				 db.pass = new String(passTxt.getPassword());
				 db.connect();
				 try {
					db.getTables();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vPanel.listOfTables.setListData(db.tables.toArray(new String[db.tables.size()]));

			}
			
			if(e.getSource() == loginButton || e.getSource() == passTxt) {
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
			        lblError.setText("Invalid username or password!! Try again.");
					userTxt.requestFocus();
				}
			}
			
			if(e.getSource() == clickHereButton) {
				swithToSetUpHelp(card, overallPanel);
				set.userTxt.setText("");
				set.passTxt.setText("");	
				set.lblStatus.setText("");
				set.userVerifierLabel.setText("");
				set.passVerifierLabel.setText("");			
				set.userTxt.requestFocus();
			}

			if(e.getSource() == set.backButton) {
				logout(card, overallPanel);
				lblError.setText("");
				userTxt.setText("");
				passTxt.setText("");
				userTxt.requestFocus();
			}
			
			if(e.getSource() == set.setUpButton || e.getSource() == set.passTxt) {
				db.user = set.userTxt.getText();
				char [] p = set.passTxt.getPassword();
				db.pass = new String(p);
				db.setUpConnection();
				try {
					db.createDatabase();
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					set.lblStatus.setText("Error occured!!");
					set.userTxt.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(db.connected) {
					//login(card, overallPanel);
					set.lblStatus.setText("Set up seccessful. Click on the back button and login.");
			        set.lblStatus.setForeground(new java.awt.Color(0, 0, 0));
				}
				else {
			        set.lblStatus.setForeground(new java.awt.Color(255, 0, 51));
			        set.lblStatus.setText("Invalid username or password!! Try again.");
					set.userTxt.requestFocus();
				}
			}
			

			if(e.getSource() == setUpMenu) {
				swithToSetUp(card, contentPanel);
				frame.setTitle(title + " | Set Up the Project Tables");
				db.user = userTxt.getText();
				db.pass = new String(passTxt.getPassword());
				db.connect();
				try {
					db.createDatabase();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			if(e.getSource() == setUp.buttonCheck) {
				db.user = userTxt.getText();
				db.pass = new String(passTxt.getPassword());
				db.connect();
				try {
					db.getTables();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(listOfTables.containsAll(db.tables)) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "All tables are set up.");
				}
				else {
					JOptionPane.showMessageDialog(null, "You need to set up the tables. Click set up.", "Project set up", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(db.tables +" "+ listOfTables);
				}
			}
			
			if(e.getSource() == setUp.buttonSetUp) {
				db.user = userTxt.getText();;
				db.pass = new String(passTxt.getPassword());
				db.connect();
				try {
					db.createSalesTable();
					db.createExpenseHistoryTable();
					db.createExpenseReasonsTable();
					db.createInventoryTable();
					db.createTransactionHistoryTable();
					db.createExpensesTable();
					db.createItemsTable();
					JOptionPane.showMessageDialog(null, "Successfully created the tables");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Could not create tables!");
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == im.bAdd || e.getSource() == im.txtSell) {
//				db.user = userTxt.getText();
//				db.pass = new String(passTxt.getPassword());
//				db.connect();
//				try {
//					db.name = im.txtName.getText();
//					db.quantity = Integer.parseInt(im.txtQuantity.getText());
//					db.unit = im.comboUnits.getSelectedItem().toString();
//					db.buyPrice = Integer.parseInt(im.txtBuy.getText());
//					db.sellPrice = Integer.parseInt(im.txtSell.getText());
//					db.addInventory();
//					db.addToItemsTable();
//					loadInventory();
//				} catch(MySQLIntegrityConstraintViolationException me) {
//					try {
//						db.addNewerInventory();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
//					//JOptionPane.showMessageDialog(null, "Item name already exits! Consider just editing from here.", "Error", JOptionPane.ERROR_MESSAGE);
//				} catch (NumberFormatException nfe) {
//					im.txtName.requestFocus();
//					JOptionPane.showMessageDialog(null, "Please provide the right data.", "Invalid data", JOptionPane.ERROR_MESSAGE);					
//				}
//				
//				catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				im.txtName.setText("");
//				im.txtQuantity.setText("");
//				im.txtBuy.setText("");
//				im.txtSell.setText("");
//				im.txtName.requestFocus();
//				name varchar(45), quantity int(10),units varchar(45), buying_price double, selling_price double, created_at datetime
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("name", im.txtName.getText());
//				map.put("quantity", im.txtQuantity.getText());
//				map.put("units", im.comboUnits.getSelectedItem().toString());
//				map.put("buying_price", im.txtBuy.getText());
//				map.put("selling_price", im.txtSell.getText());
				if(!Stock.exists("name", im.txtName.getText())) {
					Stock.create(ViewHelpers.constructParamsMap("name", im.txtName.getText(), "quantity", im.txtQuantity.getText(),
							"units", im.comboUnits.getSelectedItem().toString(), "buying_price", im.txtBuy.getText(),
							"selling_price", im.txtSell.getText()));
					ViewHelpers.clearJTable(im.tblModel);
					ViewHelpers.loadTable(im.tblModel, Stock.showAll());
					im.txtName.setText("");
					im.txtQuantity.setText("");
					im.txtBuy.setText("");
					im.txtSell.setText("");
					im.txtName.requestFocus();
				}
				else {
					JOptionPane.showMessageDialog(null, "Record already exits!");
				}
//				im.txtName.setText("");
//				im.txtQuantity.setText("");
//				im.txtBuy.setText("");
//				im.txtSell.setText("");
//				im.txtName.requestFocus();

//				loadInventory();
			}
			/*
			if(e.getSource() == im.txtSell) {
				db.user = userTxt.getText();;
				db.pass = new String(passTxt.getPassword());
				db.connect();
				try {
					db.name = im.txtName.getText();
					db.quantity = Integer.parseInt(im.txtQuantity.getText());
					db.unit = im.comboUnits.getSelectedItem().toString();
					db.buyPrice = Integer.parseInt(im.txtBuy.getText());
					db.sellPrice = Integer.parseInt(im.txtSell.getText());
					db.addInventory();
					db.addToItemsTable();
					loadInventory();
				} catch(MySQLIntegrityConstraintViolationException me) {
					try {
						db.addNewerInventory();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					//JOptionPane.showMessageDialog(null, "Item name already exits! Consider just editing from here.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException nfe) {
					im.txtName.requestFocus();
					JOptionPane.showMessageDialog(null, "Please provide the right data.", "Invalid data", JOptionPane.ERROR_MESSAGE);					
				}
				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				im.txtName.setText("");
				im.txtQuantity.setText("");
				im.txtBuy.setText("");
				im.txtSell.setText("");
				im.txtName.requestFocus();
				loadInventory();
			}
			*/
			if(e.getSource() == im.bDelete) {
				db.user = userTxt.getText();
				db.pass = new String(passTxt.getPassword());
				db.connect();
				if (im.tblInventory.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select the item(s) to remove from inventory");
				}
				else {
					int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove selected item(s) from inventory?",
									"Remove", JOptionPane.YES_NO_OPTION);
					//System.out.println(choice);
					if (choice == 0) {
						for (int i = 0; i < im.tblInventory.getSelectedRows().length; i++) {
							db.name = (String) im.tblModel.getValueAt(
									im.tblInventory.getSelectedRows()[i], 0);
							try {
								db.deleteInventoryRecord();
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null,
										"Problem occurred!!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				im.txtName.requestFocus();
				loadInventory();
			}
			
			if(e.getSource() == im.bEdit) {
				db.user = userTxt.getText();
				db.pass = new String(passTxt.getPassword());
				db.connect();
				if(im.tblInventory.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select which row to edit.");
				}
				else {
					int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to edit selected item?",
							"Edit", JOptionPane.YES_NO_OPTION);
					//System.out.println(choice);
					if (choice == 0) {
						db.name = (String) im.tblModel.getValueAt(im.tblInventory.getSelectedRow(), 0);
						db.col = ((String) im.tblInventory.getColumnName(im.tblInventory.getSelectedColumn()).replace(" ", "_"));
						db.newValue = (String) im.tblModel.getValueAt(im.tblInventory.getSelectedRow(), im.tblInventory.getSelectedColumn());
						//JOptionPane.showMessageDialog(null, db.col);
						try {
							db.editInventoryRecord();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Problem occurred!!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				im.txtName.requestFocus();
				loadInventory();
			}
			
		}
    	
    }
    
    void loadInventory() {
    	try {
    		for (int i = im.tblModel.getRowCount() - 1; i >= 0; i--) {
    			im.tblModel.removeRow(i);
    		}
    		//db.columns.clear();
			db.loadInventoryTable();
			/*for (int i = 0; i < db.columns.size(); i++) {
				im.tblModel.addColumn(db.columns.get(i));
			}*/
			//db.columns.clear();
			for (int i = 0; i < db.listOfVectors.size(); i++) {
				im.tblModel.addRow(db.listOfVectors.get(i));
			}
			db.listOfVectors.clear();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    
    void resetForNewSession(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "home");
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
    
    void swithToSetUp(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "setup");
	}
    
    void swithToView(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "view");
	}
    
    void swithToCalc(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "calc");
	}
    
    void swithToStats(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "stats");
	}
    
    void swithToSalesStats(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "salesStats");
	}
    
    void swithToXpense(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "xpense");
	}
    
    void login(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "panel");
		resetForNewSession(card, contentPanel);	
		lblError.setText("");
		userVerifierLabel.setText("");
		passVerifierLabel.setText("");
	}
    
    void logout(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "login");
	}

    void swithToSetUpHelp(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "setHelp");
	}
    
    void swithToInventoryPanel(CardLayout card, JPanel cardpanel) {

		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, "inventory");
	}
    
    private void center( Window window ) {

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 9;
        int y = ((dim.height - h) / (dim.height - h)) + 10;

        // Move the window
        window.setLocation(x, y);
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
            java.util.logging.Logger.getLogger(MursalSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MursalSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MursalSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MursalSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	MursalSales ms = new MursalSales();
                ms.createGUI();
            }
        });
    }
}
