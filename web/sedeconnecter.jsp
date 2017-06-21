<%-- 
    Document   : sedeconnecter
    Created on : 30 mai 2017, 01:33:40
    Author     : Taleb
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    
   
    request.getServletContext().removeAttribute("current-user");

    RequestDispatcher requestDispatcher = (RequestDispatcher) request.getRequestDispatcher("index.jsp");
    requestDispatcher.forward(request, response);
%>
