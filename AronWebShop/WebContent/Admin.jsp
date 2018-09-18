<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/home.css" type="text/css" />
<title>Admin</title>
</head>
<body>
	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="ShoppingHistoryServlet">Shopping History</a></li>
				<li style="float: right"><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>



		<div id="content">
			<form method="post" action="AddToCartServlet">
				<table border="1">
					<c:forEach var="user" items="${ADMIN_USERS_LIST}">
						<tr>
							<td>
								<table id="customers">
									<tr style="background-color: #A52A2A">
										<td>${user.firstName}</td>
									</tr>
									<tr><td>
										<table id="customers2">
											<c:forEach var="loginTimes" items="${user.logins}">
												<tr>
													<td>${loginTimes}</td>
												</tr>
											</c:forEach>
											
										</table></td>
									</tr>
								</table>
								<br><br>
							</td>
						</tr>
						
					</c:forEach>
				</table>
			</form>
		</div>
	</div>

</body>
</html>