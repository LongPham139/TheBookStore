/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class SearchUserServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "adminUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int index = 0;
            List<UserDTO> list = new ArrayList<>();
            UserDTO user = null;
            UserDAO dao = new UserDAO();
            String searchValue = request.getParameter("txtSearch");
            if (searchValue == null) {
                searchValue = "";
            }
            String indexPage = request.getParameter("txtIndex");
            if (indexPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexPage);
            }
            list = dao.searchByName(searchValue, index);
            request.setAttribute("LIST_USER", list);
            request.setAttribute("tag", index);
            if (list != null && list.size() > 0) {
                url = SUCCESS;
            } else {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at SearchUserServlet: " + e.toString());
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
