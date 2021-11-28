<%-- 
    Document   : admin
    Created on : Sep 27, 2021, 4:36:01 PM
    Author     : Long Pham
--%>

<%@page import="longpt.order.OrderDTO"%>
<%@page import="longpt.order.OrderDAO"%>
<%@page import="longpt.user.UserDAO"%>
<%@page import="longpt.product.ProductDAO"%>
<%@page import="longpt.discount.DiscountDTO"%>
<%@page import="longpt.discount.DiscountDAO"%>
<%@page import="longpt.category.CategoryDTO"%>
<%@page import="longpt.category.CategoryDAO"%>
<%@page import="longpt.product.ProductDTO"%>
<%@page import="longpt.cart.CartOBJ"%>
<%@page import="java.util.List"%>
<%@page import="longpt.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="font/fontawesome-free-5.15.4-web/css/all.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            try {
                if (user == null) {
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "no-store");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    response.sendRedirect("login.jsp");
                    return;
                }
                if (user != null && user.getRoleID().equals("user")) {
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "no-store");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    response.sendRedirect("SearchBookServlet");
                    return;
                }
            } catch (Exception e) {
                out.println(e);
            }
            String searchValue = request.getParameter("txtSearch");
            if (searchValue == null) {
                searchValue = "";
            }
//            if (user == null) {
//                response.sendRedirect("login.jsp");
//                return;
//            }
            String proID = "";
            String searchBook = request.getParameter("txtSearchBook");
            if (searchBook == null) {
                searchBook = "";
            }
        %>
        <div class="grid header">
            <div class="row">
                <div class="col l-2 header-logo " >
                    <a href="SearchBookServlet"><img src="https://vietnam9.net/wp-content/uploads/2020/09/Arsenal-FC-Logo.png"></a>              
                </div>
                <div class="col l-7 header-search">
                    <form action="DispatchServlet" method="POST">
                        <div class="row no-gutters">
                            <div class="search-input col l-12">
                                <h1 style="text-align: center;color: #f00000;">ADMIN PAGE</h1>
                            </div> 
                        </div>
                    </form>
                </div>
                <div class="col l-3 header-account">
                    <%
                        if (user == null) {

                    %>
                    <div class="row">
                        <div class="col l-8 header-user">
                            <a href="login.jsp" class="row no-gutters">
                                <div class="col l-4">
                                    <i class="fas fa-user user-icon"></i>
                                </div>
                                <div class="col l-8">                          
                                    <div><p>Login/Register</p></div>
                                </div>                             
                            </a>
                        </div>
                        <div class="col l-4 header-cart">
                            <a href="viewCart.jsp"><i class="fas fa-shopping-cart user-cart"></i></a>
                        </div>
                    </div>
                    <%                    } else if (user != null) {
// && user.isRoleID() == false
%>
                    <div class="row">
                        <div class="col l-8 header-user">
                            <div class="row no-gutters">
                                <div class="col l-4">
                                    <i class="fas fa-user user-icon"></i>
                                </div>
                                <div class=" col l-8">
                                    <div class="row account-infor">
                                        <div class="col l-12">
                                            <div class="row">
                                                <div class="col l-4"></div>
                                                <div class="col l-8">
                                                    <p>Account</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col l-12">
                                            <div class="row">
                                                <div class="col l-4"></div>
                                                <div class="col l-8">
                                                    <ul class="nav">
                                                        <li><a class="user-name"><%= user.getFullName()%><i class="fas fa-sort-down name-icon"></i></a>
                                                            <ul class="list-option">
                                                                <li><a href="#">1111</a></li>
                                                                <li><a href="#">2222</a></li>
                                                                <li>                                                                    
                                                                    <a href="DispatchServlet?btnAction=LogOut">Log Out</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                    </ul> 
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%                        }
//else if (user != null && user.isRoleID() == true) {
//                            response.sendRedirect("admin.jsp");
//                            return;
//                        }
                    %>


                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="grid wide table" style="max-width: 1500px;">
            <div class="row">
                <div class="col l-2 manage-list">
                    <ul>
                        <p class="main-content">Management</p>
                        <li>
                            <a href="SearchUserServlet" class="js-user" >User</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addUser.jsp" style="display: inline-block;float: left;" class="">Add User</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="SearchBookServlet" class="js-book" style="display: block;float: left;width: 100%;" >Product</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addProduct.jsp" style="display: inline-block;float: left;" class="">Add Product</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="SearchDiscountServlet" class="js-discount" style="display: block;float: left;width: 100%;">Discount</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addDiscount.jsp" style="display: inline-block;float: left;" class="">Add Discount</a>
                                </li>
                            </ul>
                        </li>
                        <li><a href="SearchOrderServlet" class="js-th" style="display: block;float: left;width: 100%;" >Transaction history</a></li>
                        <!--<li><a href=""></a></li>-->
                    </ul>
                </div>

                <div class="col l-10 table-th">
                    <%
                        int endPage = 0;
                    %>
                    <table border="1" class="list-discount" >  
                        <%
                            OrderDAO dao = new OrderDAO();
                            List<OrderDTO> listOrder = (List<OrderDTO>) request.getAttribute("LIST_ORDER");
                            endPage = dao.endPage(dao.totalOrder());
                            System.out.println(endPage);
                            if (listOrder != null && listOrder.size() > 0) {
                        %>
                        <thead>
                            <tr class="list-title">
                                <th>Stt</th>
                                <th>Order ID</th>
                                <th>User Bought</th>
                                <th>Total</th>
                                <th>Hour</th>
                                <th>Day</th>     
                                <th>Discount used</th>     
                            </tr>
                        </thead>
                        <tbody class="user-infor">

                            <%                                    int count = 1;
                                for (OrderDTO order : listOrder) {
                                    String disID = order.getDiscountID();
                                    if (disID == null) {
                                        disID = "";
                                    }

                            %>    
                        <form action="DispatchServlet" method="POST">
                            <tr class="list-infor">
                                <td style="text-align: center;"><%= order.getStt()%></td>
                                <td >
                                    <%= order.getOrderID()%>
                                </td>
                                <td style="text-align: center;">
                                    <input type="text" name="txtValueDiscount" value="<%= order.getUserID()%>" class="text-input"/>
                                </td>                               
                                <td>
                                    <input type="text" name="txtDiscountDes" value="<%= order.getTotal()%>" class="text-input"/>
                                </td>
                                <td>
                                    <input type="text" name="txtStartDate" value="<%= dao.getOrderHour(order.getTime())%>" class="text-input"/>
                                </td>
                                <td>
                                    <input type="text" name="txtStartDate" value="<%= dao.getOrderDate(order.getTime())%>" class="text-input"/>
                                </td>
                                <td>
                                    <input type="text" name="txtEndDate" value="<%= disID%>" class="text-input"/>
                                </td>

                            </tr>
                        </form>
                        <%

                            }
                        %>
                        </tbody>
                        <%
                        } else {
                        %>
                        No record matches!
                        <%
                            }
                        %>
                    </table>
                    <section class="col l-12 list-paging" >

                        <%
                            for (int i = 1; i <= endPage; i++) {
                                int index = (int) request.getAttribute("tag");
                        %>
                        <a class="
                           <%
                               if(index == i){
                                   %> 
                                   active
                                   <%
                               }
                           %>
                           " href="SearchOrderServlet?txtIndex=<%= i%>" ><%= i%> </a>
                        <%
                            }
                        %>
                    </section>


                </div>

            </div>

        </div>
        <script>
            const buyBtns = document.querySelector('.js-buy-ticket');
            const modal = document.querySelector('.modal');
            const modalClose = document.querySelector('.js-modal-close');
            const modalContainer = document.querySelector('.js-modal-container');
            //Hàm hiển thị mua vé 
            function showBuyTicket() {
                modal.classList.add('open');
            }
            //Hàm ẩn modal mua vé 
            function hideBuyTicket() {
                modal.classList.remove('open');
            }
            //Lặp qua từng thẻ button và nghe hành vi click

            buyBtns.addEventListener('click', showBuyTicket);

            //nghe hành vi click vào button close
            modalClose.addEventListener('click', hideBuyTicket);
            modal.addEventListener('click', hideBuyTicket);
            modalContainer.addEventListener('click', function (event) {
                event.stopPropagation();
            });
        </script>

    </body>
</html>
