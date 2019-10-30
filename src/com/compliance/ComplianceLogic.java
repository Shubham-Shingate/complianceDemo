package com.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.database.DBConnect;

public class ComplianceLogic 
{
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	ResultSetMetaData rsmd=null;

	public static void main(String[] args) throws SQLException 
	{
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		String colname="";
		String transID="1001";
		
		CompilanceDAO cd = new CompilanceDAO();
		ComplanceValidate cv = new ComplanceValidate();
		
	    con=DBConnect.getConnection();
		pst = con.prepareStatement("SELECT * FROM DL_MASTERULETABLE where PROCESSID in ('DCC3')");
		rs = pst.executeQuery();
		rsmd=rs.getMetaData();
		int count=rsmd.getColumnCount();
		while(rs.next()) {
			
			for(int i=1;i<=count;i++) {
				if(rsmd.getColumnName(i).equals("PROCESSID") || rsmd.getColumnName(i).equals("RULE_DESC")){	
				}
				else {
					colname = rs.getString(rsmd.getColumnName(i))==""?"":rs.getString(rsmd.getColumnName(i));
					if(colname!="" && colname!=null && !colname.isEmpty()) {
						System.out.println(colname+"  "+rsmd.getColumnName(i)+"  "+i);
					/*	String consigneeNameBL = cd.getColValue(rsmd.getColumnLabel(i), colname, transID);
						String consigneeNamePL =
						String ConsigneeNameCertiOfOrigin =
						cv.consigneeNameVerification(consigneeNameBL, consigneeNamePL, ConsigneeNameCertiOfOrigin)*/
					}
				}
			}
			
		}
		
		
	}

}


























