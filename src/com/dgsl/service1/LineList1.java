package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LineList1 {
	
	@JsonProperty("LineStartX")
	private String LineStartX;
	
	@JsonProperty("LineStartY")
    private String LineStartY;
	
	@JsonProperty("LineID")
    private String LineID;
	
	@JsonProperty("LineHeight")
    private String LineHeight;
	
	@JsonProperty("LineWidth")
    private String LineWidth;
	
	@JsonProperty("WordList")
    private WordList[] WordList;

    public String getLineStartX ()
    {
        return LineStartX;
    }

    public void setLineStartX (String LineStartX)
    {
        this.LineStartX = LineStartX;
    }

    public String getLineStartY ()
    {
        return LineStartY;
    }

    public void setLineStartY (String LineStartY)
    {
        this.LineStartY = LineStartY;
    }

    public String getLineID ()
    {
        return LineID;
    }

    public void setLineID (String LineID)
    {
        this.LineID = LineID;
    }

    public String getLineHeight ()
    {
        return LineHeight;
    }

    public void setLineHeight (String LineHeight)
    {
        this.LineHeight = LineHeight;
    }

    public String getLineWidth ()
    {
        return LineWidth;
    }

    public void setLineWidth (String LineWidth)
    {
        this.LineWidth = LineWidth;
    }

    public WordList[] getWordList ()
    {
        return WordList;
    }

    public void setWordList (WordList[] WordList)
    {
        this.WordList = WordList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [LineStartX = "+LineStartX+", LineStartY = "+LineStartY+", LineID = "+LineID+", LineHeight = "+LineHeight+", LineWidth = "+LineWidth+", WordList = "+WordList+"]";
    }
}
