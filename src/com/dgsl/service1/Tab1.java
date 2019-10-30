package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tab1 {
	
	
		@JsonProperty("leader")
		private String leader;
	
		@JsonProperty("position")
	    private String position;

	    public String getLeader ()
	    {
	        return leader;
	    }

	    public void setLeader (String leader)
	    {
	        this.leader = leader;
	    }

	    public String getPosition ()
	    {
	        return position;
	    }

	    public void setPosition (String position)
	    {
	        this.position = position;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [leader = "+leader+", position = "+position+"]";
	    }
}
