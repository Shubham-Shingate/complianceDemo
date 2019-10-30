package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Classification1 {
	
		@JsonProperty("DocumentLanguage")
	 private String DocumentLanguage;
	
		@JsonProperty("PageTitle1")
	    private PageTitle1 PageTitle1;
	
		@JsonProperty("DocumentClass")
	    private DocumentClass DocumentClass;

	    public String getDocumentLanguage ()
	    {
	        return DocumentLanguage;
	    }

	    public void setDocumentLanguage (String DocumentLanguage)
	    {
	        this.DocumentLanguage = DocumentLanguage;
	    }

	    public PageTitle1 getPageTitle1 ()
	    {
	        return PageTitle1;
	    }

	    public void setPageTitle1 (PageTitle1 PageTitle1)
	    {
	        this.PageTitle1 = PageTitle1;
	    }

	    public DocumentClass getDocumentClass ()
	    {
	        return DocumentClass;
	    }

	    public void setDocumentClass (DocumentClass DocumentClass)
	    {
	        this.DocumentClass = DocumentClass;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [DocumentLanguage = "+DocumentLanguage+", PageTitle1 = "+PageTitle1+", DocumentClass = "+DocumentClass+"]";
	    }
}
