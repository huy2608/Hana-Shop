/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.foods;

import huylng.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class FoodDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private List<FoodDTO> foods;

    public List<FoodDTO> getFoods() {
        return this.foods;
    }

//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY HH:mm:ss.SSSZ");
    public List<FoodDTO> searchFoods(String name, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where name Like ? And status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(String category, int page_No, int i)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (category Like ?) And status = 1"
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + category + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(float begin, float end, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (price Between ? And ?) And status = 1"
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setFloat(1, begin);
                ps.setFloat(2, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(String name, float begin, float end, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) And status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                ps.setFloat(3, begin);
                ps.setFloat(4, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(String name, float begin, float end, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And price Between ? And ?) And status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(float begin, float end, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) And status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + category + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoods(String name, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ?) And status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(String name, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where name Like ? "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(String category, int page_No, int i)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (category = ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, category);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(float begin, float end, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (price Between ? And ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setFloat(1, begin);
                ps.setFloat(2, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(String name, float begin, float end, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                ps.setFloat(3, begin);
                ps.setFloat(4, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(String name, float begin, float end, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And price Between ? And ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(float begin, float end, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + category + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    public List<FoodDTO> searchFoodsForAdmin(String name, String category, int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ?) "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foods == null) {
                        this.foods = new ArrayList<>();
                    }
                    this.foods.add(dto);
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
        return this.foods;
    }

    private List<FoodDTO> foodlist;

    public List<FoodDTO> getFoodList() {
        return this.foodlist;
    }

    public void loadFoods(int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods Where status = 1 "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("Quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foodlist == null) {
                        this.foodlist = new ArrayList<>();
                    }
                    this.foodlist.add(dto);
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
    }

    public void loadFoodsForAdmin(int page_No)
            throws SQLException, NamingException {
        int recordIndex = 20 * (page_No - 1);
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Order By createDate Desc OFFSET " + recordIndex + " Rows Fetch next 20 Row Only";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("Quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (this.foodlist == null) {
                        this.foodlist = new ArrayList<>();
                    }
                    this.foodlist.add(dto);
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
    }

    public boolean deleteFood(String id)
            throws SQLException, NamingException {
        try {
            String sql = "Update Foods "
                    + "Set status = 0 "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

    public boolean updateFood(String id, String name, String image, String description, float price, Date createDate, String category, String status, int quantity)
            throws SQLException, NamingException {
        try {
            String sql = "Update Foods "
                    + "Set name = ?, description = ?, image = ?,price = ?, createDate = ?,category = ?, status = ?, quantity = ? "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setString(3, image);
                ps.setFloat(4, price);
                ps.setTimestamp(5, new Timestamp(createDate.getTime()));
                ps.setString(6, category);
                ps.setString(7, status);
                ps.setInt(8, quantity);
                ps.setString(9, id);
                int result = ps.executeUpdate();
                if (result > 1) {
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

    public boolean updateFoodQuantityById(String id, int quantity) throws SQLException, NamingException {
        try {
            String sql = "Update Foods "
                    + "Set quantity = ? "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, id);
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

    public boolean createFood(String id, String name, String image, String description, float price, Date createDate, String category, boolean status, int quantity)
            throws SQLException, NamingException {
        try {
            String sql = "Insert into Foods(id, name, image, description, price, createDate, category, quantity, status) "
                    + "Values (?,?,?,?,?,?,?,?,?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, image);
                ps.setString(4, description);
                ps.setFloat(5, price);
                ps.setTimestamp(6, new Timestamp(createDate.getTime()));
                ps.setString(7, category);
                ps.setInt(8, quantity);
                ps.setBoolean(9, status);
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

    public FoodDTO getFoodByIdAndSetQuantity(String id, int quantity)
            throws SQLException, NamingException {
        try {
            String sql = "Select id, name, image, description, price, createDate, category, quantity, status "
                    + "From Foods "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    return dto;
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
        return null;
    }

    public FoodDTO getFoodById(String id)
            throws SQLException, NamingException {
        try {
            String sql = "Select id, name, image, description, price, createDate, category, quantity, status "
                    + "From Foods "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    Boolean status = rs.getBoolean("status");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("Quantity");
                    FoodDTO dto = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    return dto;
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
        return null;
    }

    public boolean checkExistedId(String id) throws SQLException, NamingException {
        try {
            String sql = "Select id "
                    + "From Foods "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
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

    public boolean updateFoodStatusById(String id) throws SQLException, NamingException {
        try {
            String sql = "Update Foods "
                    + "Set status = 1 "
                    + "Where id = ?";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

    public int getNoOfRecords()
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String name, String category, float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                ps.setFloat(3, begin);
                ps.setFloat(4, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String name)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String category, int a)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where ( category Like ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, category);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (price Between ? And ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setFloat(1, begin);
                ps.setFloat(2, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String name, String category)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? ) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String name, float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And price Between ? And ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecords(String category, float begin, float end, int a)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where ( category Like ? And price Between ? And ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + category + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin()
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods ";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String name, String category, float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? And price Between ? And ?) And status = 1";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                ps.setFloat(3, begin);
                ps.setFloat(4, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String name)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String category, int a)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where ( category Like ?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, category);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (price Between ? And ?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setFloat(1, begin);
                ps.setFloat(2, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String name, String category)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And category Like ? )";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setString(2, "%" + category + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String name, float begin, float end)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where (name Like ? And price Between ? And ?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNoOfRecordsOfAdmin(String category, float begin, float end, int a)
            throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select id, name, image, description, price, createDate, category, status, quantity "
                    + "From Foods "
                    + "Where ( category Like ? And price Between ? And ?)";
            con = MyConnection.getMyConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + category + "%");
                ps.setFloat(2, begin);
                ps.setFloat(3, end);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }
}
