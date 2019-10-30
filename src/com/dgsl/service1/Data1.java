package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data1 {
	
	@JsonProperty("AIOutput")
	 private String[] AIOutput;
	
	@JsonProperty("CustomerCode")
	    private String CustomerCode;
	
	@JsonProperty("analyzerId")
	    private String analyzerId;
	
	@JsonProperty("CustomerUniqueIdentifier")
	    private String CustomerUniqueIdentifier;
	
	@JsonProperty("DocumentExtension")
	    private String DocumentExtension;
	
	@JsonProperty("ErrorList")
	    private String[] ErrorList;
	
	@JsonProperty("UserID")
	    private String UserID;
	
	@JsonProperty("Classification1")
	    private Classification1 Classification1;
	
	@JsonProperty("DSOutput")
	    private String[] DSOutput;
	
	@JsonProperty("DocumentArrivalTime")
	    private String DocumentArrivalTime;
	
	@JsonProperty("pageList")
	    private PageList1[] pageList;
	
	@JsonProperty("createDate")
	    private String createDate;
	
	@JsonProperty("DocumentName")
	    private String DocumentName;
	
	@JsonProperty("DocumentOCRConfidence")
	    private String DocumentOCRConfidence;

	    public String[] getAIOutput ()
	    {
	        return AIOutput;
	    }

	    public void setAIOutput (String[] AIOutput)
	    {
	        this.AIOutput = AIOutput;
	    }

	    public String getCustomerCode ()
	    {
	        return CustomerCode;
	    }

	    public void setCustomerCode (String CustomerCode)
	    {
	        this.CustomerCode = CustomerCode;
	    }

	    public String getAnalyzerId ()
	    {
	        return analyzerId;
	    }

	    public void setAnalyzerId (String analyzerId)
	    {
	        this.analyzerId = analyzerId;
	    }

	    public String getCustomerUniqueIdentifier ()
	    {
	        return CustomerUniqueIdentifier;
	    }

	    public void setCustomerUniqueIdentifier (String CustomerUniqueIdentifier)
	    {
	        this.CustomerUniqueIdentifier = CustomerUniqueIdentifier;
	    }

	    public String getDocumentExtension ()
	    {
	        return DocumentExtension;
	    }

	    public void setDocumentExtension (String DocumentExtension)
	    {
	        this.DocumentExtension = DocumentExtension;
	    }

	    public String[] getErrorList ()
	    {
	        return ErrorList;
	    }

	    public void setErrorList (String[] ErrorList)
	    {
	        this.ErrorList = ErrorList;
	    }

	    public String getUserID ()
	    {
	        return UserID;
	    }

	    public void setUserID (String UserID)
	    {
	        this.UserID = UserID;
	    }

	    public Classification1 getClassification1 ()
	    {
	        return Classification1;
	    }

	    public void setClassification1 (Classification1 Classification1)
	    {
	        this.Classification1 = Classification1;
	    }

	    public String[] getDSOutput ()
	    {
	        return DSOutput;
	    }

	    public void setDSOutput (String[] DSOutput)
	    {
	        this.DSOutput = DSOutput;
	    }

	    public String getDocumentArrivalTime ()
	    {
	        return DocumentArrivalTime;
	    }

	    public void setDocumentArrivalTime (String DocumentArrivalTime)
	    {
	        this.DocumentArrivalTime = DocumentArrivalTime;
	    }

	    public PageList1[] getPageList ()
	    {
	        return pageList;
	    }

	    public void setPageList (PageList1[] pageList)
	    {
	        this.pageList = pageList;
	    }

	    public String getCreateDate ()
	    {
	        return createDate;
	    }

	    public void setCreateDate (String createDate)
	    {
	        this.createDate = createDate;
	    }

	    public String getDocumentName ()
	    {
	        return DocumentName;
	    }

	    public void setDocumentName (String DocumentName)
	    {
	        this.DocumentName = DocumentName;
	    }

	    public String getDocumentOCRConfidence ()
	    {
	        return DocumentOCRConfidence;
	    }

	    public void setDocumentOCRConfidence (String DocumentOCRConfidence)
	    {
	        this.DocumentOCRConfidence = DocumentOCRConfidence;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [AIOutput = "+AIOutput+", CustomerCode = "+CustomerCode+", analyzerId = "+analyzerId+", CustomerUniqueIdentifier = "+CustomerUniqueIdentifier+", DocumentExtension = "+DocumentExtension+", ErrorList = "+ErrorList+", UserID = "+UserID+", Classification1 = "+Classification1+", DSOutput = "+DSOutput+", DocumentArrivalTime = "+DocumentArrivalTime+", pageList = "+pageList+", createDate = "+createDate+", DocumentName = "+DocumentName+", DocumentOCRConfidence = "+DocumentOCRConfidence+"]";
	    }
}
