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

/**
 *
 * @author Long Pham
 */
public class DispatchServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN_CONTROLLER = "LoginServlet";
    private static final String LOGOUT_CONTROLLER = "LogOutServlet";
    private static final String LOGIN_BY_FACEBOOK_CONTROLLER = "LoginByFaceServlet";
    private static final String SEARCH_CONTROLLER = "SearchUserServlet";
    private static final String UPDATE_CONTROLLER = "UpdateServlet";
    private static final String DELETE_CONTROLLER = "DeleteServlet";
    private static final String SEARCH_BOOK_CONTROLLER = "SearchBookServlet";
//    private static final String SEARCH_ORDER_CONTROLLER = "SearchOrderServlet";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private static final String DISCOUNT_CART_CONTROLLER = "DiscountToCartServlet";
    private static final String CHECKOUT_CART_CONTROLLER = "CheckOutToCartServlet";
    private static final String EDIT_CART_CONTROLLER = "EditToCartServlet";
    private static final String REMOVE_CART_CONTROLLER = "RemoveToCartServlet";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserServlet";
    private static final String UPDATE_BOOK_CONTROLLER = "UpdateBookServlet";
    private static final String DELETE_BOOK_CONTROLLER = "DeleteBookServlet";
    private static final String ADD_BOOK_CONTROLLER = "AddProductServlet";
    private static final String ADD_USER_CONTROLLER = "AddUserServlet";
    private static final String ADD_DISCOUNT_CONTROLLER = "AddDiscountServlet";
    private static final String UPDATE_DISCOUNT_CONTROLLER = "UpdateDiscountServlet";
    private static final String DELETE_DISCOUNT_CONTROLLER = "DeleteDiscountServlet";
    private static final String SEARCH_DISCOUNT_CONTROLLER = "SearchDiscountServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SEARCH_BOOK_CONTROLLER;
        try {
            String button = request.getParameter("btnAction");
            if (button.isEmpty()) {
//                url = SEARCH_BOOK_CONTROLLER;
            }
            if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            }
            if (button.equals("LogOut")) {
                url = LOGOUT_CONTROLLER;
            }
            if (button.equals("LoginFaceBook")) {
                url = LOGIN_BY_FACEBOOK_CONTROLLER;
            }
            if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            }
            if (button.equals("Update")) {
                url = UPDATE_CONTROLLER;
            }
            if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            }
            if (button.equals("Search Book")) {
                url = SEARCH_BOOK_CONTROLLER;
            }
            if (button.equals("Buy Now")) {
                url = ADD_TO_CART_CONTROLLER;
            }
            if (button.equals("Apply")) {
                url = DISCOUNT_CART_CONTROLLER;
            }
            if (button.equals("Check Out")) {
                url = CHECKOUT_CART_CONTROLLER;
            }
            if (button.equals("Edit")) {
                url = EDIT_CART_CONTROLLER;
            }
            if (button.equals("Remove")) {
                url = REMOVE_CART_CONTROLLER;
            }
            if (button.equals("Update User")) {
                url = UPDATE_USER_CONTROLLER;
            }
            if (button.equals("Update Book")) {
                url = UPDATE_BOOK_CONTROLLER;
            }
            if (button.equals("Delete Book")) {
                url = DELETE_BOOK_CONTROLLER;
            }
            if (button.equals("Add Book")) {
                url = ADD_BOOK_CONTROLLER;
            }
            if (button.equals("Add User")) {
                url = ADD_USER_CONTROLLER;
            }
            if (button.equals("Add Discount")) {
                url = ADD_DISCOUNT_CONTROLLER;
            }
            if (button.equals("Update Discount")) {
                url = UPDATE_DISCOUNT_CONTROLLER;
            }
            if (button.equals("Delete Discount")) {
                url = DELETE_DISCOUNT_CONTROLLER;
            }
            if (button.equals("Search Discount")) {
                url = SEARCH_DISCOUNT_CONTROLLER;
            }
//            if (button.equals("Search Order")) {
//                url = SEARCH_ORDER_CONTROLLER;
//            }
        } catch (Exception e) {
            log("Error at DispatchServlet: " + e.toString());
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
