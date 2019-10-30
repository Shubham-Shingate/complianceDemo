package com.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;


public class ComplanceValidate {
	
	HashMap<String, Boolean> map ;
	
	public HashMap<String, Boolean> getMap() {
		return map;
	}


	public void setMap(HashMap<String, Boolean> map) {
		this.map = map;
	}


	public Boolean nameVerification(String consigneeNameBL, String consigneeNamePL,String ConsigneeNameCertiOfOrigin) 
	 {
		Boolean flag = false;
		 if(consigneeNameBL!=null && !consigneeNameBL.isEmpty() && consigneeNamePL!=null 
				 && !consigneeNamePL.isEmpty() && ConsigneeNameCertiOfOrigin!=null && ConsigneeNameCertiOfOrigin.isEmpty())
		 {
				if (consigneeNameBL.equalsIgnoreCase(consigneeNamePL)) {
					flag  = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }


	public Boolean insuranceDateValidate( String DateInsurance, String DateShipmentMT, String DateShipmentBL) throws ParseException 
	 {
		Boolean flag = false;
		 
		 if(DateInsurance!=null && !DateInsurance.isEmpty() && !DateInsurance.equals("")
				 && DateShipmentMT!=null && !DateShipmentMT.isEmpty() && !DateShipmentMT.equals("")
				 && DateShipmentBL!=null && !DateShipmentBL.isEmpty() && !DateShipmentBL.equals(""))
		 {
			    Date DateOfInsurance = stringToDate(DateInsurance);
			    Date DateOfShipmentMT = stringToDate(DateShipmentMT);
			    Date DateOfShipmentBL = stringToDate(DateShipmentBL);
			 
		        if (DateOfInsurance.after(DateOfShipmentMT) || DateOfInsurance.equals(DateOfShipmentMT) ||
		        		DateOfInsurance.after(DateOfShipmentBL) || DateOfInsurance.equals(DateOfShipmentBL)) {
		            flag  = true;
		        }
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }

		return flag;
	 }
	
	public Boolean currencyValidate(String currencyMT700,String currencyInsurance) 
	 {
		Boolean flag = false;
		 
		 if(currencyMT700!=null && !currencyMT700.isEmpty() && currencyInsurance!=null && !currencyInsurance.isEmpty())
		 {

			 //Double currencyMT700Value = Double.parseDouble(currencyMT700);
			// Double currencyInsuranceValue = Double.parseDouble(currencyInsurance); 
			 if (currencyMT700.equals(currencyInsurance)) {

					flag = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }
	
	public Boolean nameValidateMTAndInsurance(String nameMT, String nameInsurance)
	 {
		Boolean flag = false;
		 if(nameMT!=null && !nameMT.isEmpty() && nameInsurance!=null && !nameInsurance.isEmpty() )
		 {
				if (nameMT.equalsIgnoreCase(nameInsurance)) {
					flag  = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }
	
	public Boolean currencyValidateAcrossAll(String currencyMTAll,String currencyInvoiceAll,String currencyInsuranceAll,String currencyPLAll)
	 {
		Boolean flag = false;
		 

			
		 if(currencyMTAll!=null && !currencyMTAll.isEmpty() && currencyInvoiceAll!=null && !currencyInvoiceAll.isEmpty()
				 && currencyInsuranceAll!=null && !currencyInsuranceAll.isEmpty() && currencyPLAll!=null && !currencyPLAll.isEmpty())
		 {
			 //Double currencyMTAllValue = Double.parseDouble(currencyMTAll);
			 //Double currencyInvoiceAllValue = Double.parseDouble(currencyInvoiceAll); 
			 //Double currencyInsuranceAllValue = Double.parseDouble(currencyInsuranceAll); 
			 //Double currencyPLAllValue = Double.parseDouble(currencyPLAll); 
			 if (currencyMTAll.equals(currencyInvoiceAll) && currencyMTAll.equals(currencyInsuranceAll)
					&& currencyMTAll.equals(currencyPLAll)) {
					flag = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }
	
	public Boolean DiffDatePOPValiadte( String expiryDateBL, String shipmentDateBL, String periodOfPeresentation) throws ParseException 
	 {
		Boolean flag = false;
		 long diffDays=0;
		 
		 if(expiryDateBL!=null && !shipmentDateBL.equals("") 
				 && shipmentDateBL!=null && !shipmentDateBL.equals("")
				 && periodOfPeresentation!=null && !periodOfPeresentation.equals(""))
		 {
			 LocalDate expir = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(stringToDate(expiryDateBL)),
				        DateTimeFormatter.ISO_LOCAL_DATE);
		     LocalDate ship = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(stringToDate(shipmentDateBL)),
				        DateTimeFormatter.ISO_LOCAL_DATE);
			 /*if(stringToDate(expiryDateBL).compareTo(stringToDate(shipmentDateBL))==1) 
			 {*/
				 Duration diff = Duration.between(ship.atStartOfDay(), expir.atStartOfDay());
				 diffDays = diff.toDays();
				 System.out.println(diffDays+" diffDays");
		    /* }
			 else 
			 {
			 
				 Duration diff = Duration.between(expir.atStartOfDay(), ship.atStartOfDay());
				 diffDays = diff.toDays();
			 }*/
			 if(diffDays==Long.parseLong(periodOfPeresentation)) 
			 {
				 flag = true; 
			 }
		 }
		 
		 else
		 {
			 System.out.println("provide all the required data");
		 }

		return flag;
	 }
	
	public Boolean expiryBankNameCheck(String expiryBank, String issuingBank, String negotiatingBank)
	 {
		Boolean flag = false;
		 if(expiryBank!=null && !expiryBank.isEmpty() && issuingBank!=null && !issuingBank.isEmpty() 
				 && negotiatingBank!=null && !negotiatingBank.isEmpty())
		 {
				if (expiryBank.equalsIgnoreCase(issuingBank) || expiryBank.equalsIgnoreCase(negotiatingBank)) {
					flag  = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }
	public Boolean discrepancyCheck(String MT700)
	 {
		Boolean flag = false;
		 if(MT700!=null && !MT700.isEmpty())
		 {
				if (MT700.toLowerCase().contains("discrepancy") || MT700.toLowerCase().contains("all discrepancies")
						|| MT700.toLowerCase().contains("minor discrepancy") || MT700.toLowerCase().contains("discrepancies")) {
					flag  = true;
				}
		 }
		 else
		 {
			 System.out.println("provide all the required data");
		 }
		return flag;
	 }
	
	
	public Date stringToDate(String strDate) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");// date format for original date
	    Date date =sdf.parse(strDate);
	    return date;
	}

}
