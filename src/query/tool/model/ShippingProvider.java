package query.tool.model;

import java.sql.Date;

public class ShippingProvider {
    private int shippingID;
    private String shippingName;
    private int priceID;
    private String address;
    private String contactNo;
    private Date dateAdded;

    public ShippingProvider(int shippingID, String shippingName, int priceID, String address, String contactNo,
            Date dateAdded) {
        this.shippingID = shippingID;
        this.shippingName = shippingName;
        this.priceID = priceID;
        this.address = address;
        this.contactNo = contactNo;
        this.dateAdded = dateAdded;
    }

    public int getShippingID() {
        return this.shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public int getPriceID() {
        return this.priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "{" +
                " shippingID='" + getShippingID() + "'" +
                ", shippingName='" + getShippingName() + "'" +
                ", priceID='" + getPriceID() + "'" +
                ", address='" + getAddress() + "'" +
                ", contactNo='" + getContactNo() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                "}";
    }

}
