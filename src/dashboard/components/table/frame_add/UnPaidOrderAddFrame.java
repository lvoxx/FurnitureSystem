/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard.components.table.frame_add;

import dashboard.components.table.controllers.IAlert;
import dashboard.components.table.controllers.IData;
import dashboard.components.table.controllers.IPaid;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import query.connect.Settings;
import query.tool.model.OrderDetails;
import query.tool.model.Product;
import query.tool.model.ShippingProvider;
import query.tool.model.User;
import query.tool.query.CustomerQuery;
import query.tool.query.OrderDetailsQuery;
import query.tool.query.OrderQuery;
import query.tool.query.ProductQuery;
import query.tool.query.ShippingProviderQuery;

/**
 *
 * @author Admin
 */
public class UnPaidOrderAddFrame extends javax.swing.JFrame {

    private JFrame frame;
    private IData dataI;
    private IPaid paidI;
    private Connection conn;
    private ShippingProviderQuery queryS;
    private CustomerQuery queryC;

    private User user;
    private List<ShippingProvider> shippingCategory;
    private List<String> customerCategory;
    private List<OrderDetails> orderDetails;
    private boolean isQuantityValid = true;
    private boolean isFixedPriceValid = true;

    public UnPaidOrderAddFrame(User user) {
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initComponents();
        this.frame = this;
        this.user = user;
        tableUnpaidOrderDetails1.getTable().getColumnModel().getColumn(0).setHeaderValue("Product ID");
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);

        alertQuantity.setVisible(false);
        alertFixedPrice.setVisible(false);

        try {
            this.conn = Settings.BuildConnect();
        } catch (SQLException ex) {
            Logger.getLogger(UnPaidOrderAddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        getData();
        setData();

        dataI = new IData() {
            @Override
            public void addAllRow() {
            }

            @Override
            public void refreshData() {
                //setTotalPrice();
            }
        };
        this.tableUnpaidOrderDetails1.setAlert(new IAlert() {
            @Override
            public void alertQuantityOn(boolean isVisible) {
                alertQuantity.setVisible(isVisible);
                isQuantityValid = !isVisible;
                //System.out.println("Quantity " + isQuantityValid);
            }

            @Override
            public void alertFixedPriceOn(boolean isVisible) {
                alertFixedPrice.setVisible(isVisible);
                isFixedPriceValid = !isVisible;
                //System.out.println("FixedPrice " + isFixedPriceValid);
            }

            @Override
            public void setNewTotal(String total) {
                setTotalPrice();
                //totalPrice.setText(total);
            }

        });
        //tableUnpaidOrderDetails1.setData(dataI);
        //setTotalPrice();
        tableUnpaidOrderDetails1.setFrame(frame);
    }

    public void setData() {
        //Customer Choose
        for (String i : customerCategory) {
            customerChooseBox.addItem(i);
        }

        //Status Choose
        StatusChoose.addItem("UnPaid");
        StatusChoose.addItem("Paid");

        //Shipping Category Choose
        for (ShippingProvider i : shippingCategory) {
            shippingChooseBox.addItem(i.getShippingName());
        }

        //UserID Created
        userCreatedBox.setText(this.user.getName());
//        customerNameBox.setText(rowData[1].toString());
//        orderIDBox.setText(rowData[0].toString());
//        dateOrderBox.setText(rowData[4].toString());

    }

    public void getData() {
        try {
            queryS = new ShippingProviderQuery(this.conn);
            queryC = new CustomerQuery(this.conn);

            shippingCategory = queryS.selectShippingProvider();
            customerCategory = queryC.selectCustomerName();
        } catch (SQLException ex) {
            Logger.getLogger(UnPaidOrderAddFrame.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setIPaid(IPaid paidI) {
        this.paidI = paidI;
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
        productSuggestionBox = new swing.SuggestionBox();
        addProductBtn = new swing.MyButton();
        customerChooseBox = new dashboard.components.combobox.Combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 32)); // NOI18N
        jLabel1.setText("Add Order Info");

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
        totalPrice.setText("0 $");

        productSuggestionBox.setForeground(new java.awt.Color(255, 255, 255));
        productSuggestionBox.setToolTipText("");

        addProductBtn.setBackground(new java.awt.Color(0, 255, 255));
        addProductBtn.setForeground(new java.awt.Color(0, 0, 0));
        addProductBtn.setText("Add");
        addProductBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        addProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductBtnActionPerformed(evt);
            }
        });

