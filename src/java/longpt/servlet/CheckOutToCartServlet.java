/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpt.cart.CartOBJ;
import longpt.details.DetailDAO;
import longpt.details.DetailDTO;
import longpt.order.OrderDAO;
import longpt.order.OrderDTO;
import longpt.product.ProductDTO;

/**
 *
 * @author Long Pham
 */
public class CheckOutToCartServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "checkOut.jsp";
    private final static String formatOrderID = "dd-MM-yyyy HH:mm:ss";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            OrderDAO orderDAO = new OrderDAO();
            DetailDAO detailDAO = new DetailDAO();
            long mil = System.currentTimeMillis();
            Date date = new Date(mil);
            Time time = new Time(mil);
            SimpleDateFormat formatOrder = new SimpleDateFormat(formatOrderID);
            String strOrder = formatOrder.format(date);
            String timeCurrent = "" + date+ " " + time;
            System.out.println(timeCurrent);
            int count = 1;
            int stt = 0;
            String userID = request.getParameter("txtUserID").trim();
            String orderID = orderDAO.getLastOrder();
            float tot = (float) session.getAttribute("TOTAL");
            double total = Math.round(tot * 100.0) / 100.0;
            float mnd = (float) session.getAttribute("MND");
            double moneyNotDiscount = Math.round(mnd * 100.0) / 100.0;
            String discountID = request.getParameter("txtDiscount");
            if (discountID.isEmpty()) {
                discountID = null;
            }
            if (orderID.isEmpty()) {
                orderID = "C-" + strOrder + "-" + count;
                stt = 1;
            } else if (orderID != null) {
                String newOrID = orderDAO.generateOderID(orderID);
                orderID = "" + newOrID;
                stt = orderDAO.countUp() + 1;
            }
            OrderDTO order = new OrderDTO(orderID, userID, timeCurrent, discountID, (float) total, (float) moneyNotDiscount, stt);
            boolean checkOrder = orderDAO.insertOrder(order);
            if (checkOrder) {
                session.setAttribute("ORDER", order);
                CartOBJ cart = (CartOBJ) session.getAttribute("CART");
                if (cart != null) {
                    for (ProductDTO product : cart.getCart().values()) {
                        String detailID = detailDAO.getLastDetail();
                        if (detailID.isEmpty()) {
                            detailID = "D-" + "1";
                            stt = 1;
                        } else if (detailID != null) {
                            String newDet = detailDAO.generateDetailID(detailID);
                            detailID = newDet;
                            stt = detailDAO.countUp() + 1;
                        }
                        String proID = product.getProductID();
                        int qtt = product.getQuantity();
                        double price = Math.round((product.getPrice() * qtt) * 100.0) / 100.0;
                        DetailDTO detail = new DetailDTO(detailID, proID, orderID, timeCurrent, qtt, stt, (float) price);
                        boolean checkDetail = detailDAO.insertDetail(detail);
                        if (checkDetail) {
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckOutToCartServlet: " + e.toString());
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
