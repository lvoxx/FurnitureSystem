/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dashboard.components.tabledrawer;

import dashboard.components.table.controllers.IData;
import dashboard.components.table.frame_edit.ProductEditFrame;
import dashboard.components.table.frame_view.ProductViewFrame;
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
import query.connect.Settings;
import query.tool.query.ProductQuery;

/**
 *
 * @author Admin
 */
public class TableProduct extends javax.swing.JPanel {

    private IData data;
    private final int ACTION_COLL = 6;
    private JFrame frame;

    public TableProduct() {
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

    // ------------------------------------------------------------------------------------
    // EDIT HERE
    private void addRowEvent() {
        //COLLUMN EDITTOR START
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());

                //Init new JFrame to fill data
                ProductEditFrame productFrame = new ProductEditFrame(rowData);
                productFrame.addWindowListener(new WindowListener() {
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
                productFrame.show();

            }

            @Override
            public void onDelete(int row) {
                try {
//                    if (table.isEditing()) {
//                        table.getCellEditor().stopCellEditing();
//                    }
//                    DefaultTableModel model = (DefaultTableModel) table.getModel();
//                    model.removeRow(row);
                    Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());
                    MessageDialog dialog = new MessageDialog(frame);
                    dialog.showMessage("Delete product id: " + rowData[0].toString(), "Deleted data cannot be recovered");
                    if (dialog.getMessageType().equals(MessageDialog.MessageType.OK)) {
                        //Delete row on SQL server

                        ProductQuery query = new ProductQuery(Settings.BuildConnect());
                        query.deleteProduct(Integer.valueOf(rowData[0].toString()));
                        //Refresh table in client
                        data.refreshData();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TableProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onView(int row) {
                //Get CustomerID by row index

                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());

                //Init new JFrame to fill data
                new ProductViewFrame(rowData).show();
            }
        };
        table.getColumnModel().getColumn(ACTION_COLL).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(ACTION_COLL).setCellEditor(new TableActionCellEditor(event));
        //COLLUMN EDITTOR END

        //COLLUM EFFECT START
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for(int i = 0; i < 6; ++i){
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
                "ID", "Product Name", "In Stock Quantity", "Price", "Product Category", "Date Added", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
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
