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
import longpt.discount.DiscountDAO;
import longpt.discount.DiscountDTO;

/**
 *
 * @author Long Pham
 */
public class AddDiscountServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "SearchDiscountServlet";
    private static final String DUPLICATED = "addDiscount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DiscountDAO dao = new DiscountDAO();
            String disID = request.getParameter("txtDiscountID");
            disID = disID.replaceAll(" ", "");
            DiscountDTO disCheck = dao.checkDiscount(disID);
            if (disCheck == null) {
                String dcr = request.getParameter("txtDescr");
                Float value = Float.parseFloat(request.getParameter("txtDiscountValue"));
                String startDate = request.getParameter("txtStartDate");
                String endDate = request.getParameter("txtEndDate");
                DiscountDTO newDis = new DiscountDTO(disID.toUpperCase(), dcr, startDate, endDate, value, true);
                boolean check = dao.addDiscount(newDis);
                if(check){
                    url = SUCCESS;
                    request.setAttribute("ADD_SCF", "Add Successful!!!");
                }
            }else {
                request.setAttribute("DUPLICATED", "The discount duplicated!!!");
                request.setAttribute("DIS_ID", disID);
                url = DUPLICATED;
            }
        } catch (Exception e) {
            log("Error at AddDiscountServlet: " + e.toString());
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
