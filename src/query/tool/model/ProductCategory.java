package query.tool.model;

public class ProductCategory {
    private int productCtgID;
    private String categoryName;

    public ProductCategory(int productCtgID, String categoryName) {
        this.productCtgID = productCtgID;
        this.categoryName = categoryName;
    }

    public int getProductCtgID() {
        return this.productCtgID;
    }

    public void setProductCtgID(int productCtgID) {
        this.productCtgID = productCtgID;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "{" +
                " productCtgID='" + getProductCtgID() + "'" +
                ", categoryName='" + getCategoryName() + "'" +
                "}";
    }

}
