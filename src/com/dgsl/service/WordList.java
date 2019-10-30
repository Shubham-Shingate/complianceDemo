package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordList
{

	@JsonProperty("WordOCRConfidence")
    private String WordOCRConfidence;

	@JsonProperty("WordID")
    private String WordID;

	@JsonProperty("WordHeight")
    private String WordHeight;

	@JsonProperty("underline")
    private String underline;

	@JsonProperty("WordFontSize")
    private String WordFontSize;

	@JsonProperty("WordStartY")
    private String WordStartY;

	@JsonProperty("WordStartX")
    private String WordStartX;

	@JsonProperty("italics")
    private String italics;

	@JsonProperty("bold")
    private String bold;

	@JsonProperty("FontSize")
    private String FontSize;

	@JsonProperty("WordWidth")
    private String WordWidth;

	@JsonProperty("WordFontSizeGroup")
    private String WordFontSizeGroup;

	@JsonProperty("WordCharN")
    private String WordCharN;

	@JsonProperty("WordValue")
    private String WordValue;
	
	@JsonProperty("CharListrunlist")
    private String[] CharListrunlist;


    public String[] getCharListrunlist() {
		return CharListrunlist;
	}

	public void setCharListrunlist(String[] charListrunlist) {
		CharListrunlist = charListrunlist;
	}

	public String getWordOCRConfidence ()
    {
        return WordOCRConfidence;
    }

    public void setWordOCRConfidence (String WordOCRConfidence)
    {
        this.WordOCRConfidence = WordOCRConfidence;
    }

    public String getWordID ()
    {
        return WordID;
    }

    public void setWordID (String WordID)
    {
        this.WordID = WordID;
    }

    public String getWordHeight ()
    {
        return WordHeight;
    }

    public void setWordHeight (String WordHeight)
    {
        this.WordHeight = WordHeight;
    }

    public String getUnderline ()
    {
        return underline;
    }

    public void setUnderline (String underline)
    {
        this.underline = underline;
    }

    public String getWordFontSize ()
    {
        return WordFontSize;
    }

    public void setWordFontSize (String WordFontSize)
    {
        this.WordFontSize = WordFontSize;
    }

    public String getWordStartY ()
    {
        return WordStartY;
    }

    public void setWordStartY (String WordStartY)
    {
        this.WordStartY = WordStartY;
    }

    public String getWordStartX ()
    {
        return WordStartX;
    }

    public void setWordStartX (String WordStartX)
    {
        this.WordStartX = WordStartX;
    }

    public String getItalics ()
    {
        return italics;
    }

    public void setItalics (String italics)
    {
        this.italics = italics;
    }

    public String getBold ()
    {
        return bold;
    }

    public void setBold (String bold)
    {
        this.bold = bold;
    }

    public String getFontSize ()
    {
        return FontSize;
    }

    public void setFontSize (String FontSize)
    {
        this.FontSize = FontSize;
    }

    public String getWordWidth ()
    {
        return WordWidth;
    }

    public void setWordWidth (String WordWidth)
    {
        this.WordWidth = WordWidth;
    }

    public String getWordFontSizeGroup ()
    {
        return WordFontSizeGroup;
    }

    public void setWordFontSizeGroup (String WordFontSizeGroup)
    {
        this.WordFontSizeGroup = WordFontSizeGroup;
    }

    public String getWordCharN ()
    {
        return WordCharN;
    }

    public void setWordCharN (String WordCharN)
    {
        this.WordCharN = WordCharN;
    }

    public String getWordValue ()
    {
        return WordValue;
    }

    public void setWordValue (String WordValue)
    {
        this.WordValue = WordValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [WordOCRConfidence = "+WordOCRConfidence+", WordID = "+WordID+", WordHeight = "+WordHeight+", underline = "+underline+", WordFontSize = "+WordFontSize+", WordStartY = "+WordStartY+", WordStartX = "+WordStartX+", italics = "+italics+", bold = "+bold+", FontSize = "+FontSize+", WordWidth = "+WordWidth+", WordFontSizeGroup = "+WordFontSizeGroup+", WordCharN = "+WordCharN+", WordValue = "+WordValue+"]";
    }
}
			
			
