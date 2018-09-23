<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.athompson.model.User"%>
<html>
<head>
<title>Complete payment</title>
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

		<div id="content">
			<form method="post" action="CompletePaymentServlet">
				<table>
					<tr>
						<td><input type="submit" name="Cash" value="Cash" id="horde-button"/></td>
					</tr>
					<tr>
						<td><input type="submit" name="PayPal" value="PayPal" id="alliance-button"/></td>
					</tr>
				</table>
			</form>
		</div>

		<div id="paypal-button"></div>
		<script src="https://www.paypalobjects.com/api/checkout.js"></script>
		<script>
			paypal.Button.render({
				// Configure environment
				env : 'sandbox',
				client : {
					sandbox : 'ARw0_uKF7uwJa1FScef-g_kJG57xZQoGeKrxnBnoixBuXyM6GAU8AaWNaUqnE7MbaXyHU3O4vla09hwb',
					production : 'demo_production_client_id'
				},
				// Customize button (optional)
				locale : 'en_US',
				style : {
					size : 'small',
					color : 'gold',
					shape : 'pill',
				},
				// Set up a payment
				payment : function(data, actions) {
					return actions.payment.create({
						transactions : [ {
							amount : {
								total : '120.00',
								currency : 'USD'
							}
						} ]
					});
				},
				// Execute the payment
				onAuthorize : function(data, actions) {
					return actions.payment.execute().then(function() {
						// Show a confirmation message to the buyer
						window.alert('Thank you for your purchase!');
						document.getElementById("alliance-button").click();
					});
				}
			}, '#paypal-button');
		</script>



	</div>

</body>
</html>
