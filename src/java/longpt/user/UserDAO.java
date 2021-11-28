/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.user;

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
public class UserDAO {

    private static Connection con = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public static UserDTO checkLogin(String userID, String password) throws NamingException, SQLException {
        UserDTO user = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT roleID, fullName, phone, address, dateCreated FROM tblUser WHERE userID=? AND password=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                String roleID = rs.getString("roleID");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String dateCreated = rs.getString("dateCreated");
                user = new UserDTO(userID, password, fullName, phone, address, dateCreated, roleID);
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
        return user;
    }

    public boolean register(UserDTO user) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO tblUser(userID, password, roleID, fullName, phone, address, dateCreated) "
                    + "VALUES(?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, user.getUserID());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getRoleID());
            stm.setString(4, user.getFullName());
            stm.setString(5, user.getPhone());
            stm.setString(6, user.getAddress());
            stm.setString(7, user.getDateCreated());
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

    public int endPage(int total) {
        int endPage = total / 8;
        if (total % 8 != 0) {
            endPage++;
        }
        return endPage;
    }

    public int totalUser() throws NamingException, SQLException {
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM tblUser ";
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

    public List<UserDTO> searchByName(String name, int index) throws NamingException, SQLException {
        List<UserDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT userID, password, roleID, fullName, phone, address, dateCreated FROM tblUser WHERE fullName LIKE ? "
                    + "ORDER BY userID "
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (index - 1) * 8);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String password = rs.getString("password");
                String roleID = rs.getString("roleID");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String date = rs.getString("dateCreated");
                UserDTO user = new UserDTO(userID, password, fullName, phone, address, date, roleID);
                list.add(user);
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

    public boolean update(UserDTO user) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblUser SET roleID=?, fullName=?, phone=?, address=? WHERE userID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, user.getRoleID());
            stm.setString(2, user.getFullName());
            stm.setString(3, user.getPhone());
            stm.setString(4, user.getAddress());
            stm.setString(5, user.getUserID());
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

    public boolean updateUser(UserDTO user) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblUser SET address=?, phone=? WHERE userID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, user.getAddress());
            stm.setString(2, user.getPhone());
            stm.setString(3, user.getUserID());
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

    public boolean delete(String userID) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "DELETE FROM tblUser WHERE userID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
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

    public UserDTO searchByID(String id) throws NamingException, SQLException {
        UserDTO user = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT userID, password, roleID, fullName, phone, address, dateCreated FROM tblUser WHERE userID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String password = rs.getString("password");
                String roleID = rs.getString("roleID");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String date = rs.getString("dateCreated");
                user = new UserDTO(userID, password, fullName, phone, address, date, roleID);
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
        return user;
    }

    public boolean checkDupID(String id) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT userID FROM tblUser WHERE userID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                check = true;
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
        return check;
    }

}
