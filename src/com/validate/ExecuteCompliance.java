package com.validate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExecuteCompliance {

	private static ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	
	public ArrayList<compilanceValidatePojo> getResultList(){
		ArrayList<compilanceValidatePojo> compList = new ArrayList<>();

		try {
			Connection con = DBConnect.getConnection();
			if (con != null) {
				try {

					String query = rb.getString("RuleQuery");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			//		System.out.println("Statt validation @@@@@@@@@@@@");


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
				//			System.out.println(result);
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
				//			System.out.println(result1);
							comp.setCompRule("The ports of loading / dispatch / taking in charge differ from those specified in the documentary credit");
							comp.setResult(result1);
							comp.setComment(rs.getString("comments"));
							break;

						case "Name_on_Bill_of_Lading_Validation":
							String lading = rs.getString("BILL_LADING");
							boolean result2 = comRules.checkCarrierName(lading);
				//			System.out.println(result2);
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
			//				System.out.println(result3);
							comp.setCompRule("A document may be dated prior to the issuance date of the credit, but must not be dated later than its date of presentation.");
							comp.setResult(result3);
							comp.setComment(rs.getString("comments"));
							break;
							
						case "Shipper_Validation":
							String shipper = rs.getString("BILL_LADING");
							boolean result4 = comRules.checkShipperName(shipper);
				//			System.out.println(result4);
							comp.setCompRule("The bills of lading are made out to order of shipper, or to order, and not endorsed in blank");
							comp.setResult(result4);
							comp.setComment(rs.getString("comments"));
							break;
							
						case "Goods_Desc_Validation":
							boolean result5 = comRules.validateDescription(rs.getString("MT_700"), rs.getString("BILL_LADING"), rs.getString("INVOICE"), rs.getString("INSURANCE"), rs.getString("PACKING_LIST"));
				//			System.out.println(result5);
							comp.setCompRule("Description of goods not conflicting with the documentary credit");
							comp.setResult(result5);
							comp.setComment(rs.getString("comments"));
							break;
						}
						
						if (comp.getResult() != null)
						{
							compList.add(comp);
						}
						
					}
					
					/*validation*/
					ComplanceValidate cv = new ComplanceValidate();
					compilanceValidatePojo comp = new compilanceValidatePojo();
					comp.setCompRule("Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.");
					comp.setResult(cv.nameVerification("", "",""));
					comp.setComment("comments....");
		//			System.out.println(cv.nameVerification("", "",""));
					compList.add(comp);
					
					compilanceValidatePojo comp1 = new compilanceValidatePojo();
					comp1.setCompRule("The effective date of insurance is later than the date of shipment; and / or");
					comp1.setResult(cv.insuranceDateValidate("", "",""));
					comp1.setComment("comments....");
					compList.add(comp1);
					
					compilanceValidatePojo comp2 = new compilanceValidatePojo();
					comp2.setCompRule("The insurance document must indicate the amount of insurance coverage and be in the same currency as the credit.");
					comp2.setResult(cv.currencyValidate("", ""));
					comp2.setComment("comments....");
					compList.add(comp2);
					
					compilanceValidatePojo comp3 = new compilanceValidatePojo();
					comp3.setCompRule("Name of insured party as appearing on insurance cert matches with name of applicant.");
					comp3.setResult(cv.nameValidateMTAndInsurance("", ""));
					comp3.setComment("comments....");
					compList.add(comp3);
					
					compilanceValidatePojo comp4 = new compilanceValidatePojo();
					comp4.setCompRule("Currency should be consistent across the documents.");
					comp4.setResult(cv.currencyValidateAcrossAll("", "","",""));
					comp4.setComment("comments....");
					compList.add(comp4);
					
					compilanceValidatePojo comp5 = new compilanceValidatePojo();
					comp5.setCompRule("Difference between Expiry Date and Shipment date should be equal to Period of Presentation (Field 48).");
					comp5.setResult(cv.DiffDatePOPValiadte("", "",""));
					comp5.setComment("comments....");
					compList.add(comp5);
					
					compilanceValidatePojo comp6 = new compilanceValidatePojo();
					comp6.setCompRule("Place of expiry must be either the place of issuing bank or the place of negotiating bank");
					comp6.setResult(cv.expiryBankNameCheck("", "",""));
					comp6.setComment("comments....");
					compList.add(comp6);
					
					compilanceValidatePojo comp7 = new compilanceValidatePojo();
					comp7.setCompRule("There should not be any onerours clause/condition included in LC/BL ");
					comp7.setResult(cv.discrepancyCheck(""));
					comp7.setComment("comments....");
					compList.add(comp7);
					/*validation*/
					return compList;


				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					con.close();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return compList;

	}

	/*public static void main(String args[]) {
		ExecuteCompliance e = new ExecuteCompliance();
		ArrayList<compilanceValidatePojo> a = new ArrayList<>();
		a= e.getResultList();
		System.out.println("-----------------------------------------------------");
		for(int i=0;i<a.size();i++) {
			compilanceValidatePojo cp = new compilanceValidatePojo();
			cp = a.get(i);
			System.out.print(cp.getResult());
			System.out.print(cp.getComment());
			System.out.print(cp.getCompRule());
			System.out.println("");

		}
		System.out.println("-----------------------------------------------------");

	}*/
}
