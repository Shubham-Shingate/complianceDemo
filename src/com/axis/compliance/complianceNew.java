package com.axis.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.validate.compilanceValidatePojo;

public class complianceNew {

	public List<compilanceValidatePojo> getResultList(String transactionId) {

		List<compilanceValidatePojo> compList = new LinkedList<compilanceValidatePojo>();
		List<compilanceValidatePojo> compListRules = new LinkedList<compilanceValidatePojo>();
		Connection conn = null;
		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
		String propertyId = "", ruleName = "", key = "", value = "", actualVal = "", ruleId = "";
		// String transactionId = "1002";
		// key is table , value is column
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet propRs = null;
		ArrayList<String> columnData = new ArrayList<>();
		ArrayList<String> keyList = new ArrayList<>();
		// ExecuteRules execRules = null;
		// List<Map<String, String>> jsonVal = new ArrayList<>();
		// Map<String, String> jsonEntry = new HashMap<>();
		try {
			Connection con = DBConnect.getConnection();

			String query = rb.getString("RuleQuery");
			 stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			//String transactionId = "1002";

			String mtCol = "", billCol = "", invoiceCol = "", insuranceCol = "", packagingCol = "",
					originCol = "";
			String mtRule = "", invoiceRule = "", insuranceRule = "", billRule = "", packagingRule = "",
					originRule = "";

			while (rs.next()) {
				Boolean result = false;
				String Status = "unchecked";
				compilanceValidatePojo comp = new compilanceValidatePojo();

				mtCol = "";
				billCol = "";
				invoiceCol = "";
				insuranceCol = "";
				packagingCol = "";
				originCol = "";
				String ruleCategory = rs.getString("PROCESSID");
				ruleName = rs.getString("RULE_DESC");
				mtRule = rs.getString("MT700");
				invoiceRule = rs.getString("INVOICE");
				insuranceRule = rs.getString("INSURANCE");
				billRule = rs.getString("BILL_LADING");
				packagingRule = rs.getString("PACKING_LIST");
				originRule = rs.getString("CERT_OF_ORIGIN");

				
				//System.out.println(ruleName);
				if (mtRule != null && !(mtRule.contains("&"))) {
					if (mtRule != null && !(mtRule.equals(""))) {
						String mtQuery = rb.getString("MTQuery");

						PreparedStatement prepMT = con.prepareStatement(mtQuery);
						prepMT.setString(1, transactionId);

						ResultSet rsMt = prepMT.executeQuery();
						if (rsMt.next()) {
							mtCol = rsMt.getString(mtRule);
						}
					}
				}

				if (billRule != null && !(billRule.contains("&"))) {
					if (billRule != null && !(billRule.equals(""))) {
						String billQuery = rb.getString("BillQuery");

						PreparedStatement prepbill = con.prepareStatement(billQuery);
						prepbill.setString(1, transactionId);

						ResultSet rsbill = prepbill.executeQuery();
						if (rsbill.next()) {
							billCol = rsbill.getString(billRule);
						}
					}
				}

				if (invoiceRule != null && !(invoiceRule.contains("&"))) {
					if (invoiceRule != null && !(invoiceRule.equals(""))) {
						String invoiceQuery = rb.getString("InvoiceQuery");

						PreparedStatement prepinvoice = con.prepareStatement(invoiceQuery);
						prepinvoice.setString(1, transactionId);

						ResultSet rsInv = prepinvoice.executeQuery();
						if (rsInv.next()) {
							invoiceCol = rsInv.getString(invoiceRule);
						}
					}
				}

				if (insuranceRule != null && !(insuranceRule.contains("&"))) {
					if (insuranceRule != null && !(insuranceRule.equals(""))) {
						String insuranceQuery = rb.getString("InsuranceQuery");

						PreparedStatement prepinsurance = con.prepareStatement(insuranceQuery);
						prepinsurance.setString(1, transactionId);

						ResultSet rsinsurance = prepinsurance.executeQuery();
						if (rsinsurance.next()) {
							insuranceCol = rsinsurance.getString(insuranceRule);
						}
					}
				}

				if (packagingRule != null && !(packagingRule.contains("&"))) {
					if (packagingRule != null && !(packagingRule.equals(""))) {

						String packingQuery = rb.getString("PakingQuery");

						PreparedStatement preppacking = con.prepareStatement(packingQuery);
						preppacking.setString(1, transactionId);

						ResultSet rspacking = preppacking.executeQuery();
						if (rspacking.next()) {
							packagingCol = rspacking.getString(packagingRule);
						}
					}
				}

				if (originRule != null && !(originRule.contains("&"))) {
					if (originRule != null && !(originRule.equals(""))) {
						String originQuery = rb.getString("OriginQuery");

						PreparedStatement preporigin = con.prepareStatement(originQuery);
						preporigin.setString(1, transactionId);

						ResultSet rsorigin = preporigin.executeQuery();
						if (rsorigin.next()) {
							originCol = rsorigin.getString(originRule);
						}
					}
				}
				ComplianceRules comRules = new ComplianceRules();
				switch (ruleName) {
				case "The documentary credit has not expired":
					if (!mtCol.equals("")) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
						Date date1 = sdf.parse(mtCol);
						Status = "checked";
						result = comRules.checkExpiryGreater(date1);
					}
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "The ports of Discharge differ from those specified in the documentary credit":
				case "The Place of taking in charge differ from those specified in the documentary credit":
				case "The ports of Final Destination differ from those specified in the documentary credit":
				case "The ports of loading differ from those specified in the documentary credit":
					result = comRules.validatePOL_dispatch_charge(mtCol, billCol, invoiceCol, insuranceCol,
							packagingCol, originCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "The transport documents do not identify the name of the carrier":

					result = comRules.checkCarrierName(billCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);

					break;

				case "A document may be dated prior to the issuance date of the credit, but must not be dated later than its date of presentation":

					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
					Date dateLading = sdf1.parse(billCol);
					Date dateInvoice = sdf1.parse(invoiceCol);
					Date dateInsurance = sdf1.parse(insuranceCol);
					Date datePackaging = sdf1.parse(packagingCol);
					Date dateOrigin = sdf1.parse(originCol);

					result = comRules.validateDate(dateLading, dateInvoice, dateInsurance, datePackaging,
							dateOrigin);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);

					System.out.println(result);
					break;

				case "The bills of lading are made out to order of shipper, or to order, and not endorsed in blank":

					result = comRules.checkShipperName(billCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					System.out.println(result);
					break;

				case "Description of goods not conflicting with the documentary credit":
					result = comRules.validateDescription(mtCol, billCol, invoiceCol, insuranceCol,
							packagingCol, originCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					System.out.println(result);
					break;

				case "Shipment_Date/ Date_of_Issue_BL/On_board_Date":
					/*
					 * SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy"); Date dateMT =
					 * sdf2.parse(rs.getString("MT_700")); Date dateOfLading =
					 * sdf2.parse(rs.getString("BILL_LADING")); boolean result6 =
					 * comRules.validateShipmentDate(dateMT, dateOfLading);
					 * System.out.println(result6); break;
					 */
				case "The title of the document should be as shown in the credit or may be abbreviated to ‘Invoice’. It should not be titled ‘provisional’, ‘proforma’ or similar unless required by the documentary credit":
					result = comRules.titleValidation(mtCol, invoiceCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					System.out.println(result);
					break;

				case "Not to conflict with the consignee information in the transport document. However, this is subject to the allowances made in ISBP 745, paragraph L5.":
					result = comRules.validateConsigneeName(billCol, invoiceCol, packagingCol, originCol);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "The effective date of insurance is later than the date of shipment; and / or":

					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MMM-yyyy");
					Date shipmentMT = sdf3.parse(mtCol);
					Date shipmentLading = sdf3.parse(billCol);
					Date insurance = sdf3.parse(insuranceCol);
					result = comRules.validateInsuranceDate(shipmentMT, shipmentLading, insurance);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "The insurance document must indicate the amount of insurance coverage and be in the same currency as the credit":
				case "Name of insured party as appearing on insurance cert matches with name of applicant":
					result = comRules.titleValidation(mtCol, insuranceCol);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "Currency should be consistent across the documents":
					result = comRules.validateCurrency(mtCol, invoiceCol, insuranceCol, packagingCol);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "Whether LC needs Confirmation":
					result = comRules.validateConfirmation(mtCol);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				/*case "Difference between Expiry Date and Shipment date should be equal to Period of Presentation (Field 48).":
					String mtColumns[] = mtRule.split("&");
					Date expDate, shipmentDate;
					String days;
					String arr[] = new String[4];
					int i = 0;

					for (String mt : mtColumns) {
						String mtQuery = rb.getString("MTQuery");

						PreparedStatement prepMT = con.prepareStatement(mtQuery);
						prepMT.setString(1, transactionId);

						ResultSet rsMt = prepMT.executeQuery();
						if (rsMt.next()) {
							arr[i++] = rsMt.getString(mt);
						}
					}

					SimpleDateFormat sdfShip = new SimpleDateFormat("dd-MMM-yyyy");
					expDate = sdfShip.parse(arr[0]);
					shipmentDate = sdfShip.parse(arr[1]);
					Date billShipmentDate = sdfShip.parse(billCol);
					days = arr[2];

					result = comRules.shipmentDateValidation(expDate, shipmentDate, days, billShipmentDate);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr[0]+" "+arr[1]+" "+arr[2],
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);

					break;*/

				case "Place of expiry must be either the place of issuing bank or the place of negotiating bank":
					String mtColumns1[] = mtRule.split("&");
					String mtQuery = rb.getString("MTQuery");
					String arr1[] = new String[4];
					PreparedStatement prepMT = con.prepareStatement(mtQuery);
					prepMT.setString(1, transactionId);
					int k=0;
					for (String mt : mtColumns1) {
						ResultSet rsMt = prepMT.executeQuery();
						if (rsMt.next()) {
							arr1[k++] = rsMt.getString(mt);
						}
					}
					
					String expPlace = arr1[0];
					String negoPlace=arr1[1];
					
					result = comRules.titleValidation(expPlace, negoPlace);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), expPlace+" "+negoPlace ,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;
					
				case "There should not be any onerours clause/condition included in LC/BL ":
					result = comRules.discripanceValidation(mtCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "If only Fields 44E and 44F are completed, the required document should be a bill of lading, non-negotiable sea waybill, charter party bill of lading or air transport document.":
					result = comRules.field44EValidation(mtCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;
					
				case "If only Fields 44A and 44B are completed, the required document should be a multimodal transport document, road, rail or inland waterway transport document, or a courier receipt, postal receipt or certificate of posting.":
					result = comRules.field44AValidation(mtCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;
					
				case "When LC is available with issuing bank, type of settlement 'negotiation' is not allowed. ":
					result = comRules.field41AValidation(mtCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), "negotiation",
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;
					
				case "If LC is issued with term acceptance, then draft is mandatory.":
					result = comRules.field41A_Acceptance_Validation(mtCol);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), "Draft not present",
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;
					
					// START CHANGES DONE BY SUBHASIS

				case "Insurance policy is provided i/o Insurance Certificate":
					result = comRules.validatePolicyProvided(mtCol);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), mtCol,
							billCol, invoiceCol, insuranceCol, packagingCol, originCol);
					break;

				case "No of original & copy mentioned for Cert of Origin(COO) is not presented.":
					String mtColumns11[] = mtRule.split("&");
					String originColumns[] = originRule.split("&");

					String arr12[] = new String[2];
					String arr13[] = new String[2];
					int j1 = 0;
					int k1=0;

					String mtQuery1 = rb.getString("MTQuery");
					PreparedStatement prepMT1 = con.prepareStatement(mtQuery1);

					prepMT1.setString(1, transactionId);

					for (String mt : mtColumns11) {
						ResultSet rsMt = prepMT1.executeQuery();
						if (rsMt.next()) {
							arr12[j1++] = rsMt.getString(mt);
						}
					}

					String orgQuery1 = rb.getString("OriginQuery");
					PreparedStatement prepMT2 = con.prepareStatement(orgQuery1);

					prepMT2.setString(1, transactionId);

					for (String kt : originColumns) {
						ResultSet rsMt1 = prepMT2.executeQuery();
						if (rsMt1.next()) {
							arr13[k1++] = rsMt1.getString(kt);
						}
					}

					result = comRules.validateDocRecvd(arr12,arr13);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr12[0]+","+arr12[1],
							billCol, invoiceCol, insuranceCol, packagingCol, arr13[0]+","+arr13[1]);
					break;
					
					
				case "No of Original & copy mentioned for Insurance is not presented.":
					String mtColumns2[] = mtRule.split("&");
					String insuranceCol1[] = insuranceRule.split("&");

					String arr3[] = new String[2];
					String arr4[] = new String[2];
					int x = 0;
					int y=0;

					String mtQuery2 = rb.getString("MTQuery");
					PreparedStatement prepMT3 = con.prepareStatement(mtQuery2);

					prepMT3.setString(1, transactionId);

					for (String mt : mtColumns2) {
						ResultSet rsMt = prepMT3.executeQuery();
						if (rsMt.next()) {
							arr3[x++] = rsMt.getString(mt);
						}
					}

					String insQuery1 = rb.getString("InsuranceQuery");
					PreparedStatement prepMT5 = con.prepareStatement(insQuery1);

					prepMT5.setString(1, transactionId);

					for (String kt : insuranceCol1) {
						ResultSet rsMt1 = prepMT5.executeQuery();
						if (rsMt1.next()) {
							arr4[y++] = rsMt1.getString(kt);
						}
					}

					result = comRules.validateDocRecvd(arr3,arr4);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr3[0]+","+arr3[1],
							billCol, invoiceCol, arr4[0]+","+arr4[1], packagingCol, originCol );
					break;
					
					
				case "No of Original & copy mentioned for Packing List is not presented.":
					String mtColumns3[] = mtRule.split("&");
					String packagingRule1[] = packagingRule.split("&");

					String arr5[] = new String[2];
					String arr6[] = new String[2];
					int ab = 0;
					int ac = 0;

					String mtQuery3 = rb.getString("MTQuery");
					PreparedStatement prepMT6 = con.prepareStatement(mtQuery3);

					prepMT6.setString(1, transactionId);

					for (String mt : mtColumns3) {
						ResultSet rsMt = prepMT6.executeQuery();
						if (rsMt.next()) {
							arr5[ab++] = rsMt.getString(mt);
						}
					}

					String pklistQuery1 = rb.getString("PakingQuery");
					PreparedStatement prepMT7 = con.prepareStatement(pklistQuery1);

					prepMT7.setString(1, transactionId);

					for (String kt : packagingRule1) {
						ResultSet rsMt1 = prepMT7.executeQuery();
						if (rsMt1.next()) {
							arr6[ac++] = rsMt1.getString(kt);
						}
					}

					result = comRules.validateDocRecvd(arr5,arr6);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr5[0]+","+arr5[1],
							billCol, invoiceCol, insuranceCol, arr6[0]+","+arr6[1], originCol);
					break;
					
					
				case "No of Original & copy mentioned for Transport Docs is not presented.":
					String mtColumns4[] = mtRule.split("&");
					String billRule1[] = billRule.split("&");

					String arr7[] = new String[2];
					String arr8[] = new String[2];
					int ax = 0;
					int ay = 0;

					String mtQuery4 = rb.getString("MTQuery");
					PreparedStatement prepMT8 = con.prepareStatement(mtQuery4);

					prepMT8.setString(1, transactionId);

					for (String mt : mtColumns4) {
						ResultSet rsMt = prepMT8.executeQuery();
						if (rsMt.next()) {
							arr7[ax++] = rsMt.getString(mt);
						}
					}

					String billQuery1 = rb.getString("BillQuery");
					PreparedStatement prepMT9 = con.prepareStatement(billQuery1);

					prepMT9.setString(1, transactionId);

					for (String kt : billRule1) {
						ResultSet rsMt1 = prepMT9.executeQuery();
						if (rsMt1.next()) {
							arr8[ay++] = rsMt1.getString(kt);
						}
					}

					result = comRules.validateDocRecvd(arr7,arr8);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr7[0]+","+arr7[1],
							arr8[0]+","+arr8[1], invoiceCol, insuranceCol, packagingCol , originCol);
					break;
					
					
				case "No of Original & copy mentioned for Invoice is not presented.":
					String mtColumns5[] = mtRule.split("&");
					String invoiceRule1[] = invoiceRule.split("&");

					String arr9[] = new String[2];
					String arr10[] = new String[2];
					int ba = 0;
					int bc = 0;

					String mtQuery5 = rb.getString("MTQuery");
					PreparedStatement prepMT10 = con.prepareStatement(mtQuery5);

					prepMT10.setString(1, transactionId);

					for (String mt : mtColumns5) {
						ResultSet rsMt = prepMT10.executeQuery();
						if (rsMt.next()) {
							arr9[ba++] = rsMt.getString(mt);
						}
					}

					String invoiceQuery1 = rb.getString("InvoiceQuery");
					PreparedStatement prepMT11 = con.prepareStatement(invoiceQuery1);

					prepMT11.setString(1, transactionId);

					for (String kt : invoiceRule1) {
						ResultSet rsMt1 = prepMT11.executeQuery();
						if (rsMt1.next()) {
							arr10[bc++] = rsMt1.getString(kt);
						}
					}

					result = comRules.validateDocRecvd(arr9,arr10);
					System.out.println(result);
					Status = "checked";
					saveResultToTable(transactionId, ruleCategory, ruleName, Boolean.toString(result), arr9[0]+","+arr9[1],
							billCol, arr10[0]+","+arr10[1], insuranceCol, packagingCol , originCol);
					break;
					
				// END CHANGES DONE BY SUBHASIS
					
				default:
					System.out.println("Rule Execution not created");
					comp.setCompRule(ruleName);
					comp.setTransactionId(transactionId);
					compListRules.add(comp);
				}

