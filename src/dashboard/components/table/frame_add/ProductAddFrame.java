/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard.components.table.frame_add;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.sql.*;
import query.connect.Settings;
import query.tool.model.Product;
import query.tool.model.ProductCategory;
import query.tool.query.ProductCategoryQuery;
import query.tool.query.ProductQuery;

/**
 *
 * @author Admin
 */
public class ProductAddFrame extends javax.swing.JFrame {

    private JFrame frame;
    private Connection conn;
    private ProductCategoryQuery query;

    private List<ProductCategory> productCategory;
    private boolean isInStockQuantityValid = false;
    private boolean isPriceValid = false;

    public ProductAddFrame() {
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initComponents();
        this.frame = this;
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        alertISQ.setVisible(false);
        alertPrice.setVisible(false);

        try {
            this.conn = Settings.BuildConnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductAddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        getData();
        setData();
    }

    public void setData() {
        for (ProductCategory i : productCategory) {
            productCategoryChoose.addItem(i.getCategoryName());

        }
    }

    public void getData() {
        try {
            query = new ProductCategoryQuery(this.conn);

            productCategory = query.selectProductCategoryList();
        } catch (SQLException ex) {
            Logger.getLogger(ProductAddFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        productNameBox = new textfield.swing.TextField();
        closeBtn = new swing.MyButton();
        productCategoryChoose = new dashboard.components.combobox.Combobox();
        okBtn = new swing.MyButton();
        priceBox = new textfield.swing.TextField();
        inStockQuantityBox = new textfield.swing.TextField();
        alertISQ = new javax.swing.JLabel();
        alertPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 32)); // NOI18N
        jLabel1.setText("Add Product Info");

        productNameBox.setLabelText("Product Name");
        productNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameBoxActionPerformed(evt);
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

        productCategoryChoose.setLabeText("Product Category");

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

        priceBox.setLabelText("Price");
        priceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceBoxActionPerformed(evt);
            }
        });
        priceBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceBoxKeyReleased(evt);
            }
        });

        inStockQuantityBox.setLabelText("In Stock Quantity");
        inStockQuantityBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inStockQuantityBoxActionPerformed(evt);
            }
        });
        inStockQuantityBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inStockQuantityBoxKeyReleased(evt);
            }
        });

        alertISQ.setForeground(new java.awt.Color(255, 0, 51));
        alertISQ.setText("Invalid In Stock Quantity");

        alertPrice.setForeground(new java.awt.Color(255, 0, 51));
        alertPrice.setText("Invalid Price");

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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(priceBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                .addComponent(inStockQuantityBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(productNameBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(productCategoryChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 41, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alertISQ)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(alertPrice)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(productNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inStockQuantityBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(priceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productCategoryChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(alertISQ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alertPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameBoxActionPerformed

    private void closeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeBtnMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        frame.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void okBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okBtnMouseClicked
        if (isInStockQuantityValid && isPriceValid) {
            try {
                int productCtgID = 0;
                for (ProductCategory i : productCategory) {
                    if (i.getCategoryName().equals(productCategoryChoose.getSelectedItem())) {
                        productCtgID = i.getProductCtgID();
                        break;
                    }
                }
                new ProductQuery(this.conn).insertProduct(productNameBox.getText(), Integer.valueOf(inStockQuantityBox.getText()), Integer.valueOf(priceBox.getText()), productCtgID);
            } catch (SQLException ex) {
                Logger.getLogger(ProductAddFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.dispose();
        }
    }//GEN-LAST:event_okBtnMouseClicked

    private void priceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceBoxActionPerformed

    private void inStockQuantityBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inStockQuantityBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inStockQuantityBoxActionPerformed

    private void inStockQuantityBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inStockQuantityBoxKeyReleased
        if (!inStockQuantityBox.getText().matches("[0-9]+")) {
            alertISQ.setVisible(true);
            isInStockQuantityValid = false;
        } else {
            alertISQ.setVisible(false);
            isInStockQuantityValid = true;
        }
    }//GEN-LAST:event_inStockQuantityBoxKeyReleased

    private void priceBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceBoxKeyReleased
        if (!priceBox.getText().matches("[0-9]+")) {
            alertPrice.setVisible(true);
            isPriceValid = false;
        } else {
            alertPrice.setVisible(false);
            isPriceValid = true;
        }
    }//GEN-LAST:event_priceBoxKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertISQ;
    private javax.swing.JLabel alertPrice;
    private swing.MyButton closeBtn;
    private textfield.swing.TextField inStockQuantityBox;
    private javax.swing.JLabel jLabel1;
    private swing.MyButton okBtn;
    private textfield.swing.TextField priceBox;
    private dashboard.components.combobox.Combobox productCategoryChoose;
    private textfield.swing.TextField productNameBox;
    // End of variables declaration//GEN-END:variables
}
