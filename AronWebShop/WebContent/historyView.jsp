<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History</title>
<link rel="stylesheet" href="styles/home.css" type="text/css" />
</head>
<body>

	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="HomeServlet">Home</a></li>
				<c:set var="count" value="${tempLink}" scope="page" />
				<li><a href="CartServlet">Cart</a></li>

				<%
						HttpSession ses = request.getSession();
						User user = (User)ses.getAttribute("user");
						if(user != null)
						{
							
							out.println("<li>");
							out.println("<a href='HistoryServlet'>Shopping History</a>");
							out.println("</li>");
							
							out.println("<li style='float: right'>");
							out.println("<a href='LogoutServlet'>Logout</a>");
							out.println("</li>");
							
							out.println("<li style='float: right'>");
							out.println("Welcome, " +user.getFirstName());
							out.println("</li>");
						}
						
						if(user == null)
						{
							
							out.println("<li style='float: right'>");
							out.println("<a href='loginView.jsp'>Login</a>");
							out.println("</li>");
							
							
						}
					
					%>

			</ul>
		</div>
	</div>
	<div id=content>
		<c:forEach var="tempItem" items="${USER_HISTORY}">



			<table border="1" id="customers2">
				<tr>
					<td colspan="2">${tempItem.shoppingDateTime}</td>
				</tr>
				<tr>
					<td>
						<table id="customers">
							<c:forEach var="product" items="${tempItem.itemsAndAmount}">
								<tr><td style="background-color: #A52A2A">${product}</td></tr>
							</c:forEach>
						</table>

					</td>
				</tr>
				<tr>
					<td>PRICE: ${tempItem.price}</td>
				</tr>
				<tr>
					<td>Payment type: ${tempItem.paymentType}</td>
				</tr>
			</table>


			<br><br>
		</c:forEach>
	</div>


</body>
</html>