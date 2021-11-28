<%-- 
    Document   : addProduct
    Created on : Nov 18, 2021, 11:15:50 PM
    Author     : Long Pham
--%>

<%@page import="longpt.category.CategoryDTO"%>
<%@page import="longpt.category.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="longpt.product.ProductDTO"%>
<%@page import="longpt.product.ProductDAO"%>
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
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                if (user == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
                if (user != null && user.getRoleID().equals("user")) {
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
            ProductDAO dao = new ProductDAO();           
            CategoryDAO daoCate = new CategoryDAO();
            List<CategoryDTO> listCate = daoCate.getForAllCate();
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
                    <div class="add-title">Add Product</div>
                    <div class="title-body">
                        <form action="DispatchServlet" method="POST">
                            <label for="name-modal" class="modal-label add-label">
                                Book's Name: 
                            </label>
                            <input name="txtBookName" id="name-modal" type="text" class="modal-input add-input" value="" placeholder="Get Book's Name?"/>
                            <label for="cbCategory" class="modal-label add-label">
                                Category : 
                            </label>
                            <select name="cbCategory" id="cbCategory">
                                <%
                                    for (CategoryDTO cate : listCate) {
                                %>
                                <option class="cbCt-option" value="<%= cate.getCategoryID()%>"> <%= cate.getCategoryName()%></option>
                                <%
                                    }
                                %>
                            </select>
                            <label for="tel-modal" class="modal-label add-label">
                                Book's Image:
                            </label>
                            <input name="txtBookImg" id="tel-modal" type="text" class="modal-input add-input" placeholder="Get URL Image?"/>
                            <label for="bookQuantity" class="modal-label add-label">
                                Book's Quantity: 
                            </label>
                            <input id="bookQuantity" type="number" name="txtBookQuantity" class="modal-input add-input" value="" placeholder="Get Book's Quantity?"/>        
                            <label for="bookPrice" class="modal-label add-label">
                                Book's Price: 
                            </label>
                            <input id="bookPrice" type="number" name="txtBookPrice" class="modal-input add-input" value="" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+" placeholder="Get Book's Price?"/>                                                             
                            <label for="bookDes" class="modal-label add-label">
                                Book's Description: 
                            </label>
                            <input id="bookDes" type="text" name="txtBookDes" class="modal-input add-input" value="" placeholder="Get Book's Description?"/>                            
                            <button id="update-btn" value="Add Book" name="btnAction">
                                Add Book <i class="fas fa-check update-icon"></i>
                            </button>
                            <!--<input type="submit" value="Update Book" name="btnAction" />-->
                            <!--                                                    <a href="UpdateBookServlet">Update Book</a>-->
                            <input type="hidden" name="txtProductID" value="" />
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
