<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="heading" value="Home"/>
<%@ include file="header.jsp" %>

<style type="text/css">
body {
  background-color:#F0F8FF;
}
.logo {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.container{
	width: 30%;
}
h5{
	color: #1B4965;
}
</style>
<div class="container" style = "width:30%!important;">
            <img src="Images/giphy.gif" alt="Stock-go-go" class="logo" style = "width:320px;height:280px;">
			<h5>Login</h5>
			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to home page if already logged in
				if(email != null) {
					if(role.equals("manager")) {
						response.sendRedirect("managerHome.jsp");
					}
					else if(role.equals("customerRepresentative")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else {
						response.sendRedirect("home.jsp");	
					}
				}
				
				String status = request.getParameter("status");
				if(status != null) {
					if(status.equals("false")) {
						out.print("Incorrect Login credentials!");
					}
					else {
						out.print("Some error occurred! Please try again.");
					}
				}
			%>
			<form action="login">
				<div class="form-group">
					<input type="email" class="form-control" name="username" placeholder="Username" style="width:350px;">
				</div>
				<div class="form-group">
	            	<input type="password" class="form-control" name="password" placeholder="Password" style="width:350px;">
	        	</div>
				<div class="form-group">
					<select class="form-control" name="role" style="width:350px;">
                        <option value="customer">Customer</option>
                        <option value="manager">Manager</option>
                        <option value="customerRepresentative">Customer Representative</option>
					</select>
				</div>
				<input type="submit" value="Login" class="btn btn-info"/>
			</form>
		</div>
<%@ include file="footer.jsp" %>
