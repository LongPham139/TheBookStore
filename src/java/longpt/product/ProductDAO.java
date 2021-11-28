/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longpt.utils.DBUtils;

/**
 *
 * @author Long Pham
 */
public class ProductDAO {

    private static Connection con = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public static List<ProductDTO> searchByName(String name, int index) throws SQLException, NamingException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT productID, productName, quantity, price, description, dateCreated, img, categoryID, stt "
                    + "FROM tblProduct WHERE productName LIKE ?  "
                    + "ORDER BY stt ASC "
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (index - 1) * 8);
            rs = stm.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String dateCreated = rs.getString("dateCreated");
                String categoryID = rs.getString("categoryID");
                String image = rs.getString("img");
                int stt = rs.getInt("stt");
                ProductDTO product = new ProductDTO(productID, productName, description, dateCreated, categoryID, image, quantity, stt, price);
                list.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public static ProductDTO searchByID(String id) throws SQLException, NamingException {
        ProductDTO product = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT productID, productName, quantity, price, description, dateCreated, categoryID, img "
                    + "FROM tblProduct WHERE productID=? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String dateCreated = rs.getString("dateCreated");
                String categoryID = rs.getString("categoryID");
                String image = rs.getString("img");
                product = new ProductDTO(productID, productName, description, dateCreated, categoryID, image, quantity, price);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return product;
    }

    public static boolean addProduct(ProductDTO product) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO tblProduct(productID, productName, quantity, price, description, dateCreated, categoryID, img, stt) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, product.getProductID());
            stm.setString(2, product.getProductName());
            stm.setInt(3, product.getQuantity());
            stm.setFloat(4, product.getPrice());
            stm.setString(5, product.getDescription());
            stm.setString(6, product.getDateCreated());
            stm.setString(7, product.getCategoryID());
            stm.setString(8, product.getImg());
            stm.setInt(9, product.getStt());
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public static boolean updateProduct(ProductDTO product) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblProduct SET productName=?, quantity=?, price=?, description=?, categoryID=?, img=? "
                    + "WHERE productID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, product.getProductName());
            stm.setInt(2, product.getQuantity());
            stm.setFloat(3, product.getPrice());
            stm.setString(4, product.getDescription());
            stm.setString(5, product.getCategoryID());
            stm.setString(6, product.getImg());
            stm.setString(7, product.getProductID());
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public static boolean deleteProduct(String id) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "DELETE FROM tblProduct "
                    + "WHERE productID=? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public static String getLastID() throws NamingException, SQLException {
        String id = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 productID, stt FROM tblProduct ORDER BY stt DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getString("productID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }

    public static int countProID() throws NamingException, SQLException {
        int count = 0;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 stt FROM tblProduct ORDER BY stt DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("stt");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;
    }

    public static String generateProID(String proID) {
        String newCh = "";
        int count = Integer.parseInt(proID);
        count++;
        newCh = "" + count;
        return newCh;
    }

    public int endPage(int total, int num) {
        int endPage = total / num;
        if (total % num != 0) {
            endPage++;
        }
        return endPage;
    }

    public int totalProduct() throws NamingException, SQLException {
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM tblProduct ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
    public int totalProductByName(String name) throws NamingException, SQLException {
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM tblProduct WHERE productName LIKE ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public static List<ProductDTO> searchByName4Rows(String name, int index) throws SQLException, NamingException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT productID, productName, quantity, price, description, dateCreated, img, categoryID, stt "
                    + "FROM tblProduct WHERE productName LIKE ?  "
                    + "ORDER BY stt "
                    + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (index-1)*4);
            rs = stm.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String dateCreated = rs.getString("dateCreated");
                String categoryID = rs.getString("categoryID");
                String image = rs.getString("img");
                int stt = rs.getInt("stt");
                ProductDTO product = new ProductDTO(productID, productName, description, dateCreated, categoryID, image, quantity, stt, price);
                list.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
