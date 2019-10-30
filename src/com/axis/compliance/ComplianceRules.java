package com.axis.compliance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

			if (d2.compareTo(d1) > 0) {

				return true;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkCarrierName(String carrName) {
		if (carrName != null && !(carrName.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateDate(Date dateBillOfLading, Date dateInvoice, Date dateInsurance, Date datePackaging,
			Date dateOrigin) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");
			Date today = new Date();
			Date d1 = sdf.parse(sdf.format(today));
			Date d2 = sdf.parse(sdf.format(dateBillOfLading));
			Date d3 = sdf.parse(sdf.format(dateInvoice));
			Date d4 = sdf.parse(sdf.format(dateInsurance));
			Date d5 = sdf.parse(sdf.format(datePackaging));
			Date d6 = sdf.parse(sdf.format(dateOrigin));

			if (d2.compareTo(d1) <= 0 && d3.compareTo(d1) <= 0 && d4.compareTo(d1) <= 0 && d5.compareTo(d1) <= 0
					&& d6.compareTo(d1) <= 0) {

				return true;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean noFutureDate(Date dateBillOfLading, Date dateInvoice, Date dateInsurance, Date datePackaging,
			Date dateOrigin) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");
			Date today = new Date();
			Date d1 = sdf.parse(sdf.format(today));
			Date d2 = sdf.parse(sdf.format(dateBillOfLading));
			Date d3 = sdf.parse(sdf.format(dateInvoice));
			Date d4 = sdf.parse(sdf.format(dateInsurance));
			Date d5 = sdf.parse(sdf.format(datePackaging));
			Date d6 = sdf.parse(sdf.format(dateOrigin));

			if (d2.compareTo(d1) > 0 && d3.compareTo(d1) > 0 && d4.compareTo(d1) > 0 && d5.compareTo(d1) > 0
					&& d6.compareTo(d1) > 0) {

				return true;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkShipperName(String carrName) {
		if (carrName != null && !(carrName.equals("")) && carrName.contains("to the order of shipper")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateDescription(String mt, String lading, String invoice, String insurance, String packaging,
			String origin) {
		if (mt.equals(lading) && mt.equals(invoice) && mt.equals(insurance) && mt.equals(packaging)
				&& mt.equals(origin)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validatePOL_dispatch_charge(String mt, String lading, String invoice, String insurance,
			String packaging, String origin) {
		if (mt.equals(lading) && mt.equals(insurance) && mt.equals(packaging) && mt.equals(invoice)
				&& mt.equals(origin)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateShipmentDate(Date date1, Date date2) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");

			Date d2 = sdf.parse(sdf.format(date1));
			Date d3 = sdf.parse(sdf.format(date2));

			if (d3.compareTo(d2) <= 0) {

				return true;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean titleValidation(String mt, String invoice) {
		if(invoice!=null && invoice!="" && mt !=null && mt!=null) {
			if (mt.equals(invoice)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean validateConsigneeName(String consigneeBL, String consigneeInvoice, String consigneePackaging,
			String consigneeOrigin) {
		if (consigneeBL.equals(consigneeInvoice) && consigneeBL.equals(consigneePackaging)
				&& consigneeBL.equals(consigneeOrigin)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateInsuranceDate(Date dateShipmentMT700, Date dateShipmentLading, Date dateInsurance) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");

			Date d1 = sdf.parse(sdf.format(dateShipmentMT700));
			Date d2 = sdf.parse(sdf.format(dateShipmentLading));
			Date d3 = sdf.parse(sdf.format(dateInsurance));

			if (d2.compareTo(d3) >= 0 && d1.compareTo(d3) >= 0) {

				return true;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateCurrency(String mtCurrency, String invoiceCurrency, String insuranceCurrency,
			String packingCurrency) {

		if (mtCurrency.equals(insuranceCurrency) && mtCurrency.equals(invoiceCurrency)
				&& mtCurrency.equals(packingCurrency)) {
			return true;
		}
		return false;
	}

	public boolean validateConfirmation(String mt49) {
		if ((mt49 != null && mt49.equals("")) || mt49.contains("confirm") || mt49.contains("may add")) {

			return true;
		}
		return false;

	}

	public boolean shipmentDateValidation(Date expDate, Date shipDate, String days, Date shipmentDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, shipDate.getDate());
		cal.set(Calendar.MONTH, shipDate.getMonth());
		cal.set(Calendar.YEAR, shipDate.getYear());
		cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(days));
		System.out.println(cal.getTime());
		System.out.println(shipmentDate);
		if ((cal.getTime().compareTo(shipmentDate)) > 0) {
			return true;
		}
		return false;
	}

	public boolean discripanceValidation(String key) {
		if (key != null && (key.contains("Discrepancy") || key.contains("discrepancies")
				|| key.contains("Discrepancies") || key.contains("discrepancy"))) {
			return true;
		}
		return false;
	}

	public boolean field44EValidation(String key) {
		if (key != null
				&& (key.contains("BL") || key.contains("NNSW") || key.contains("CPBL") || key.contains("AWP"))) {
			return true;
		}
		return false;
	}

	public boolean field44AValidation(String key) {
		if (key != null && (key.contains("MMTD") || key.contains("Courier receipt") || key.contains("postal receipt")
				|| key.contains("cert of posting"))) {
			return true;
		}
		return false;
	}

	public boolean field41AValidation(String key) {
		if (key != null && (key.contains("BY NEGOTIATION"))) {
			return true;
		}
		return false;
	}

	public boolean field41A_Acceptance_Validation(String key) {
		if (key != null && (key.contains("BY ACCEPTANCE"))) {
			return true;
		}
		return false;
	}

	// START CHANGES DONE BY SUBHASIS
	public boolean validatePolicyProvided(String mtCol) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateDocRecvd(String[] arr1, String[] arr2) {
		// TODO Auto-generated method stub
		if (arr1[0] == arr2[0] && arr1[1] == arr2[1]) {
			return true;
		}
		return false;
	}
	// END CHANGES DONE BY SUBHASIS
}
