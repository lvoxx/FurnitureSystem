package query.tool.query;

import dashboard.components.model.MCustomer;
import query.tool.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import query.tool.model.Discount;


public class CustomerQuery {
    private final String SELECT = "SELECT * FROM tblCustomer WHERE CustomerID = ?";
    private final String INSERT = "EXEC proc_InsertCustomer @DiscountID = ?, "
            + "@Name = ?, "
            + "@ContactNo = ?, "
            + "@Address = ?";
    private final String UPDATE = "EXEC proc_UpdateCustomer @CustomerID = ?, @DiscountID = ?, @Name = ?, @ContactNo = ?, @Address = ?";
    private final String DELETE = "EXEC proc_DeleteCustomer @CustomerID = ?";
    private Connection conn;

    public CustomerQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Customer> selectCustomer(List<Integer> customerIDs) throws SQLException {
        try {
            List<Customer> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < customerIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, customerIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Customer(rs.getInt("CustomerID"),
                            rs.getInt("DiscountID"),
                            rs.getString("Name"),
                            rs.getString("ContactNo"),
                            rs.getString("Address"),
                            rs.getDate("DateAdded")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Customer ID: " + customerIDs.toString());
        }
    }
    public List<MCustomer> selectMCustomer() throws SQLException {
        try {
            List<MCustomer> res = new ArrayList<>();
            PreparedStatement preSt = preSt = this.conn
                        .prepareStatement("SELECT [CustomerID], C.[DiscountID], [Name], [ContactNo], [Address], [DateAdded], [Discount], [CustomerType] FROM tblCustomer AS C INNER JOIN tblDiscount AS D ON c.DiscountID = d.DiscountID");
            ResultSet rs = preSt.executeQuery();
            
            while(rs.next()){
                int customerID = rs.getInt("CustomerID");
                int discountID = rs.getInt("DiscountID");
                String name = rs.getString("Name");
                String contactNo = rs.getString("ContactNo");
                String address = rs.getString("Address");
                Date dateAdded = rs.getDate("DateAdded");
                int discount = rs.getInt("Discount");
                String customerType = rs.getString("CustomerType");
                
                Customer tempC = new Customer(customerID, discountID, name, contactNo, address, dateAdded);
                Discount tempD = new Discount(discountID, discount, customerType);
                
                res.add(new MCustomer(tempC, tempD));
            }
            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Customer List");
        }
    }
    public List<MCustomer> selectMCustomerSearch(String nameSearch) throws SQLException {
        try {
            List<MCustomer> res = new ArrayList<>();
            PreparedStatement preSt = preSt = this.conn
                        .prepareStatement("SELECT [CustomerID], C.[DiscountID], [Name], [ContactNo], [Address], [DateAdded], [Discount], [CustomerType] FROM tblCustomer AS C INNER JOIN tblDiscount AS D ON c.DiscountID = d.DiscountID WHERE C.Name LIKE '%?%'");
            preSt.setString(1, nameSearch);
            ResultSet rs = preSt.executeQuery();
            
            while(rs.next()){
                int customerID = rs.getInt("CustomerID");
                int discountID = rs.getInt("DiscountID");
                String name = rs.getString("Name");
                String contactNo = rs.getString("ContactNo");
                String address = rs.getString("Address");
                Date dateAdded = rs.getDate("DateAdded");
                int discount = rs.getInt("Discount");
                String customerType = rs.getString("CustomerType");
                
                Customer tempC = new Customer(customerID, discountID, name, contactNo, address, dateAdded);
                Discount tempD = new Discount(discountID, discount, customerType);
                
                res.add(new MCustomer(tempC, tempD));
            }
            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Customer List");
        }
    }

    public int insertCustomer(Customer customer) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, customer.getDiscountID());// DiscountID
            preSt.setString(2, customer.getName());// Name
            preSt.setString(3, customer.getContactNo());// ContactNo
            preSt.setString(4, customer.getAddress());// Address

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the customer " + customer.toString());
        }
        return -1;
    }

    public int updateCustomer(Customer customer) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, customer.getCustomerID());// CustomerID
            preSt.setInt(2, customer.getDiscountID());// DiscountID
            preSt.setString(3, customer.getName());// Name
            preSt.setString(4, customer.getContactNo());// ContactNo
            preSt.setString(5, customer.getAddress());// Address
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the customer: " + customer.toString());
        }
        return -1;
    }

    public int deleteCustomer(Customer customer) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, customer.getCustomerID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the customer with id: " + customer.getCustomerID());
        }
        return -1;
    }
}
