package query.tool.model;

import java.sql.Date;
import java.util.List;

public class Order {
    private int orderID;
    private int customerID;
    private int shippingID;
    private String status;
    private Date dateOrder, datePaid;
    private List<OrderDetails> orderDetails;
    private int userIDCreated;

    public Order(int orderID, int customerID, int shippingID, String status, Date dateOrder, Date datePaid,
            List<OrderDetails> orderDetails, int userIDCreated) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.shippingID = shippingID;
        this.status = status;
        this.dateOrder = dateOrder;
        this.datePaid = datePaid;
        this.orderDetails = orderDetails;
        this.userIDCreated = userIDCreated;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getShippingID() {
        return this.shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOrder() {
        return this.dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDatePaid() {
        return this.datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public List<OrderDetails> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getUserIDCreated() {
        return this.userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    @Override
    public String toString() {
        return "{" +
                " orderID='" + getOrderID() + "'" +
                ", customerID='" + getCustomerID() + "'" +
                ", shippingID='" + getShippingID() + "'" +
                ", status='" + getStatus() + "'" +
                ", dateOrder='" + getDateOrder() + "'" +
                ", datePaid='" + getDatePaid() + "'" +
                ", orderDetails='" + getOrderDetails() + "'" +
                ", userIDCreated='" + getUserIDCreated() + "'" +
                "}";
    }

}
