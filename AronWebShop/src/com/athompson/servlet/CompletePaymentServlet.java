package com.athompson.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.athompson.model.Cart;
import com.athompson.model.PaymentType;
import com.athompson.model.Product;
import com.athompson.model.ShoppingHistory;
import com.athompson.model.User;
import com.athompson.repo.DatabaseISH;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 * Servlet implementation class CompletePaymentServlet
 */
@WebServlet("/CompletePaymentServlet")
public class CompletePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompletePaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/completePaymentView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cash = request.getParameter("Cash");
		String ppal = request.getParameter("PayPal");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");

		if (cash != null) {
			LocalDateTime shoppingTime = LocalDateTime.now();
			ShoppingHistory newShopping = new ShoppingHistory(cart, shoppingTime, PaymentType.CASH);
			newShopping.setPrice(cart.totalPrice());
			for (Product item : cart.getProducts()) {
				newShopping.addItemAndAmount(item.getName() + " " + item.getPrice() + "$  (x" + item.getAmount() + ")");
			}

			DatabaseISH.getInstance().addShopping(user, newShopping);
			// WORKAROUND
			// @SuppressWarnings("unchecked")
			// ArrayList<ShoppingHistory> shoppingHistories = (ArrayList<ShoppingHistory>)
			// session.getAttribute("SHOPPING_HISTORY");
			// if (shoppingHistories != null) {
			// shoppingHistories.add(newShopping);
			// }else {
			// shoppingHistories = new ArrayList<>();
			// shoppingHistories.add(newShopping);
			// }
			// session.setAttribute("SHOPPING_HISTORY", shoppingHistories);

			// for (ShoppingHistory shoppingHistory : shoppingHistories) {
			// System.out.println("CURRENT PAYMENT: "
			// +shoppingHistory.getCart().totalPrice());
			// }

			cart.setProducts(new ArrayList<>());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SucessServlet");
			dispatcher.forward(request, response);
		}

		if (ppal != null) {
			LocalDateTime shoppingTime = LocalDateTime.now();
			ShoppingHistory newShopping = new ShoppingHistory(cart, shoppingTime, PaymentType.PAYPAL);
			newShopping.setPrice(cart.totalPrice());
			for (Product item : cart.getProducts()) {
				newShopping.addItemAndAmount(item.getName() + " " + item.getPrice() + "$  (x" + item.getAmount() + ")");
			}

			DatabaseISH.getInstance().addShopping(user, newShopping);

			cart.setProducts(new ArrayList<>());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SucessServlet");
			dispatcher.forward(request, response);
		}

		if (cash == null && ppal == null) {
			doGet(request, response);
		}

	}

}
