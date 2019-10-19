package com.Finden.findenBackEnd.models.entity;

public class GetPlane {
	private String namePlane;
	private Integer version;
	public String getNamePlane() {
		return namePlane;
	}
	public void setNamePlane(String namePlane) {
		this.namePlane = namePlane;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public GetPlane() {
		
	}
	@Override
	public String toString() {
		return "GetPlane [namePlane=" + namePlane + ", version=" + version + "]";
	}
	
}
