package com.axis.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import com.validate.compilanceValidatePojo;

public class MoreInfoDAO {

	public String UpdateResult(String ruleName, String transactionId, String categoryId, String status, String comment,
			String sub) throws SQLException {

		String newStatus = status;
		String result = "";
		if (sub.equals("Disagree")) {
			if (status.equals("true")) {
				newStatus = "false";
			}
			if (status.equals("false")) {
				newStatus = "true";
			}
		}

		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
		Connection con = DBConnect.getConnection();
		String qry = "UPDATE DL_COMPLAINCE_CHECKER_RESULT SET STATUS=?,COMMENTS=? WHERE"
				+ " TRANSACTION_ID=? AND CATEGORY=? AND RULE_DESC=? AND STATUS=? " + " AND FLAG='L' ";
		System.out.println(qry);
		PreparedStatement pst = con.prepareStatement(qry);
		pst.setString(1, newStatus);
		pst.setString(2, comment);
		pst.setString(3, transactionId);
		pst.setString(4, categoryId);
		pst.setString(5, ruleName);
		pst.setString(6, status);
		int i = pst.executeUpdate();
		if (i > 0) {
			result = "success";
		} else {
			result = "no value updated";
		}
		return result;
	}

	public LinkedHashMap<String, String> getResultInfo(String ruleName, String transactionId, String categoryId,
			String status) throws SQLException {

		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
		Connection con = DBConnect.getConnection();
		LinkedHashMap<String, String> compList = new LinkedHashMap<>();
		// String qry=rb.getString("RuleQueryforOne");
		String qry = "SELECT * FROM DCC_COMPLAINCE_CHECKER_RESULT WHERE"
				+ " TRANSACTION_ID=? AND CATEGORY=? AND RULE_DESC=? AND STATUS=? " + " AND (FLAG is null or FLAG='L')";
//	System.out.println("SELECT * FROM DL_COMPLAINCE_CHECKER_RESULT WHERE STATUS='false' AND (FLAG is null or FLAG='L')");
		System.out.println("Inside fetch info dao");
		System.out.println("ruleName : " + ruleName + " , tranId : " + transactionId + ", category : " + categoryId
				+ " , status = " + status);
//	System.out.println("AND TRANSACTION_ID='"+transactionId+"'");
//	System.out.println("AND CATEGORY='"+categoryId+"'");
//	System.out.println("AND RULE_DESC='"+ruleName+"'");
//	System.out.println("Here end");

		PreparedStatement pst = con.prepareStatement(qry);
		pst.setString(1, transactionId);
		pst.setString(2, categoryId);
		pst.setString(3, ruleName);
		pst.setString(4, status);
		ResultSetMetaData rsmd = null;
		ResultSet rs = pst.executeQuery();
		rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while (rs.next()) {

			for (int i = 1; i <= count; i++) {
				String value = rs.getString(rsmd.getColumnName(i)) == null ? "NA" : rs.getString(rsmd.getColumnName(i));
				String colName = rsmd.getColumnName(i);
				if (!value.equals("NA")) {
					if (!colName.equals("TRANSACTION_ID") && !colName.equals("CATEGORY") && !colName.equals("STATUS")
							&& !colName.equals("FLAG")) {
						System.out.println(value + "  value" + " col  " + colName);
						compList.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
					}
				}
			}
		}
		System.out.println("compList :: " + compList);
		return compList;
	}

	/*
	 * public LinkedHashMap<String, String> getResultInfo(String ruleName, String
	 * transactionId, String categoryId,String status) throws SQLException{
	 * 
	 * ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	 * Connection con = DBConnect.getConnection(); LinkedHashMap<String,String>
	 * compList = new LinkedHashMap<>(); //String
	 * qry=rb.getString("RuleQueryforOne"); String qry =
	 * "SELECT * FROM DL_COMPLAINCE_CHECKER_RESULT WHERE" +
	 * " TRANSACTION_ID=? AND CATEGORY=? AND RULE_DESC=? AND STATUS=? " +
	 * " AND (FLAG is null or FLAG='L')"; System.out.
	 * println("SELECT * FROM DL_COMPLAINCE_CHECKER_RESULT WHERE STATUS='false' AND (FLAG is null or FLAG='L')"
	 * ); System.out.println("AND TRANSACTION_ID='"+transactionId+"'");
	 * System.out.println("AND CATEGORY='"+categoryId+"'");
	 * System.out.println("AND RULE_DESC='"+ruleName+"'");
	 * 
	 * PreparedStatement pst = con.prepareStatement(qry); pst.setString(1,
	 * transactionId); pst.setString(2, categoryId); pst.setString(3, ruleName);
	 * pst.setString(4, status); ResultSetMetaData rsmd=null; ResultSet rs =
	 * pst.executeQuery(); rsmd=rs.getMetaData(); int count = rsmd.getColumnCount();
	 * while (rs.next()) {
	 * 
	 * for (int i = 1; i <= count; i++) { String
	 * value=rs.getString(rsmd.getColumnName(i))==null?"NA":rs.getString(rsmd.
	 * getColumnName(i)); String colName=rsmd.getColumnName(i);
	 * if(!value.equals("NA")) { if(!colName.equals("TRANSACTION_ID") &&
	 * !colName.equals("CATEGORY") && !colName.equals("STATUS") &&
	 * !colName.equals("FLAG")) {
	 * System.out.println(value+"  value"+" col  "+colName);
	 * compList.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i))); } }
	 * } } return compList; }
	 */

	public ArrayList<String> getStatusResult(String transactionId, String status) throws SQLException {

		ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
		Connection con = DBConnect.getConnection();
		ArrayList<String> compList = new ArrayList<>();
		// String qry=rb.getString("RuleQueryforOne");
		String qry = "SELECT RULE_DESC FROM DL_COMPLAINCE_CHECKER_RESULT WHERE" + " TRANSACTION_ID=? AND STATUS=? "
		// + " AND (FLAG is null or FLAG='L')";
				+ " AND FLAG='L' ";

		// System.out.println("AND TRANSACTION_ID='"+transactionId+"'");

		PreparedStatement pst = con.prepareStatement(qry);
		pst.setString(1, transactionId);
		pst.setString(2, status);
		ResultSetMetaData rsmd = null;
		ResultSet rs = pst.executeQuery();
		rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= count; i++) {
				String value = rs.getString(rsmd.getColumnName(i)) == null ? "NA" : rs.getString(rsmd.getColumnName(i));
				String colName = rsmd.getColumnName(i);
				if (!value.equals("NA")) {
					System.out.println(value + "  value" + " col  " + colName);
					compList.add(value);
				}
			}
		}
		return compList;
	}

	public static void main(String[] args) throws SQLException {

		MoreInfoDAO md = new MoreInfoDAO();
		ArrayList<String> list = md.getStatusResult("1001", "false");
		// LinkedHashMap<String, String> map = md.getData("The documentary credit has
		// not expired","1001");
		System.out.println(list.size() + "##########################");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("===============================================");
			System.out.println(list.get(i) + " key  ");
			System.out.println("===============================================");
		}
	}

}
