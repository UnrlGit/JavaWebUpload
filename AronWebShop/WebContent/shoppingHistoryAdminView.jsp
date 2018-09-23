<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/home.css" type="text/css" />
<title>Shopping History</title>
</head>
<body>



	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="AdminServlet">Login History</a></li>
				<li style="float: right"><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>

		<div class="custom-dropdown">
			<form method="post" action="ShoppingHistoryServlet">
				<select onchange="this.form.submit()" name="userEmailSelect">
					<option>--SELECT USER--</option>
					<c:forEach var="user" items="${ADMIN_USERS_LIST}">
						<option value="${user.email}">${user.firstName}
							${user.lastName}</option>
					</c:forEach>
				</select>
			</form>
		</div>


		<div id="content">
			<c:forEach var="tempItem" items="${SELECTED_USER.shoppingHistory}">
				<table border="1" id="customers" style="width: 50%">
					<tr>
						<td colspan="2">${tempItem.shoppingDateTime}</td>
					</tr>
					<tr>
						<td>
							<table id="customers2">
								<c:forEach var="product" items="${tempItem.itemsAndAmount}">
									<tr>
										<td>${product}</td>
									</tr>
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
				<br>
			</c:forEach>
		</div>

	</div>

</body>
</html>