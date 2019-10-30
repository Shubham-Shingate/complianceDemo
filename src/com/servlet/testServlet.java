package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axis.compliance.ComplianceRules;
import com.axis.compliance.DBConnect;
import com.axis.compliance.MoreInfoDAO;
import com.validate.compilanceValidatePojo;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			System.out.println("===================================IN SERVLET===========================================");
			String valuePass = request.getParameter("id");
			System.out.println("valuePass ::::::::"+valuePass);
			valuePass =valuePass.replace("No of Original and copy","No of Original & copy");//replaces all occurrences of 'a' to 'e'  

		    String[] arrSplit = valuePass.split("::::");
			System.out.println("arrSplit[0] ::::::::"+arrSplit[0]);
			System.out.println("arrSplit[1] ::::::::"+arrSplit[1]);
			System.out.println("arrSplit[2] ::::::::"+arrSplit[2]);
			System.out.println("arrSplit[3] ::::::::"+arrSplit[3]);

			String transactionId =arrSplit[1];
			String ruleName =arrSplit[0];
			String categoryId =arrSplit[2];
			String status =arrSplit[3];

			//String categoryId="DCC19";

			MoreInfoDAO md = new MoreInfoDAO();
			LinkedHashMap<String, String> compList = md.getResultInfo(ruleName, transactionId, categoryId,status);
			//LinkedHashMap<String, String> compList = md.getData(ruleName, transactionId);

			String rule = compList.get("RULE_DESC");
			compList.remove("RULE_DESC");
		   PrintWriter writer = response.getWriter();
		   
		   
	       String htmlRespone = "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js'></script><script>";
	       htmlRespone += "$(document).ready(function(){";
	       htmlRespone += "$('.changeStatus').click(function(){";
	       htmlRespone += "var a = this.id;";
	       htmlRespone += "alert(a);";
	       htmlRespone += " $('#subject').val(a);";
	       htmlRespone += " $('#comment').val('');";
	       htmlRespone += "$('#hiddenBox').show();";
	       htmlRespone += "});";
	       
	       htmlRespone += "$('#SaveChanges').click(function(){";
	       htmlRespone += "var rule = $('#ruleDis').val();";
	       htmlRespone += "var trn = $('#transactionId').val();";
	       htmlRespone += "var cat = $('#categoryId').val();";
	       htmlRespone += "var status = $('#status').val();";
	       htmlRespone += "var subject =  $('#subject').val();";
	       htmlRespone += "var comment =  $('#comment').val();";

	       htmlRespone += "$.ajax({";
	       htmlRespone += "type: 'post',";
	       htmlRespone += "url: 'AgreeDisagreeUpdation', ";
	       htmlRespone += " data:{";
	       htmlRespone += " 'rule': rule,";
	       htmlRespone += " 'transactionId': trn,";
	       htmlRespone += " 'categoryId': cat,";
	       htmlRespone += " 'status': status,";
	       htmlRespone += " 'subject': subject,";
	       htmlRespone += " 'comment': comment,";
	       htmlRespone += " },";

	       htmlRespone += "success: function(msg){ ";
	       htmlRespone += "$('#hiddenBox').hide();";
	       htmlRespone += "}";//ajax result
	       htmlRespone += "});";//ajax 
	       htmlRespone += "});";
	       htmlRespone += "});";
	       htmlRespone += "</script>";
	 	   htmlRespone += "<input type='hidden' id='ruleDis' value='"+rule+"'>"  ;  
	 	   htmlRespone += "<input type='hidden' id='transactionId' value='"+transactionId+"'>"  ;  
	 	   htmlRespone += "<input type='hidden' id='categoryId' value='"+categoryId+"'>"  ;  
	 	   htmlRespone += "<input type='hidden' id='status' value='"+status+"'>"  ;  

	       
	       htmlRespone += "<div class='DCC_Fail_Document_Details' id='DCC_Fail_Document_Details'>";
	       htmlRespone += "<div class='modal-dialog custom_modalsuccessmsg modal-lg modal-sm modal-md modal-xs' id='custom_modalsuccessmsg'>";
	            htmlRespone += "<div class='modal-content'>";
	               htmlRespone += "<div class='modal-header'>";
	                  htmlRespone += "<button type='button' onclick=\"document.getElementById('DCC_Fail_Document_Details').remove()\" class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>";
	                  htmlRespone += "<h4 class='modal-title' id='myModalLabel'>Failed Document Details</h4>";
	               htmlRespone += "</div>";
	               htmlRespone += "<div class='modal-body'>";
	                  htmlRespone += "<p><b>RULE : </b>"+rule+"</p>";
	                  htmlRespone += "<div class='table-responsive'>";
	                     htmlRespone += "<table class='table table-bordered table-hover'>";
	                        htmlRespone += "<thead>";
	                        int count=0;
	                        int baseCount=0;
	                        String baseValue="";
            				String applyClass="";
            				int mapCount = compList.size();
            				if(status.equalsIgnoreCase("false")) {
		            				if(mapCount>0) {
				            			for(Entry<String, String> m:compList.entrySet()){
				            		//	System.out.println(mapCount+" mapCount");	
				            			if(mapCount == 1) {
			            					applyClass="danger text-danger";
			
				            			}
				            			else {
					            				if(m.getKey().equals("MT700") && baseCount==0) {
					            					baseValue=m.getValue();
					            					baseCount++;
					            					count++;
					            				}
					            				else if (count==0)
					            				{
					            					baseValue=m.getValue();
					            					count++;
					            				}
					            				if(m.getValue().equals(baseValue)) {
					            					applyClass="success text-success";
					            				}
					            				else {
					            					applyClass="danger text-danger";
					            				}
				            			   }
					                           htmlRespone += "<tr>";
					                              htmlRespone += "<th>"+m.getKey()+"</th>";
					                              htmlRespone += "<td class='"+applyClass+"'>"+m.getValue()+"</td>";
					                           htmlRespone += "</tr>";
				            			   }
			            			
		            				}
		            				else {
		            					applyClass="danger text-danger";
		
		            					 htmlRespone += "<tr>";
			                              htmlRespone += "<th>Name not found</th>";
			                              htmlRespone += "<td class='"+applyClass+"'>Name not found</td>";
			                           htmlRespone += "</tr>";
		            				}
            			}
            			
            				else {
		            			for(Entry<String, String> m:compList.entrySet()){

            					applyClass="success text-success";
            					
           					 htmlRespone += "<tr>";
           					 htmlRespone += "<th>"+m.getKey()+"</th>";
                             htmlRespone += "<td class='"+applyClass+"'>"+m.getValue()+"</td>";
	                           htmlRespone += "</tr>";
		            			}
            				}
	            			
	                        htmlRespone += "</thead>";
	                        htmlRespone += "<tbody>";
	                        htmlRespone += "</tbody>";
	                     htmlRespone += "</table>";
	                     htmlRespone += "<div style='display:none;' id='hiddenBox'>";
	                     htmlRespone += " <input type='text' id='subject' name='subject'>";
	                     htmlRespone += "<textarea  id ='comment' name='tarea'  cols='30' rows='4'></textarea>";
		                  htmlRespone += " <a href='#' id='SaveChanges' class='btn btn-primary'>Save </a>";
	                     htmlRespone +="</div>";
	                  htmlRespone += "</div>";
	               htmlRespone += "</div>";
	               htmlRespone += "<div class='modal-footer'>";
	                  htmlRespone += " <a href='#' id='Disagree' class='changeStatus btn btn-primary'>Disagree </a>";
	                 htmlRespone += " <a href='#' id='Agree' class='changeStatus btn btn-primary'>Agree </a>";
	                  htmlRespone += "<button type='button' class='btn btn-default closemodal'  onclick=\"document.getElementById('DCC_Fail_Document_Details').remove()\"  id='colsethis'  data-dismiss='modal'>Close</button>";
	               htmlRespone += "</div>";
	            htmlRespone += "</div>";
	         htmlRespone += "</div>";
	         htmlRespone += "</div>";
			
	        writer.println(htmlRespone);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
















