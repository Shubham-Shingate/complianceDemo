package com.finacle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.validate.ComplianceRules;
import com.validate.DBConnect;

public class FinacleValidationRule {
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	ResultSetMetaData rsmd=null;

	
	private static ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	final static Logger logger = Logger.getLogger(ComplianceRules.class.getName());
	
	/* ORIGIN SOL CHECK*/
	public Boolean OriginSolIDCheck(String org_sol_id,String cust_id) throws SQLException {
		Boolean flag = false;
		String db_org_sol_id="";
		if(org_sol_id!=null && !org_sol_id.isEmpty() && cust_id!=null && !cust_id.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("OriginSolIDCheck"));
			pst.setString(1, cust_id);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    	db_org_sol_id= rs.getString(rsmd.getColumnName(i));
			    }
			}
			if(db_org_sol_id.equalsIgnoreCase(org_sol_id)) {
				flag=true;
			}
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return flag;
	}
	
	/* RETRIVE DATA*/
	public String fetchAgainstCustID(String cust_id) throws SQLException {
		String json="",segment="",gst_num="";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		if(cust_id!=null && !cust_id.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("FETCH_AGAINST_CUST_ID"));
			pst.setString(1, cust_id);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
			    
			     if(rsmd.getColumnName(i).contains(SchedulerConstants.SEGMENT.value())) {
			    	 segment=rs.getString(rsmd.getColumnName(i));
			     }
			     if(rsmd.getColumnName(i).contains(SchedulerConstants.GST_NUM.value())) {
			    	 gst_num=rs.getString(rsmd.getColumnName(i));
			     }
			    
			    }
			}
			map.put(SchedulerConstants.APP_REQ.value(),fetchApprovalList(segment));
			map.put(SchedulerConstants.GST_FLAG.value(),fetchGSTFlag(gst_num));

			Gson gson = new Gson(); 
			json = gson.toJson(map); 
			System.out.println(json);
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return json;

	}
	
	/* GET APPROVAL_LIST AGAINST CUST_ID*/
	public String fetchApprovalList(String segment) throws SQLException {
		String approval="";
		String json="";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		if(segment!=null && !segment.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("fetchApprovalList"));
			pst.setString(1, segment);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    	approval =  rs.getString(rsmd.getColumnName(i));
			    	//map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));

			    }
			}
			/*Gson gson = new Gson(); 
			json = gson.toJson(map); 
			System.out.println(json);*/
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return approval;

	}
	
	/* GET GST_FLAG AGAINST GST_NUM */
	public String fetchGSTFlag(String gst_num) throws SQLException {
		String flag="";
		String json="";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		if(gst_num!=null && !gst_num.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("fetchGSTFlag"));
			pst.setString(1, gst_num);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    	flag =  rs.getString(rsmd.getColumnName(i));
			    	//map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
			    }
			}
			/*Gson gson = new Gson(); 
			json = gson.toJson(map); 
			System.out.println(json);*/
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return flag;

	}
	
	/* IFSC_CODE CHECK FROM DB AGAINST CUST_ID */
	public Boolean IFSCCheck(String cust_id, String IFSC_code) throws SQLException {
		Boolean flag = false;
		String db_IFSC_code="";
		if(IFSC_code!=null && !IFSC_code.isEmpty() && cust_id!=null && !cust_id.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("IFSCCheck"));
			pst.setString(1, cust_id);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    	db_IFSC_code= rs.getString(rsmd.getColumnName(i));
			    }
			}			
			if(db_IFSC_code.equalsIgnoreCase(db_IFSC_code)) {
				flag=true;
			}
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return flag;
	}
	
	/* GET invocation Acc Name against invocation Acc num*/
	public String getInvAccName(String inv_acc_no) throws SQLException {
		String json="";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		if(inv_acc_no!=null && !inv_acc_no.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("getInvAccName"));
			pst.setString(1, inv_acc_no);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
			    }
			}
			Gson gson = new Gson(); 
			json = gson.toJson(map); 
			System.out.println(json);
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return json;

	}
	
	/* GET ACC NUM AGAINST AMOUNT*/
	public String getAccNum(String amount) throws SQLException {
		String accNum="";
		String json="";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		if(amount!=null && !amount.isEmpty()) {
			con = DBConnect.getConnection();
			pst = con.prepareStatement(rb.getString("getAccNum"));
			pst.setString(1, amount);
			rs = pst.executeQuery();
			rsmd=rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
			    for (int i = 1; i <= count; i++) {
			    map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
			    }
			}
			Gson gson = new Gson(); 
			json = gson.toJson(map); 
			System.out.println(json);
			closeConnection(con, pst, rs);

		}else{
			System.out.println("provide all the required data");
		}
		return json;

	}
	
	
	/* CONNECTION CLOSING */
	public void closeConnection(Connection con, PreparedStatement pst,ResultSet rs) throws SQLException {
		if(rs!=null) {rs.close(); rs=null;}
		if(pst!=null) {pst.close(); pst=null;}
		if(con!=null) {con.close(); con=null;}
	}
	
	
	
	
	
	public static void main(String[] args) throws SQLException {

		FinacleValidationRule v = new FinacleValidationRule();
		v.fetchAgainstCustID("1001");
		System.out.println(v.IFSCCheck("1001", "ifsc test")+" ifcs check");
		System.out.println(v.getInvAccName("4567895544")+" invaccname check");
		System.out.println(v.getAccNum("45000")+" Accnum check");
		System.out.println(v.OriginSolIDCheck("SOL12345", "1001")+" solidcheck");
		//System.out.println(v.fetchApprovalList("segment test")+" app_list");
		//System.out.println(v.fetchGSTFlag("gst no test")+" fst_flag");

	}

}
