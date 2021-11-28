<%-- 
    Document   : checkOut
    Created on : Oct 7, 2021, 4:09:37 PM
    Author     : Long Pham
--%>

<%@page import="longpt.discount.DiscountDTO"%>
<%@page import="longpt.discount.DiscountDAO"%>
<%@page import="longpt.product.ProductDTO"%>
<%@page import="longpt.cart.CartOBJ"%>
<%@page import="longpt.user.UserDTO"%>
<%@page import="longpt.user.UserDAO"%>
<%@page import="longpt.order.OrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta HTTP-EQUIV="REFRESH" content="5; url=SearchBookServlet">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="font/fontawesome-free-5.15.4-web/css/all.css">        
        <title>JSP Page</title>
    </head>
    <body class=" bg-cl">
        <%

            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String searchBook = request.getParameter("txtSearchBook");
            if (searchBook == null) {
                searchBook = "";
            }
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
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
            OrderDTO order = (OrderDTO) session.getAttribute("ORDER");
            UserDAO dao = new UserDAO();
            DiscountDAO disdao = new DiscountDAO();
        %>
        <div class="grid wide">
            <h1>Your Order</h1>
            <div class="row">
                <div class="col l-8" style="background-color: #fff">                
                    <%
                        float mnd = 0;
                        float total = 0;
                        float disVal = 0;
                        double subtotal = 0;
                        CartOBJ cart = (CartOBJ) session.getAttribute("CART");
                        if (cart != null) {
                            int count = 1;

                    %>
                    <table border="1" class="tbl-CheckOut">
                        <thead>
                            <tr class="checkOut-th">                               
                                <th>Stt</th>
                                <th>Product's Image</th>
                                <th>Product's Name</th>
                                <th>Product's Quantity</th>
                                <th>Product's Price</th>
                                <th>Price Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%                                for (ProductDTO pro : cart.getCart().values()) {
                            %>
                            <tr class="checkOut-th">
                                <td><%= count++%></td>
                                <td><img src="<%= pro.getImg()%>" class="checkOut-img"></td>
                                <td><%= pro.getProductName()%></td>
                                <td><%= pro.getQuantity()%></td>
                                <td><%= pro.getPrice()%>$</td>
                                <td><%= Math.round((pro.getPrice() * pro.getQuantity()) * 100.0) / 100.0%>$</td>
                            </tr>
                            <%
                                }

                            %>
                        </tbody>
                    </table>
                    <%                            mnd = order.getMoneyNotDiscount();
                            total = order.getTotal();
                        } else if (cart == null) {
                            response.sendRedirect("SearchBookServlet");
                            return;
                        }
                    %>
                    <div class="btn-continue" style="text-align: center;"><a href="SearchBookServlet">Shopping Again!</a></div>
                </div>
                <div class="col l-1 "></div>
                <div class="col l-3 checkOut-Infor" style="background-color: #fff">
                    <h1 style="padding-bottom: 20px;"><br></h1>
                    <div>Bill ID: <%= order.getOrderID()%></div>
                    <div>Customer's Name: <%= user.getFullName()%></div>
                    <div>Customer's Phone: <%= user.getPhone()%></div>
                    <div>Customer's Address: <%= user.getAddress()%></div>
                    <div class="co-bb" style="padding: 0;" ></div>
                    <div class="checkOut-Total">
                        <h4>Subtotal: <%= mnd%>$</h4>
                        <%
                            DiscountDTO discount = disdao.checkDiscount(order.getDiscountID());
                            if (discount != null) {
                                disVal = discount.getPercenValue();
                                subtotal = Math.round(((mnd * disVal) / 100) * 100.0) / 100.0;
                        %>
                        <h4>Discount: <%= disVal%>% (<%= subtotal%>$)</h4>
                        <%
                        } else {
                        %>
                        <h4>Discount: 0</h4>
                        <%
                            }
                        %>
                        <b><h1 style=" padding: 15px 0;">TOTAL: <%= total%>$</h1></b>
                    </div>
                </div>
            </div>
        </div>
        <%
            session.setAttribute("CART", null);
        %>
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
