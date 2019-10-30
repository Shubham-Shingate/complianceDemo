package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status1 {
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("messageId")
    private String messageId;
	
	@JsonProperty("message")
    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMessageId ()
    {
        return messageId;
    }

    public void setMessageId (String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", messageId = "+messageId+", message = "+message+"]";
    }

}
