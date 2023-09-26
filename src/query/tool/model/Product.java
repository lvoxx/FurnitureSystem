package query.tool.model;

import java.sql.Date;

public class Product {
    private int productID;
    private String productName;
    private int inStockQuantity;
    private int price;
    private int productCtgID;
    private Date dateAdded;

    public Product(int productID, String productName, int inStockQuantity, int price, int productCtgID,
            Date dateAdded) {
        this.productID = productID;
        this.productName = productName;
        this.inStockQuantity = inStockQuantity;
        this.price = price;
        this.productCtgID = productCtgID;
        this.dateAdded = dateAdded;
    }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getInStockQuantity() {
        return this.inStockQuantity;
    }

    public void setInStockQuantity(int inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductCtgID() {
        return this.productCtgID;
    }

    public void setProductCtgID(int productCtgID) {
        this.productCtgID = productCtgID;
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
                " productID='" + getProductID() + "'" +
                ", productName='" + getProductName() + "'" +
                ", inStockQuantity='" + getInStockQuantity() + "'" +
                ", price='" + getPrice() + "'" +
                ", productCtgID='" + getProductCtgID() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                "}";
    }
}
