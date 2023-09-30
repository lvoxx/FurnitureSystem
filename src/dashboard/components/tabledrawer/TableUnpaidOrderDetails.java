/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dashboard.components.tabledrawer;

import dashboard.components.table.controllers.IAlert;
import dashboard.components.table.controllers.IData;
import dashboard.components.table.swing.TableActionCellEditor_Delete;
import dashboard.components.table.swing.TableActionCellRender_Delete;
import dashboard.components.table.swing.TableActionEvent;
import dialog.message.MessageDialog;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import query.connect.Settings;
import query.tool.query.OrderQuery;

/**
 *
 * @author Admin
 */
public class TableUnpaidOrderDetails extends javax.swing.JPanel {

    private IData data;
    private IAlert alert;
    private final int ACTION_COLL = 5;
    private JFrame frame;

    public TableUnpaidOrderDetails() {
        initComponents();
        addRowEvent();
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

    public void setAlert(IAlert alert) {
        this.alert = alert;
    }

    // ------------------------------------------------------------------------------------
    // EDIT HERE
    private void addRowEvent() {
        //COLLUMN EDITTOR START
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            }

            @Override
            public void onDelete(int row) {
                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());
                MessageDialog dialog = new MessageDialog(frame);
                dialog.showMessage("Delete product: " + rowData[1].toString(), "Deleted this product in the order cannot be recovered");
                if (dialog.getMessageType().equals(MessageDialog.MessageType.OK)) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                }
                alert.setNewTotal(String.valueOf(getNewPrice(row, 0)) + " $");

            }

            @Override
            public void onView(int row) {
            }
        };
        table.getColumnModel().getColumn(ACTION_COLL).setCellRenderer(new TableActionCellRender_Delete());
        table.getColumnModel().getColumn(ACTION_COLL).setCellEditor(new TableActionCellEditor_Delete(event));
        //COLLUMN EDITTOR END

        //COLLUM EFFECT START
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        //COLLUMN EFFECT END

        //SET QUANTITY LISTENER
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int collumn = tcl.getColumn();
                int row = tcl.getRow();
                //[1-9]\\d*
                String newValue = String.valueOf(tcl.getNewValue());
                System.out.print(newValue);
                //Quantity
                if (collumn == 2) {
                    if (newValue.matches("[1-9]\\d*")) {
                        int newQuantity = Integer.parseInt(newValue);
                        int newSubtotal = newQuantity * Integer.parseInt(table.getModel().getValueAt(row, collumn + 1).toString());
                        table.getModel().setValueAt(newSubtotal, row, 4);
                        alert.alertQuantityOn(false);
                        alert.setNewTotal(String.valueOf(getNewPrice(row, newSubtotal)) + " $");
                    } else {
                        alert.alertQuantityOn(true);
                    }
                } else {
                    //FixedPrice
                    if (newValue.matches("0|[1-9]\\d*")) {
                        int newQuantity = Integer.parseInt(newValue);
                        int newSubtotal = newQuantity * Integer.parseInt(table.getModel().getValueAt(row, collumn - 1).toString());
                        table.getModel().setValueAt(newSubtotal, row, 4);
                        alert.alertFixedPriceOn(false);
                        alert.setNewTotal(String.valueOf(getNewPrice(row, newSubtotal)) + " $");
                    } else {
                        alert.alertFixedPriceOn(true);
                    }
                }

            }
        };

        TableCellListener tclFixedPrice = new TableCellListener(table, action);
    }

    private int getNewPrice(int newRow, int newSubtotal) {
        int rows = table.getModel().getRowCount();
        int total = 0;
        for (int i = 0; i < rows; ++i) {
            if (i == newRow) {
                total += newSubtotal;
            }
            total += Integer.valueOf(table.getModel().getValueAt(i, 2).toString()) * Integer.valueOf(table.getModel().getValueAt(i, 3).toString());
        }

        return total;
    }

    private Object[] getRowAt(int row, DefaultTableModel model) {
        Object[] result = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            result[i] = model.getValueAt(row, i);
        }

        return result;
    }

    // EDIT HERE
    // ------------------------------------------------------------------------------------ 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(500, 350));

        table.setForeground(new java.awt.Color(51, 51, 51));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Name", "Quantity", "Fixed Price", "Subtotal", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
