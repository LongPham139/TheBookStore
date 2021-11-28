/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longpt.user.UserDAO;
import longpt.user.UserDTO;

/**
 *
 * @author Long Pham
 */
public class AddUserServlet extends HttpServlet {

    public static final String ERROR = "invalid.html";
    public static final String SUCCESS = "SearchUserServlet";
    public static final String DUPLICATED = "addUser.jsp";
    private final static String formatOrderID = "ddMMyyyy";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            long mil = System.currentTimeMillis();
            Date date = new Date(mil);
            SimpleDateFormat formatOrder = new SimpleDateFormat(formatOrderID);
            String strOrder = formatOrder.format(date);
            String timeCurrent = "" + date;
            String userID = request.getParameter("txtUserID");
            boolean dup = dao.checkDupID(userID);
            if (dup) {
                request.setAttribute("DUPLICATED", "User ID duplicated!!!");
                request.setAttribute("DUP_USERID", userID);
                url = DUPLICATED;
            } else {
                String password = request.getParameter("txtPassword");
                String roleID = request.getParameter("cbRole");
                String fullName = request.getParameter("txtFullName");
                String phone = request.getParameter("txtPhone");
                String address = request.getParameter("txtAddress");
                UserDTO user = new UserDTO(userID, password, fullName, phone, address, timeCurrent, roleID);
                boolean check = dao.register(user);
                if (check) {
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at AddUserServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
