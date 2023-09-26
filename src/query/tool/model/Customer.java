package query.tool.model;

import java.sql.Date;

public class Customer {
    private int customerID;
    private int discountID;
    private String name;
    private String contactNo;
    private String address;
    private Date dateAdded;

    public Customer(int customerID, int discountID, String name, String contactNo, String address, Date dateAdded) {
        this.customerID = customerID;
        this.discountID = discountID;
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "{" +
                " customerID='" + getCustomerID() + "'" +
                ", discountID='" + getDiscountID() + "'" +
                ", name='" + getName() + "'" +
                ", contactNo='" + getContactNo() + "'" +
                ", address='" + getAddress() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                "}";
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getDiscountID() {
        return this.discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
