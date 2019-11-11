package com.Finden.findenBackEnd.models.entity;

import org.springframework.web.multipart.MultipartFile;
/**
 * Esta clase representa la información obtenida desde el front para agregar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class AddPlane {
 private MultipartFile File;
 private String Description;
 /**
  * Método para obtener el archivo dfx del plano
  * @return El archivo dxf del plano
  */
public MultipartFile getFile() {
	return File;
}
/**
 * Método para asignar el archivo dfx del plano
 * @param El archivo dxf del plano
 */
public void setFile(MultipartFile file) {
	File = file;
}
/**
 * Método para obtener la descripción del plano
 * @return La descripción del plano
 */
public String getDescription() {
	return Description;
}
/**
 * Método para asignar la descripción del plano
 * @param La descripción del plano
 */
public void setDescription(String description) {
	Description = description;
}
/**
 * Constructor de la clase
 * @return Una instancia de la clase con sus campos en null
 */
 public AddPlane() {
	 
 }
 /**
  * Método para imprimir la instancia de la clase AddPlane 
  * 
  */
public String toString() {
	return "AddPlane [File=" + File + ", Description=" + Description + "]";
}
 
}
