package com.axis.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.validate.compilanceValidatePojo;

public class ExecuteRules {

	public List<compilanceValidatePojo> executeRule(String transactionId, String ruleId, String key,
			ArrayList<String> columnData, ArrayList<String> keyList, String ruleDesc) {
		List<compilanceValidatePojo> compListRules = new LinkedList<compilanceValidatePojo>();
		List<compilanceValidatePojo> compList = new LinkedList<compilanceValidatePojo>();
		compilanceValidatePojo comp = new compilanceValidatePojo();
		String result = "";
		String Status = "unchecked";
		System.out.println("**************Executing rules**************");
		System.out.println("key :: " + key);
		System.out.println("columnData ::" + columnData);
		System.out.println("ruleId ::" + ruleDesc);
		// Fetch rule desc based on rule ID
		// For rule no1 : The documentary credit has not expired
		// tranID,ruleDesc,result,mtCol,billCol,invoiceCol,insuranceCol,packaginCol,originalCol
		switch (ruleDesc) {
		case "The documentary credit has not expired":
			String d1 = columnData.get(0);
			String d2 = columnData.get(1);
			// String col1 = keyList.get(0);
			// String col2 = keyList.get(1);
			// result = TestComplianceRules.checkExpiryDate(d1, d2);
			Status = "checked";
			System.out.println("Json Keys :: " + keyList + "result in main class :: " + result);
			saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true", d1, "", "", "", "",
					"");
			result = "";
			break;

		case "Difference between Expiry Date and Shipment date should be equal to Period of Presentation (Field 48).":
			System.out.println("2nd rule to be executed ++++++++++++++++ " + columnData);
			// 0 exp date, 1 - no of days , 2 - shipment date
			break;
		default:
			System.out.println("Rule Execution not created");
			comp.setCompRule(ruleDesc);
			comp.setTransactionId(transactionId);
			compListRules.add(comp);
		}

		comp.setCompRule(ruleDesc);
		comp.setResult(true);
		comp.setComment("comment");
		comp.setProcessID("DCC");
		comp.setTransactionId(transactionId);
		if (Status.equals("checked")) {
			// System.out.println("
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			// "+comp.getCompRule());
			compList.add(comp);
		}
		return compList;

	}

	private static void saveResultToTable(String transactionId, String ruleDesc, String result, String mtCol,
			String billCol, String invoiceCol, String insuranceCol, String packagingCol, String originCol) {
		try {
			System.out.println("Saving result in DB :: " + result);
			ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
			String updateQuery = rb.getString("updateResult");

			String insertQuery = rb.getString("insertResult");
			Connection con = DBConnect.getConnection();

			PreparedStatement prepUp = con.prepareStatement(updateQuery);
			prepUp.setString(1, transactionId);
			prepUp.setString(2, ruleDesc);

			ResultSet rsUp = prepUp.executeQuery();

			PreparedStatement prep = con.prepareStatement(insertQuery);
			prep.setString(1, transactionId);
			prep.setString(2, "DCC");
			prep.setString(3, ruleDesc);
			prep.setString(4, result);
			prep.setString(5, mtCol);
			prep.setString(6, billCol);
			prep.setString(7, invoiceCol);
			prep.setString(8, insuranceCol);
			prep.setString(9, packagingCol);
			prep.setString(10, originCol);

			ResultSet rs = prep.executeQuery();

		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			String date1 = "180730";
			String date2 = "180730";
			DateFormat format = new SimpleDateFormat("yymmDD", Locale.ENGLISH);
			// Date d1 = format.parse(sDate1);
			// Date d2 = format.parse(sDate2);
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			if (d1.compareTo(d2) > 0) {
				System.out.println("Expired 1");
				// return "expired";
			} else if (d1.compareTo(d2) < 0) {
				System.out.println("valid 1");
				// return "valid";
			} else {
				System.out.println("valid 2");
				// return "valid";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}