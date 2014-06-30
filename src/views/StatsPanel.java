/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import org.jdesktop.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Muaad
 */
public class StatsPanel extends JFrame {

    /**
     * Creates new form StatsPanel
     */
    /*public StatsPanel() {
        createStatsPanel();
    }*/
    
    //MursalSales ms = new MursalSales();
    JPanel contentPane = new JPanel();
    JPanel buttonContainer;
    JButton inventoryButton;
    JPanel jPanel2;
    JPanel jPanel3;
    JPanel jPanel4;
    JPanel jPanel5;
    JButton profitNLossButton;
    JButton salesButton;
    JPanel statsPanel;
    JButton xpenseButton;
    List<String> columns = new LinkedList<String>();
    
    public void createStatsPanel() {

        statsPanel = new JPanel();
        buttonContainer = new JPanel();
        jPanel2 = new JPanel();
        salesButton = new JButton();
        jPanel3 = new JPanel();
        xpenseButton = new JButton();
        jPanel4 = new JPanel();
        inventoryButton = new JButton();
        jPanel5 = new JPanel();
        profitNLossButton = new JButton();

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        statsPanel.setBackground(new Color(0, 51, 102));

        buttonContainer.setBackground(new Color(153, 204, 255));
        buttonContainer.setBorder(new LineBorder(new Color(51, 255, 51), 3, true));

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Sales Figures", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 1, 12)));
        jPanel2.setOpaque(false);

        salesButton.setBackground(new Color(255, 153, 51));
        salesButton.setFont(new Font("Tahoma", 1, 14));
        salesButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\sale.PNG"));
        salesButton.setToolTipText("Sales");
        salesButton.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        salesButton.setFocusable(false);
        salesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        salesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        //salesButton.addActionListener(ms.eHandler);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(salesButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(salesButton)
                .addContainerGap())
        );

        jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Expense details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 1, 12)));
        jPanel3.setOpaque(false);

        xpenseButton.setBackground(new Color(255, 153, 51));
        xpenseButton.setFont(new Font("Tahoma", 1, 14));
        xpenseButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\expenses.PNG"));
        xpenseButton.setText("Expenses");
        xpenseButton.setToolTipText("Expenses");
        xpenseButton.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        xpenseButton.setFocusable(false);
        xpenseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        xpenseButton.setMaximumSize(new Dimension(211, 131));
        xpenseButton.setMinimumSize(new Dimension(211, 131));
        xpenseButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        //xpenseButton.addActionListener(ms.eHandler);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(xpenseButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 207, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(xpenseButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Inventory Levels", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 1, 12)));
        jPanel4.setOpaque(false);

        inventoryButton.setBackground(new Color(255, 153, 51));
        inventoryButton.setFont(new Font("Tahoma", 1, 14));
        inventoryButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\inventory.PNG"));
        inventoryButton.setText("Inventory Management");
        inventoryButton.setToolTipText("Inventory Management");
        inventoryButton.setBorder(new LineBorder(new Color(255, 0, 102), 1, true));
        inventoryButton.setFocusable(false);
        inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        inventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        //inventoryButton.addActionListener(ms.eHandler);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(inventoryButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(inventoryButton)
                .addContainerGap())
        );

        jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Profit, Loss and Other Stats", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 1, 12)));
        jPanel5.setOpaque(false);

        profitNLossButton.setIcon(new ImageIcon("F:\\POSWorkspace\\POS\\pnl.PNG"));
        profitNLossButton.setText("Profit & Loss");
        profitNLossButton.setToolTipText("Profit & Loss");
        //profitNLossButton.addActionListener(ms.eHandler);
        
        columns.add("Transaction ID");
        columns.add("Total Amount");
        columns.add("Amount Paid");
        columns.add("Balance");
        columns.add("Time");
        columns.add("Discount");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(profitNLossButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 207, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(profitNLossButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout buttonContainerLayout = new org.jdesktop.layout.GroupLayout(buttonContainer);
        buttonContainer.setLayout(buttonContainerLayout);
        buttonContainerLayout.setHorizontalGroup(
            buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonContainerLayout.createSequentialGroup()
                .add(63, 63, 63)
                .add(buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 136, Short.MAX_VALUE)
                .add(buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(98, 98, 98))
        );
        buttonContainerLayout.setVerticalGroup(
            buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonContainerLayout.createSequentialGroup()
                .add(24, 24, 24)
                .add(buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 35, Short.MAX_VALUE)
                .add(buttonContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(30, 30, 30))
        );

        org.jdesktop.layout.GroupLayout statsPanelLayout = new org.jdesktop.layout.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statsPanelLayout.createSequentialGroup()
                .add(42, 42, 42)
                .add(buttonContainer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statsPanelLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(buttonContainer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(statsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(statsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        //pack();
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
            java.util.logging.Logger.getLogger(StatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatsPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new StatsPanel().setVisible(true);
            }
        });
    }
}