				comp.setCompRule(ruleName);
				comp.setResult(result);
				comp.setComment("comment");
				comp.setProcessID(ruleCategory);
				comp.setTransactionId(transactionId);
				if (Status.equals("checked")) {
			//		 System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   "+comp.getCompRule());
					compList.add(comp);
				}

			}

		//	System.out.println(compList.size()+"=========================================");
			compList.addAll(compListRules);
		//	System.out.println(compList.size()+"=========================================");


			return compList;
} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return compList;
	}

	
	  private void saveResultToTable(String transactionId, String ruleCategory,
	  String ruleName, String status, String mtCol, String billCol, String
	  invoiceCol, String insuranceCol, String packagingCol, String originCol) { try
	  { ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	  String updateQuery = rb.getString("UpdateLastResult");
	  
	  String insertQuery = rb.getString("ResultQuery"); Connection con =
	  DBConnect.getConnection();
	  
	  PreparedStatement prepUp = con.prepareStatement(updateQuery);
	  prepUp.setString(1, transactionId); prepUp.setString(2, ruleName);
	  
	  ResultSet rsUp = prepUp.executeQuery();
	  
	  PreparedStatement prep = con.prepareStatement(insertQuery); prep.setString(1,
	  transactionId); prep.setString(2, ruleCategory); prep.setString(3, ruleName);
	  prep.setString(4, status); prep.setString(5, mtCol); prep.setString(6,
	  billCol); prep.setString(7, invoiceCol); prep.setString(8, insuranceCol);
	  prep.setString(9, packagingCol); prep.setString(10, originCol);
	  
	  ResultSet rs = prep.executeQuery();
	  
	  } catch (SQLException se) { se.printStackTrace(); }
	  
	  }
	 

}
