
package com.dgsl.service;
import com.fasterxml.jackson.annotation.JsonProperty;




public class Data
{

	@JsonProperty("AIOutput")
	private String[] AIOutput;

	@JsonProperty("MandatoryInformation")
    private String MandatoryInformation;

	@JsonProperty("CustomerCode")
    private String CustomerCode;

	@JsonProperty("analyzerId")
    private String analyzerId;

	@JsonProperty("ExtraInformation")
    private String ExtraInformation;

	@JsonProperty("CustomerUniqueIdentifier")
    private String CustomerUniqueIdentifier;

	@JsonProperty("DocumentExtension")
    private String DocumentExtension;

	@JsonProperty("ErrorList")
    private String[] ErrorList;

	@JsonProperty("UserID")
    private String UserID;

	@JsonProperty("Classification")
    private Classification Classification;

	@JsonProperty("DSOutput")
    private String[] DSOutput;

	@JsonProperty("DocumentArrivalTime")
    private String DocumentArrivalTime;

	@JsonProperty("pageList")
    private PageList[] pageList;

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

    public String getMandatoryInformation ()
    {
        return MandatoryInformation;
    }

    public void setMandatoryInformation (String MandatoryInformation)
    {
        this.MandatoryInformation = MandatoryInformation;
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

    public String getExtraInformation ()
    {
        return ExtraInformation;
    }

    public void setExtraInformation (String ExtraInformation)
    {
        this.ExtraInformation = ExtraInformation;
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

    public Classification getClassification ()
    {
        return Classification;
    }

    public void setClassification (Classification Classification)
    {
        this.Classification = Classification;
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

    public PageList[] getPageList ()
    {
        return pageList;
    }

    public void setPageList (PageList[] pageList)
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
        return "ClassPojo [AIOutput = "+AIOutput+", MandatoryInformation = "+MandatoryInformation+", CustomerCode = "+CustomerCode+", analyzerId = "+analyzerId+", ExtraInformation = "+ExtraInformation+", CustomerUniqueIdentifier = "+CustomerUniqueIdentifier+", DocumentExtension = "+DocumentExtension+", ErrorList = "+ErrorList+", UserID = "+UserID+", Classification = "+Classification+", DSOutput = "+DSOutput+", DocumentArrivalTime = "+DocumentArrivalTime+", pageList = "+pageList+", createDate = "+createDate+", DocumentName = "+DocumentName+", DocumentOCRConfidence = "+DocumentOCRConfidence+"]";
    }
}
			