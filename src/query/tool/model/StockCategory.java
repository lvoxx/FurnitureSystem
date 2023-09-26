package query.tool.model;

public class StockCategory {
    private int stockCtgID;
    private String stockCtgName;

    public StockCategory(int stockCtgID, String stockCtgName) {
        this.stockCtgID = stockCtgID;
        this.stockCtgName = stockCtgName;
    }

    public int getStockCtgID() {
        return this.stockCtgID;
    }

    public void setStockCtgID(int stockCtgID) {
        this.stockCtgID = stockCtgID;
    }

    public String getStockCtgName() {
        return this.stockCtgName;
    }

    public void setStockCtgName(String stockCtgName) {
        this.stockCtgName = stockCtgName;
    }

}
