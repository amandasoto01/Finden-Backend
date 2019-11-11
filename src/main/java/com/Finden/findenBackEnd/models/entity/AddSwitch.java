package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la indformación obtenida desde el front para agregar un Swtich
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class AddSwitch {
private int Switch;
private int index;
/**
 * Método para obtener el numero del siwtch en el centro de cableado
 * @return El numero del siwtch en el centro de cableado
 */
public int getSwitch() {
	return Switch;
}
/**
 * Método para asignar el numero del siwtch en el centro de cableado
 * @param El numero del siwtch en el centro de cableado
 */
public void setSwitch(int switch1) {
	Switch = switch1;
}
/**
 * Método para obtener el indice del primer puerto del siwtch segun HPeIMC
 * @return El  indice del primer puerto del siwtch segun HPeIMC
 */
public int getIndex() {
	return index;
}
/**
 * Método para asignar el indice del primer puerto del siwtch segun HPeIMC
 * @param El  indice del primer puerto del siwtch segun HPeIMC
 */
public void setIndex(int index) {
	this.index = index;
}
/**
 * Constructor de la clase
 * @return Una instancia de la clase con sus campos en null
 */
public AddSwitch() {
	
}
/**
 * Método para imprimir la instancia de la clase AddSwith 
 * 
 */
public String toString() {
	return "AddSwitch [Switch=" + Switch + ", index=" + index + "]";
}

}
