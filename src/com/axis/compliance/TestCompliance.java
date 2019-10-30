package com.axis.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.validate.compilanceValidatePojo;

public class TestCompliance {

	public List<compilanceValidatePojo> ruleRunning() {
		List<compilanceValidatePojo> compList = new LinkedList<compilanceValidatePojo>();
		List<compilanceValidatePojo> compListRules = new LinkedList<compilanceValidatePojo>();
		Connection conn = null;
		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
		String fetchRules = rb.getString("fetchRules");
		String propertyId = "", key = "", value = "", ruleId = "";
		String transactionId = "1002";
		// key is table , value is column
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet propRs = null;
		ResultSet rsRuleDesc = null;
		PreparedStatement prepRuleDesc = null;
		ArrayList<String> columnData = new ArrayList<>();
		ArrayList<String> keyList = new ArrayList<>();
		try {
			// System.out.println("Inside method !");
			JSONArray jsonArray = null;
			conn = TestDbConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(fetchRules);
			while (rs.next()) {
				// System.out.println("After executing query");
				String result = "";
				String Status = "unchecked";
				compilanceValidatePojo comp = new compilanceValidatePojo();
				propertyId = rs.getString("PROPERTY_ID");
				ruleId = rs.getString("MASTERRULE_ID");
				jsonArray = new JSONArray(propertyId);
				// System.out.println("Json Length :: " + jsonArray.length());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject json = jsonArray.getJSONObject(i);
					@SuppressWarnings("unchecked")
					Iterator<String> keys = json.keys();
					while (keys.hasNext()) {
						// To get key from Json
						key = keys.next().trim();
						// To get value for that particular key
						value = (String) json.get(key);
						keyList.add(key);
						System.out.println("Key -> " + key + " Value -> " + value);
						Statement propStmt = conn.createStatement();
						if (value.contains(",")) {
							// System.out.println("multiple columns");
							String[] multipleVal = value.split(",");
							for (int j = 0; j < multipleVal.length; j++) {
								propRs = propStmt.executeQuery("SELECT " + multipleVal[j].trim() + " from " + key
										+ " where transactionid=" + transactionId);
								while (propRs.next()) {
									// actualVal = propRs.getString(1);
									columnData.add(propRs.getString(1));
								}
								// System.out.println("value from particular table :: " + actualVal);
							}
						} else {
							// System.out.println("single column");
							propRs = propStmt.executeQuery(
									"SELECT " + value + "  from " + key + " where transactionid=" + transactionId);
							// MTQuery = SELECT * FROM DL_MT700 WHERE TRANSCTIONID = ?
							while (propRs.next()) {
								// actualVal = propRs.getString(1);
								columnData.add(propRs.getString(1));
							}
						}
						// System.out.println("Data from that table :: " + columnData);
						// System.out.println("value from particular table :: "+actualVal);
						// jsonEntry.put(key, value);
					} // End of Json keys
						// executeRules(key,ruleName,data);
				}

				String ruleDesc = "";
				String fetchRuleDesc = rb.getString("fetchRuleDesc");

				prepRuleDesc = conn.prepareStatement(fetchRuleDesc);

				prepRuleDesc.setString(1, ruleId);
				rsRuleDesc = prepRuleDesc.executeQuery();
				while (rsRuleDesc.next()) {
					System.out.println("%%%%%%%%%%%%%%%%%%% YES %%%%%%%%%%%%%%%%%%%");
					ruleDesc = rsRuleDesc.getString("RULE_DESC");
					System.out.println("RULE_DESC === " + ruleDesc);
				}
				// String ruleDesc = "Difference between Expiry Date and Shipment date should be
				// equal to Period of Presentation (Field 48).";
				// execRules.executeRule(transactionId, ruleId, key, columnData,
				// keyList,ruleDesc);
				switch (ruleDesc) {
				// Rule 01
				case "The documentary credit has not expired":
					System.out.println("1st rule executing ++++++++++++++++ " + columnData);
					String d1 = columnData.get(0);
					String d2 = columnData.get(1);
					// String col1 = keyList.get(0);
					// String col2 = keyList.get(1);
					result = TestComplianceRules.checkExpiryDate(d1, d2);
					Status = "checked";
					// System.out.println("Json Keys :: " + keyList + "result in main class :: " +
					// result);
					saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true", d1, d2, "",
							"", "", "", "");
					break;
				// Rule 02
				case "Difference between Expiry Date and Shipment date should be equal to Period of Presentation (Field 48).":
					System.out.println("2nd rule executing ++++++++++++++++ " + columnData);
					// 0 exp date, 1 - no of days , 2 - shipment date
					result = TestComplianceRules.checkNoOfDays(columnData);
					break;
				// Rule 03
				case "Currency should be consistent across the documents.":
					System.out.println("3rd rule executing ++++++++++++++++ " + columnData);
					result = TestComplianceRules.checkCurrency(columnData);
					Status = "checked";
					saveResultToTable(transactionId, ruleDesc, result, columnData.get(0), columnData.get(1),
							columnData.get(2), columnData.get(3), columnData.get(4), columnData.get(5), "");
					break;
				// Rule 04
				case "Place of expiry must be either the place of issuing bank or the place of negotiating bank":
					System.out.println("4th rule executing ++++++++++++++++ " + columnData);
					// result = TestComplianceRules.checkExpiryPlace(columnData);
					Status = "checked";
					saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true",
							(columnData.get(0) + "," + columnData.get(1)),
							(columnData.get(2) + "," + columnData.get(3)), "", "", "", "", "");
					break;
				// Rule 05
				case "The gross value of invoice should not exceed the credit amount.":
					System.out.println("5th rule executing ++++++++++++++++ " + columnData);
					// result = TestComplianceRules.checkAmountClaimed(columnData);
					Status = "checked";
					// saveResultToTable(transactionId, ruleDesc, result, columnData.get(0), "", "",
					// "","", "", "");
					break;
				// Rule 06
				case "Whether LC needs Confirmation?":
					System.out.println("6th rule executing ++++++++++++++++ " + columnData);
					String field = columnData.get(0);
					// result = TestComplianceRules.checkConfirmation(field);
					Status = "checked";
					// saveResultToTable(transactionId, ruleDesc, result, field, "", "", "", "","",
					// "");
					break;

				// Rule 7
				case "If only Fields 44E and 44F are completed, the required document should be a bill of lading, non-negotiable sea waybill, charter party bill of lading or air transport document.":
					System.out.println("7th rule executing ++++++++++++++++ " + columnData);
					result = TestComplianceRules.checkRequiredDocument(columnData);
					System.out.println("Json Keys :: " + keyList + "result in main class :: " + result);
					// 1 transactionId, 2 ruleDesc, 3 result, 4 mtCol, 5 preBankCoverCol, 6 billCol,
					// 7 invoiceCol, 8 insuranceCol, 9 packagingCol, 10 originCol
					saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true",
							(columnData.get(0) + " , " + columnData.get(1)), "", columnData.get(2), "", "", "", "");
					break;

