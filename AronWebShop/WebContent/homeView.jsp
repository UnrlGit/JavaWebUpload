<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="styles/home.css" type="text/css" />

</head>
<body>

	<!-- <div id="wrapper">
		<div id="header">
			<img src="pictures/other/treeBannerWoW.jpg" width="100%">
		</div>
	</div>-->

	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="HomeServlet">Home</a></li>

				<c:set var="count" value="${tempLink}" scope="page" />
				<li><a href="CartServlet">Cart(${TOTAL_PRODUCTS})</a></li>

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

		<div id="filter">

			<c:url var="tempLink" value="HomeServlet">
				<c:param name="command" value="ALL">ALL</c:param>

			</c:url>
			<a href="${tempLink}" style="class: 'agreen'"> <img height="45px"
				alt="" src="pictures/icons/all_logo.png">
			</a>

			<c:url var="tempLink" value="HomeServlet">
				<c:param name="command" value="HORDE">HORDE</c:param>

			</c:url>
			<a href="${tempLink}"> <img height="45px" alt=""
				src="pictures/icons/horde_logo.png">
			</a>

			<c:url var="tempLink" value="HomeServlet">
				<c:param name="command" value="ALLIANCE">ALLIANCE</c:param>

			</c:url>
			<a href="${tempLink}"> <img height="45px" alt=""
				src="pictures/icons/alliance_logo.png">
			</a>

		</div>




		<div id="content">
			<!--<form method="post" action="AddToCartServlet"> -->
			<table>

				<c:set var="count" value="1" scope="page" />
				<c:forEach var="tempProduct" items="${ALL_PRODUCTS}">

					<c:if test="${(count - 1 % 5) == 0}">
						<!--<c:out value="${count}"/> -->
						<tr>
					</c:if>
					<td>
						<form method="post" action="AddToCartServlet">
							<table>
								<tr>

									<td colspan="5" align="center"><div
											style="height: 250px; overflow: hidden;">
											<img src="${tempProduct.imagePatj}">
										</div></td>
								</tr>
								<tr>
									<td><label>${tempProduct.price}0$</label></td>
									<td><input type="number" name="quantity" min="1" max="20"
										value="1"></td>
									<td colspan="2" align="right"><button name="product"
											value="${tempProduct.id}" type="submit">Add to cart</button>
								</tr>
							</table>
						</form>
					</td>
					<c:if test="${count % 5 == 0}">
						<!--<c:out value="${count}"/> -->
						</tr>
					</c:if>
					<c:set var="count" value="${count + 1}" scope="page" />

				</c:forEach>

			</table>
			<!--</form>-->
		</div>
	</div>

</body>
</html>
