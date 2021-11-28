/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import longpt.utils.DBUtils;

/**
 *
 * @author Long Pham
 */
public class DetailDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public String getLastDetail() throws NamingException, SQLException {
        String lastDetail = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 detailID, stt FROM tblDetail ORDER BY stt DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastDetail = rs.getString("detailID");
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
        return lastDetail;
    }

    public boolean insertDetail(DetailDTO detail) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO tblDetail(detailID, productID, quantity, orderID, stt, price, time) "
                    + "VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, detail.getDetailID());
            ps.setString(2, detail.getProductID());
            ps.setInt(3, detail.getQuantity());
            ps.setString(4, detail.getOrderID());
            ps.setInt(5, detail.getStt());
            ps.setFloat(6, detail.getPrice());
            ps.setString(7, detail.getTime());
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

    public String generateDetailID(String detailID) {
        String newCh = "";
        String tmp[] = detailID.split("-");
        String id = tmp[0];
        String stt = tmp[1];
        int count = Integer.parseInt(stt) + 1;
        newCh = id + "-" + count;
        return newCh;
    }

    public int countUp() throws NamingException, SQLException {
        int count = 0;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT TOP 1 stt FROM tblDetail ORDER BY stt DESC";
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
}
