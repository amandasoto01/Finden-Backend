package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Switch")
public class Switch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="indication")
	private Integer index;
	
	@Column(name="Writingcenter_id")
	private Integer WritingCenter_id;
	
	@Column(name="Numeroswitch")
	private Integer NumeroSwitch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getWritingCenter_id() {
		return WritingCenter_id;
	}

	public void setWritingCenter_id(Integer writingCenter_id) {
		WritingCenter_id = writingCenter_id;
	}

	public Integer getNumeroSwitch() {
		return NumeroSwitch;
	}

	public void setNumeroSwitch(Integer numeroSwitch) {
		NumeroSwitch = numeroSwitch;
	}

	@Override
	public String toString() {
		return "Switch [id=" + id + ", index=" + index + ", WritingCenter_id=" + WritingCenter_id + ", NumeroSwitch="
				+ NumeroSwitch + "]";
	}

	
	
	
}