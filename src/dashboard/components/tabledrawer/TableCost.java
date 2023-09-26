/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dashboard.components.tabledrawer;

import dashboard.components.table.controllers.IData;
import dashboard.components.table.frame_edit.CostEditFrame;
import dashboard.components.table.frame_view.CostViewFrame;
import dashboard.components.table.swing.TableActionCellEditor;
import dashboard.components.table.swing.TableActionCellRender;
import dashboard.components.table.swing.TableActionEvent;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TableCost extends javax.swing.JPanel {

    private IData data;
    private final int ACTION_COLL = 5;

    public TableCost() {
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

    // ------------------------------------------------------------------------------------
    // EDIT HERE
    private void addRowEvent() {
        //COLLUMN EDITTOR START
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                 //Get CostID by row index
                String rowData = table.getModel().getValueAt(row, 0).toString();
                
                //Init new JFrame to fill data
                new CostEditFrame(Integer.valueOf(rowData)).show();
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
                
                //Delete row on SQL server
                
                
                //Refresh table in client
                data.refreshData();
            }

            @Override
            public void onView(int row) {
                //Get CustomerID by row index
                
                Object[] rowData = getRowAt(row, (DefaultTableModel) table.getModel());
                
                
                //Init new JFrame to fill data
                new CostViewFrame(rowData).show();
            }
        };
        table.getColumnModel().getColumn(ACTION_COLL).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(ACTION_COLL).setCellEditor(new TableActionCellEditor(event));
        //COLLUMN EDITTOR END

        //COLLUM EFFECT START
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
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
                "ID", "Creator Name", "Cost Category", "Expense", "Date Added", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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
