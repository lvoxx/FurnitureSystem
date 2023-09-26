package query.tool.model;

public class Department {
    private int DpmID;
    private String departmentName;

    public Department(int DpmID, String departmentName) {
        this.DpmID = DpmID;
        this.departmentName = departmentName;
    }

    public int getDpmID() {
        return this.DpmID;
    }

    public void setDpmID(int DpmID) {
        this.DpmID = DpmID;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "{" +
                " DpmID='" + getDpmID() + "'" +
                ", departmentName='" + getDepartmentName() + "'" +
                "}";
    }

}
