package com.athompson.servlet;

import java.io.IOException;
import java.util.Iterator;
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
import com.athompson.model.User;
import com.athompson.model.UserType;
import com.athompson.repo.DatabaseISH;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("product");
		// Integer id = Integer.valueOf(productId);
		// System.out.println(productId);
		Product newProduct = DatabaseISH.getInstance().getProduct(productId);
		// System.out.println("ADDING TO CART" + newProduct.getName());

		String amountOfItems = request.getParameter("quantity");
		Integer amount = Integer.valueOf(amountOfItems);
		// System.out.println("AMOUNT: " + amount);
		newProduct.setAmount(amount);
		// System.out.println("NEW PRODUCT AMOUNT: " + newProduct.getAmount());

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();

		}

		// if (cart != null) {
		boolean productExists = false;
		for (Product productFromList : cart.getProducts()) {
			if (newProduct.getName().equals(productFromList.getName())) {
				System.out.println(productFromList.getAmount());
				productFromList.setAmount(amount);
				productExists = true;
			}
		}
		if (productExists == false) {
			cart.getProducts().add(newProduct);
		}
		// }
		// else if (cart==null){
		// cart = new Cart();
		// cart.addProduct(newProduct);
		// }

		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeServlet");
		dispatcher.forward(request, response);

	}

}
