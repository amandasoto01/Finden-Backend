package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Planexuser")
public class PlaneXUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="User_id")
	private Integer User_Id;
	
	@Column(name="Plane_id")
	private Integer Plane_Id;
	
	public PlaneXUser() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(Integer user_Id) {
		User_Id = user_Id;
	}

	public Integer getPlane_Id() {
		return Plane_Id;
	}

	public void setPlane_Id(Integer plane_Id) {
		Plane_Id = plane_Id;
	}

	@Override
	public String toString() {
		return "PlaneXUser [id=" + id + ", User_Id=" + User_Id + ", Plane_Id=" + Plane_Id + "]";
	}
	

}
