package query.tool.model;

public class ShippingProviderCategory {
    private int priceID;
    private int price;

    public ShippingProviderCategory(int priceID, int price) {
        this.priceID = priceID;
        this.price = price;
    }

    public int getPriceID() {
        return this.priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                " priceID='" + getPriceID() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }

}
