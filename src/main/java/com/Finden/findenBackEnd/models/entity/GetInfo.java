package com.Finden.findenBackEnd.models.entity;

public class GetInfo {
	private String phyAddress;
	private String adminStatusDesc;
	private String ifDescription;
	private Integer ifspeed;
	public String getPhyAddress() {
		return phyAddress;
	}
	public void setPhyAddress(String phyAddress) {
		this.phyAddress = phyAddress;
	}
	public String getAdminStatusDesc() {
		return adminStatusDesc;
	}
	public void setAdminStatusDesc(String adminStatusDesc) {
		this.adminStatusDesc = adminStatusDesc;
	}
	public String getIfDescription() {
		return ifDescription;
	}
	public void setIfDescription(String ifDescription) {
		this.ifDescription = ifDescription;
	}
	public Integer getIfspeed() {
		return ifspeed;
	}
	public void setIfspeed(Integer ifspeed) {
		this.ifspeed = ifspeed;
	}
	public GetInfo() {
		
	}
	@Override
	public String toString() {
		return "GetInfo [phyAddress=" + phyAddress + ", adminStatusDesc=" + adminStatusDesc + ", ifAlias="
				+ ifDescription + ", ifspeed=" + ifspeed + "]";
	}

	
}
