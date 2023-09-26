package query.tool.model;

public class OrderDetails {
    private int orderID;
    private int productID;
    private int quantity;
    private int fixedPrice;

    public OrderDetails(int orderID, int productID, int quantity, int fixedPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.fixedPrice = fixedPrice;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFixedPrice() {
        return this.fixedPrice;
    }

    public void setFixedPrice(int fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    @Override
    public String toString() {
        return "{" +
                " orderID='" + getOrderID() + "'" +
                ", productID='" + getProductID() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", fixedPrice='" + getFixedPrice() + "'" +
                "}";
    }
}
