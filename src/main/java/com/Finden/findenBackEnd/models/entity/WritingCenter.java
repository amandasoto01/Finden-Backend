package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wiringcenter")
public class WritingCenter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column (name="name")
	private String name;
	
	@Column (name="idwiringcenter")
	private int idWirtingCenter;
	
	@Column(name="Floor_Building_Id")
	private int Floor_Building_Id;
	
	@Column(name="Floor_Id")
	private int Floor_Id;

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

	public int getIdWirtingCenter() {
		return idWirtingCenter;
	}

	public void setIdWirtingCenter(int idWirtingCenter) {
		this.idWirtingCenter = idWirtingCenter;
	}

	public int getFloor_Building_Id() {
		return Floor_Building_Id;
	}

	public void setFloor_Building_Id(int floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}

	public int getFloor_Id() {
		return Floor_Id;
	}

	public void setFloor_Id(int floor_Id) {
		Floor_Id = floor_Id;
	}

	@Override
	public String toString() {
		return "WritingCenter [id=" + id + ", name=" + name + ", idWirtingCenter=" + idWirtingCenter
				+ ", Floor_Building_Id=" + Floor_Building_Id + ", Floor_Id=" + Floor_Id + "]";
	}


	
}
