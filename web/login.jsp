<%-- 
    Document   : login
    Created on : Sep 27, 2021, 10:45:55 AM
    Author     : Long Pham
--%>

<%@page import="longpt.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="font/fontawesome-free-5.15.4-web/css/all.css">
        <link rel="stylesheet" href="js/login.js">
        <title>JSP Page</title>       
    </head>
    <%
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
//            if(user == null){
//                response.sendRedirect("login.jsp");
//                return;
//            }
        } catch (Exception e) {
            out.println(e);
        }
        if (user != null && user.getRoleID().equals("admin")) {
            response.sendRedirect("SearchUserServlet");
            return;
        }
        if (user != null && user.getRoleID().equals("user")) {
            response.sendRedirect("SearchBookServlet");
            return;
        }

        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <body id="particles-js" onload="disableBack()">
        <!--        <div>
                      <h1>Login Page!</h1>
                      <div id="status" style="color: red">
                      </div>
          
                      <form action="DispatchServlet" method="POST">
                          <div>UserID: <input type="text" name="txtUserID" value="" /></div>
                          <div>Password: <input type="password" name="txtPassword" value="" /></div>
                          <div><input type="submit" value="Login" name="btnAction" /></div>
                          <p style="color: red"><%= error%></p>
                          <p>Hoặc bạn có thể đăng nhập bằng </p>
                          <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
                          </fb:login-button>
                      </form>
                  </div>-->
        <div class="animated bounceInDown">
            <div class="container">
                <span class="error animated tada" id="msg"></span>
                <form action="DispatchServlet" method="POST" name="form1" class="box" onsubmit="return checkStuff()">
                    <h4>Login<span>Dashboard</span></h4>
                    <h5>Sign in to your account.</h5>
                    <%
                        if (error != null) {
                    %>
                    <span style="color: red; background-color: #fff;"><%= error%></span>
                    <%
                        }
                    %>
                    <input type="text" name="txtUserID" placeholder="User ID" value="">
                    <i class="typcn typcn-eye" id="eye"></i>
                    <input type="password" name="txtPassword" placeholder="Passsword" id="pwd">
                    <label>
                        <input type="checkbox">
                        <span></span>
                        <small class="rmb">Remember me</small>
                    </label>
                    <a href="#" class="forgetpass">Forget Password?</a>
                    <input type="submit" value="Login" name="btnAction" class="btn1" onclick="errorMsg"><br>
                    <p class="fb-text">Hoặc bạn có thể đăng nhập bằng </p>
                    <div class="fb-login" >
                        <fb:login-button scope="public_profile,email" onlogin="checkLoginState();" >
                        </fb:login-button>
                    </div>

                </form>
                <a href="#" class="dnthave">Don’t have an account? Sign up</a>
            </div> 
            <div class="footer">
                <span>Made with <i class="fa fa-heart pulse"></i> <a href="https://www.google.de/maps/place/Augsburger+Puppenkiste/@48.360357,10.903245,17z/data=!3m1!4b1!4m2!3m1!1s0x479e98006610a511:0x73ac6b9f80c4048f"><a href="https://codepen.io/lordgamer2354">By Long Pham.</a></span>
            </div>
        </div>
        <script>
            // This is called with the results from from FB.getLoginStatus().
            function statusChangeCallback(response) {
                console.log('statusChangeCallback');
                console.log(response);
                console.log(response.authResponse.accessToken);
                //alert(response.authResponse.accessToken);
                if (response.status === 'connected') {
                    window.location.href = 'LoginByFaceServlet?access_token=' + response.authResponse.accessToken;
                } else {
                    // The person is not logged into your app or we are unable to tell.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into this app.';
                }
            }
            // This function is called when someone finishes with the Login
            // Button. See the onlogin handler attached to it in the sample
            // code below.
            function checkLoginState() {
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });
            }
            window.fbAsyncInit = function () {
                FB.init({
                    appId: '903207520304958',
                    cookie: true, // enable cookies to allow the server to access 
                    // the session
                    xfbml: true, // parse social plugins on this page
                    version: 'v12.0' // use graph api version 2.8
                });
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });
            };
            // Load the SDK asynchronously
            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "https://connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
            // Here we run a very simple test of the Graph API after login is
            // successful. See statusChangeCallback() for when this call is made.
        </script>
        <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>   

    </body>

</html>
