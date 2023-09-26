package query.tool.model;

import java.sql.Date;

public class Stock {
    private int stockID;
    private String stockName;
    private int quantity;
    private int stockCtgID;
    private int supplierID;
    private String status;
    private Date dateAdded;

    public Stock(int stockID, String stockName, int quantity, int stockCtgID, int supplierID, String status,
            Date dateAdded) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.quantity = quantity;
        this.stockCtgID = stockCtgID;
        this.supplierID = supplierID;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    public int getStockID() {
        return this.stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return this.stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStockCtgID() {
        return this.stockCtgID;
    }

    public void setStockCtgID(int stockCtgID) {
        this.stockCtgID = stockCtgID;
    }

    public int getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                " stockID='" + getStockID() + "'" +
                ", stockName='" + getStockName() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", stockCtgID='" + getStockCtgID() + "'" +
                ", supplierID='" + getSupplierID() + "'" +
                ", status='" + getStatus() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                "}";
    }

}
