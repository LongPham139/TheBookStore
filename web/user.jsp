<%-- 
    Document   : user
    Created on : Sep 27, 2021, 4:36:07 PM
    Author     : Long Pham
--%>

<%@page import="longpt.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || user.getRoleID().equals("admin")) {
                response.sendRedirect("login.jsp");
                return;
            }

        %>
        <h1>Hello User: <%= user.getFullName()%></h1><a href="DispatchServlet?btnAction=LogOut">Log Out</a>
    </body>
</html>
