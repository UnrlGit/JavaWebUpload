<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="stylesheet" href="styles/home.css" type="text/css" />
</head>
<body id="treebody">



	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="HomeServlet">Home</a></li>

				<c:set var="count" value="${tempLink}" scope="page" />

				<%
					HttpSession ses = request.getSession();
					User user = (User) ses.getAttribute("user");
					if (user != null) {

						out.println("<li>");
						out.println("<a href='HistoryServlet'>Shopping History</a>");
						out.println("</li>");

						out.println("<li style='float: right'>");
						out.println("<a href='LogoutServlet'>Logout</a>");
						out.println("</li>");

						out.println("<li style='float: right'>");
						out.println("Welcome, " + user.getFirstName());
						out.println("</li>");
					}

					if (user == null) {

						out.println("<li style='float: right'>");
						out.println("<a href='loginView.jsp'>Login</a>");
						out.println("</li>");
					}
				%>
			</ul>
		</div>

		<div>
			<c:set var="totalPrice" value="${CART.totalPrice()}" scope="page" />
			<h2 id="horde-label">Total cart price: ${totalPrice} $</h2>
			<%
				if (user != null) {
					out.println("<a href='CompletePaymentServlet' id='alliance-label'>Complete payment</a>");
				}
			%>
		</div>




		<div id="content">
			<form method="post" action="CartServlet">
				<table>

					<c:set var="count" value="1" scope="page" />
					<c:forEach var="tempProduct" items="${CART.getProducts()}">

						<tr>

							<td>
								<table>
									<tr>
										<td colspan="8"><img src="${tempProduct.imagePatj}"></td>
									</tr>
									<tr>
										<td><label>PRICE: ${tempProduct.price}</label></td>

										<td><label>Amount: ${tempProduct.amount}</label></td>
										<td><label>Total:
												${tempProduct.price*tempProduct.amount}</label></td>
									</tr>
									<tr>
										<td align="right"><button name="productAdd"
												value="${tempProduct.id}" type="submit">+</button></td>
										<td align="right"><button name="productReduce"
												value="${tempProduct.id}" type="submit">-</button></td>
										<td align="right"><button name="productRemove"
												value="${tempProduct.id}" type="submit">X</button></td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>

				</table>
			</form>
		</div>
	</div>

</body>