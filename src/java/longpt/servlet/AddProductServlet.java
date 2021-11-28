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
import longpt.product.ProductDAO;
import longpt.product.ProductDTO;

/**
 *
 * @author Long Pham
 */
public class AddProductServlet extends HttpServlet {

    public static final String ERROR = "invalid.html";
    public static final String SUCCESS = "SearchBookServlet";
    private final static String formatOrderID = "ddMMyyyy";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO dao = new ProductDAO();
            long mil = System.currentTimeMillis();
            Date date = new Date(mil);
            SimpleDateFormat formatOrder = new SimpleDateFormat(formatOrderID);
            String strOrder = formatOrder.format(date);
            String timeCurrent = "" + date;
            String id = dao.generateProID(dao.getLastID());
            System.out.println(id);
            String name = request.getParameter("txtBookName");
            String categoryID = request.getParameter("cbCategory");
            String img = request.getParameter("txtBookImg");
            int qtt = Integer.parseInt(request.getParameter("txtBookQuantity"));
            float price = Float.parseFloat(request.getParameter("txtBookPrice"));
            String des = request.getParameter("txtBookDes");
            int stt = dao.countProID();
            int count = stt +1;
            ProductDTO pro = new ProductDTO(id, name, des, timeCurrent, categoryID, img, qtt, count, price);
            boolean check = dao.addProduct(pro);
            if (check) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AddProductServlet: " + e.toString());
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
