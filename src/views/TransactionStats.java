/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muaad
 */
public class TransactionStats extends javax.swing.JFrame {

    DefaultTableModel tblModel = new DefaultTableModel();
    String total = "Total for the selected period: Kshs. ";
    public TransactionStats() {
        createTransactionPanel();
    }

    void createTransactionPanel() {

        transactionStatsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable(tblModel);
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        transactionStatsPanel.setBackground(new java.awt.Color(0, 51, 102));

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 102));

        tblTransaction.setBackground(new java.awt.Color(153, 204, 255));
        //tblTransaction.setFillsViewportHeight(true);
        //tblTransaction.setShowGrid(true);
        tblTransaction.setRowSelectionAllowed(true);
        tblTransaction.setAutoCreateRowSorter(true);
        
        jScrollPane1.setViewportView(tblTransaction);

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotal.setForeground(Color.white);
        lblTotal.setText(total);
        //lblTotal.setOpaque(true);

        javax.swing.GroupLayout transactiionStatsPanelLayout = new javax.swing.GroupLayout(transactionStatsPanel);
        transactionStatsPanel.setLayout(transactiionStatsPanelLayout);
        transactiionStatsPanelLayout.setHorizontalGroup(
            transactiionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactiionStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transactiionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                    .addGroup(transactiionStatsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        transactiionStatsPanelLayout.setVerticalGroup(
            transactiionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactiionStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transactionStatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transactionStatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransactionStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionStats().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    javax.swing.JLabel lblTotal;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable tblTransaction;
    javax.swing.JPanel transactionStatsPanel;
    // End of variables declaration
}
