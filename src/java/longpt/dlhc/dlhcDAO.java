/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.dlhc;

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
public class dlhcDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<tinhTPDTO> getListTTPByID() throws NamingException, SQLException {
        List<tinhTPDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT ID, tenTinhThanhPho, quocGiaId FROM TinhThanhPho";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String tenTinhThanhPho = rs.getString("tenTinhThanhPho");
                int quocGiaId = rs.getInt("quocGiaId");
                tinhTPDTO ttp = new tinhTPDTO(id, quocGiaId, tenTinhThanhPho);
                list.add(ttp);
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

    public List<quanHuyenDTO> getListQHByID(int ttpID) throws NamingException, SQLException {
        List<quanHuyenDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT ID, tenQuanHuyen, tinhThanhPhoId FROM QuanHuyen WHERE tinhThanhPhoId=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ttpID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String tenQuanHuyen = rs.getString("tenQuanHuyen");
                quanHuyenDTO qh = new quanHuyenDTO(id, ttpID, tenQuanHuyen);
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

    public List<xaPhuongDTO> getListXPByID(int qhID) throws NamingException, SQLException {
        List<xaPhuongDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT ID, tenXaPhuong, quanHuyenId FROM XaPhuong WHERE quanHuyenId=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, qhID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String tenXaPhuong = rs.getString("tenXaPhuong");
                xaPhuongDTO xp = new xaPhuongDTO(id, qhID, tenXaPhuong);
                list.add(xp);
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

    public String getNameStateByID(int id) throws NamingException, SQLException {
        String name = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT tenTinhThanhPho FROM TinhThanhPho WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("tenTinhThanhPho");
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
        return name;
    }

    public String getNameCityByID(int id) throws NamingException, SQLException{
        String name = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT tenQuanHuyen FROM QuanHuyen WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("tenQuanHuyen");
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
        return name;
    }

    public String getNameWardByID(int id) throws NamingException, SQLException{
        String name = "";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT tenXaPhuong FROM XaPhuong WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("tenXaPhuong");
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
        return name;
    }
}
