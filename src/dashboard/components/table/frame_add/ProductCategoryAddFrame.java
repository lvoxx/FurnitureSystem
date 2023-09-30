/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard.components.table.frame_add;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.sql.*;
import query.connect.Settings;
import query.tool.query.ProductCategoryQuery;

/**
 *
 * @author Admin
 */
public class ProductCategoryAddFrame extends javax.swing.JFrame {

    private JFrame frame;
    private Connection conn;

    private boolean isInvalid = true;

    public ProductCategoryAddFrame() {
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initComponents();
        this.frame = this;
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        closeBtn.setEnabled(false);
        alertProCtg.setVisible(false);

        try {
            this.conn = Settings.BuildConnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryAddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        categoryNameBox = new textfield.swing.TextField();
        closeBtn = new swing.MyButton();
        okBtn = new swing.MyButton();
        alertProCtg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 32)); // NOI18N
        jLabel1.setText("Add Product Category ");

        categoryNameBox.setLabelText("Product Category Name");
        categoryNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryNameBoxActionPerformed(evt);
            }
        });
        categoryNameBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoryNameBoxKeyReleased(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(255, 0, 51));
        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setText("Cancel");
        closeBtn.setColor(new java.awt.Color(255, 0, 51));
        closeBtn.setColorOver(new java.awt.Color(255, 102, 102));
        closeBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeBtnMouseClicked(evt);
            }
        });

        okBtn.setBackground(new java.awt.Color(0, 255, 255));
        okBtn.setForeground(new java.awt.Color(0, 0, 0));
        okBtn.setText("Ok");
        okBtn.setColor(new java.awt.Color(0, 255, 255));
        okBtn.setColorOver(new java.awt.Color(102, 255, 255));
        okBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        okBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okBtnMouseClicked(evt);
            }
        });

        alertProCtg.setForeground(new java.awt.Color(255, 0, 51));
        alertProCtg.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(categoryNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(alertProCtg, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(categoryNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(alertProCtg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoryNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryNameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryNameBoxActionPerformed

    private void closeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeBtnMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        frame.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void okBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okBtnMouseClicked
        if (!categoryNameBox.getText().isEmpty() && !isInvalid) {
            try {
                new ProductCategoryQuery(conn).insertProductCategory(categoryNameBox.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ProductCategoryAddFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.dispose();
        } else if(categoryNameBox.getText().isEmpty()){
            alertProCtg.setText("Do not empty field");
            alertProCtg.setVisible(true);
            isInvalid = true;
        }
    }//GEN-LAST:event_okBtnMouseClicked

    private void categoryNameBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryNameBoxKeyReleased
        try {
            if (new ProductCategoryQuery(conn).selectProductCategoryByName(categoryNameBox.getText()).size() > 0) {
                alertProCtg.setText(categoryNameBox.getText() + " is already exists");
                alertProCtg.setVisible(true);
                isInvalid = true;
            } else {
                alertProCtg.setVisible(false);
                isInvalid = false;
            }
            if (isInvalid) {
                closeBtn.setEnabled(false);
            } else {
                closeBtn.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryAddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_categoryNameBoxKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertProCtg;
    private textfield.swing.TextField categoryNameBox;
    private swing.MyButton closeBtn;
    private javax.swing.JLabel jLabel1;
    private swing.MyButton okBtn;
    // End of variables declaration//GEN-END:variables
}
