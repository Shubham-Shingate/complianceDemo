package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KVPTable1 {
	
	@JsonProperty("KeyWidth")
	 private String KeyWidth;
	
		@JsonProperty("ValueConfidence")
	    private String ValueConfidence;
	
		@JsonProperty("ValueStartY")
	    private String ValueStartY;
	
		@JsonProperty("KeyStartY")
	    private String KeyStartY;
	
		@JsonProperty("KeyStartX")
	    private String KeyStartX;
	
		@JsonProperty("Sensitivity")
	    private String Sensitivity;
	
		@JsonProperty("ValueWidth")
	    private String ValueWidth;
	
		@JsonProperty("KeyHeight")
	    private String KeyHeight;
	
		@JsonProperty("Value")
	    private String Value;
	
		@JsonProperty("KeyConfidence")
	    private String KeyConfidence;
	
		@JsonProperty("EditedValue")
	    private String EditedValue;
	
		@JsonProperty("ValueHeight")
	    private String ValueHeight;
	
		@JsonProperty("Key")
	    private String Key;
	
		@JsonProperty("ValueStartX")
	    private String ValueStartX;

	    public String getKeyWidth ()
	    {
	        return KeyWidth;
	    }

	    public void setKeyWidth (String KeyWidth)
	    {
	        this.KeyWidth = KeyWidth;
	    }

	    public String getValueConfidence ()
	    {
	        return ValueConfidence;
	    }

	    public void setValueConfidence (String ValueConfidence)
	    {
	        this.ValueConfidence = ValueConfidence;
	    }

	    public String getValueStartY ()
	    {
	        return ValueStartY;
	    }

	    public void setValueStartY (String ValueStartY)
	    {
	        this.ValueStartY = ValueStartY;
	    }

	    public String getKeyStartY ()
	    {
	        return KeyStartY;
	    }

	    public void setKeyStartY (String KeyStartY)
	    {
	        this.KeyStartY = KeyStartY;
	    }

	    public String getKeyStartX ()
	    {
	        return KeyStartX;
	    }

	    public void setKeyStartX (String KeyStartX)
	    {
	        this.KeyStartX = KeyStartX;
	    }

	    public String getSensitivity ()
	    {
	        return Sensitivity;
	    }

	    public void setSensitivity (String Sensitivity)
	    {
	        this.Sensitivity = Sensitivity;
	    }

	    public String getValueWidth ()
	    {
	        return ValueWidth;
	    }

	    public void setValueWidth (String ValueWidth)
	    {
	        this.ValueWidth = ValueWidth;
	    }

	    public String getKeyHeight ()
	    {
	        return KeyHeight;
	    }

	    public void setKeyHeight (String KeyHeight)
	    {
	        this.KeyHeight = KeyHeight;
	    }

	    public String getValue ()
	    {
	        return Value;
	    }

	    public void setValue (String Value)
	    {
	        this.Value = Value;
	    }

	    public String getKeyConfidence ()
	    {
	        return KeyConfidence;
	    }

	    public void setKeyConfidence (String KeyConfidence)
	    {
	        this.KeyConfidence = KeyConfidence;
	    }

	    public String getEditedValue ()
	    {
	        return EditedValue;
	    }

	    public void setEditedValue (String EditedValue)
	    {
	        this.EditedValue = EditedValue;
	    }

	    public String getValueHeight ()
	    {
	        return ValueHeight;
	    }

	    public void setValueHeight (String ValueHeight)
	    {
	        this.ValueHeight = ValueHeight;
	    }

	    public String getKey ()
	    {
	        return Key;
	    }

	    public void setKey (String Key)
	    {
	        this.Key = Key;
	    }

	    public String getValueStartX ()
	    {
	        return ValueStartX;
	    }

	    public void setValueStartX (String ValueStartX)
	    {
	        this.ValueStartX = ValueStartX;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [KeyWidth = "+KeyWidth+", ValueConfidence = "+ValueConfidence+", ValueStartY = "+ValueStartY+", KeyStartY = "+KeyStartY+", KeyStartX = "+KeyStartX+", Sensitivity = "+Sensitivity+", ValueWidth = "+ValueWidth+", KeyHeight = "+KeyHeight+", Value = "+Value+", KeyConfidence = "+KeyConfidence+", EditedValue = "+EditedValue+", ValueHeight = "+ValueHeight+", Key = "+Key+", ValueStartX = "+ValueStartX+"]";
	    }
}
