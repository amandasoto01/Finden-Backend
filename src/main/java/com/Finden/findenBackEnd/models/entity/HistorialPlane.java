package com.Finden.findenBackEnd.models.entity;

public class HistorialPlane {
	private Integer Version;
	private String Description;
	public Integer getVersion() {
		return Version;
	}
	public void setVersion(Integer version) {
		Version = version;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public HistorialPlane() {
		
	}
	@Override
	public String toString() {
		return "HistorialPlane [Version=" + Version + ", Description=" + Description + "]";
	}
	
}
