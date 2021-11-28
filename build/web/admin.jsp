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
                            <a href="SearchUserServlet" class="js-user" onclick="return toggle('pg0')">User</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addUser.jsp" style="display: inline-block;float: left;" class="">Add User</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="SearchBookServlet" class="js-book" style="display: block;float: left;width: 100%;" onclick="return toggle('pg1')">Product</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addProduct.jsp" style="display: inline-block;float: left;" class="">Add Product</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="SearchDiscountServlet" class="js-discount" style="display: block;float: left;width: 100%;" onclick="return toggle('pg2')">Discount</a>
                            <ul class="sub-manage-list">
                                <li>
                                    <a href="addDiscount.jsp" style="display: inline-block;float: left;" class="">Add Discount</a>
                                </li>
                            </ul>
                        </li>
                        <li><a href="SearchOrderServlet" class="js-th" style="display: block;float: left;width: 100%;" onclick="return toggle('pg3')">Transaction history</a></li>
                        <!--<li><a href=""></a></li>-->
                    </ul>
                </div>

                <div id="pg" class="col l-10">
                    <div class="row"></div>
                    <div class="col l-12 table-user pg" style="display: block;" id="pg0">
                        <table border="1" class="list-user" >  
                            <%
                                UserDAO daoUs = new UserDAO();
                                List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
                                if (list != null && list.size() > 0) {
                            %>
                            <thead>
                                <tr>
                                    <th>Stt</th>
                                    <th>User ID</th>
                                    <th>Password</th>
                                    <th>Admin</th>
                                    <th>Full Name</th>
                                    <th>Phone</th>
                                    <th>Address</th>      
                                    <th>Update</th>      
                                    <th>Delete</th>      

                                </tr>
                            </thead>
                            <tbody class="user-infor">

                                <%
                                    int count = 1;
                                    for (UserDTO usr : list) {
                                %>    
                            <form action="DispatchServlet" method="POST">
                                <tr >
                                    <td style="text-align: center;"><%= count++%></td>
                                    <td style="padding-left: 10px;">
                                        <%= usr.getUserID()%>

                                    </td>
                                    <td style="text-align: center;">*****</td>
                                    <td style="text-align: center;">
                                        <input type="checkbox" name="chkAdmin" value="ON" 
                                               <%
                                                   if (usr.getRoleID().equals("admin")) {
                                               %>
                                               checked="checked"
                                               <%
                                                   }
                                               %>
                                               />                
                                    </td>
                                    <td>
                                        <input type="text" name="txtFullName" value="<%= usr.getFullName()%>" class="text-input"/>
                                    </td>
                                    <td>
                                        <input type="number" name="txtPhone" value="<%= usr.getPhone()%>" class="text-input"/>
                                    </td>
                                    <td>
                                        <input type="text" name="txtAdd" value="<%= usr.getAddress()%>" class="text-input"/>
                                    </td>
                                    <td>

                                        <input type="submit" value="Update" name="btnAction" class="text-btn"/>
                                        <input type="hidden" name="txtUserID" value="<%= usr.getUserID()%>" />
                                        <input type="hidden" name="txtSearch" value="<%= searchValue%>" />
                                    </td>
                                    <td style="text-align: center;">
                                        <a class="text-btn-delete" href="DispatchServlet?btnAction=Delete&txtUserID=<%= usr.getUserID()%>&txtSearch=<%= searchValue%>">Delete</a>
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

                    </div>
                    
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
        <script>
            function toggle(IDS) {
                var sel = document.getElementById('pg').getElementsByTagName('div');
                for (var i = 0; i < sel.length; i++) {
                    if (sel[i].id !== IDS) {
                        sel[i].style.display = 'none';
                    }
                }
                var status = document.getElementById(IDS).style.display;
                if (status === 'block') {
                    document.getElementById(IDS).style.display = 'none';
                } else {
                    document.getElementById(IDS).style.display = 'block';
                    if(document.getElementById(IDS) === 'pg0'){
                        var button = document.getElementById('pg0');
                        button.form.submit();
                    }
                    if(document.getElementById(IDS) === 'pg1'){
                        var button = document.getElementById('pg1');
                        button.form.submit();
                    }
                    if(document.getElementById(IDS) === 'pg2'){
                        var button = document.getElementById('pg2');
                        button.form.submit();
                    }if(document.getElementById(IDS) === 'pg3'){
                        var button = document.getElementById('pg3');
                        button.form.submit();
                    }
                    
                }
                return false;
            }
        </script>
    </body>
</html>
