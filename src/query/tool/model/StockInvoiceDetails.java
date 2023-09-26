package query.tool.model;

public class StockInvoiceDetails {
    private int stkInvID;
    private int stockInvID;
    private int quantity;

    public StockInvoiceDetails(int stkInvID, int stockInvID, int quantity) {
        this.stkInvID = stkInvID;
        this.stockInvID = stockInvID;
        this.quantity = quantity;
    }

    public int getStkInvID() {
        return this.stkInvID;
    }

    public void setStkInvID(int stkInvID) {
        this.stkInvID = stkInvID;
    }

    public int getStockInvID() {
        return this.stockInvID;
    }

    public void setStockInvID(int stockInvID) {
        this.stockInvID = stockInvID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
