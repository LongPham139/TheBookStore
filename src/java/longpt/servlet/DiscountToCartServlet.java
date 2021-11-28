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
import longpt.discount.DiscountDAO;
import longpt.discount.DiscountDTO;

/**
 *
 * @author Long Pham
 */
public class DiscountToCartServlet extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            DiscountDAO dao = new DiscountDAO();
            String discountID = request.getParameter("txtDiscount");
            String userID = request.getParameter("txtUserID");
            DiscountDTO discount = dao.checkDiscount(discountID);
            if (discount != null) {
                boolean check = dao.checkUsed(discountID, userID);
                if (check) {
                    session.setAttribute("MSG", "Discout already used!");
                    url = SUCCESS;
                } else {
//                    boolean update = dao.updateDiscount(userID, discountID);
//                    if (update) {
                    session.setAttribute("DISCOUNT", discount);
                    session.setAttribute("DISCOUNT_USERID", discount.getUserID());
                    url = SUCCESS;
//                    }
                }
            } else if (discount == null) {
                url = SUCCESS;
                session.setAttribute("DC-ERR", "Discount is not available!!!");
            }
        } catch (Exception e) {
            log("Error at DiscountToCartServlet: " + e.toString());
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
