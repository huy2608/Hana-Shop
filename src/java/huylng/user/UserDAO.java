/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.user;

import huylng.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class UserDAO implements Serializable{

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserDTO checkLogin(String username, String password)
            throws SQLException, NamingException{
        UserDTO dto = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select username, password, role, name, address, phoneNumber "
                        + "From [User] "
                        + "Where username = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                while (rs.next()) {
                    username = rs.getString("username");
                    password = rs.getString("password");
                    Boolean role = rs.getBoolean("role");
                    String name =  rs.getString("name");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phoneNumber");
                    dto = new UserDTO(username, password, role, name, address, phoneNumber);
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
        return dto;
    }
    public boolean updateLogin(String name, String address, String phoneNumber, String username)
            throws SQLException, NamingException{
        
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Update [User] "
                        + "Set name = ?, address = ?, phoneNumber = ? "
                        + "Where username = ?";
                        
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, phoneNumber);
                ps.setString(4, username);
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
}
