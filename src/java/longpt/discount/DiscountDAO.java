/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.discount;

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
public class DiscountDAO {

    private static Connection con = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public DiscountDTO checkDiscount(String discountID) throws NamingException, SQLException {
        DiscountDTO discount = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT description, percentValue, starDiscount, endDiscount, isEnable "
                    + "FROM tblDiscount WHERE discountID =?";
            stm = con.prepareStatement(sql);
            stm.setString(1, discountID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String des = rs.getString("description");
                float percentValue = rs.getFloat("percentValue");
                String starDiscount = rs.getString("starDiscount");
                String endDiscount = rs.getString("endDiscount");
                boolean isEnable = rs.getBoolean("isEnable");
                discount = new DiscountDTO(discountID, des, starDiscount, endDiscount, percentValue, isEnable);
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
        return discount;
    }

    public boolean checkUsed(String disID, String userID) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT discountID, userID FROM tblDiscount "
                    + "WHERE discountID =? AND userID =?";
            stm = con.prepareStatement(sql);
            stm.setString(1, disID);
            stm.setString(2, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
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

    public boolean updateDiscountUser(String userID, String disID) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblDiscount() "
                    + "SET userID=? WHERE discountID=? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, disID);
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

    public List<DiscountDTO> getAllDiscount() throws NamingException, SQLException {
        List<DiscountDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT discountID, description, percentValue, starDiscount, endDiscount, userID, isEnable "
                    + "FROM tblDiscount ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String discountID = rs.getString("discountID");
                String des = rs.getString("description");
                float percent = rs.getFloat("percentValue");
                String starDiscount = rs.getString("starDiscount");
                String endDiscount = rs.getString("endDiscount");
                String userID = rs.getString("userID");
                boolean isEnable = rs.getBoolean("isEnable");
                DiscountDTO dis = new DiscountDTO(discountID, des, starDiscount, endDiscount, userID, percent, isEnable);
                list.add(dis);
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

    public String generateDate(String date) {
        String newCh = "";
        String tmp[] = date.split(" ");
        String day = tmp[0];
        String time = tmp[1];
        newCh = day;
        return newCh;
    }

    public List<DiscountDTO> searchDiscount(String discount, int index) throws SQLException, NamingException {
        List<DiscountDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT discountID, description, percentValue, starDiscount, endDiscount, isEnable "
                    + "FROM tblDiscount "
                    + "WHERE discountID LIKE ? "
                    + "ORDER BY discountID "
                    + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + discount + "%");
            stm.setInt(2, (index-1)*8);
            rs = stm.executeQuery();
            while (rs.next()) {
                String discountID = rs.getString("discountID");
                String description = rs.getString("description");
                float percentValue = rs.getFloat("percentValue");
                String starDiscount = rs.getString("starDiscount");
                String endDiscount = rs.getString("endDiscount");
                boolean isEnable = rs.getBoolean("isEnable");
                DiscountDTO dto = new DiscountDTO(discountID, description, starDiscount, endDiscount, percentValue, isEnable);
                list.add(dto);
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

    public boolean addDiscount(DiscountDTO discount) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO tblDiscount(discountID, description, percentValue, starDiscount, endDiscount, userID) "
                    + "VALUES(?,?,?,?,?,?) ";
            stm = con.prepareStatement(sql);
            stm.setString(1, discount.getDiscountID());
            stm.setString(2, discount.getDescription());
            stm.setFloat(3, discount.getPercenValue());
            stm.setString(4, discount.getStarDiscount());
            stm.setString(5, discount.getEndDiscount());
            stm.setString(6, discount.getUserID());
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

    public boolean update(DiscountDTO dto) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblDiscount "
                    + "SET description=? , percentValue=? , starDiscount=? , endDiscount=? "
                    + "WHERE discountID=? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getDescription());
            stm.setFloat(2, dto.getPercenValue());
            stm.setString(3, dto.getStarDiscount());
            stm.setString(4, dto.getEndDiscount());
            stm.setString(5, dto.getDiscountID());
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

    public boolean delete(String id) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblDiscount "
                    + "SET isEnable=0 "
                    + "WHERE discountID=? ";
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

    public int totalDiscount() throws NamingException, SQLException {
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM tblDiscount ";
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

    public int endPage(int total) {
        int endPage = total / 8;
        if (total % 8 != 0) {
            endPage++;
        }
        return endPage;
    }

    public List<DiscountDTO> pagingBy5Row(int index) throws NamingException, SQLException {
        List<DiscountDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT discountID, description, percentValue, starDiscount, endDiscount, userID, isEnable "
                    + "FROM tblDiscount ORDER BY discountID "
                    + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                String discountID = rs.getString("discountID");
                String description = rs.getString("description");
                float percentValue = rs.getFloat("percentValue");
                String starDiscount = rs.getString("starDiscount");
                String endDiscount = rs.getString("endDiscount");
                String userID = rs.getString("userID");
                boolean isEnable = rs.getBoolean("isEnable");
                DiscountDTO dto = new DiscountDTO(discountID, description, starDiscount, endDiscount, userID, percentValue, isEnable);
                list.add(dto);
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
