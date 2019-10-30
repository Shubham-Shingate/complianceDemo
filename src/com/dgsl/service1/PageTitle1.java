package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageTitle1 {
	
	
	@JsonProperty("Confidence")
	private String Confidence;
	
	@JsonProperty("Title")
    private String[] Title;
	
	@JsonProperty("Trained")
    private String Trained;

    public String getConfidence ()
    {
        return Confidence;
    }

    public void setConfidence (String Confidence)
    {
        this.Confidence = Confidence;
    }

    public String[] getTitle ()
    {
        return Title;
    }

    public void setTitle (String[] Title)
    {
        this.Title = Title;
    }

    public String getTrained ()
    {
        return Trained;
    }

    public void setTrained (String Trained)
    {
        this.Trained = Trained;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Confidence = "+Confidence+", Title = "+Title+", Trained = "+Trained+"]";
    }
}
