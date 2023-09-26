package query.tool.model;

public class CostCategory {
    private int costCtgID;
    private String costCtgName;

    public CostCategory(int costCtgID, String costCtgName) {
        this.costCtgID = costCtgID;
        this.costCtgName = costCtgName;
    }

    public int getCostCtgID() {
        return this.costCtgID;
    }

    public void setCostCtgID(int costCtgID) {
        this.costCtgID = costCtgID;
    }

    public String getCostCtgName() {
        return this.costCtgName;
    }

    public void setCostCtgName(String costCtgName) {
        this.costCtgName = costCtgName;
    }

    @Override
    public String toString() {
        return "{" +
                " costCtgID='" + getCostCtgID() + "'" +
                ", costCtgName='" + getCostCtgName() + "'" +
                "}";
    }

}
