package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageInfo
{

	@JsonProperty("PageID")
    private String PageID;

	@JsonProperty("Language")
    private String[] Language;

	@JsonProperty("PageStartY")
    private String PageStartY;

	@JsonProperty("PageStartX")
    private String PageStartX;

	@JsonProperty("PageOCRConfidence")
    private String PageOCRConfidence;

	@JsonProperty("PageNo")
    private String PageNo;

	@JsonProperty("PageHeight")
    private String PageHeight;

	@JsonProperty("PageWidth")
    private String PageWidth;

    public String getPageID ()
    {
        return PageID;
    }

    public void setPageID (String PageID)
    {
        this.PageID = PageID;
    }

    public String[] getLanguage ()
    {
        return Language;
    }

    public void setLanguage (String[] Language)
    {
        this.Language = Language;
    }

    public String getPageStartY ()
    {
        return PageStartY;
    }

    public void setPageStartY (String PageStartY)
    {
        this.PageStartY = PageStartY;
    }

    public String getPageStartX ()
    {
        return PageStartX;
    }

    public void setPageStartX (String PageStartX)
    {
        this.PageStartX = PageStartX;
    }

    public String getPageOCRConfidence ()
    {
        return PageOCRConfidence;
    }

    public void setPageOCRConfidence (String PageOCRConfidence)
    {
        this.PageOCRConfidence = PageOCRConfidence;
    }

    public String getPageNo ()
    {
        return PageNo;
    }

    public void setPageNo (String PageNo)
    {
        this.PageNo = PageNo;
    }

    public String getPageHeight ()
    {
        return PageHeight;
    }

    public void setPageHeight (String PageHeight)
    {
        this.PageHeight = PageHeight;
    }

    public String getPageWidth ()
    {
        return PageWidth;
    }

    public void setPageWidth (String PageWidth)
    {
        this.PageWidth = PageWidth;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PageID = "+PageID+", Language = "+Language+", PageStartY = "+PageStartY+", PageStartX = "+PageStartX+", PageOCRConfidence = "+PageOCRConfidence+", PageNo = "+PageNo+", PageHeight = "+PageHeight+", PageWidth = "+PageWidth+"]";
    }
}
			
			
