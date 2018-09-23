package com.athompson.servlet;

import com.athompson.model.Cart;
import com.athompson.model.Product;
import com.athompson.repo.DatabaseISH;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/HomeServlet")
public class HomeServlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();

	}

	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		doGet(request, response);

	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart != null) {
			request.setAttribute("TOTAL_PRODUCTS", cart.totalProducts());
		} else {
			request.setAttribute("TOTAL_PRODUCTS", 0);
		}

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			System.out.println("COMM " + theCommand);
			String theCommandBackup = theCommand;
			System.out.println("COMMBK " + theCommandBackup);
			// rout to the appropriate method
			
			String filter = (String) session.getAttribute("filter");
			System.out.println("FILTER: "+ filter);
			
			
			
			if (theCommand == null && filter == null) {
				theCommand = "ALL";
			}
			
			if (filter!= null) {
				theCommand = filter;
			}
			
			if(theCommandBackup != null) {
				theCommand = theCommandBackup;
			}
			
			System.out.println("FINAL " + theCommand);

			switch (theCommand) {
			case "ALL":
				System.out.println(1);
				listAll(request, response);
				session.setAttribute("filter", "ALL");
				break;

			case "HORDE":
				System.out.println(2);
				listHorde(request, response);
				session.setAttribute("filter", "HORDE");
				break;

			case "ALLIANCE":
				System.out.println(3);
				listAlliance(request, response);
				session.setAttribute("filter", "ALLIANCE");
				break;

			default:
				System.out.println(4);
				listAll(request, response);
				session.setAttribute("filter", "ALL");
				break;
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Product> allProducts = DatabaseISH.getInstance().getItem("All");
		for (Product p : allProducts) {
			// System.out.println(p.getName());
		}
		request.setAttribute("ALL_PRODUCTS", allProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
		dispatcher.forward(request, response);
	}

	private void listHorde(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> allProducts = DatabaseISH.getInstance().getItem("Plushies");
		for (Product p : allProducts) {
			// System.out.println(p.getName());
		}
		request.setAttribute("ALL_PRODUCTS", allProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
		dispatcher.forward(request, response);
	}

	private void listAlliance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> allProducts = DatabaseISH.getInstance().getItem("Figures");
		for (Product p : allProducts) {
			// System.out.println(p.getName());
		}
		request.setAttribute("ALL_PRODUCTS", allProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
		dispatcher.forward(request, response);
	}
}
