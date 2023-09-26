package query.tool.model;

import java.sql.Date;

public class MaterialSupplier {
    private int supplierID;
    private String supplierName;
    private String address;
    private String contactNo;
    private Date dateAdded;

    public MaterialSupplier(int supplierID, String supplierName, String address, String contactNo, Date dateAdded) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.address = address;
        this.contactNo = contactNo;
        this.dateAdded = dateAdded;
    }

    public int getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

}
