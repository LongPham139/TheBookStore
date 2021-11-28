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
import longpt.cart.CartOBJ;
import longpt.product.ProductDAO;
import longpt.product.ProductDTO;

/**
 *
 * @author Long Pham
 */
public class AddToCartServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "SearchBookServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ProductDAO dao = new ProductDAO();
            boolean check = false;
            String productID = request.getParameter("txtProductID");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            ProductDTO product = dao.searchByID(productID);
            product.setQuantity(quantity);
            CartOBJ cart = (CartOBJ) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartOBJ();
            }
            check = cart.add(product);
            if (check) {
                int qtt = cart.getCart().size();
                session.setAttribute("CART", cart);
                session.setAttribute("CART_SIZE", qtt);
                url = SUCCESS;
                request.setAttribute("MSG", "You have added " + product.getProductName() + " success!!!");
            }
        } catch (Exception e) {
            log("Error at AddToCartServlet: " + e.toString());
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
