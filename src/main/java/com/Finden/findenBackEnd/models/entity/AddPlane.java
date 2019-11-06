package com.Finden.findenBackEnd.models.entity;

import org.springframework.web.multipart.MultipartFile;

public class AddPlane {
 private MultipartFile File;
 private String Description;
public MultipartFile getFile() {
	return File;
}
public void setFile(MultipartFile file) {
	File = file;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
 public AddPlane() {
	 
 }
@Override
public String toString() {
	return "AddPlane [File=" + File + ", Description=" + Description + "]";
}
 
}
