package query.tool.model;

public class Discount {
    private int discountID;
    private int discount;
    private String customerType;

    public Discount(int discountID, int discount, String customerType) {
        this.discountID = discountID;
        this.discount = discount;
        this.customerType = customerType;
    }

    public int getDiscountID() {
        return this.discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "{" +
                " discountID='" + getDiscountID() + "'" +
                ", discount='" + getDiscount() + "'" +
                ", customerType='" + getCustomerType() + "'" +
                "}";
    }

}
