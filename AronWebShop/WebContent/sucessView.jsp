<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
<link rel="stylesheet" href="styles/home.css" type="text/css" />
</head>
<body>
	<div id="wrapperMenu">
		<div id="navigation">
			<ul id="nav">
				<li><a href="HomeServlet">Home</a></li>

				<%
					HttpSession ses = request.getSession();
					User user = (User) ses.getAttribute("user");
					if (user != null) {

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

		<div align="center">
			<h1 id="alli">Your items will be shipped soon</h1>
		</div>




	</div>

</body>
</html>