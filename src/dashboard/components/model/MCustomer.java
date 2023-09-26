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
public class MCustomer {
    private Customer customer;
    private Discount discount;

    public MCustomer(Customer customer, Discount discount) {
        this.customer = customer;
        this.discount = discount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
    
    
}
