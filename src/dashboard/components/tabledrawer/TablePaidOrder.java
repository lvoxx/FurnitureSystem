/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dashboard.components.tabledrawer;

import dashboard.components.table.controllers.IData;
import dashboard.components.table.controllers.IPaid;
import dashboard.components.table.frame_edit.UnPaidOrderEditFrame;
import dashboard.components.table.frame_view.UnPaidOrderViewFrame;
import dashboard.components.table.swing.TableActionCellEditor;
import dashboard.components.table.swing.TableActionCellRender;
import dashboard.components.table.swing.TableActionEvent;
import dialog.message.MessageDialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import notifi.swing.Notification;
import query.connect.Settings;
import query.tool.query.OrderQuery;

/**
 *
 * @author Admin
 */
public class TablePaidOrder extends javax.swing.JPanel {

    private IData data;
    private IPaid paidI;
    private final int ACTION_COLL = 6;
    private JFrame frame;

    public TablePaidOrder() {
        initComponents();
    }

    public void setData(IData data) {
        this.data = data;
        data.addAllRow();
    }

    public JTable getTable() {
        return table;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setIPaid(IPaid paidI) {
        this.paidI = paidI;
    }

    // EDIT HERE
    // ------------------------------------------------------------------------------------ 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        table.setForeground(new java.awt.Color(51, 51, 51));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Name", "Shipping Name", "Status", "Date Order", "User Created", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setRowHeight(40);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
