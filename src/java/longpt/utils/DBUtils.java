/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Long Pham
 */
public class DBUtils {

    public static Connection getConnection() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context tcCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) tcCtx.lookup("TheBookStore");
        Connection con = ds.getConnection();
        return con;
    }
}
