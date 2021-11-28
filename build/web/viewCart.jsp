<%-- 
    Document   : viewCart
    Created on : Oct 5, 2021, 2:04:42 PM
    Author     : Long Pham
--%>

<%@page import="longpt.dlhc.quanHuyenDTO"%>
<%@page import="java.util.List"%>
<%@page import="longpt.dlhc.dlhcDAO"%>
<%@page import="longpt.dlhc.tinhTPDTO"%>
<%@page import="longpt.discount.DiscountDTO"%>
<%@page import="longpt.product.ProductDTO"%>
<%@page import="longpt.cart.CartOBJ"%>
<%@page import="longpt.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        <div class="grid wide"> 
            <div class="row">                
                <%
                    float price = 0;
                    CartOBJ cart = (CartOBJ) session.getAttribute("CART");
                    if (cart != null && cart.getCart().size() > 0) {
                %>
                <div class="col l-12 cart-title"><p>YOUR CART:</p></div>
                <div class="col l-8 cart-left">
                    <%
                        for (ProductDTO product : cart.getCart().values()) {
                            price += product.getPrice() * product.getQuantity();
                    %>
                    <div class="row">
                        <div class="col l-12 cart-product">
                            <form action="DispatchServlet" method="POST">
                                <div class="row">
                                    <div class="col l-3 cart-img">
                                        <img src="<%= product.getImg()%>" class="cimg">
                                    </div>
                                    <div class="col l-3 div-center">     
                                        <div class="row">
                                            <div class="col l-6 pro-name"> <%= product.getProductName()%> </div>
                                            <div class="col l-6 div-center-rl">
                                                <%= product.getQuantity() * product.getPrice()%>$
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col l-4 div-center">
                                        <div class="row">
                                            <div class="col l-7" >
                                                <input class="pro-quan" type="number" name="txtQuantity" value="<%= product.getQuantity()%>" min="1"/>                                            
                                            </div>   
                                            <div class="col l-5 div-center" style="text-align: center">
                                                <input type="submit" value="Edit" name="btnAction" class="pro-edit" />
                                                <input type="hidden" name="txtProID" value="<%= product.getProductID()%>" />
                                                <input type="hidden" name="txtQuantity" value="<%= product.getQuantity()%>" />
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col l-2 div-center"> 
                                        <div class="div-center-rl">
                                            <!--                                            <input type="submit" value="Remove" name="btnAction" class="pro-remove" />-->
                                            <a href="DispatchServlet?btnAction=Remove&txtProID=<%= product.getProductID()%>" class="pro-remove">
                                                <i class="far fa-trash-alt remove-icon"></i>
                                            </a>
                                            <input type="hidden" name="txtProID" value="<%= product.getProductID()%>" />
                                        </div>

                                    </div>
                                </div>
                                <div class="bdbt"></div>
                            </form>

                        </div>                        
                    </div>

                    <%                    }
                        String fmt = String.format("%.2f", price);
                        float value = 0;
                        float total = 0;
                    %> 
                    <div class="col l-12 div-center-rl btn-continue">
                        <a href="SearchBookServlet">Continue to selecting store</a>
                    </div>
                </div>

                <div class="col l-1">                   
                </div>
                <div class="col l-3 cart-right">
                    <div class="row">
                        <div class="col l-12 cart-add">
                            <%
                                if (user != null) {
                            %>

                            <div class="row">
                                <div class="col l-8 cart-p"><p>Giao tới</p></div>
                                <div class="col l-4 cart-p" style="text-align: end;">
                                    <a href="#" style="text-decoration: none; color: #0D5CB6;" class="js-buy-ticket">Update</a>
                                    <!--<button class="js-update-btn">Update</button>-->
                                </div>
                                <div class="col l-12">
                                    <div class="row">
                                        <div class="col l-6 cart-p"><%= user.getFullName()%></div>
                                        <div class="col l-6 cart-p" style="border-left: 1px solid;"><%= user.getPhone()%>
                                        </div>
                                    </div>
                                </div>
                                <div class="col l-12 cart-p" style="text-align: justify;">
                                    <%= user.getAddress()%>
                                </div>
                            </div>                   


                            <%
                                }
                            %>
                        </div>

                        <div class="col l-12 cart-discount">
                            <div class="discount-title">
                                <p>Nhập Khuyến mãi:</p>
                            </div>
                            <div class="discount-form">
                                <%
                                    String discountID = request.getParameter("txtDiscount");
                                    if (discountID == null) {
                                        discountID = "";
                                    }
                                %>
                                <form action="DispatchServlet" method="POST">
                                    <div class="row no-gutters">
                                        <div class="col l-8 discount-input">
                                            <input type="text" name="txtDiscount" value="<%= discountID%>" class="dip"/>
                                        </div>       
                                        <input type="hidden" name="txtUserID" value=" <%= user.getUserID()%>" />
                                        <div class="col l-4 discount-btn">
                                            <input type="submit" value="Apply" name="btnAction" class="dbtn"/>
                                        </div>
                                        <%
                                            DiscountDTO discount = (DiscountDTO) session.getAttribute("DISCOUNT");
                                            String disUsrID = (String) session.getAttribute("DISCOUNT_USERID");
                                            String msg = (String) session.getAttribute("MSG");
                                            String dcErr = (String) session.getAttribute("DC-ERR");
                                            if (discount != null) {
                                                if (disUsrID == user.getUserID()) {
                                        %>
                                        <p class="col l-12 discount-msg"><%= msg%></p>
                                        <%
                                        } else if (disUsrID != user.getUserID()) {
                                            value = discount.getPercenValue();
                                        %>
                                        <p class="col l-12 discount-msg">Bạn được giảm <%= value%>% tổng hoá đơn.</p>
                                        <%
                                            }
                                        } else if (discount == null && discountID.length() > 0) {
                                        %>
                                        <p class="col l-12 discount-msg"><%= dcErr%></p>
                                        <%
                                            }
                                        %>

                                    </div>

                                </form>
                            </div>

                        </div>
                        <%
                            total = price - (price * value) / 100;
                            double subValue = Math.round(((price * value) / 100) * 100.0) / 100.0;
                        %>
                        <div class="col l-12 cart-money">
                            <div class="row">
                                <div class="col l-9 money-title"><p>Tạm tính:</p></div>
                                <div class="col l-3 money-number"><p><%= fmt%>$</p></div>
                                <div class="col l-9 money-title">
                                    <div class="row">
                                        <div class="col l-5"><p>Giảm giá:</p></div>
                                        <div class="col l-7"><p><%= value%>%</p></div>

                                    </div>
                                </div>
                                <div class="col l-3 money-number"><p><%= subValue%>$</p></div>
                                <div class="col l-12 bdbt"></div>
                                <div class="col l-9 money-title" style="text-transform: uppercase;font-weight: 600;font-size: 20px;">
                                    <p>Tổng cộng:</p>
                                </div>
                                <div class="col l-3 money-number" style="font-weight: 600;font-size: 20px;"><p><%= String.format("%.2f", total)%>$</p></div>

                            </div>
                        </div>
                        <div class="col l-12">
                            <form action="DispatchServlet" method="POST">
                                <div class="checkOut-btn">
                                    <input type="submit" value="Check Out" name="btnAction" class="cbtn" />
                                </div>

                                <input type="hidden" name="txtTotal" value="<%= String.format("%.2f", total)%>" />
                                <input type="hidden" name="txtDiscount" value="<%= discountID%>" />
                                <input type="hidden" name="txtUserID" value=" <%= user.getUserID()%>" />
                                <%
                                    session.setAttribute("TOTAL", total);
                                    session.setAttribute("MND", price);
                                %>
                            </form>
                        </div>

                    </div>
                </div>
                <%
                } else {
                %>
                <div class="col l-12 cart-middle">
                    <div class="middle-empty">
                        <div class="middle-img">
                            <img src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="Your cart is empty!!!"/>
                        </div>
                        <div class="middle-cmt">
                            <p>There are no products in your shopping cart.</p>
                        </div>
                        <div class="middle-home">
                            <a href="DispatchServlet?btnAction=Search Book">Shopping Now</a>
                        </div>
                    </div>
                </div>
                <%
                        session.setAttribute("DISCOUNT", null);
                        session.setAttribute("DISCOUNT_USERID", null);
                    }
                %>
            </div>
        </div>
        <div class="modal js-modal">
            <div class="modal-container js-modal-container">
                <div class="modal-close js-modal-close"><i class="fas fa-times"></i></div>
                <div class="modal-header">
                    <i class="fas fa-user-edit  header-icon"></i>
                    Change Address
                </div> 
                <form action="UpdateUserServlet" method="POST">
                    <div class="modal-body">
                        <!--                                                <label for="name-modal" class="modal-label">
                                                                            Your Name: 
                                                                        </label>
                                                                        <input name="txtName" id="name-modal" type="text" class="modal-input" placeholder="Your name?"/>-->
                        <label for="tel-modal" class="modal-label">
                            Your Number: 
                        </label>
                        <input id="txtPhoneInput" name="txtPhoneInput" id="tel-modal" type="tel" class="modal-input" placeholder="Your number?"/>
                        <label for="cbb" class="modal-label">
                            Your Address: 
                        </label>
                        <input id="txtAddresInput" type="text" name="txtAddresInput" value="" class="modal-input" placeholder="Specific address..."/>
                        <select name="country" id="country" class="modal-cbb">
                            <option value="">Select country</option>
                        </select>
                        <select id="state" name="state" class="modal-cbb">
                            <option>Select Provice</option>                         
                            <option value=""></option>                          
                        </select>
                        <select name="city" id="city" class="modal-cbb">
                            <option value="">Select City</option>                           
                        </select>
                        <select name="ward" id="ward" class="modal-cbb">
                            <option>Select Ward</option>
                        </select>
                        <button id="update-btn" value="Update User" name="btnAction">
                            Update <i class="fas fa-check update-icon"></i>
                        </button>
                        <input type="hidden" name="txtUserID" value="<%= user.getUserID()%>" />

                        <!--<input type="submit" value="Update User" name="btnAction" />-->
                    </div>
                </form>
                <footer class="modal-footer">
                    <p class="modal-help">
                        Need <a href="" class="help-a">help?</a>
                    </p>
                </footer>
            </div>

        </div>

        <script>
            const buyBtns = document.querySelectorAll('.js-buy-ticket');
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
            for (const buyBtn of buyBtns) {
                buyBtn.addEventListener('click', showBuyTicket);
            }
            //nghe hành vi click vào button close
            modalClose.addEventListener('click', hideBuyTicket);
            modal.addEventListener('click', hideBuyTicket);
            modalContainer.addEventListener('click', function (event) {
                event.stopPropagation();
            });
        </script>
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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#update-btn").on("click", function () {
                var country_id = $("#country").val();
                var state_id = $("#state").val();
                var city_name = $("#city").val();
                var ward_name = $("#ward").val();
                var add_name = $("#txtAddresInput").val();
                var phone_input = $("#txtPhoneInput").val();
                if (country_id === "" || country_id === null) {
                    $("#error").html("All fields are mandatory.");
                } else {
                    $("#error").html("");
                    $.ajax({
                        url: "UpdateUserServlet",
                        method: "GET",
                        data: {country_id: country_id, state_id: state_id, city_name: city_name, ward_name: ward_name, add_name: add_name, phone_input: phone_input},
                        success: function (data)
                        {
                            $("#update-btn").html(data);
                            $("#UpdateUserServlet").trigger("reset");
                        }
                    });
                }

            });
        });
    </script>
