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
public class TableUnpaidOrder extends javax.swing.JPanel {

    private IData data;
    private IPaid paidI;
    private final int ACTION_COLL = 6;
    private JFrame frame;

    public TableUnpaidOrder() {
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

    public void setIPaid(IPaid paidI) {
        this.paidI = paidI;
    }

    // ------------------------------------------------------------------------------------
    // EDIT HERE
    private void addRowEvent() {
        //COLLUMN EDITTOR START
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());

                //Init new JFrame to fill data
                UnPaidOrderEditFrame orderFrame = new UnPaidOrderEditFrame(rowData);
                orderFrame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }

                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        Notification notif = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Edit Order Success");
                        notif.showNotification();
                        data.refreshData();
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                });
                orderFrame.setIPaid(paidI);
                orderFrame.show();
            }

            @Override
            public void onDelete(int row) {
                try {
//                    if (table.isEditing()) {
//                        table.getCellEditor().stopCellEditing();
//                    }
                    System.out.println("Row is: " + row);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    Object[] rowData = getRowAt(row, (DefaultTableModel) model);
                    MessageDialog dialog = new MessageDialog(frame);
                    dialog.showMessage("Delete order" + rowData[0].toString(), "Deleted data cannot be recovered");
                    if (dialog.getMessageType() == MessageDialog.MessageType.OK) {
                        //Delete row on SQL server
                        OrderQuery query = new OrderQuery(Settings.BuildConnect());
                        query.hideOrder(Integer.valueOf(rowData[0].toString()));
                        System.out.println(rowData[0].toString());
                        //Refresh table in client
                        data.refreshData();
                    }
                } catch (SQLException ex) {
                    Notification notif = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Delete Order Failed");
                    notif.showNotification();
                }
                Notification notif = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Delete Order Success");
                notif.showNotification();
            }

            @Override
            public void onView(int row) {
                //Get CustomerID by row index

                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());

                //Init new JFrame to fill data
                new UnPaidOrderViewFrame(rowData).show();
            }
        };
        table.getColumnModel().getColumn(ACTION_COLL).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(ACTION_COLL).setCellEditor(new TableActionCellEditor(event));
        //COLLUMN EDITTOR END

        //COLLUM EFFECT START
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int i = 0; i < 6; ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);

        //COLLUMN EFFECT END
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

        table.setForeground(new java.awt.Color(51, 51, 51));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Name", "Shipping Name", "Status", "Date Order", "User Created", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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
