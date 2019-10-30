package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageList
{

	@JsonProperty("PageInfo")
    private PageInfo PageInfo;

	@JsonProperty("BlockList")
    private BlockList[] BlockList;

	@JsonProperty("KVPTable")
    private KVPTable[] KVPTable;

    public PageInfo getPageInfo ()
    {
        return PageInfo;
    }

    public void setPageInfo (PageInfo PageInfo)
    {
        this.PageInfo = PageInfo;
    }

    public BlockList[] getBlockList ()
    {
        return BlockList;
    }

    public void setBlockList (BlockList[] BlockList)
    {
        this.BlockList = BlockList;
    }

    public KVPTable[] getKVPTable ()
    {
        return KVPTable;
    }

    public void setKVPTable (KVPTable[] KVPTable)
    {
        this.KVPTable = KVPTable;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PageInfo = "+PageInfo+", BlockList = "+BlockList+", KVPTable = "+KVPTable+"]";
    }
}
			
			