package com.validate;

public class compilanceValidatePojo {

	String compRule;
	Boolean result;
	String Comment;
	String transactionId;
	String processID;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getCompRule() {
		return compRule;
	}

	public void setCompRule(String compRule) {
		this.compRule = compRule;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	@Override
	public String toString() {
		return "compilanceValidatePojo [compRule=" + compRule + ", result=" + result + ", Comment=" + Comment
				+ ", transactionId=" + transactionId + ", processID=" + processID + "]";
	}

}
