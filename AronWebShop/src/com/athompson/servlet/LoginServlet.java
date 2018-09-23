package com.athompson.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.*;

import com.athompson.model.User;
import com.athompson.model.UserType;
import com.athompson.repo.DatabaseISH;

import net.sourceforge.jtds.jdbc.DateTime;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseISH databaseISH = DatabaseISH.getInstance();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User loggedUser = databaseISH.getUser(userName, password);

		if (loggedUser != null) {
			if (loggedUser.getUserType() == UserType.USER) {
				HttpSession session = request.getSession();
				session.setAttribute("user", loggedUser);
				DatabaseISH.getInstance().getUser(loggedUser.getEmail(), loggedUser.getPassword())
						.addLogin(LocalDateTime.now());

				response.sendRedirect("HomeServlet");

			} else {
				response.sendRedirect("AdminServlet");
			}

		} else {
			response.sendRedirect("LoginServlet");
			System.out.println("No Such user");
		}
		;
	}

}
