/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard.components.table.frame_edit;

import dashboard.components.table.controllers.IAlert;
import dashboard.components.table.controllers.IData;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.sql.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import query.connect.Settings;
import query.tool.model.OrderDetails;
import query.tool.model.ShippingProvider;
import query.tool.query.OrderDetailsQuery;
import query.tool.query.ProductQuery;
import query.tool.query.ShippingProviderQuery;

/**
 *
 * @author Admin
 */
public class UnPaidOrderEditFrame extends javax.swing.JFrame {

    private JFrame frame;
    private IData dataI;
    private Connection conn;
    private ShippingProviderQuery query;

    private Object[] rowData;
    private List<ShippingProvider> shippingCategory;
    private List<OrderDetails> orderDetails;
    private boolean isQuantityValid = false;
    private boolean isFixedPriceValid = false;

    public UnPaidOrderEditFrame(Object[] data) {
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initComponents();
        this.frame = this;
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);

        alertQuantity.setVisible(false);
        alertFixedPrice.setVisible(false);

        try {
            this.conn = Settings.BuildConnect();
        } catch (SQLException ex) {
            Logger.getLogger(UnPaidOrderEditFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rowData = data;
        getData();
        setData();

        dataI = new IData() {
            @Override
            public void addAllRow() {
                loadOrderDetails();
            }

            @Override
            public void refreshData() {
//                try {
//                    reloadData();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        };
        this.tableUnpaidOrderDetails1.setAlert(new IAlert() {
            @Override
            public void alertQuantityOn(boolean isVisible) {
                alertQuantity.setVisible(isVisible);
                isQuantityValid = isVisible;
            }

            @Override
            public void alertFixedPriceOn(boolean isVisible) {
                alertFixedPrice.setVisible(isVisible);
                isQuantityValid = isVisible;
            }

            @Override
            public void setNewTotal(String total) {
                totalPrice.setText(total);
            }

        });
        tableUnpaidOrderDetails1.setData(dataI);
        setTotalPrice();
        tableUnpaidOrderDetails1.setFrame(frame);
    }

    public void setData() {
        StatusChoose.addItem("UnPaid");
        StatusChoose.addItem("Paid");

        shippingChooseBox.addItem(rowData[2].toString());
        for (ShippingProvider i : shippingCategory) {
            if (i.getShippingName().equals(rowData[2].toString())) {
                continue;
            }
            shippingChooseBox.addItem(i.getShippingName());
        }

        customerNameBox.setText(rowData[1].toString());
        orderIDBox.setText(rowData[0].toString());
        dateOrderBox.setText(rowData[4].toString());
        userCreatedBox.setText(rowData[5].toString());
    }

    public void loadOrderDetails() {
        //Load all order details by ID
        DefaultTableModel model = (DefaultTableModel) tableUnpaidOrderDetails1.getTable().getModel();
        orderDetails.stream().forEach((item) -> {
            //Load Data to table
            String productName = "";
            try {
                productName = new ProductQuery(this.conn).selectProduct(Arrays.asList(item.getProductID())).get(0).getProductName();
                model.addRow(new Object[]{item.getOrderID(), productName, item.getQuantity(), item.getFixedPrice(), item.getQuantity() * item.getFixedPrice()});
            } catch (SQLException ex) {
                Logger.getLogger(UnPaidOrderEditFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        model.fireTableDataChanged();
    }

    public void getData() {
        try {
            query = new ShippingProviderQuery(this.conn);
            shippingCategory = query.selectShippingProvider();
            orderDetails = new OrderDetailsQuery(this.conn).selectOrderDetails(Integer.valueOf(rowData[0].toString()));
        } catch (SQLException ex) {
            Logger.getLogger(UnPaidOrderEditFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTotalPrice() {
        DefaultTableModel model = (DefaultTableModel) tableUnpaidOrderDetails1.getTable().getModel();
        int rows = model.getRowCount();
        int total = 0;
        for (int i = 0; i < rows; ++i) {
            total += Integer.valueOf(model.getValueAt(i, 4).toString());
        }
        totalPrice.setText(total + " $");
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
        customerNameBox = new textfield.swing.TextField();
        dateOrderBox = new textfield.swing.TextField();
        userCreatedBox = new textfield.swing.TextField();
        closeBtn = new swing.MyButton();
        StatusChoose = new dashboard.components.combobox.Combobox();
        okBtn = new swing.MyButton();
        shippingChooseBox = new dashboard.components.combobox.Combobox();
        alertQuantity = new javax.swing.JLabel();
        alertFixedPrice = new javax.swing.JLabel();
        tableUnpaidOrderDetails1 = new dashboard.components.tabledrawer.TableUnpaidOrderDetails();
        jLabel2 = new javax.swing.JLabel();
        totalPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 32)); // NOI18N
        jLabel1.setText("Edit Order Info");

        orderIDBox.setEditable(false);
        orderIDBox.setText("CostID");
        orderIDBox.setLabelText("Order ID");
        orderIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderIDBoxActionPerformed(evt);
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

        dateOrderBox.setText("Date Added");
        dateOrderBox.setLabelText("Date Order");
        dateOrderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateOrderBoxActionPerformed(evt);
            }
        });
        dateOrderBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateOrderBoxKeyReleased(evt);
            }
        });

        userCreatedBox.setEditable(false);
        userCreatedBox.setText("Discount");
        userCreatedBox.setLabelText("User Created");
        userCreatedBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userCreatedBoxActionPerformed(evt);
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

        StatusChoose.setLabeText("Status");

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
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        shippingChooseBox.setLabeText("Shipping Name");

        alertQuantity.setForeground(new java.awt.Color(255, 0, 51));
        alertQuantity.setText("Quantity must be greater than or equal to 1");

        alertFixedPrice.setForeground(new java.awt.Color(255, 0, 51));
        alertFixedPrice.setText("Fixed must be greater than 0");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 28)); // NOI18N
        jLabel2.setText("TOTAL:");

        totalPrice.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        totalPrice.setText("999");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(StatusChoose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(shippingChooseBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(orderIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(customerNameBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(userCreatedBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2)
                            .addComponent(totalPrice)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(alertQuantity)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(alertFixedPrice)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableUnpaidOrderDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableUnpaidOrderDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userCreatedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(shippingChooseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(StatusChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alertQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(alertFixedPrice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPrice)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIDBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderIDBoxActionPerformed

    private void customerNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameBoxActionPerformed

    private void dateOrderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateOrderBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateOrderBoxActionPerformed

    private void userCreatedBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userCreatedBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userCreatedBoxActionPerformed

    private void closeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeBtnMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        frame.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void okBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okBtnMouseClicked
//        if (isQuantityValid && isFixedPriceValid) {
//            try {
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(UnPaidOrderEditFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            frame.dispose();
//        }
    }//GEN-LAST:event_okBtnMouseClicked

    private void dateOrderBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateOrderBoxKeyReleased

    }//GEN-LAST:event_dateOrderBoxKeyReleased

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed

    }//GEN-LAST:event_okBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dashboard.components.combobox.Combobox StatusChoose;
    private javax.swing.JLabel alertFixedPrice;
    private javax.swing.JLabel alertQuantity;
    private swing.MyButton closeBtn;
    private textfield.swing.TextField customerNameBox;
    private textfield.swing.TextField dateOrderBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private swing.MyButton okBtn;
    private textfield.swing.TextField orderIDBox;
    private dashboard.components.combobox.Combobox shippingChooseBox;
    private dashboard.components.tabledrawer.TableUnpaidOrderDetails tableUnpaidOrderDetails1;
    private javax.swing.JLabel totalPrice;
    private textfield.swing.TextField userCreatedBox;
    // End of variables declaration//GEN-END:variables
}
