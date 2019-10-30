package com.axis.compliance;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.ResourceBundle;

import com.validate.compilanceValidatePojo;


public class ExecuteCompliance {

	private static ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);

	public static void main(String args[]) {
		try {
			Connection con = DBConnect.getConnection();
			if (con != null) {
				try {

					String query = rb.getString("RuleQuery");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					String transactionId = "1001";
					while (rs.next()) {
						
						compilanceValidatePojo comp = new compilanceValidatePojo();

						
						String ruleName = rs.getString("RULE_DESC");
						String mtRule = rs.getString("MT700");
						String invoiceRule = rs.getString("INVOICE");
						String insuranceRule = rs.getString("INSURANCE");
						String billRule = rs.getString("BILL_LADING");
						String packagingRule = rs.getString("PACKING_LIST");
						String originRule = rs.getString("CERT_OF_ORIGIN");

						ComplianceRules comRules = new ComplianceRules();
						switch (ruleName) {
						
						case "The documentary credit has not expired":
							if (!(mtRule.equals("")) && mtRule != null) {

								String mtQuery = rb.getString("MTQuery");

								PreparedStatement prepMT = con.prepareStatement(mtQuery);
								prepMT.setString(1, transactionId);

								ResultSet rsMT = prepMT.executeQuery();
								if (rsMT.next()) {
									String date = rsMT.getString(mtRule);
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
									Date date1 = sdf.parse(date);
									boolean result = comRules.checkExpiryGreater(date1);
									System.out.println(result+" expry date");
									comp.setCompRule(rsMT.getString(ruleName));
									comp.setResult(result);
									comp.setComment("comment");
								}
								
							}
							break;

						case "Check_POL":
							String fieldMT = rs.getString("MT_700");
							String fieldLading = rs.getString("BILL_LADING");
							String fieldInvoice = rs.getString("Invoice");
							String fieldInsurance = rs.getString("INSURANCE");
							String fieldPackaging = rs.getString("PACKING_LIST");
							String fieldOrigin = rs.getString("ORIGIN");

							boolean result1 = comRules.validatePOL_dispatch_charge(fieldMT, fieldLading, fieldInvoice,
									fieldInsurance, fieldPackaging, fieldOrigin);
							System.out.println(result1);
							break;

						case "Check_Dispatch":
							String fieldMT1 = rs.getString("MT_700");
							String fieldLading1 = rs.getString("BILL_LADING");
							String fieldInvoice1 = rs.getString("Invoice");
							String fieldInsurance1 = rs.getString("INSURANCE");
							String fieldPackaging1 = rs.getString("PACKING_LIST");
							String fieldOrigin1 = rs.getString("ORIGIN");

							boolean result7 = comRules.validatePOL_dispatch_charge(fieldMT1, fieldLading1,
									fieldInvoice1, fieldInsurance1, fieldPackaging1, fieldOrigin1);
							System.out.println(result7);
							break;

						case "Check_Taking_in_charge":
							String fieldMT2 = rs.getString("MT_700");
							String fieldLading2 = rs.getString("BILL_LADING");
							String fieldInvoice2 = rs.getString("Invoice");
							String fieldInsurance2 = rs.getString("INSURANCE");
							String fieldPackaging2 = rs.getString("PACKING_LIST");
							String fieldOrigin2 = rs.getString("ORIGIN");

							boolean result8 = comRules.validatePOL_dispatch_charge(fieldMT2, fieldLading2,
									fieldInvoice2, fieldInsurance2, fieldPackaging2, fieldOrigin2);
							System.out.println(result8);
							break;

						case "Check_POD":
							String fieldMT3 = rs.getString("MT_700");
							String fieldLading3 = rs.getString("BILL_LADING");
							String fieldInvoice3 = rs.getString("Invoice");
							String fieldInsurance3 = rs.getString("INSURANCE");
							String fieldPackaging3 = rs.getString("PACKING_LIST");
							String fieldOrigin3 = rs.getString("ORIGIN");

							boolean result9 = comRules.validatePOL_dispatch_charge(fieldMT3, fieldLading3,
									fieldInvoice3, fieldInsurance3, fieldPackaging3, fieldOrigin3);
							System.out.println(result9);
							break;

						case "Name_on_Bill_of_Lading_Validation":
							String lading = rs.getString("BILL_LADING");
							boolean result2 = comRules.checkCarrierName(lading);
							System.out.println(result2);
							break;

						case "Future_Date_Validation":

							/*
							 * SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); Date dateLading =
							 * sdf1.parse(rs.getString("BILL_LADING")); Date dateInvoice =
							 * sdf1.parse(rs.getString("INVOICE")); Date dateInsurance =
							 * sdf1.parse(rs.getString("INSURANCE")); Date datePackaging =
							 * sdf1.parse(rs.getString("PACKAGING")); Date dateOrigin =
							 * sdf1.parse(rs.getString("ORIGIN"));
							 * 
							 * boolean result3 = comRules.validateDate(dateLading, dateInvoice,
							 * dateInsurance, datePackaging, dateOrigin); System.out.println(result3);
							 * break;
							 */

						case "Shipper_Validation":
							String shipper = rs.getString("BILL_LADING");
							boolean result4 = comRules.checkShipperName(shipper);
							System.out.println(result4);
							break;

						case "Goods_Desc_Validation":
							boolean result5 = comRules.validateDescription(rs.getString("MT_700"),
									rs.getString("BILL_LADING"), rs.getString("INVOICE"), rs.getString("INSURANCE"),
									rs.getString("PACKING_LIST"), rs.getString("ORIGIN"));
							System.out.println(result5);
							break;

						case "Shipment_Date/ Date_of_Issue_BL/On_board_Date":
							/*
							 * SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy"); Date dateMT =
							 * sdf2.parse(rs.getString("MT_700")); Date dateOfLading =
							 * sdf2.parse(rs.getString("BILL_LADING")); boolean result6 =
							 * comRules.validateShipmentDate(dateMT, dateOfLading);
							 * System.out.println(result6); break;
							 */
						case "Invoice_Document_Title_Validation":
							boolean result10 = comRules.titleValidation(rs.getString("MT700"), rs.getString("INVOICE"));
							System.out.println(result10);
							break;

						case "validateConsigneeName":
							boolean result11 = comRules.validateConsigneeName(rs.getString("BILL_LADING"),
									rs.getString("INVOICE"), rs.getString("PACKAGING_LIST"), rs.getString("ORIGIN"));
							System.out.println(result11);
							break;

						case "validateInsuranceDate":
							/*
							 * SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy"); Date shipmentMT =
							 * sdf3.parse(rs.getString("MT_700")); Date shipmentLading =
							 * sdf3.parse(rs.getString("BILL_LADING")); Date insurance =
							 * sdf3.parse(rs.getString("INSURANCE")); boolean result12 =
							 * comRules.validateInsuranceDate(shipmentMT, shipmentLading, insurance);
							 * System.out.println(result12); break;
							 */

						default:
							System.out.println("Rule Execution not created");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					con.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