        customerChooseBox.setLabeText("Customer Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerChooseBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(totalPrice)
                                    .addComponent(userCreatedBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(shippingChooseBox, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(StatusChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productSuggestionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(alertQuantity)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(alertFixedPrice)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableUnpaidOrderDetails1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableUnpaidOrderDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userCreatedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerChooseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(shippingChooseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StatusChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productSuggestionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(alertQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alertFixedPrice)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPrice)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tableUnpaidOrderDetails1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (isQuantityValid && isFixedPriceValid) {
            try {
                //UPDATE ORDER
                int orderID = 0;

                int userIDCreated = user.getUserID();
                int customerID = new CustomerQuery(Settings.BuildConnect()).selectCustomerIDByName(customerChooseBox.getSelectedItem().toString());
                int shippingID = new ShippingProviderQuery(Settings.BuildConnect()).selectShippingProviderByName(shippingChooseBox.getSelectedItem().toString()).getShippingID();
                String status = StatusChoose.getSelectedItem().toString();

                orderID = new OrderQuery(Settings.BuildConnect()).insertOrderCustom(customerID, shippingID, userIDCreated, status);

                //UPDATE ORDER DETAILS
                DefaultTableModel model = (DefaultTableModel) tableUnpaidOrderDetails1.getTable().getModel();
                int rows = model.getRowCount();
                System.out.println(rows);
                //new OrderDetailsQuery(Settings.BuildConnect()).deleteAllOrderDetails(orderID);
                for (int i = 0; i < rows; ++i) {
                    
                    int productID = Integer.parseInt(model.getValueAt(i, 0).toString());
                    int quantity = Integer.parseInt(model.getValueAt(i, 2).toString());
                    int fixedPrice = Integer.parseInt(model.getValueAt(i, 3).toString());

                    OrderDetails item = new OrderDetails(orderID, productID, quantity, fixedPrice);
                    System.out.println(item);
                    new OrderDetailsQuery(Settings.BuildConnect()).insertOrderDetails(item);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UnPaidOrderAddFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (StatusChoose.getSelectedItem().toString().equalsIgnoreCase("Paid")) {
                this.paidI.makeEventOrderChange();
            }
            frame.dispose();
        }
    }//GEN-LAST:event_okBtnMouseClicked

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
    }//GEN-LAST:event_okBtnActionPerformed

    private void addProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductBtnActionPerformed
        try {
            //System.out.print(suggestionBox1.getText());            
            if (!productSuggestionBox.getText().isEmpty()) {
                Product productSearch = new ProductQuery(Settings.BuildConnect()).selectProductByName(productSuggestionBox.getText());
                DefaultTableModel model = (DefaultTableModel) tableUnpaidOrderDetails1.getTable().getModel();
                model.addRow(new Object[]{productSearch.getProductID(), productSearch.getProductName(), 1, productSearch.getPrice(), productSearch.getPrice()});
                model.fireTableDataChanged();
                setTotalPrice();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnPaidOrderAddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addProductBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dashboard.components.combobox.Combobox StatusChoose;
    private swing.MyButton addProductBtn;
    private javax.swing.JLabel alertFixedPrice;
    private javax.swing.JLabel alertQuantity;
    private swing.MyButton closeBtn;
    private dashboard.components.combobox.Combobox customerChooseBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private swing.MyButton okBtn;
    private swing.SuggestionBox productSuggestionBox;
    private dashboard.components.combobox.Combobox shippingChooseBox;
    private dashboard.components.tabledrawer.TableUnpaidOrderDetails tableUnpaidOrderDetails1;
    private javax.swing.JLabel totalPrice;
    private textfield.swing.TextField userCreatedBox;
    // End of variables declaration//GEN-END:variables
}
