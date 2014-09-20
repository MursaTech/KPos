package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.ClientController;

public class PostPaidDialog {
	JLabel addressLabel, balanceLabel, currentLabel, dueLabel, idLabel, limitLabel, nameLabel, paidLabel, totalLabel;
    JButton cancelButton, saveButton;
    JComboBox comboID;
    JPanel contPanel, topPanel;
    public JTextField txtAddress, txtBalance, txtCurrent, txtDue, txtLimit, txtName, txtPaid, txtTotal;
    JDialog dialog;
    ClientController controller;
//    SalesPanel sp = new SalesPanel(controller);
    
    public PostPaidDialog(ClientController controller) {
		this.controller = controller;
//        sp.createSalesPanel();
	}

	public void createGUI() {
    	dialog = new JDialog();
        topPanel = new javax.swing.JPanel();
        contPanel = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        paidLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        comboID = new javax.swing.JComboBox();
        txtLimit = new javax.swing.JTextField();
        currentLabel = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtDue = new javax.swing.JTextField();
        txtCurrent = new javax.swing.JTextField();
        txtPaid = new javax.swing.JTextField();
        balanceLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        dueLabel = new javax.swing.JLabel();
        limitLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        
        dialog.setIconImage(new ImageIcon("F:\\POSWorkspace\\POS\\sale.png").getImage());
        dialog.setTitle("Add to Postpaid Account");
        dialog.setModal(true);	
        
        topPanel.setBackground(new java.awt.Color(0, 51, 102));

        contPanel.setBackground(new java.awt.Color(153, 204, 255));

        idLabel.setBackground(new java.awt.Color(153, 153, 255));
        idLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idLabel.setForeground(new java.awt.Color(0, 0, 204));
        idLabel.setText("ID number");
        idLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        idLabel.setOpaque(true);

        paidLabel.setBackground(new java.awt.Color(153, 153, 255));
        paidLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        paidLabel.setForeground(new java.awt.Color(0, 0, 204));
        paidLabel.setText("Paid");
        paidLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        paidLabel.setOpaque(true);

        nameLabel.setBackground(new java.awt.Color(153, 153, 255));
        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(0, 0, 204));
        nameLabel.setText("Full name");
        nameLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        nameLabel.setOpaque(true);

        comboID.setEditable(true);
        comboID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        currentLabel.setBackground(new java.awt.Color(153, 153, 255));
        currentLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        currentLabel.setForeground(new java.awt.Color(0, 0, 204));
        currentLabel.setText("Current transaction");
        currentLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        currentLabel.setOpaque(true);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        
//		txtPaid.setText(sp.lblShowTotal.getText());
//		txtCurrent.setText(sp.lblShowTotal.getText());
//		txtBalance.setText("l");

        balanceLabel.setBackground(new java.awt.Color(153, 153, 255));
        balanceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        balanceLabel.setForeground(new java.awt.Color(0, 0, 204));
        balanceLabel.setText("Balance owing");
        balanceLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        balanceLabel.setOpaque(true);

        addressLabel.setBackground(new java.awt.Color(153, 153, 255));
        addressLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(0, 0, 204));
        addressLabel.setText("Address");
        addressLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        addressLabel.setOpaque(true);

        dueLabel.setBackground(new java.awt.Color(153, 153, 255));
        dueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dueLabel.setForeground(new java.awt.Color(0, 0, 204));
        dueLabel.setText("Due date");
        dueLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        dueLabel.setOpaque(true);

        limitLabel.setBackground(new java.awt.Color(153, 153, 255));
        limitLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        limitLabel.setForeground(new java.awt.Color(0, 0, 204));
        limitLabel.setText("Limit");
        limitLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        limitLabel.setOpaque(true);

        totalLabel.setBackground(new java.awt.Color(153, 153, 255));
        totalLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalLabel.setForeground(new java.awt.Color(0, 0, 204));
        totalLabel.setText("Toal owing");
        totalLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 2, true));
        totalLabel.setOpaque(true);

        saveButton.setBackground(new java.awt.Color(255, 153, 51));
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveButton.setText("Save Transaction");
        saveButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				db.user = user;
//				db.pass = pass;
//				
//				db.natID = Integer.parseInt(comboID.getSelectedItem().toString());
//				db.customerName = txtName.getText();
//				db.address = txtAddress.getText();
//				db.limit = Double.parseDouble(txtLimit.getText());
//				db.dueDate = txtDue.getText();
//				
//				db.registerPostpaidCustomer();
				
			}
		});

        cancelButton.setBackground(new java.awt.Color(255, 153, 51));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        javax.swing.GroupLayout contPanelLayout = new javax.swing.GroupLayout(contPanel);
        contPanel.setLayout(contPanelLayout);
        contPanelLayout.setHorizontalGroup(
            contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contPanelLayout.createSequentialGroup()
                        .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(limitLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dueLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paidLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(balanceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboID, 0, 185, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtAddress)
                            .addComponent(txtLimit)
                            .addComponent(txtDue)
                            .addComponent(txtCurrent)
                            .addComponent(txtPaid)
                            .addComponent(txtTotal)
                            .addComponent(txtBalance))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(contPanelLayout.createSequentialGroup()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        contPanelLayout.setVerticalGroup(
            contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(contPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelButton, saveButton});

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(contPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(contPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        
        center(dialog);
        dialog.pack();
        dialog.setVisible(true);
    
	}
    
    private void center( Window window ) {

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 5;
        int y = ((dim.height - h) / (dim.height - h)) + 35;

        // Move the window
        window.setLocation(x, y);
    }
}

