/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.form;

import dashboard.components.model.*;
import dashboard.components.table.controllers.IData;
import dashboard.components.textfield.EventCallBack;
import dashboard.components.textfield.EventTextField;
import javax.swing.JFrame;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import main.Main;
import query.connect.Settings;
import query.tool.model.*;
import query.tool.query.*;

public class CostForm extends javax.swing.JPanel {

    private JFrame frame;
    private Connection conn;
    private CostQuery queryC;
    private List<MCost> costs;
    private IData data;

    public CostForm(JFrame frame) {
        this.frame = frame;
        initComponents();
        //Get Cust Data From Database
        try {
            getCostDataFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null,ex);
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
                    Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null,ex);
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
                    Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        
        //Settings
        table.setData(data);
        table.setFrame(frame);
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        costs.stream().forEach((item) -> {
            //Load Data by Query
            String userIDCreated = item.getUserNameCreated();
            Cost cost = item.getCost();
            CostCategory costCategory = item.getCostCategory();
            
            model.addRow(new Object[]{cost.getCostID(), userIDCreated, costCategory.getCostCtgName(), cost.getExpense(), cost.getDateCreated()});
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
        getCostDataFromDB();
        loadData();
        
    }

    private void getCostDataFromDB() throws SQLException {
        //Load All Data from server
        try {
            this.conn = Settings.BuildConnect();
            if (this.conn == null) {
                Settings.TryGetConnection(frame, conn);
            } else {
                this.queryC = new CostQuery(conn);
            }
        } catch (SQLException ex) {
            //toLoginForm();
        }
        this.costs = queryC.selectMCostList();
    }
    
    private void reloadDataFromSearch() throws SQLException {
        //Remove All
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        //model.fireTableDataChanged();
        getCostDataBySearching(searchField.getText());
        loadData();
    }
    
    private void getCostDataBySearching(String searchName) throws SQLException {
        //Load All Data from server
        try {
            this.conn = Settings.BuildConnect();
            if (this.conn == null) {
                Settings.TryGetConnection(frame, conn);
            } else {
                this.queryC = new CostQuery(conn);
            }
        } catch (SQLException ex) {
            //toLoginForm();
        }
        this.costs = queryC.selectMCostListSearch(searchName);
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
        table = new dashboard.components.tabledrawer.TableCost();
        addCustomerBtn = new dialog.custom.ButtonCustom();
        addCustomerBtn1 = new dialog.custom.ButtonCustom();

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

        addCustomerBtn.setBackground(new java.awt.Color(51, 51, 255));
        addCustomerBtn.setText("Add New Cost Category");
        addCustomerBtn.setActionCommand("Add New Cost Category");
        addCustomerBtn.setColorHover(new java.awt.Color(102, 102, 255));
        addCustomerBtn.setColorPressed(new java.awt.Color(0, 0, 204));
        addCustomerBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        addCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerBtnActionPerformed(evt);
            }
        });

        addCustomerBtn1.setText("Add New Customer");
        addCustomerBtn1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        addCustomerBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerBtn1ActionPerformed(evt);
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
                .addGap(16, 16, 16)
                .addComponent(addCustomerBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addCustomerBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            Logger.getLogger(CostForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clearButton1MouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void addCustomerBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addCustomerBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dialog.custom.ButtonCustom addCustomerBtn;
    private dialog.custom.ButtonCustom addCustomerBtn1;
    private dashboard.components.table.swing.ClearButton clearButton1;
    private dashboard.components.textfield.TextFieldAnimation searchField;
    private dashboard.components.tabledrawer.TableCost table;
    // End of variables declaration//GEN-END:variables
}
