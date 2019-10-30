package com.axis.compliance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestComplianceRules {

	public static String checkExpiryDate(String date1, String date2) {
		try {
			DateFormat format = new SimpleDateFormat("yymmDD", Locale.ENGLISH);
			// Date d1 = format.parse(sDate1);
			// Date d2 = format.parse(sDate2);
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			if (d1.compareTo(d2) > 0) {
				return "expired";
			} else if (d1.compareTo(d2) < 0) {
				return "valid";
			} else {
				return "valid";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expired";

	}

	public static String checkNoOfDays(ArrayList<String> colData) {
		SimpleDateFormat myFormat = new SimpleDateFormat("yymmDD", Locale.ENGLISH);
		String result = "false";
		try {
			String date1 = colData.get(0);
			String date2 = colData.get(2);
			int days = Integer.parseInt(colData.get(1));
			Date d1 = myFormat.parse(date1);
			Date d2 = myFormat.parse(date2);
			long diff = d2.getTime() - d1.getTime();
			long noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			if (days == noOfDays) {
				result = "true";
			}
			System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String checkCurrency(ArrayList<String> colData) {
		String actualCurrency = colData.get(0);
		String result = "true";
		for (int i = 1; i < colData.size(); i++) {
			if (!actualCurrency.equals(colData.get(i))) {
				result = "false";
				break;
			}
		}
		return result;
	}

	public static String checkAmountClaimed(ArrayList<String> colData) {
		String result = "false";
		long creditAmt = Long.parseLong(colData.get(0));
		long coverLetterAmt = Long.parseLong(colData.get(1));
		long boeAmt = Long.parseLong(colData.get(2));
		long invoiceAmt = Long.parseLong(colData.get(3));
		if (coverLetterAmt <= creditAmt && boeAmt <= creditAmt && invoiceAmt <= creditAmt) {
			result = "true";
		}
		return result;
	}

	public static String checkConfirmation(String field) {
		String result = "false";
		if (field.equalsIgnoreCase("confirmation") || field.equalsIgnoreCase("may add")
				|| field.equalsIgnoreCase("without"))
			result = "true";
		return result;
	}

	public static String checkExpiryPlace(ArrayList<String> columnData) {
		String colval1 = columnData.get(0);
		String colval2 = columnData.get(1);
		String colval3 = columnData.get(2);
		String colval4 = columnData.get(3);
		System.out.println(
				"col1 :: " + colval1 + ", col2 :: " + colval2 + ", col3 :: " + colval3 + ", col4 :: " + colval4);
		if (colval1 != null) {
			if (colval1.equalsIgnoreCase(colval3) && colval2.equalsIgnoreCase(colval4)) {
				System.out.println("INSIDE IF");
				return "valid";
			}
		} else {
			if (colval2.equalsIgnoreCase(colval4)) {
				System.out.println("INSIDE ELSE");
				return "valid";
			}
		}
		System.out.println("LAST");
		return "expired";
	}

	public static String checkRequiredDocument(ArrayList<String> columnData) {
		String d1 = columnData.get(0).trim();
		String d2 = columnData.get(1).trim();
		if (d1 != null) {
			if (d1.equalsIgnoreCase("BL") || d1.equalsIgnoreCase("NNSW") || d1.equalsIgnoreCase("CPBL")
					|| d1.equalsIgnoreCase("AWB")) {
				return "valid";
			}
		}
		if (d2 != null) {
			if (d2.equalsIgnoreCase("BL") || d2.equalsIgnoreCase("NNSW") || d2.equalsIgnoreCase("CPBL")
					|| d2.equalsIgnoreCase("AWB")) {
				return "valid";
			}
		}
		return "expired";
	}

	public static String checkRequiredTransportDocument(ArrayList<String> columnData) {
		// TODO Auto-generated method stub
		// MMTD/Courier receipt/ postal Receipt/Cert of Posting
		String d1 = columnData.get(0).trim();
		String d2 = columnData.get(1).trim();
		if (d1 != null) {
			if (d1.equalsIgnoreCase("MMTD") || d1.equalsIgnoreCase("Courier receipt")
					|| d1.equalsIgnoreCase("postal Receipt") || d1.equalsIgnoreCase("Cert of Posting")) {
				return "valid";
			}
		}
		if (d2 != null) {
			if (d2.equalsIgnoreCase("MMTD") || d2.equalsIgnoreCase("Courier receipt")
					|| d2.equalsIgnoreCase("postal Receipt") || d2.equalsIgnoreCase("Cert of Posting")) {
				return "valid";
			}
		}
		return "expired";
	}

	public static String checkNegotiation(ArrayList<String> columnData) {
		// TODO Auto-generated method stub
		String d1 = columnData.get(0).trim().toLowerCase();
		if (d1.contains("negotiation")) {
			return "valid";
		}
		return "expired";
	}

}
