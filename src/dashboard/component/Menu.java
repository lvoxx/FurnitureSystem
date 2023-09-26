package dashboard.component;

import dashboard.event.EventMenuSelected;
import dashboard.model.Model_Menu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Menu extends javax.swing.JPanel {

    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }

    private void init() {
        //GENERAL
        listMenu1.addItem(new Model_Menu("1", "Dashboard", Model_Menu.MenuType.MENU));          //Index 0   (Usability)
        listMenu1.addItem(new Model_Menu("2", "Order", Model_Menu.MenuType.MENU));              //Index 1   (Usability)
        listMenu1.addItem(new Model_Menu("3", "Cost Invoice", Model_Menu.MenuType.MENU));       //Index 2   (Usability)
        listMenu1.addItem(new Model_Menu("4", "Warehouse", Model_Menu.MenuType.MENU));          //Index 3   (Usability)
        listMenu1.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY));                  //Index 4

        //MANAGE
        listMenu1.addItem(new Model_Menu("", "Data Manage", Model_Menu.MenuType.TITLE));        //Index 5
        listMenu1.addItem(new Model_Menu("5", "Customer", Model_Menu.MenuType.MENU));           //Index 6   (Usability)
        listMenu1.addItem(new Model_Menu("6", "Cost", Model_Menu.MenuType.MENU));               //Index 7   (Usability)
        listMenu1.addItem(new Model_Menu("7", "Staff", Model_Menu.MenuType.MENU));              //Index 8   (Usability)
        listMenu1.addItem(new Model_Menu("8", "Product", Model_Menu.MenuType.MENU));            //Index 9   (Usability)
        listMenu1.addItem(new Model_Menu("9", "Stock", Model_Menu.MenuType.MENU));              //Index 10  (Usability)
        listMenu1.addItem(new Model_Menu("10", "Shipping Provider", Model_Menu.MenuType.MENU)); //Index 11  (Usability)
        listMenu1.addItem(new Model_Menu("11", "Material Supplier", Model_Menu.MenuType.MENU)); //Index 12  (Usability)

        //LOG OUT
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));                   //Index 13
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));                   //Index 14
        listMenu1.addItem(new Model_Menu("12", "Logout", Model_Menu.MenuType.MENU));            //Index 15  (Usability)
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new dashboard.swing.ListMenu<>();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard/icon/logo.png"))); // NOI18N
        jLabel1.setText("Application");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    public void setTitleTxt(String userName) {
        jLabel1.setText(" " + userName);
    }

    public void setTitleIcon(String icon) {
        jLabel1.setIcon(new ImageIcon(getClass().getResource(icon)));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private dashboard.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