</html>
<script>
    $(document).ready(function () {
        load_json_data('country');
        function load_json_data(id, parent_id)
        {
            var html_code = '';
            $.getJSON('country_state_city.json', function (data) {

                html_code += '<option value="">Select ' + id + '</option>';
                $.each(data, function (key, value) {
                    if (id === 'country')
                    {
                        if (value.parent_id === '0')
                        {
                            html_code += '<option value="' + value.id + '">' + value.name + '</option>';
                        }
                    } else
                    {
                        if (value.parent_id === parent_id)
                        {
                            html_code += '<option value="' + value.id + '">' + value.name + '</option>';
                        }
                    }
                });
                $('#' + id).html(html_code);
            });

        }

        $(document).on('change', '#country', function () {
            var country_id = $(this).val();
            if (country_id !== '')
            {
                load_json_data('state', country_id);
            } else
            {
                $('#state').html('<option value="">Select state</option>');
                $('#city').html('<option value="">Select city</option>');
            }
        });
        $(document).on('change', '#state', function () {
            var state_id = $(this).val();
            if (state_id !== '')
            {
                load_json_data('city', state_id);
            } else
            {
                $('#city').html('<option value="">Select city</option>');
            }
        });
        $(document).on('change', '#city', function () {
            var city_id = $(this).val();
            if (city_id !== '')
            {
                load_json_data('ward', city_id);
            } else
            {
                $('#ward').html('<option value="">Select city</option>');
            }
        });
    });
</script>