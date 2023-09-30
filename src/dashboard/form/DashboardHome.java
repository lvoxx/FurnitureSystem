package dashboard.form;

import dashboard.components.model.MOrder;
import dashboard.model.Model_Card;
import dashboard.model.StatusType;
import dashboard.swing.ScrollBar;
import java.sql.*;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import query.connect.Settings;
import query.tool.model.Order;
import query.tool.model.OrderDetails;
import query.tool.query.OrderDetailsQuery;
import query.tool.query.OrderQuery;

public class DashboardHome extends javax.swing.JPanel {

    private int revDay = 0, revMonth = 0;
    private Connection conn;
    private OrderQuery query;
    private List<MOrder> orders;

    public DashboardHome() {
        initComponents();
        try {
            this.conn = Settings.BuildConnect();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardHome.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        try {
            getPaidOrderDataFromDB();
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/dashboard/icon/monthly.png")), "This Month Revenue", revMonth  + " $", "Month: " + java.time.LocalDate.now().getMonthValue() + ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/dashboard/icon/profit.png")), "Today Revenue", revDay + " $", "Day: " + java.time.LocalDate.now().getDayOfMonth() + ""));
    }

    private void loadData() {
        int subTotal = 0;
        int currentDay = java.time.LocalDate.now().getDayOfMonth();
        int currentMonth = java.time.LocalDate.now().getMonthValue();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.setRowCount(0);
        for (MOrder item : orders) {

            List<OrderDetails> list = new ArrayList<>();

            try {
                list = new OrderDetailsQuery(conn).selectOrderDetails(item.getOrder().getOrderID());
                for (OrderDetails i : list) {
                    subTotal += i.getFixedPrice() * i.getQuantity();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("Day: " + new SimpleDateFormat("dd").format(item.getOrder().getDatePaid()));
            //System.out.println("Month: " + item.getOrder().getDatePaid().getMonth());
            //Daily Revenue
            if (Integer.parseInt(new SimpleDateFormat("dd").format(item.getOrder().getDatePaid())) == currentDay) {
                revDay += subTotal;
            }
            //Monthly Revenue
            if (Integer.parseInt(new SimpleDateFormat("MM").format(item.getOrder().getDatePaid())) == currentMonth) {
                revMonth += subTotal;
            }

            //Load Data by Query
            String userName = item.getUserName();
            String cusName = item.getCustomer();
            Date datePaid = item.getOrder().getDatePaid();
            Order order = item.getOrder();

            //USER CREATED - CUSTOMER NAME - DATE OF PAYMENT - TOTAL - STATUS
            table.addRow(new Object[]{userName, cusName, new SimpleDateFormat("yyyy-MM-dd").format(datePaid), subTotal + " $", StatusType.APPROVED});
            subTotal = 0;
        }
        model.fireTableDataChanged();

    }

    private void reloadData() throws SQLException {
        //Remove All
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        model.setRowCount(0);

        getPaidOrderDataFromDB();
        loadData();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/dashboard/icon/monthly.png")), "This Month Revenue", revMonth + "", "Month: " + java.time.LocalDate.now().getMonthValue() + ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/dashboard/icon/profit.png")), "Today Revenue", revDay + "", "Day: " + java.time.LocalDate.now().getDayOfMonth() + ""));
    }

    private void getPaidOrderDataFromDB() throws SQLException {
        //Load All Data from server
        try {
            this.conn = Settings.BuildConnect();
            this.query = new OrderQuery(conn);
        } catch (SQLException ex) {
            //toLoginForm();
        }
        this.orders = query.selectOrderPaid();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new dashboard.component.Card();
        card2 = new dashboard.component.Card();
        panelBorder1 = new dashboard.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new dashboard.swing.Table();
        clearButton1 = new dashboard.components.table.swing.ClearButton();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Recent payment order");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Created", "Customer Name", "Date of payment", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        clearButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard/icon/reload.png"))); // NOI18N
        clearButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButton1MouseClicked(evt);
            }
        });
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void clearButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButton1MouseClicked
        try {
            reloadData();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clearButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dashboard.component.Card card1;
    private dashboard.component.Card card2;
    private dashboard.components.table.swing.ClearButton clearButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private dashboard.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private dashboard.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
