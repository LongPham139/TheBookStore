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
import longpt.product.ProductDAO;
import longpt.product.ProductDTO;
import longpt.user.UserDTO;

/**
 *
 * @author Long Pham
 */
public class SearchBookServlet extends HttpServlet {

    private static final String ERROR = "shopping.jsp";
    private static final String USER_PAGE = "shopping.jsp";
    private static final String ADMIN_PAGE = "adminBook.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = new ArrayList<>();
            int index = 0;
            int listSize = 0;
            String indexPage = request.getParameter("txtIndex");
            if (indexPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexPage);
            }
            String searchValue = request.getParameter("txtSearchBook");
            if (searchValue == null) {
                searchValue = "";
            }
            listSize = dao.totalProductByName(searchValue);
            if (userLogin == null) {
                list = dao.searchByName(searchValue, index);
                if (list != null && list.size() > 0) {
                    request.setAttribute("LIST_BOOK", list);
                    request.setAttribute("tag", index);
                    request.setAttribute("LIST_SIZE", listSize);
                    url = USER_PAGE;
                } else {
                    request.setAttribute("ERROR__LIST", "Don't find the book!!!");
                    url = USER_PAGE;
                }
            }
            if (userLogin.getRoleID().equals("user")) {
                list = dao.searchByName(searchValue, index);
                if (list != null && list.size() > 0) {
                    request.setAttribute("LIST_BOOK", list);
                    request.setAttribute("tag", index);
                    request.setAttribute("LIST_SIZE", listSize);
                    url = USER_PAGE;
                } else {
                    request.setAttribute("ERROR__LIST", "Don't find the book!!!");
                    url = USER_PAGE;
                }
            } else if (userLogin.getRoleID().equals("admin")) {
                list = dao.searchByName4Rows(searchValue, index);
                if (list != null && list.size() > 0) {
                    request.setAttribute("LIST_BOOK", list);
                    request.setAttribute("tag", index);
                    request.setAttribute("LIST_SIZE", listSize);
                    url = ADMIN_PAGE;
                } else {
                    request.setAttribute("ERROR__LIST", "Don't find the book!!!");
                    url = ADMIN_PAGE;
                }

            }

        } catch (Exception e) {
            log("Error at SearchBookServlet: " + e.toString());
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
