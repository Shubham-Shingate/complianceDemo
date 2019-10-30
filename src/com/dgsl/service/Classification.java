package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Classification
{
	@JsonProperty("DocumentLanguage")
    private String DocumentLanguage;

	@JsonProperty("PageTitle")
    private PageTitle PageTitle;

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

    public PageTitle getPageTitle ()
    {
        return PageTitle;
    }

    public void setPageTitle (PageTitle PageTitle)
    {
        this.PageTitle = PageTitle;
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
        return "ClassPojo [DocumentLanguage = "+DocumentLanguage+", PageTitle = "+PageTitle+", DocumentClass = "+DocumentClass+"]";
    }
}
