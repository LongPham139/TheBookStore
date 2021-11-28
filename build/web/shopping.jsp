<%-- 
    Document   : shopping
    Created on : Oct 2, 2021, 10:16:21 PM
    Author     : Long Pham
--%>

<%@page import="longpt.product.ProductDAO"%>
<%@page import="longpt.cart.CartOBJ"%>
<%@page import="longpt.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="longpt.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="font/fontawesome-free-5.15.4-web/css/all.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
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
                            <div class="search-input col l-10">
                                <input type="text" name="txtSearchBook" value="<%= searchBook%>" class="input-text" placeholder="Find the book you want..."/>
                            </div>
                            <div class="search-button col l-2">
                                <i class="fas fa-search btn-icon"></i>
                                <input type="submit" value="Search Book" name="btnAction" class="input-btn-book"/>
                            </div>
                        </div>
                    </form>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
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
                                                                <li><a href="DispatchServlet?btnAction=LogOut">Log Out</a></li>
                                                                <li><a href="#">1111</a></li>
                                                                <li><a href="#">2222</a></li>
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

                        <div class="col l-4 header-cart">
                            <a href="viewCart.jsp">
                                <i class="fas fa-shopping-cart user-cart"></i>
                                <%
                                    CartOBJ cart = (CartOBJ) session.getAttribute("CART");
                                    int qtt = 0;
                                    if (cart != null && cart.getCart().size() > 0) {
                                        qtt = cart.getCart().size();
                                %>
                                <p class="cart-qtt"><%= qtt%></p>
                                <%
                                    }

                                %>
                            </a>

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
        <%
            int endPage = 0;
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_BOOK");

            String error = (String) request.getAttribute("ERROR__LIST");
            if (error == null) {
                error = "";
            }
            if (list != null && list.size() > 0) {
                int listSize = (int) request.getAttribute("LIST_SIZE");
                endPage = dao.endPage(listSize, 8);
        %>
        <div class="grid wide container">
            <div class="row">
                <div class="container-left col l-3"></div>
                <div class="container-right col l-9">
                    <div class="row">
                        <%                            int count = 1;
                            for (ProductDTO product : list) {
                        %>
                        <div class="col l-3 product" style="padding-bottom: 10px;">
                            <a href="detailProduct.jsp?txtProductID=<%= product.getProductID()%>">
                                <div >
                                    <img src="<%= product.getImg()%>" alt="<%= product.getProductName()%>" class="pro-img"/>
                                </div>
                                <div class="pro-infor">
                                    <div class="infor-name">
                                        <h3><%= product.getProductName()%></h3>
                                    </div>
                                    <div class="infor-price">
                                        <b><h2><%= product.getPrice()%>$</h2></b>
                                    </div>
                                    <div class="infor-des">
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                </div>
                            </a>
                        </div>                       
                        <%
                            }
                        %>                        
                    </div>   
                    <section class="col l-12 list-paging" style="padding: 10px 0;">
                        <%
                            for (int i = 1; i <= endPage; i++) {
                                int index = (int) request.getAttribute("tag");
                        %>
                        <a class="
                           <%
                               if (index == i) {
                           %> 
                           active
                           <%
                               }
                           %>
                           " href="SearchBookServlet?txtIndex=<%= i%>&txtSearchBook=<%= searchBook%>"><%= i%> </a>
                        <%
                            }
                        %>
                    </section>
                    <%                    } else {
                    %>
                    <div class="col l-12 cart-middle">
                        <div class="middle-empty">
                            <div class="middle-img">
                                <img src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="Your cart is empty!!!"/>
                            </div>
                            <div class="middle-cmt">
                                <p>Don't find the book you want!!!</p>
                            </div>
                            <div class="middle-home">
                                <a href="SearchBookServlet?txtIndex=1">Search Again</a>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>

    </body>
    <footer class="grid footer">
        <div class="row" style="height: 300px;">
            <div class="col l-4 footer-logo" >
                <div class="row" >
                    <div class="col l-12" style="padding-bottom: 15px;">
                        <h2>About Me</h2>
                        <div class="footer-border" ></div>
                        <p style="font-size: 19px;">Hi everyone, I'm Thanh Long and I'm a java web developer. This is my first project, in the process of developing the project there are still many shortcomings, I hope everyone can feedback to help the website become more and more perfect.</p>
                    </div>
                    <div class="col l-12 footer-social">
                        <h2>Follow Me</h2>
                        <div class="footer-border" ></div>
                        <ul class="social-icon">
                            <li>
                                <a href="https://www.facebook.com/LongPham139/" class="footer-fa"><i class="fab fa-facebook-square"></i></a>
                            </li>
                            <li>
                                <a href="https://www.instagram.com/ptl.long/?hl=vi" class="footer-fa"><i class="fab fa-instagram"></i></a>
                            </li>
                            <li>
                                <a href="" class="footer-fa"><i class="far fa-envelope"></i></a>
                            </li>
                            <li>
                                <a href="https://github.com/LongPham139" class="footer-fa"><i class="fab fa-github-square"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>                
            </div>
            <div class="col l-8" >
                <div class="row">
                    <div class="col l-4" >
                        <div class="footer-list-link">
                            <h2>Links</h2>
                            <div class="footer-border" ></div>
                            <ul class="footer-link">
                                <li><a href="#">Home Page</a></li>
                                <li><a href="#">About Us</a></li>
                                <li><a href="#">Contact Us</a></li>
                                <li><a href="#">Services</a></li>
                                <li><a href="#">Policy Conditions</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col l-8" >
                        <div class="footer-contact">
                            <h2>Contact Information</h2>
                            <div class="footer-border" style="width: 58%;"></div>
                            <ul class="contact-info">
                                <li>
                                    <span class="span-icon"><i class="fa fa-map-marker"></i></span>
                                    <span class="span-text">153/18C, Cau Hang Hamlet, Hoa An Ward, Bien Hoa, Dong Nai</span>
                                </li>
                                <li>
                                    <span class="span-icon"><i class="fa fa-phone"></i></span>
                                    <span class="span-text">
                                        <a href="#">+84 918 805 311</a><br/>
                                        <!--<a href="#" style="padding-left: 35px;">+84 987 654 321</a>-->
                                    </span>

                                </li>
                                <li>
                                    <span class="span-icon"><i class="fa fa-envelope"></i></span>
                                    <span class="span-text">                                    
                                        <a href="#">phamlong139@gmail.com</a>
                                    </span>
                                </li>                               
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</html>
