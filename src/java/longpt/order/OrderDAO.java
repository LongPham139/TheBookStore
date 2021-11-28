/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.order;

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
public class OrderDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public String getLastOrder() throws NamingException, SQLException {
        String lastOrder = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 orderID, stt FROM tblOrder ORDER BY stt DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastOrder = rs.getString("orderID");
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
        return lastOrder;
    }

    public boolean insertOrder(OrderDTO order) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO tblOrder(orderID, userID, time, total, stt, discountID, moneyNotDiscount) "
                    + "VALUES(?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, order.getOrderID());
            ps.setString(2, order.getUserID());
            ps.setString(3, order.getTime());
            ps.setFloat(4, order.getTotal());
            ps.setInt(5, order.getStt());
            ps.setString(6, order.getDiscountID());
            ps.setFloat(7, order.getMoneyNotDiscount());
            check = ps.executeUpdate() > 0 ? true : false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public String generateOderID(String oderID) {
        String newCh = "";
        String tmp[] = oderID.split("-");
        String id = tmp[0];
        String time = tmp[1];
        String stt = tmp[2];
        int count = Integer.parseInt(stt) + 1;
        newCh = id + "-" + time + "-" + count;

        return newCh;
    }

    public int countUp() throws NamingException, SQLException {
        int count = 0;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 stt FROM tblOrder ORDER BY stt DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("stt");
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
        return count;
    }

    public int totalOrder() throws NamingException, SQLException {
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM tblOrder ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
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
        return 0;
    }

    public List<OrderDTO> getAllOrder() throws NamingException, SQLException {
        List<OrderDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT orderID, userID, time, stt, moneyNotDiscount, discountID, total "
                    + "FROM tblOrder ORDER BY stt ASC ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString("orderID");
                String userID = rs.getString("userID");
                String time = rs.getString("time");
                int stt = rs.getInt("stt");
                float moneyNotDiscount = rs.getFloat("moneyNotDiscount");
                String discountID = rs.getString("discountID");
                float total = rs.getFloat("total");
                OrderDTO order = new OrderDTO(orderID, userID, time, discountID, total, moneyNotDiscount, stt);
                list.add(order);
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
        return list;
    }

    public String getOrderTime(String id) throws NamingException, SQLException {
        String time = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT time FROM tblOrder WHERE orderID=? ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                time = rs.getString("time");
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
        return time;
    }

    public String getOrderDate(String time) {
        String date = "";
        String tmp[] = time.split(" ");
        date = tmp[0];
        return date;
    }

    public String getOrderHour(String time) {
        String hour = "";
        String tmp[] = time.split(" ");
        hour = tmp[1];
        return hour;
    }

    public int endPage(int total) {
        int endPage = total / 10;
        if (total % 10 != 0) {
            endPage++;
        }
        return endPage;
    }
    public List<OrderDTO> pagingBy5Row(int index) throws NamingException, SQLException{
        List<OrderDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT orderID, userID, time, stt, moneyNotDiscount, discountID, total "
                    + "FROM tblOrder ORDER BY stt "
                    + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index-1)*10);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String orderID = rs.getString("orderID");
                String userID = rs.getString("userID");
                String time = rs.getString("time");
                int stt = rs.getInt("stt");
                float moneyNotDiscount = rs.getFloat("moneyNotDiscount");
                String discountID = rs.getString("discountID");
                float total = rs.getFloat("total");
                OrderDTO dto = new OrderDTO(orderID, userID, time, discountID, total, moneyNotDiscount, stt);
                list.add(dto);
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
        return list;
    }
    public static void main(String[] args) {
        String time = "2021-11-25 23:54:34.000";
        OrderDAO dao = new OrderDAO();
        String date = dao.getOrderDate(time);
        String hour = dao.getOrderHour(time);
        System.out.println(date);
        System.out.println(hour);
        int total = 101;
        int endPage = dao.endPage(total);
        System.out.println(endPage);
    }
}
