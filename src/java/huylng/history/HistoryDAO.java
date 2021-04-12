/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.history;

import huylng.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class HistoryDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insert(String id, Date createDate, String description) throws SQLException, NamingException {
        try {
            String sql = "Insert Into History(id, createDate, description) "
                    + "Values (?,?,?)";
            con = MyConnection.getMyConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setTimestamp(2, new Timestamp(createDate.getTime()));
                ps.setString(3, description);
                int result = ps.executeUpdate();
                if(result > 0){
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
