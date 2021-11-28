/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpt.user.UserDAO;
import longpt.user.UserDTO;

/**
 *
 * @author Long Pham
 */
public class LoginServlet extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "SearchUserServlet";
    private static final String USER_PAGE = "SearchBookServlet";
    private static final boolean ADMIN = true;
    private static final boolean USER = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            UserDTO user = UserDAO.checkLogin(userID, password);
            HttpSession session = request.getSession();
            if (user == null) {
                request.setAttribute("ERROR", "Wrong username or password!!!");
            } else {
                session.setAttribute("LOGIN_USER", user);
                String roleID = user.getRoleID();
                if (roleID.equals("admin")) {
                    url = ADMIN_PAGE;
                }
                if (roleID.equals("user")) {
                    url = USER_PAGE;
                }
            }

        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
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
