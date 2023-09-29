package query.tool.model;

import java.sql.Date;
import java.util.List;

public class Order {
    private int orderID;
    private int customerID;
    private int shippingID;
    private String status;
    private Date dateOrder, datePaid;
    private int userIDCreated;

    public Order(int orderID, int customerID, int shippingID, String status, Date dateOrder, Date datePaid, int userIDCreated) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.shippingID = shippingID;
        this.status = status;
        this.dateOrder = dateOrder;
        this.datePaid = datePaid;
        this.userIDCreated = userIDCreated;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public int getUserIDCreated() {
        return userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    

}
