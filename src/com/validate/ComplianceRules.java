package com.validate;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ComplianceRules {

	final static Logger logger = Logger.getLogger(ComplianceRules.class.getName());

	public boolean checkExpiryGreater(Date expDate) {
			try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date today = new Date();
			Date d1 = sdf.parse(sdf.format(today));
			Date d2 = sdf.parse(sdf.format(expDate));
			
			if(d2.compareTo(d1) > 0) {
				
				return true;
			}
			
			}catch (Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			return false;
	}
	 
	public boolean checkCarrierName(String carrName) {
		if(carrName != null && !(carrName.equals(""))) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateDate(Date date1, Date date2, Date date3) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");
			Date today = new Date();
			Date d1 = sdf.parse(sdf.format(today));
			Date d2 = sdf.parse(sdf.format(date1));
			Date d3 = sdf.parse(sdf.format(date2));
			Date d4 = sdf.parse(sdf.format(date3));
			
			if(d2.compareTo(d1) < 0 && d3.compareTo(d1) < 0 && d4.compareTo(d1) < 0) {
				
				return true;
			}
			
			}catch (Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			return false;
	}
	
	public boolean checkShipperName(String carrName) {
		if(carrName != null && !(carrName.equals("")) && carrName.contains( "to the order of shipper")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateDescription(String mt, String lading, String invoice, String insurance, String packaging) {
		if(mt.equals(lading) && mt.equals(invoice) && mt.equals(insurance) && mt.equals(packaging)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validatePOL_dispatch_charge(String mt, String lading,  String insurance, String packaging) {
		if(mt.equals(lading) && mt.equals(insurance) && mt.equals(packaging)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
