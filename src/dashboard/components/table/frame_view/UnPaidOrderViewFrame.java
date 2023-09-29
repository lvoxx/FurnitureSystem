/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard.components.table.frame_view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class UnPaidOrderViewFrame extends javax.swing.JFrame {
    private JFrame frame;

    private Object[] rowData;
    
    public UnPaidOrderViewFrame(Object[] data) {
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initComponents();
        this.frame = this;
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);

        this.rowData = data;
        setData();
    }


    public void setData() {
        customerNameBox.setText(rowData[1].toString());
        shippingBox.setText(rowData[2].toString());
        creatorNameBox.setText(rowData[5].toString());
        orderIDBox.setText(rowData[0].toString());
        statusBox.setText(rowData[3].toString());
        dateOrderBox.setText(rowData[4].toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        orderIDBox = new textfield.swing.TextField();
        creatorNameBox = new textfield.swing.TextField();
        shippingBox = new textfield.swing.TextField();
        statusBox = new textfield.swing.TextField();
        dateOrderBox = new textfield.swing.TextField();
        closeBtn = new swing.MyButton();
        customerNameBox = new textfield.swing.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 32)); // NOI18N
        jLabel1.setText("Order Info");

        orderIDBox.setEditable(false);
        orderIDBox.setText("CostID");
        orderIDBox.setLabelText("Order ID");
        orderIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderIDBoxActionPerformed(evt);
            }
        });

        creatorNameBox.setEditable(false);
        creatorNameBox.setText("Contact");
        creatorNameBox.setLabelText("Creator Name");
        creatorNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creatorNameBoxActionPerformed(evt);
            }
        });

        shippingBox.setEditable(false);
        shippingBox.setText("Address");
        shippingBox.setLabelText("Shipping Service Name");
        shippingBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shippingBoxActionPerformed(evt);
            }
        });

        statusBox.setEditable(false);
        statusBox.setText("Date Added");
        statusBox.setLabelText("Status");
        statusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBoxActionPerformed(evt);
            }
        });

        dateOrderBox.setEditable(false);
        dateOrderBox.setText("Discount");
        dateOrderBox.setLabelText("Date Order");
        dateOrderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateOrderBoxActionPerformed(evt);
            }
        });

        closeBtn.setForeground(new java.awt.Color(0, 0, 0));
        closeBtn.setText("Ok");
        closeBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeBtnMouseClicked(evt);
            }
        });

        customerNameBox.setEditable(false);
        customerNameBox.setText("Contact");
        customerNameBox.setLabelText("Customer Name");
        customerNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dateOrderBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusBox, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                .addComponent(shippingBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(creatorNameBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orderIDBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(customerNameBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(orderIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customerNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shippingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(creatorNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIDBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderIDBoxActionPerformed

    private void creatorNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creatorNameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creatorNameBoxActionPerformed

    private void shippingBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shippingBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shippingBoxActionPerformed

    private void statusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusBoxActionPerformed

    private void dateOrderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateOrderBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateOrderBoxActionPerformed

    private void closeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeBtnMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        frame.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void customerNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton closeBtn;
    private textfield.swing.TextField creatorNameBox;
    private textfield.swing.TextField customerNameBox;
    private textfield.swing.TextField dateOrderBox;
    private javax.swing.JLabel jLabel1;
    private textfield.swing.TextField orderIDBox;
    private textfield.swing.TextField shippingBox;
    private textfield.swing.TextField statusBox;
    // End of variables declaration//GEN-END:variables
}
