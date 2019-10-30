package com.compliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.database.DBConnect;

public class CompilanceDAO {
	
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	ResultSetMetaData rsmd=null;
	String colname="";
	
	public String getColValue(String table,String colName,String transID) throws SQLException {
		String result="111";
		String qry="SELECT "+colName+" FROM DL_"+table+" WHERE TRANSCTIONID="+transID;
		System.out.println(qry);
		con=DBConnect.getConnection();
		pst = con.prepareStatement(qry);
		rs = pst.executeQuery();
		rsmd=rs.getMetaData();
		int count=rsmd.getColumnCount();
		while(rs.next()) {	
			for(int i=1; i<=count; i++) {
					result=rs.getString(rsmd.getColumnName(i));
			}
		}
		return result;
		
				
	}
	public static void main(String[] args) throws SQLException{ 
		CompilanceDAO cd = new CompilanceDAO();
		String a = cd.getColValue("INVOICE", "CONSIGNEE_NAME", "1001");
		System.out.println(a);
   }

}
































