/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpt.dlhc.dlhcDAO;
import longpt.user.UserDAO;
import longpt.user.UserDTO;

/**
 *
 * @author Long Pham
 */
public class UpdateUserServlet extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            /* TODO output your page here. You may use following sample code. */
            String stateName, cityName, wardName, address;
            HttpSession session = request.getSession();
            String add = request.getParameter("txtAddresInput");
            String country_id = request.getParameter("country");
            String state_id = request.getParameter("state");
            String city = request.getParameter("city");
            String ward = request.getParameter("ward");
            String user_id = request.getParameter("txtUserID");
            String phone_input = request.getParameter("txtPhoneInput");
            dlhcDAO dao = new dlhcDAO();
            UserDAO daoUser = new UserDAO();
            UserDTO userLogin = daoUser.searchByID(user_id);
            if(phone_input.isEmpty() || phone_input == null){
                phone_input = userLogin.getPhone();
            }
//            if(nameInput.isEmpty()){
//                nameInput = daoUser.searchByID(user_id).getFullName();
//            }
//            if(phoneInput.isEmpty()){
//                phoneInput = daoUser.searchByID(user_id).getPhone();
//            }
//            dateCrated = daoUser.searchByID(user_id).getDateCreated();
            stateName = dao.getNameStateByID(Integer.parseInt(state_id));
            cityName = dao.getNameCityByID(Integer.parseInt(city));
            wardName = dao.getNameWardByID(Integer.parseInt(ward));
            address = add + ", " + wardName + ", " + cityName + ", " + stateName;
            UserDTO user = new UserDTO(user_id, userLogin.getPassword(), userLogin.getFullName(), phone_input, address, userLogin.getDateCreated(), "user");
            boolean check = daoUser.updateUser(user);
            if (check) {
                session.setAttribute("LOGIN_USER", user);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateUserServlet: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
