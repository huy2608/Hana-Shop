/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.bill;

import huylng.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class BillDAO implements Serializable{

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insert(String billId, String username, Date createDate, String description, float total) throws SQLException, NamingException {
        try {
            String sql = "Insert Into Bill(billID, username, createDate, description, total) "
                    + "Values (?,?,?,?,?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, billId);
                ps.setString(2, username);
                ps.setTimestamp(3, new Timestamp(createDate.getTime()));
                ps.setString(4, description);
                ps.setFloat(5, total);
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<BillDTO> billList;

    public List<BillDTO> getBillList() {
        return this.billList;
    }
    
    public List<BillDTO> loadBillHistoryOfUser(String userID) throws SQLException, NamingException{
        try {
            String sql = "Select billID, username, createDate, description, total "
                    + "From Bill "
                    + "Where username = ?";
            con = MyConnection.getMyConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while(rs.next()){
                    String billID = rs.getString("billID");
                    userID = rs.getString("username");
                    Date createDate = rs.getDate("createDate");
                    String description = rs.getString("description");
                    float total = rs.getFloat("total");
                    BillDTO dto = new BillDTO(billID, userID, createDate, description, total);
                    if(this.billList == null){
                        this.billList = new ArrayList<>();
                    }
                    this.billList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return this.billList;
    }
    
    
    public List<BillDTO> loadBillHistoryByDate (Date date) throws SQLException, NamingException{
        try {
            String sql = "Select billID, username, createDate, description, total "
                    + "From Bill "
                    + "Where createDate = ?";
            con = MyConnection.getMyConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(1,  sqlDate);
                rs = ps.executeQuery();
                while(rs.next()){
                    String billID = rs.getString("billID");
                    String userID = rs.getString("username");
                    Date createDate = rs.getDate("createDate");
                    String description = rs.getString("description");
                    float total = rs.getFloat("total");
                    BillDTO dto = new BillDTO(billID, userID, createDate, description, total);
                    if(this.billList == null){
                        this.billList = new ArrayList<>();
                    }
                    this.billList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return this.billList;
    }
}
