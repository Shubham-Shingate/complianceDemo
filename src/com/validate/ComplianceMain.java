package com.validate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class ComplianceMain {
	
	public HashMap<String, Boolean> getComValidationResult() throws ParseException{
		String consigneeNameBL = "Nikita Sawant";
		String consigneeNamePL = "Nikita sawant";
		String DateInsurance = "20-05-2019";
		String DateShipmentMT = "20-05-2019";
		String DateShipmentBL = "20-05-2019";
		String currencyMT700 = "7855.2";
		String currencyInsurance = "755.2";
		String nameMT = "Nikita Sawant";
		String nameInsurance = "Nikita sawant";
		String currencyMTAll = "1000";
		String currencyInvoiceAll = "100";
		String currencyInsuranceAll = "1000";
		String currencyPLAll = "1000";
		String shipmentDateBL = "20-04-2019";
		String expiryDateBL = "20-05-2019";
		String periodOfPeresentation = "30";
		String expiryBank= "SBI BANK";
		String issuingBank= "BOB BANK";
		String negotiatingBank = "SBI BANK";
		String MT700 = "hie shfs discrepancy";

		ComplanceValidate cv = new ComplanceValidate();
	   /* System.out.println("nameVerification :: "+cv.nameVerification(consigneeNameBL, consigneeNamePL));
	    System.out.println("insuranceDateValidate :: "+cv.insuranceDateValidate(DateInsurance, DateShipmentMT, DateShipmentBL));
	    System.out.println("currencyValidate :: "+cv.currencyValidate(currencyMT700, currencyInsurance));
	    System.out.println("nameValidateMTAndInsurance :: "+cv.nameValidateMTAndInsurance(nameMT, nameInsurance));
	    System.out.println("currencyValidateAcrossAll :: "+cv.currencyValidateAcrossAll(currencyMTAll, currencyInvoiceAll, currencyInsuranceAll, currencyPLAll));
	    System.out.println( "DiffDatePOPValiadte :: "+cv.DiffDatePOPValiadte(expiryDateBL, shipmentDateBL, periodOfPeresentation)); 
		System.out.println("expiryBankNameCheck :: "+cv.expiryBankNameCheck(expiryBank, issuingBank, negotiatingBank));
		System.out.println("discrepancyCheck :: "+cv.discrepancyCheck(MT700));*/
		
		HashMap<String,Boolean> compMap = new HashMap<String,Boolean>();
		/*compMap.put("Name Verification",cv.nameVerification(consigneeNameBL, consigneeNamePL));
		compMap.put( "Insurance DateValidate",cv.insuranceDateValidate(DateInsurance, DateShipmentMT, DateShipmentBL));
		compMap.put( "Currency Validate",cv.currencyValidate(currencyMT700, currencyInsurance));
		compMap.put( "Name ValidateMT And Insurance",cv.nameValidateMTAndInsurance(nameMT, nameInsurance));
		compMap.put( "Currency Validate Across All",cv.currencyValidateAcrossAll(currencyMTAll, currencyInvoiceAll, currencyInsuranceAll, currencyPLAll));
		compMap.put( "Diff Date POP Valiadte",cv.DiffDatePOPValiadte(expiryDateBL, shipmentDateBL, periodOfPeresentation));
		compMap.put( "Expiry Bank Name Check",cv.expiryBankNameCheck(expiryBank, issuingBank, negotiatingBank));
		compMap.put( "DiscrepancyCheck",cv.discrepancyCheck(MT700));*/
		
		compMap.put("Name Verification",cv.nameVerification("", "",""));
		compMap.put( "Insurance DateValidate",cv.insuranceDateValidate("", "", ""));
		compMap.put( "Currency Validate",cv.currencyValidate("", ""));
		compMap.put( "Name ValidateMT And Insurance",cv.nameValidateMTAndInsurance("", ""));
		compMap.put( "Currency Validate Across All",cv.currencyValidateAcrossAll("", "", "", ""));
		compMap.put( "Diff Date POP Valiadte",cv.DiffDatePOPValiadte("", "", ""));
		compMap.put( "Expiry Bank Name Check",cv.expiryBankNameCheck("", "", ""));
		compMap.put( "DiscrepancyCheck",cv.discrepancyCheck(""));
		
		for(Map.Entry<String, Boolean> m:compMap.entrySet()){    
		       System.out.println(m.getKey()+" ---------------- "+m.getValue());    
		      }  
		return compMap;
	}

	public static void main(String[] args) throws ParseException {
		ComplianceMain cm = new ComplianceMain();
		cm.getComValidationResult();
	}

}
