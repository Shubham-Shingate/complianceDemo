package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BACAMain1 {
	@JsonProperty("data")
	 private Data1 data;
	
	@JsonProperty("status")
	    private Status1 status;

	    public Data1 getData1 ()
	    {
	        return data;
	    }

	    public void setData1 (Data1 data)
	    {
	        this.data = data;
	    }

	    public Status1 getStatus1 ()
	    {
	        return status;
	    }

	    public void setStatus1 (Status1 status)
	    {
	        this.status = status;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [data = "+data+", status = "+status+"]";
	    }
}
