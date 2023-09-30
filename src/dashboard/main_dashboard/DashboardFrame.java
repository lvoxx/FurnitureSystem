/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.main_dashboard;

import dashboard.components.table.controllers.ISettings;
import dashboard.event.EventMenuSelected;
import dashboard.form.*;
import dialog.message.MessageDialog;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import main.Main;
import notifi.swing.Notification;
import query.connect.Settings;
import query.tool.model.User;

/**
 *
 * @author RAVEN
 */
public class DashboardFrame extends javax.swing.JFrame {

    //For loading form by each menu index
    private DashboardHome home;

    private OrderForm orderForm;
    private CustomerForm customerForm;
    private CostForm costForm;
    private StaffForm staffForm;
    private ProductForm productForm;
    private StockForm stockForm;
    private ShippingProviderForm shippingProviderForm;
    private MaterialSupplierForm materialSupplierForm;

    private JFrame frame;
    private Connection conn;

    private User user;
    private ISettings iSettings;

    public DashboardFrame(User user) {
        initComponents();
        this.frame = this;
        this.user = user;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dashboard/icon/FurnitureStoreVector.png")));
        try {
            this.conn = Settings.BuildConnect();
            if (conn == null) {
                Settings.TryGetConnection(frame, conn);
            } else {
                putAlertOnTop("SUCCESS", "Log in success");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //For getter
        iSettings = new ISettings() {
            @Override
            public User getUser() {
                return user;
            }
        };
        
        setForm();

        //setBackground(new Color(0, 0, 0, 0));
        home = new DashboardHome();
        orderForm = new OrderForm(frame, iSettings);
        costForm = new CostForm(frame, iSettings);
        customerForm = new CustomerForm(frame);
        staffForm = new StaffForm();
        productForm = new ProductForm(frame);
        stockForm = new StockForm();
        shippingProviderForm = new ShippingProviderForm();
        materialSupplierForm = new MaterialSupplierForm();

        menu.initMoving(DashboardFrame.this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MessageDialog dialog = new MessageDialog(frame);
                dialog.showMessage("Exit Program ?", "");
                if (dialog.getMessageType() == (MessageDialog.MessageType.OK)) {
                    System.exit(0);
                }
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch (index) {
                    case 0: {
                        setForm(home);                  //Dashboard
                        break;
                    }
                    case 1: {
                        setForm(orderForm);                 //Order
                        break;
                    }
                    case 4: {
                        setForm(customerForm);                 //Customer
                        break;
                    }
                    case 5: {
                        setForm(costForm);                 //Cost
                        break;
                    }
                    case 6: {
                        setForm(staffForm);                 //Staff
                        break;
                    }
                    case 7: {
                        setForm(productForm);                 //Product
                        break;
                    }
                    case 8: {
                        setForm(stockForm);                 //Stock
                        break;
                    }
                    case 9: {
                        setForm(shippingProviderForm);                 //Shipping Provide
                        break;
                    }
                    case 10: {
                        setForm(materialSupplierForm);                 //Material Supplier
                        break;
                    }
                    //LOG OUT HERE
                    case 15: {
                        toLoginForm();                    //Logout
                        break;
                    }
                }
            }
        });
        //  set when system open start with home form
        setForm(new DashboardHome());
    }

    private void toLoginForm() {
        System.out.print(1);
        new Main().setVisible(true);
        this.dispose();
    }

    private void setForm() {
        if (this.user.getRole().toUpperCase().equals("ADMIN")) {
            frame.setTitle("Fair Deal - " + "Admin");
            menu.setTitleTxt(user.getName());
            menu.setTitleIcon("/dashboard/icon/admin.png");
        } else {
            frame.setTitle("Fair Deal - " + "Staff");
            menu.setTitleTxt(user.getName());
            menu.setTitleIcon("/dashboard/icon/staff.png");
        }
    }

    private void putAlertOnTop(String type, String message) {
        Notification.Type typeMess = null;
        switch (type) {
            case "INFO": {
                typeMess = Notification.Type.INFO;
                break;
            }
            case "SUCCESS": {
                typeMess = Notification.Type.SUCCESS;
                break;
            }
            case "WARNING": {
                typeMess = Notification.Type.WARNING;
                break;
            }
        };
        Notification notif = new Notification(frame, typeMess, Notification.Location.TOP_CENTER, message);
        notif.showNotification();
    }

    public User getUser() {
        return this.user;
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new dashboard.swing.PanelBorder();
        menu = new dashboard.component.Menu();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));
        panelBorder1.setBorder(null);
        panelBorder1.setForeground(new java.awt.Color(255, 255, 255));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private dashboard.component.Menu menu;
    private dashboard.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