				// Rule 8
				case "If only Fields 44A and 44B are completed, the required document should be a multimodal transport document, road, rail or inland waterway transport document, or a courier receipt, postal receipt or certificate of posting.":
					System.out.println("8th rule executing ++++++++++++++++ " + columnData);
					result = TestComplianceRules.checkRequiredTransportDocument(columnData);
					System.out.println("Json Keys :: " + keyList + "result in main class :: " + result);
					saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true",
							(columnData.get(0) + " , " + columnData.get(1)), "", columnData.get(2), "", "", "", "");
					break;

				// Rule 9
				case "When LC is available with issuing bank, type of settlement \"negotiation\" is not allowed.":
					System.out.println("8th rule executing ++++++++++++++++ " + columnData);
					result = TestComplianceRules.checkNegotiation(columnData);
					System.out.println("Json Keys :: " + keyList + "result in main class :: " + result);
					saveResultToTable(transactionId, ruleDesc, result.equals("expired") ? "false" : "true",
							columnData.get(0), "", "", "", "", "", "");
					break;

				default:

					System.out.println("No rules executed !");
					comp.setCompRule(ruleDesc);
					comp.setTransactionId(transactionId);
					compListRules.add(comp);
				}

				comp.setCompRule(ruleDesc);
				comp.setResult(Boolean.parseBoolean(result));
				comp.setComment("comment");
				comp.setProcessID("DCC");
				comp.setTransactionId(transactionId);
				System.out.println("comp values : " + comp);
				if (Status.equals("checked")) {
					compList.add(comp);
				}
				columnData.clear();
			}
			compList.addAll(compListRules);
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

	static void saveResultToTable(String transactionId, String ruleDesc, String result, String mtCol,
			String preBnkCovrLtr, String billCol, String invoiceCol, String insuranceCol, String packagingCol,
			String originCol) {
		try {
			System.out.println("Saving result in DB :: " + result);
			ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
			String updateQuery = rb.getString("updateResult");

			String insertQuery = rb.getString("insertResult");
			Connection con = DBConnect.getConnection();

			PreparedStatement prepUp = con.prepareStatement(updateQuery);
			prepUp.setString(1, transactionId);
			prepUp.setString(2, ruleDesc);

			prepUp.executeQuery();

			PreparedStatement prep = con.prepareStatement(insertQuery);
			prep.setString(1, transactionId);
			prep.setString(2, "DCC");
			prep.setString(3, ruleDesc);
			prep.setString(4, result);
			prep.setString(5, mtCol);
			prep.setString(6, preBnkCovrLtr);
			prep.setString(7, billCol);
			prep.setString(8, invoiceCol);
			prep.setString(9, insuranceCol);
			prep.setString(10, packagingCol);
			prep.setString(11, originCol);

			prep.executeQuery();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	/*
	 * public static void main(String[] args) { Connection conn = null;
	 * ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	 * String fetchRules = rb.getString("fetchRules"); String propertyId = "",
	 * ruleName = "", key = "", value = "", actualVal = "", ruleId = ""; String
	 * transactionId = "1002"; // key is table , value is column Statement stmt =
	 * null; ResultSet rs = null; ResultSet propRs = null; ArrayList<String>
	 * columnData = new ArrayList<>(); ArrayList<String> keyList = new
	 * ArrayList<>(); ExecuteRules execRules = null; // List<Map<String, String>>
	 * jsonVal = new ArrayList<>(); // Map<String, String> jsonEntry = new
	 * HashMap<>(); try { JSONArray jsonArray = null; execRules = new
	 * ExecuteRules(); conn = TestDbConn.getConnection(); stmt =
	 * conn.createStatement(); rs = stmt.executeQuery(fetchRules); while (rs.next())
	 * { propertyId = rs.getString("PROPERTY_ID"); ruleId =
	 * rs.getString("MASTERRULE_ID"); // ruleName = rs.getString("RULE_DESC");
	 * jsonArray = new JSONArray(propertyId); System.out.println("Json Length :: " +
	 * jsonArray.length()); for (int i = 0; i < jsonArray.length(); i++) {
	 * JSONObject json = jsonArray.getJSONObject(i);
	 * 
	 * @SuppressWarnings("unchecked") Iterator<String> keys = json.keys(); while
	 * (keys.hasNext()) { // To get key from Json key = keys.next().trim(); // To
	 * get value for that particular key value = (String) json.get(key);
	 * keyList.add(key); System.out.println("Table -> " + key + "  Column -> " +
	 * value); Statement propStmt = conn.createStatement(); if (value.contains(","))
	 * { System.out.println("multiple columns"); String[] multipleVal =
	 * value.split(","); for (int j = 0; j < multipleVal.length; j++) { propRs =
	 * propStmt.executeQuery("SELECT " + multipleVal[j].trim() + " from " + key +
	 * " where transactionid=" + transactionId); while (propRs.next()) { //
	 * actualVal = propRs.getString(1); columnData.add(propRs.getString(1)); } //
	 * System.out.println("value from particular table :: " + actualVal); } } else {
	 * System.out.println("single column"); propRs = propStmt.executeQuery(
	 * "SELECT " + value + "  from " + key + " where transactionid=" +
	 * transactionId); // MTQuery = SELECT * FROM DL_MT700 WHERE TRANSCTIONID = ?
	 * while (propRs.next()) { // actualVal = propRs.getString(1);
	 * columnData.add(propRs.getString(1)); } }
	 * System.out.println("Data from that table :: " + columnData); //
	 * System.out.println("value from particular table :: "+actualVal); //
	 * jsonEntry.put(key, value); } // End of Json keys //
	 * executeRules(key,ruleName,data); } // String ruleDesc =
	 * "Difference between Expiry Date and Shipment date should be equal to Period of Presentation (Field 48)."
	 * ; String ruleDesc = "The documentary credit has not expired";
	 * execRules.executeRule(transactionId, ruleId, key, columnData,
	 * keyList,ruleDesc); columnData.clear(); // End of JsonArray //
	 * jsonVal.add(jsonEntry); } // End of rs from rule table //
	 * System.out.println("Final value :: " + jsonVal); } catch (JSONException e) {
	 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
	 * finally { if (rs != null) { try { rs.close(); rs = null; } catch
	 * (SQLException e) { e.printStackTrace(); } } } }
	 */
}
