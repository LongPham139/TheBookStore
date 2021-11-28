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
public class UpdateServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "SearchUserServlet";
    private static final String LOGOUT = "LogOutServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {    
            String searchValue = request.getParameter("txtSearch");
            String userID = request.getParameter("txtUserID");
            String fullName = request.getParameter("txtFullName");
            String phone = request.getParameter("txtPhone");
            String add = request.getParameter("txtAdd");
            String role = request.getParameter("chkAdmin");
            String isAdmin = "user";
            if (role != null) {
                isAdmin = "admin";
            }            
            UserDTO user = new UserDTO(userID, "", fullName, phone, add, "", isAdmin);
            UserDAO dao = new UserDAO();
            boolean check = dao.update(user);
            if (check) {
                HttpSession session = request.getSession();
                UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");
                
                if (userLogin.getUserID().equals(userID)) {
                    url = LOGOUT;
                } else {
                    url = SUCCESS;
                }

            }
        } catch (Exception e) {
            log("Error at UpdateServlet: " + e.toString());
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
