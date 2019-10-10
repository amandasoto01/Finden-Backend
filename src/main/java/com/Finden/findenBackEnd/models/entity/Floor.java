package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Floor")
public class Floor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="number")
	private int number;
	
	@Column(name="Building_Id")
	private int Building_Id;
	
	public Floor() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getBuilding_Id() {
		return Building_Id;
	}

	public void setBuilding_Id(int building_Id) {
		Building_Id = building_Id;
	}
	
}
