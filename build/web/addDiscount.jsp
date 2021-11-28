<%-- 
    Document   : addUser
    Created on : Nov 19, 2021, 12:27:11 AM
    Author     : Long Pham
--%>

<%@page import="java.util.List"%>
<%@page import="longpt.user.UserDTO"%>
<%@page import="longpt.user.UserDAO"%>
<%@page import="longpt.category.CategoryDTO"%>
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

//            if (user == null) {
//                response.sendRedirect("login.jsp");
//                return;
//            }           
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
                    <%                        if (user == null) {

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
                    <%                        } else if (user != null && user.getRoleID().equals("user")) {
                            response.sendRedirect("shopping.jsp");
                            return;
                        }
                    %>


                </div>
            </div>
            <div class="clear"></div>
        </div>
        <%
            UserDAO dao = new UserDAO();
            List<UserDTO> list = dao.searchByName("");
            String dup = (String) request.getAttribute("DUPLICATED");
            String dupDisID = (String) request.getAttribute("DIS_ID");
            if (dup == null || dupDisID == null) {
                dup = "";
                dupDisID = "";
            }
        %>
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
                        <li><a href="" class="js-th" style="display: block;float: left;width: 100%;">Transaction history</a></li>
                        <!--<li><a href=""></a></li>-->
                    </ul>
                </div>
                <div class="col l-1"></div>
                <div class="col l-9">
                    <div class="add-title">Add Discount</div>
                    <div class="title-body">
                        <form action="DispatchServlet" method="POST" onsubmit="return validateForm()">
                            <label for="discountID" class="modal-label add-label">
                                Discount ID: 
                            </label>
                            <input name="txtDiscountID" id="discountID" type="text" class="modal-input add-input" value="<%= dupDisID%>" placeholder="Get Discount ID?"/>                           
                            <p style="color: red"><%= dup%></p>
                            <label for="description" class="modal-label add-label">
                                Description: 
                            </label>
                            <input name="txtDescr" id="description" type="text" class="modal-input add-input" value="" placeholder="Get Description?"/>
                            <label for="percentValue" class="modal-label add-label">
                                Percent Value : 
                            </label>
                            <input id="percentValue" type="number" name="txtDiscountValue" class="modal-input add-input" value="" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+" placeholder="Get Book's Price?"/>                                                             
                            <label for="startDate" class="modal-label add-label">
                                Start Date: 
                            </label>
                            <input name="txtStartDate" id="startDate" type="date" class="modal-input add-input" value=""/>
                            <label for="endDate" class="modal-label add-label">
                                End Date: 
                            </label>
                            <input name="txtEndDate" id="endDate" type="date" class="modal-input add-input" value=""/>                           
                            <button id="update-btn" value="Add Discount" name="btnAction">
                                Add <i class="fas fa-check update-icon"></i>
                            </button>
                            <!--<input type="submit" value="Update Book" name="btnAction" />-->
                            <!--                                                    <a href="UpdateBookServlet">Update Book</a>-->                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
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
            }
            return false;
        }

    </script>
</html>
