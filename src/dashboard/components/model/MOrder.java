/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboard.components.model;

import query.tool.model.*;

/**
 *
 * @author Admin
 */
public class MOrder {
    private Order order;
    private String customer;
    private String shippingProvider;
    private String userName;

    public MOrder(Order order, String customer, String shippingProvider, String userName) {
        this.order = order;
        this.customer = customer;
        this.shippingProvider = shippingProvider;
        this.userName = userName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShippingProvider() {
        return shippingProvider;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
