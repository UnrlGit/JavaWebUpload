package com.athompson.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.athompson.model.Cart;
import com.athompson.model.Product;
import com.athompson.repo.DatabaseISH;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
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

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		String productIdAdd = request.getParameter("productAdd");
		String productIdReduce = request.getParameter("productReduce");
		String productIdRemove = request.getParameter("productRemove");
		if (productIdAdd != null) {
			System.out.println("Adding");
			cart.increaseProductByOne(productIdAdd);
		}
		if (productIdReduce != null) {
			System.out.println("Reducing");
			cart.reduceAmountByOne(DatabaseISH.getInstance().getProduct(productIdReduce));
		}
		if (productIdRemove != null) {
			System.out.println("Removing");
			cart.removeProduct(DatabaseISH.getInstance().getProduct(productIdRemove));
		}

		if (cart != null && cart.getProducts().size() > 0) {
			request.setAttribute("CART", cart);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cartView.jsp");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeServlet");
			dispatcher.forward(request, response);
		}

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// rout to the appropriate method
			if (theCommand == null) {
				theCommand = "ALL";
			}

			switch (theCommand) {
			case "ALL":
				listAll(request, response);
				break;

			case "HORDE":
				listHorde(request, response);
				break;

			case "ALLIANCE":
				listAlliance(request, response);
				break;

			default:
				listAll(request, response);
				break;
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	private void listHorde(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	private void listAlliance(HttpServletRequest request, HttpServletResponse response) {
	}

}
