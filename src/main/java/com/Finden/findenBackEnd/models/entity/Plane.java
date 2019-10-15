package com.Finden.findenBackEnd.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plane")
public class Plane {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dateupload")
	private Date dateUpload;
	
	@Column(name="version")
	private int version;
	
	@Column(name="state")
	private int state;
	
	@Column(name="observation")
	private String observation;
	
	@Column(name="description")
	private String description;
	
	@Column(name="dateapproval")
	private Date dateApproval;
	
	@Column(name="dir")
	private String dir;
	
	@Column(name="Floor_id")
	private int Floor_id;
	
	@Column(name="Floor_building_id")
	private int Floor_Building_Id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateApproval() {
		return dateApproval;
	}

	public void setDateApproval(Date dateApproval) {
		this.dateApproval = dateApproval;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getFloor_id() {
		return Floor_id;
	}

	public void setFloor_id(int floor_id) {
		Floor_id = floor_id;
	}

	public int getFloor_Building_Id() {
		return Floor_Building_Id;
	}

	public void setFloor_Building_Id(int floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}
	public Plane() {
		
	}

	@Override
	public String toString() {
		return "Plane [id=" + id + ", name=" + name + ", dateUpload=" + dateUpload.toString() + ", version=" + version + ", state="
				+ state + ", observation=" + observation + ", description=" + description + ", dateApproval="
				+ dateApproval.toString() + ", dir=" + dir + ", Floor_id=" + Floor_id + ", Floor_Building_Id=" + Floor_Building_Id
				+ "]";
	}
	
}
