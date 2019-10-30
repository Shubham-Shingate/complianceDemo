package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rest.RestClientCall;
import com.validate.ComplanceValidate;


@WebServlet("/ValidateControllerServlet")
public class ValidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ValidateControllerServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*System.out.println("--------------------------DOGET--------------------------");
		System.out.println("--------------------------INVOICE--------------------------");
		String InvoiceDate="";
		String InvoiceAmount="";
		RestClientCall rcc = new RestClientCall();
		String filenameInvoice="commInvoice9.pdf";
		HashMap<String, String> invoiceMap =  new HashMap<>();
		String analyzerIDInvoice = rcc.getanalyzerID(filenameInvoice);
		int counter=0;
		if( counter<10) {
			counter++;			
			Thread.sleep(120000);//sleep 2 min
			System.out.println("Testing..." + new Date());	
		}
		invoiceMap = rcc.getJsonDataInvoice(analyzerIDInvoice);
		 for(Map.Entry m:invoiceMap.entrySet()){    
	           if(m.getKey().equals("Date")) {
	        	   InvoiceDate=m.getValue().toString();
	           }    
	          }  
		
		 
		 
		 
		System.out.println("--------------------------INSURANCE--------------------------");
		String InsuranceDate="";
		String InsuranceAmount="";
		String filenameInsurance="commInvoice9.pdf";
		HashMap<String, String> InsuranceMap =  new HashMap<>();
		String analyzerIDInsurance = rcc.getanalyzerID(filenameInsurance);
		counter=0;
		if( counter<10) {
			counter++;			
			Thread.sleep(120000);//sleep 2 min
			System.out.println("Testing..." + new Date());	
		}
		InsuranceMap = rcc.getJsonDataInnsurance(analyzerIDInsurance);
		
		for(Map.Entry m:InsuranceMap.entrySet()){    
			if(m.getKey().equals("Date")) {
	        	   InsuranceDate=m.getValue().toString();
	           }  
		}
		*/
		System.out.println("--------------------------VALIDATE--------------------------");
		ComplanceValidate cv = new ComplanceValidate();
		HashMap<String,Boolean> compMap = new HashMap<String,Boolean>();
		compMap.put("Name Verification",cv.nameVerification("", "",""));
		compMap.put( "Insurance DateValidate",cv.insuranceDateValidate("", "", ""));
		compMap.put( "Currency Validate",cv.currencyValidate("", ""));
		compMap.put( "Name ValidateMT And Insurance",cv.nameValidateMTAndInsurance("", ""));
		compMap.put( "Currency Validate Across All",cv.currencyValidateAcrossAll("", "", "", ""));
		compMap.put( "Diff Date POP Valiadte",cv.DiffDatePOPValiadte("", "", ""));
		compMap.put( "Expiry Bank Name Check",cv.expiryBankNameCheck("", "", ""));
		compMap.put( "DiscrepancyCheck",cv.discrepancyCheck(""));
		cv.setMap(compMap);
		HttpSession session=request.getSession();  
        session.setAttribute("compMap",compMap);  
		RequestDispatcher rd=request.getRequestDispatcher("displayCompilanceValidation.jsp");  		  
		rd.forward(request, response);
}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("doPost");
	}

}
