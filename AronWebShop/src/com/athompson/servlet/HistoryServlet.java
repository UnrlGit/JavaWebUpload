package com.athompson.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.athompson.model.Cart;
import com.athompson.model.ShoppingHistory;
import com.athompson.model.User;
import com.athompson.repo.DatabaseISH;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DatabaseISH databaseISH = DatabaseISH.getInstance();

		HttpSession session = request.getSession();

		User loggedUser = (User) session.getAttribute("user");
		User dbUser = DatabaseISH.getInstance().getUser(loggedUser.getEmail(), loggedUser.getPassword());
		System.out.println(dbUser.getFirstName());
		for (ShoppingHistory history : dbUser.getShoppingHistory()) {
			System.out.println("HSERVLET " + history.getPrice());
			for (String s : history.getItemsAndAmount()) {
				System.out.println(s);
			}
		}

		// WORKAROUND
		// @SuppressWarnings("unchecked")
		// ArrayList<ShoppingHistory> shoppingHistories = (ArrayList<ShoppingHistory>)
		// session.getAttribute("SHOPPING_HISTORY");
		// if (shoppingHistories != null) {
		// for (ShoppingHistory sh : shoppingHistories) {
		// dbUser.addShoppingCart(sh);
		// System.out.println("SHOHISTORY" + sh.getCart().totalPrice());
		// }
		// }

		session.setAttribute("USER_HISTORY", dbUser.getShoppingHistory());
		// session.setAttribute("SHOPPING_HISTORY", dbUser.getShoppingHistory());

		// TODO delete this test
		// System.out.println("TESTING DATA HSERVLET");

		// User test = DatabaseISH.getInstance().getUser("buha@buhic.com", "heck123");

		// ArrayList<ShoppingHistory> carts = test.getShoppingHistory();
		// for (ShoppingHistory sh : carts) {
		// Cart w = sh.getCart();
		// System.out.println(w.totalPrice());
		// }

		RequestDispatcher dispatcher = request.getRequestDispatcher("/historyView.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
