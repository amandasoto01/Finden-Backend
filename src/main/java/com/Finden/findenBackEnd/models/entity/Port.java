package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="port")
public class Port {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer Id;
	
	@Column(name="\"name\"")
	private String name;
	
	@Column(name="\"portinswitch\"")
	private Integer portInSwitch;
	
	@Column(name="\"type\"")
	private Integer type;
	
	@Column(name="\"floor_id\"")
	private Integer Floor_Id;
	
	@Column(name="\"floor_building_id\"")
	private Integer Floor_Building_Id;
	
	@Column(name="\"switch_id\"")
	private Integer Switch_id;
	
	@Column(name="\"Switch_writingcenter_id\"")
	private Integer Switch_WritingCenter_id;

	
	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPortInSwitch() {
		return portInSwitch;
	}


	public void setPortInSwitch(Integer portInSwitch) {
		this.portInSwitch = portInSwitch;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getFloor_Id() {
		return Floor_Id;
	}


	public void setFloor_Id(Integer floor_Id) {
		Floor_Id = floor_Id;
	}


	public Integer getFloor_Building_Id() {
		return Floor_Building_Id;
	}


	public void setFloor_Building_Id(Integer floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}


	public Integer getSwitch_id() {
		return Switch_id;
	}


	public void setSwitch_id(Integer switch_id) {
		Switch_id = switch_id;
	}


	public Integer getSwitch_WritingCenter_id() {
		return Switch_WritingCenter_id;
	}


	public void setSwitch_WritingCenter_id(Integer switch_WritingCenter_id) {
		Switch_WritingCenter_id = switch_WritingCenter_id;
	}
	public Port () {
	}

	@Override
	public String toString() {
		return "Port [Id=" + Id + ", name=" + name + ", portInSwitch=" + portInSwitch + ", type=" + type + ", Floor_Id="
				+ Floor_Id + ", Floor_Building_Id=" + Floor_Building_Id + ", Switch_id=" + Switch_id
				+ ", Switch_WritingCenter_id=" + Switch_WritingCenter_id + "]";
	}
	
	
}
