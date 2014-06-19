package com.mitake.component;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Bok508CRQBody {
	//註解驗證
	@NotNull
	private String unifiedNo;
	@Size(min=2, max=30)
	private String userName;
	private String address;
	private String tel1;
	private String tel2;
	private String tel3;
	private String txnDate;
	private String txnResult;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getUnifiedNo() {
		return unifiedNo;
	}
	public void setUnifiedNo(String unifiedNo) {
		this.unifiedNo = unifiedNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnResult() {
		return txnResult;
	}
	public void setTxnResult(String txnResult) {
		this.txnResult = txnResult;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}
