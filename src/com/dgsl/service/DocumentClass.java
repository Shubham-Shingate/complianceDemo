package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentClass
{
	@JsonProperty("ClassMatch")
    private String ClassMatch;
	
	@JsonProperty("ClassificationWords")
    private String[] ClassificationWords;
	
	@JsonProperty("Actual")
    private String Actual;
	
	@JsonProperty("ID")
    private String ID;

    public String getClassMatch ()
    {
        return ClassMatch;
    }

    public void setClassMatch (String ClassMatch)
    {
        this.ClassMatch = ClassMatch;
    }

    public String[] getClassificationWords ()
    {
        return ClassificationWords;
    }

    public void setClassificationWords (String[] ClassificationWords)
    {
        this.ClassificationWords = ClassificationWords;
    }

    public String getActual ()
    {
        return Actual;
    }

    public void setActual (String Actual)
    {
        this.Actual = Actual;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ClassMatch = "+ClassMatch+", ClassificationWords = "+ClassificationWords+", Actual = "+Actual+", ID = "+ID+"]";
    }
}
			
			