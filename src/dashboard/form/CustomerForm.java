/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.form;

import dashboard.components.model.MCustomer;
import dashboard.components.table.controllers.IData;
import dashboard.components.textfield.EventCallBack;
import dashboard.components.textfield.EventTextField;
import dashboard.manage.customer.AddCustomer;
import dashboard.manage.customer.DeleteCustomer;
import dashboard.manage.customer.UpdateCustomer;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import main.Main;
import query.connect.Settings;
import query.tool.model.Customer;
import query.tool.model.Discount;
import query.tool.query.*;

public class CustomerForm extends javax.swing.JPanel {

    private boolean isPopup;
    private Date fromDate;
    private Date toDate;

    private JFrame frame;
    private Connection conn;
    private CustomerQuery queryC;
    private DiscountQuery queryD;
    private List<MCustomer> customers;
    private List<Discount> discounts;
    private IData data;

    public CustomerForm(JFrame frame) {
        this.frame = frame;
        initComponents();
        try {
            getCustomerDateFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchField.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    //  Test
                    try {
                        for (int i = 1; i <= 70; i++) {
                            Thread.sleep(10);
                        }
                        call.done();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    
                    //Do search
                    reloadDataFromSearch();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null,ex);
                }
            }
            @Override
            public void onKeyEnterPressed(EventCallBack call) {
                try {
                    //  Test
                    try {
                        for (int i = 1; i <= 70; i++) {
                            Thread.sleep(10);
                        }
                        call.done();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    
                    //Do search
                    reloadDataFromSearch();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null,ex);
                }
            }

            @Override
            public void onCancel() {

            }
        });
        tableManipulation();

    }

    private void tableManipulation() {
        data = new IData() {
            @Override
            public void addAllRow() {
                loadData();
            }

            @Override
            public void refreshData() {
                try {
                    reloadData();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        table.setData(data);

    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        customers.stream().forEach((item) -> {
            Customer cus = item.getCustomer();
            Discount dis = item.getDiscount();
            model.addRow(new Object[]{cus.getCustomerID(), cus.getName(), cus.getAddress(), cus.getContactNo(), cus.getDateAdded(), dis.getDiscount(), dis.getCustomerType()});
        });
        model.fireTableDataChanged();

    }

    private void reloadData() throws SQLException {
        //Remove All
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        searchField.setText(null);
        //model.fireTableDataChanged();
        getCustomerDateFromDB();
        loadData();
        
    }

    private void getCustomerDateFromDB() throws SQLException {
        //Load All Data from server
        try {
            this.conn = Settings.BuildConnect();
            if (this.conn == null) {
                Settings.TryGetConnection(frame, conn);
            } else {
                this.queryC = new CustomerQuery(conn);
                this.queryD = new DiscountQuery(conn);
            }
        } catch (SQLException ex) {
            //toLoginForm();
        }
        this.customers = queryC.selectMCustomer();
        this.discounts = queryD.selectDiscountList();
    }
    
    private void reloadDataFromSearch() throws SQLException {
        //Remove All
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        //model.fireTableDataChanged();
        getCustomerDateBySearching(searchField.getText());
        loadData();
    }
    
    private void getCustomerDateBySearching(String searchName) throws SQLException {
        //Load All Data from server
        try {
            this.conn = Settings.BuildConnect();
            if (this.conn == null) {
                Settings.TryGetConnection(frame, conn);
            } else {
                this.queryC = new CustomerQuery(conn);
                this.queryD = new DiscountQuery(conn);
            }
        } catch (SQLException ex) {
            //toLoginForm();
        }
        this.customers = queryC.selectMCustomerSearch(searchName);
        this.discounts = queryD.selectDiscountList();
    }

    private void toLoginForm() {
        //System.out.print(1);
        new Main().setVisible(true);
        frame.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new dashboard.components.textfield.TextFieldAnimation();
        clearButton1 = new dashboard.components.table.swing.ClearButton();
        table = new dashboard.components.tabledrawer.TableCustomer();
        addCustomerBtn = new dialog.custom.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        clearButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard/icon/reload.png"))); // NOI18N
        clearButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButton1MouseClicked(evt);
            }
        });

        addCustomerBtn.setText("Add New Customer");
        addCustomerBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        addCustomerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCustomerBtnMouseClicked(evt);
            }
        });
        addCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerBtnActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Update Customer");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 153, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Delete Customer");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_addCustomerBtnActionPerformed

    private void clearButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButton1MouseClicked
        try {
            reloadData();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clearButton1MouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void addCustomerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCustomerBtnMouseClicked
        // TODO add your handling code here:
        
        AddCustomer addCus = new AddCustomer();
        addCus.setVisible(true);
    }//GEN-LAST:event_addCustomerBtnMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        UpdateCustomer updateCus = new UpdateCustomer();
        updateCus.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        
        DeleteCustomer deleteCus = new DeleteCustomer();
        deleteCus.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dialog.custom.ButtonCustom addCustomerBtn;
    private dashboard.components.table.swing.ClearButton clearButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private dashboard.components.textfield.TextFieldAnimation searchField;
    private dashboard.components.tabledrawer.TableCustomer table;
    // End of variables declaration//GEN-END:variables
}
