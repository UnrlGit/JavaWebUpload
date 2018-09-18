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

@WebServlet(urlPatterns="/HomeServlet")
public class HomeServlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		
		
		
		
	}
	
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	doGet(request, response);


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
			
		if (cart != null) {
			request.setAttribute("TOTAL_PRODUCTS", cart.totalProducts());
		}else {
			request.setAttribute("TOTAL_PRODUCTS", 0);
		}
		
    	try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // rout to the appropriate method
            if (theCommand == null) {
                theCommand ="ALL";
            }

            switch (theCommand) {
                case "ALL":
                    listAll(request, response);
                    break;

                case "HORDE":
                    listHorde(request,response);
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
        
        List<Product> allProducts = DatabaseISH.getInstance().getItem("All");
        for (Product p : allProducts){
           // System.out.println(p.getName());
        }
        request.setAttribute("ALL_PRODUCTS", allProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
        dispatcher.forward(request, response);
    }
    private void listHorde(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	List<Product> allProducts = DatabaseISH.getInstance().getItem("Plushies");
        for (Product p : allProducts){
           // System.out.println(p.getName());
        }
        request.setAttribute("ALL_PRODUCTS", allProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
        dispatcher.forward(request, response);
    }
    private void listAlliance(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	List<Product> allProducts = DatabaseISH.getInstance().getItem("Figures");
        for (Product p : allProducts){
           // System.out.println(p.getName());
        }
        request.setAttribute("ALL_PRODUCTS", allProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homeView.jsp");
        dispatcher.forward(request, response);
    }
}
