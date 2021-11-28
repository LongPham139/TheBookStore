/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.category;

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
public class CategoryDAO {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public List<CategoryDTO> getForAllCate() throws NamingException, SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT categoryID, categoryName FROM tblCategory";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("categoryID");
                String name = rs.getString("categoryName");
                CategoryDTO cate = new CategoryDTO(id, name);
                list.add(cate);
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
    public String getForId(String id) throws NamingException, SQLException {
        String name="";
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT categoryName FROM tblCategory WHERE categoryID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {                
                name = rs.getString("categoryName");              
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
        return name;
    }
    
}
