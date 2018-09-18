<%--
  Created by IntelliJ IDEA.
  User: Programing
  Date: 12/09/2018
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="styles/home.css" type="text/css" />
</head>
<body>
	<!--  <div id="wrapper">
		<div id="header">
			<img src="pictures/other/treeBannerWoW.jpg" width="100%">
		</div>
	</div> -->

	<div id="wrapperMenu" align="center">
		<div id="navigation">
			<ul id="nav">
				<li><a href="HomeServlet">Home</a></li>

			</ul>
		</div>
		<div id="content" align="center">
			<form method="post" action="LoginServlet">
				<table>
					<tr>
						<td><label id="horde-label">Email</label></td>
					</tr>
					<tr>
						<td><input type="text" name="userName" id="input-horde"/></td>
					</tr>
					<tr>
						<td><label id="alliance-label">Password</label></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="input-alliance" /></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Login" id="horde-button"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>


