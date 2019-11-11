/**
 * Paquete que contiene el ejecutable del servidor
 */
package com.Finden.findenBackEnd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Esta clase es el Main del servior web 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@SpringBootApplication
public class Finden {

	public static void main(String[] args) {
		SpringApplication.run(Finden.class, args);
	}
}
