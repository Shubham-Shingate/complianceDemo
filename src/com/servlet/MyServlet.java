package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/welcome")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET Method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			/*
			 * System.out.println("My servlet"); ServletContext sc =
			 * this.getServletContext();
			 * System.out.println("created object of ServletContext"); RequestDispatcher rd
			 * = sc.getRequestDispatcher("/jsp/test.jsp");
			 * System.out.println("After request Dispatcher"); rd.forward(request,
			 * response); System.out.println("end");
			 */
			System.out.println("POST method");
			RequestDispatcher requestDispatcher;
			System.out.println("created object of RequestDispatcher");
			requestDispatcher = request.getRequestDispatcher("/demo.jsp");
			System.out.println("after redirect");
			requestDispatcher.forward(request, response);
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
