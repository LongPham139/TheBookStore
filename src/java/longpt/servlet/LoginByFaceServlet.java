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
import javax.servlet.http.HttpSession;
import longpt.profile.Profile_Bean;
import longpt.profile.Profile_Modal;
import longpt.user.UserDAO;
import longpt.user.UserDTO;

/**
 *
 * @author Long Pham
 */
public class LoginByFaceServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String ADMIN_PAGE = "SearchUserServlet";
    private static final String USER_PAGE = "SearchBookServlet";
    private static final boolean ADMIN = true;
    private static final boolean USER = false;
    private static final String formatDate = "ddMMyyyy";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        Profile_Bean obj_Profile_Bean = null;
        try {
            HttpSession session = request.getSession();
            String access_token = (String) request.getParameter("access_token");
            Profile_Modal obj_Profile_Modal = new Profile_Modal();
             obj_Profile_Bean = obj_Profile_Modal.call_me(access_token);
            UserDTO user = null;
            UserDAO dao = new UserDAO();
            long mil = System.currentTimeMillis();
            Date date = new Date(mil);
            SimpleDateFormat formatOrder = new SimpleDateFormat(formatDate);
            String strOrder = formatOrder.format(date);
            String timeCurrent = "" + date;
            String email = obj_Profile_Bean.getEmail();
            String id = obj_Profile_Bean.getId();
            String name = obj_Profile_Bean.getUser_name();
            String phone = "Update Sau!!";
            String address = "Update Sau!!";
            user = dao.checkLogin(email, id);
            if (user == null) {
                user = new UserDTO(email, id, name, phone, address, timeCurrent, "user");
                session.setAttribute("LOGIN_USER", user);
                boolean check = dao.register(user);
                url = USER_PAGE;

            } else if (user != null) {
                session.setAttribute("LOGIN_USER", user);
                String role = user.getRoleID();
                if (role.equals("admin")) {
                    url = ADMIN_PAGE;
                } else if (role.equals("user")) {
                    url = USER_PAGE;
                }
            }
        } catch (Exception e) {
            log("Error at LoginByFaceServlet: " + e.toString());
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
