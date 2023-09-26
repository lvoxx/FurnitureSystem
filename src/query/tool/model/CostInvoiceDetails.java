package query.tool.model;

public class CostInvoiceDetails {
    private int costInvID;
    private int costID;
    private long quantity;

    public CostInvoiceDetails(int costInvID, int costID, long quantity) {
        this.costInvID = costInvID;
        this.costID = costID;
        this.quantity = quantity;
    }

    public int getCostInvID() {
        return this.costInvID;
    }

    public void setCostInvID(int costInvID) {
        this.costInvID = costInvID;
    }

    public int getCostID() {
        return this.costID;
    }

    public void setCostID(int costID) {
        this.costID = costID;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                " costInvID='" + getCostInvID() + "'" +
                ", costID='" + getCostID() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }
}
