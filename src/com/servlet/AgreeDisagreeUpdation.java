package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axis.compliance.MoreInfoDAO;

/**
 * Servlet implementation class AgreeDisagreeUpdation
 */
@WebServlet("/AgreeDisagreeUpdation")
public class AgreeDisagreeUpdation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AgreeDisagreeUpdation() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try {
		System.out.println("===========================in servlet================================");
		String rule = request.getParameter("rule");
		String transactionId = request.getParameter("transactionId");
		String categoryId = request.getParameter("categoryId");
		String status = request.getParameter("status");
		String subject = request.getParameter("subject");
		String comment = request.getParameter("comment");

		System.out.println(rule);
		System.out.println(transactionId);
		System.out.println(categoryId);
		System.out.println(status);
		System.out.println(subject);
		System.out.println(comment);
		
		MoreInfoDAO mid = new MoreInfoDAO();
		String result  = mid.UpdateResult(rule, transactionId, categoryId, status, comment, subject);
		System.out.println(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
