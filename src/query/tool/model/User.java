package query.tool.model;

import java.sql.Date;

public class User {
    private int userID;
    private String name;
    private String address;
    private String contactNo;
    private String role;
    private Date dateAdded;
    private int dpmID;
    private boolean orderManagePermission;
    private String username;
    private String password;
    private String recoveryKey;

    public User(int userID, String name, String address, String contactNo, String role, Date dateAdded, int dpmID,
            boolean orderManagePermission, String username, String password, String recoveryKey) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.role = role;
        this.dateAdded = dateAdded;
        this.dpmID = dpmID;
        this.orderManagePermission = orderManagePermission;
        this.username = username;
        this.password = password;
        this.recoveryKey = recoveryKey;
    }
    public User(String name, String address, String contactNo, String role, int dpmID,
            boolean orderManagePermission, String username, String password, String recoveryKey) {
        
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.role = role;
        
        this.dpmID = dpmID;
        this.orderManagePermission = orderManagePermission;
        this.username = username;
        this.password = password;
        this.recoveryKey = recoveryKey;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getDpmID() {
        return this.dpmID;
    }

    public void setDpmID(int dpmID) {
        this.dpmID = dpmID;
    }

    public boolean isOrderManagePermission() {
        return this.orderManagePermission;
    }

    public boolean getOrderManagePermission() {
        return this.orderManagePermission;
    }

    public void setOrderManagePermission(boolean orderManagePermission) {
        this.orderManagePermission = orderManagePermission;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecoveryKey() {
        return this.recoveryKey;
    }

    public void setRecoveryKey(String recoveryKey) {
        this.recoveryKey = recoveryKey;
    }

    @Override
    public String toString() {
        return "{" +
                " userID='" + getUserID() + "'" +
                ", name='" + getName() + "'" +
                ", address='" + getAddress() + "'" +
                ", contactNo='" + getContactNo() + "'" +
                ", role='" + getRole() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                ", dpmID='" + getDpmID() + "'" +
                ", orderManagePermission='" + isOrderManagePermission() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", recoveryKey='" + getRecoveryKey() + "'" +
                "}";
    }

}
