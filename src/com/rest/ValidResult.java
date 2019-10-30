package com.rest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;

import com.validate.ComplanceValidate;
import com.validate.ComplianceRules;
import com.validate.DBConnect;
import com.validate.compilanceValidatePojo;

public class ValidResult {
	
	public HashMap<String, Boolean> getResult() {
		HashMap<String,Boolean> compMap =null;
		
		try {
			/*	System.out.println("--------------------------INVOICE--------------------------");
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
			
			ArrayList<compilanceValidatePojo> compList = new ArrayList<>();
			
			/* compilance rule from Database*/
			  ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
				Connection con = DBConnect.getConnection();
				if (con != null) {

						String query = rb.getString("RuleQuery");
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);

						while (rs.next()) {
							
							compilanceValidatePojo comp = new compilanceValidatePojo();
							
							String ruleName = rs.getString("RULE_NAME");
							ComplianceRules comRules = new ComplianceRules();
							switch (ruleName) {
							case "Check_Expiry_Date":
								String date = rs.getString("MT_700");
								SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
								Date date1 = sdf.parse(date);
								boolean result = comRules.checkExpiryGreater(date1);
								System.out.println(result);
								comp.setCompRule("The documentary credit has not expired");
								comp.setResult(result);
								comp.setComment(rs.getString("comments"));
								break;

							case "Check_POL/Dispatch/Taking_in_charge":
								String fieldMT = rs.getString("MT_700");
								String fieldLading = rs.getString("BILL_LADING");
								String fieldInsurance = rs.getString("INSURANCE");
								String fieldPackaging = rs.getString("PACKING_LIST");

								boolean result1 = comRules.validatePOL_dispatch_charge(fieldMT, fieldLading, fieldInsurance,
										fieldPackaging);
								System.out.println(result1);
								comp.setCompRule("The ports of loading / dispatch / taking in charge differ from those specified in the documentary credit");
								comp.setResult(result1);
								comp.setComment(rs.getString("comments"));
								break;

							case "Name_on_Bill_of_Lading_Validation":
								String lading = rs.getString("BILL_LADING");
								boolean result2 = comRules.checkCarrierName(lading);
								System.out.println(result2);
								comp.setCompRule("The transport documents do not identify the name of the carrier");
								comp.setResult(result2);
								comp.setComment(rs.getString("comments"));
								break;
								
							case "Future_Date_Validation":
								
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
								Date dateLading = sdf1.parse(rs.getString("BILL_LADING"));
								Date dateInvoice = sdf1.parse(rs.getString("INVOICE"));
								Date dateInsurance = sdf1.parse(rs.getString("INSURANCE"));
								
								boolean result3 = comRules.validateDate(dateLading, dateInvoice, dateInsurance);
								System.out.println(result3);
								comp.setCompRule("A document may be dated prior to the issuance date of the credit, but must not be dated later than its date of presentation.");
								comp.setResult(result3);
								comp.setComment(rs.getString("comments"));
								break;
								
							case "Shipper_Validation":
								String shipper = rs.getString("BILL_LADING");
								boolean result4 = comRules.checkShipperName(shipper);
								System.out.println(result4);
								comp.setCompRule("The bills of lading are made out to order of shipper, or to order, and not endorsed in blank");
								comp.setResult(result4);
								comp.setComment(rs.getString("comments"));
								break;
								
							case "Goods_Desc_Validation":
								boolean result5 = comRules.validateDescription(rs.getString("MT_700"), rs.getString("BILL_LADING"), rs.getString("INVOICE"), rs.getString("INSURANCE"), rs.getString("PACKING_LIST"));
								System.out.println(result5);
								comp.setCompRule("Description of goods not conflicting with the documentary credit");
								comp.setResult(result5);
								comp.setComment(rs.getString("comments"));
								break;
							}
							compList.add(comp);
						}

					
				}
			
			/* compilance rule from Database*/
		    		  
			
			System.out.println("--------------------------VALIDATE--------------------------");
			ComplanceValidate cv = new ComplanceValidate();
			compMap = new HashMap<String,Boolean>();
			compMap.put("Name Verification",cv.nameVerification("", "",""));
			compMap.put( "Insurance DateValidate",cv.insuranceDateValidate("", "", ""));
			compMap.put( "Currency Validate",cv.currencyValidate("", ""));
			compMap.put( "Name ValidateMT And Insurance",cv.nameValidateMTAndInsurance("", ""));
			compMap.put( "Currency Validate Across All",cv.currencyValidateAcrossAll("", "", "", ""));
			compMap.put( "Diff Date POP Valiadte",cv.DiffDatePOPValiadte("", "", ""));
			compMap.put( "Expiry Bank Name Check",cv.expiryBankNameCheck("", "", ""));
			compMap.put( "DiscrepancyCheck",cv.discrepancyCheck(""));
			
			
			
			compilanceValidatePojo comp = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.nameVerification("", "",""));
			comp.setComment("");
			compList.add(comp);
			
			compilanceValidatePojo comp1 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.insuranceDateValidate("", "",""));
			comp.setComment("");
			compList.add(comp1);
			
			compilanceValidatePojo comp2 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.currencyValidate("", ""));
			comp.setComment("");
			compList.add(comp2);
			
			compilanceValidatePojo comp3 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.nameValidateMTAndInsurance("", ""));
			comp.setComment("");
			compList.add(comp3);
			
			compilanceValidatePojo comp4 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.currencyValidateAcrossAll("", "","",""));
			comp.setComment("");
			compList.add(comp4);
			
			compilanceValidatePojo comp5 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.DiffDatePOPValiadte("", "",""));
			comp.setComment("");
			compList.add(comp5);
			
			compilanceValidatePojo comp6 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.expiryBankNameCheck("", "",""));
			comp.setComment("");
			compList.add(comp6);
			
			compilanceValidatePojo comp7 = new compilanceValidatePojo();
			comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
			comp.setResult(cv.discrepancyCheck(""));
			comp.setComment("");
			compList.add(comp7);
			

			
			
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		return compMap;

	}

}
