package com.dgsl.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockList
{
	@JsonProperty("BlockFGColor")
    private String BlockFGColor;
	
	@JsonProperty("BlockWidth")
    private String BlockWidth;

	@JsonProperty("Miscellaneous")
    private String Miscellaneous;

	@JsonProperty("LineList")
    private LineList[] LineList;

	@JsonProperty("BlockType")
    private String BlockType;

	@JsonProperty("BlockID")
    private String BlockID;

	@JsonProperty("BlockStartY")
    private String BlockStartY;

	@JsonProperty("BlockStartX")
    private String BlockStartX;

	@JsonProperty("BlockBGColor")
    private String BlockBGColor;

	@JsonProperty("BlockHeight")
    private String BlockHeight;

	@JsonProperty("Remark")
    private String Remark;

    public String getBlockFGColor ()
    {
        return BlockFGColor;
    }

    public void setBlockFGColor (String BlockFGColor)
    {
        this.BlockFGColor = BlockFGColor;
    }

    public String getBlockWidth ()
    {
        return BlockWidth;
    }

    public void setBlockWidth (String BlockWidth)
    {
        this.BlockWidth = BlockWidth;
    }

    public String getMiscellaneous ()
    {
        return Miscellaneous;
    }

    public void setMiscellaneous (String Miscellaneous)
    {
        this.Miscellaneous = Miscellaneous;
    }

    public LineList[] getLineList ()
    {
        return LineList;
    }

    public void setLineList (LineList[] LineList)
    {
        this.LineList = LineList;
    }

    public String getBlockType ()
    {
        return BlockType;
    }

    public void setBlockType (String BlockType)
    {
        this.BlockType = BlockType;
    }

    public String getBlockID ()
    {
        return BlockID;
    }

    public void setBlockID (String BlockID)
    {
        this.BlockID = BlockID;
    }

    public String getBlockStartY ()
    {
        return BlockStartY;
    }

    public void setBlockStartY (String BlockStartY)
    {
        this.BlockStartY = BlockStartY;
    }

    public String getBlockStartX ()
    {
        return BlockStartX;
    }

    public void setBlockStartX (String BlockStartX)
    {
        this.BlockStartX = BlockStartX;
    }

    public String getBlockBGColor ()
    {
        return BlockBGColor;
    }

    public void setBlockBGColor (String BlockBGColor)
    {
        this.BlockBGColor = BlockBGColor;
    }

    public String getBlockHeight ()
    {
        return BlockHeight;
    }

    public void setBlockHeight (String BlockHeight)
    {
        this.BlockHeight = BlockHeight;
    }

    public String getRemark ()
    {
        return Remark;
    }

    public void setRemark (String Remark)
    {
        this.Remark = Remark;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [BlockFGColor = "+BlockFGColor+", BlockWidth = "+BlockWidth+", Miscellaneous = "+Miscellaneous+", LineList = "+LineList+", BlockType = "+BlockType+", BlockID = "+BlockID+", BlockStartY = "+BlockStartY+", BlockStartX = "+BlockStartX+", BlockBGColor = "+BlockBGColor+", BlockHeight = "+BlockHeight+", Remark = "+Remark+"]";
    }
}
			
			