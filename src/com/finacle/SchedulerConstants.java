/**
 * 
 */
package com.finacle;

/**
 * @author subhasis.swain
 *
 */
public enum SchedulerConstants {
	
	
	
	//Details
	SEGMENT("SEGMENT"),
	GST_NUM("GST_NUM"),
	CBO_UNIT("CBO_UNIT"),			
	IFSC("IFSC"),
	CREDIT_RATING("CREDIT_RATING"),
	HEALTH_CODE("HEALTH_CODE"),
	CBO_SRM("CBO_SRM"),
	CREDIT_MONITER("CREDIT_MONITER"),
	PRI_RELATION("PRI_RELATION"),
	SECO_RELATION("SECO_RELATION"),
	TER_RELATION("TER_RELATION"),
	AREA_CRDIT("AREA_CRDIT"),
	IBG("IBG"),
	ACCOUNTNO("ACCOUNTNO"),
	SCHEME_CODE("SCHEME_CODE"),
	APP_REQ("APP_REQ"),
	GST_FLAG("GST_FLAG"),
	INV_ACCNAME("INV_ACCNAME"),
	ORG_SOL_ID("ORG_SOL_ID");



   

    private String url;

    SchedulerConstants(String url) {
        this.url = url;
    }

    public String value() {
        return url;
    }








}
