<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="heading" value="Home"/>
<%@ include file="header.jsp" %>
<!--
	This is the Home page for Customer Representative
	This page contains various buttons to navigate the online auction house
	This page contains customer representative specific accessible buttons
-->

<style type="text/css">
    body {
        background-color:#F0F8FF;
    }
    .card{
        margin:10px 0;
    }
</style>

<div class="container">
    <h2>Customer Representative Options:</h2>
    <%
        String email = (String)session.getAttribute("email");
        String role = (String)session.getAttribute("role");
        //redirect to appropriate home page if already logged in
        if(email != null) {
            if(role.equals("manager")) {
                response.sendRedirect("managerHome.jsp");
            }
            else if(!role.equals("customerRepresentative")) {
                response.sendRedirect("home.jsp");
            }
        }
        else {
            // redirect to log in if not alreaddy logged in
            response.sendRedirect("index.jsp");
        }
    %>

<%--    <div class="row">--%>
<%--        <div class="col">--%>
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Record order</h5>
                <div class="container">
                    <form action="viewAddCustomerOrder">
                        <button type="submit" class="btn btn-outline-info"><i class="fas fa-folder-plus mr-2"></i>Record order</button>
                    <%--                        <input type="submit" value="Record order" class="btn btn-outline-info"/>--%>
                    </form>
                </div>
              </div>
            </div>
<%--        </div>--%>
<%--        <div class="col">--%>
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Manage Customer</h5>
                <div class="container">
                    <form action="viewAddCustomer.jsp">
                        <button type="submit" class="btn btn-outline-info"><i class="fas fa-paperclip mr-2"></i>Add Customer</button>
                    <%--                        <input type="submit" value="Add Customer" class="btn btn-outline-info"/>--%>
                    </form>
                    <form action="getCustomers" class="pt-1">
                        <button type="submit" class="btn btn-outline-info"><i class="far fa-edit mr-2"></i>View / Edit / Delete Customer</button>
                    <%--                        <input type="submit" value="View / Edit / Delete Customer" class="btn btn-outline-info"/>--%>
                    </form>

                </div>
              </div>
            </div>
<%--        </div>--%>
<%--        <div class="col">--%>
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Other</h5>
                <div class="container">
                    <form action="getCustomerMailingList">
                        <button type="submit" class="btn btn-outline-info"><i class="far fa-address-book mr-2"></i>Customer Mailing List</button>
<%--                        <input type="submit" value="Customer Mailing List" class="btn btn-outline-info"/>--%>
                    </form>

                    <form action="viewCustomerStockSuggestions" class="pt-1">
                        <button type="submit" class="btn btn-outline-info"><i class="fas fa-glasses mr-2"></i>View Suggestions</button>
<%--                        <input type="submit" value="View Suggestions" class="btn btn-outline-info"/>--%>
                    </form>
                </div>
              </div>
<%--            </div>--%>
<%--        </div>--%>
</div>
<%@ include file="footer.jsp" %>