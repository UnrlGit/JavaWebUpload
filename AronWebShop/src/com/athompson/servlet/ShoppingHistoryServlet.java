package com.athompson.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.athompson.model.User;
import com.athompson.repo.DatabaseISH;

/**
 * Servlet implementation class ShoppingHistoryServlet
 */
@WebServlet("/ShoppingHistoryServlet")
public class ShoppingHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userEmail = request.getParameter("userEmailSelect");
		System.out.println("SHOP HISTORY"+userEmail);
		if (userEmail != null) {
			User selectedUser = DatabaseISH.getInstance().getUserByEmail(userEmail);
			session.setAttribute("SELECTED_USER", selectedUser);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/shoppingHistoryAdminView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
