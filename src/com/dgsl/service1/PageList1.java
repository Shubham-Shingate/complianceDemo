package com.dgsl.service1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageList1 {
	 private PageInfo1 PageInfo1;

		@JsonProperty("BlockList")
	    private BlockList1[] BlockList;

		@JsonProperty("KVPTable")
	    private KVPTable1[] KVPTable1;

	    public PageInfo1 getPageInfo1 ()
	    {
	        return PageInfo1;
	    }

	    public void setPageInfo1 (PageInfo1 PageInfo1)
	    {
	        this.PageInfo1 = PageInfo1;
	    }

	    public BlockList1[] getBlockList ()
	    {
	        return BlockList;
	    }

	    public void setBlockList (BlockList1[] BlockList)
	    {
	        this.BlockList = BlockList;
	    }

	    public KVPTable1[] getKVPTable1 ()
	    {
	        return KVPTable1;
	    }

	    public void setKVPTable1 (KVPTable1[] KVPTable1)
	    {
	        this.KVPTable1 = KVPTable1;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [PageInfo1 = "+PageInfo1+", BlockList = "+BlockList+", KVPTable1 = "+KVPTable1+"]";
	    }
}
