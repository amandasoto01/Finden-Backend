package com.Finden.findenBackEnd;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    public static void main(String[] args) {
    	String textoSinEncriptar="123"; 
    	String textoEncriptadoConSHA=DigestUtils.sha1Hex(textoSinEncriptar); 
    	System.out.println("Texto Encriptado con SHA : "+textoEncriptadoConSHA);
    }
}
       /** try {

        	/**
        	 1. se abre el archivo
        	 2. se lee el plano
        	 **/
    	
    	/*
            File file = new File("Arboleda P3.dxf");// se abre el archivo.
            ArrayList<String> puertos  = ReadAutocadFile.getAutocadFile(file.getAbsolutePath());//se hace la lectura del plano y se guarda en una lista los puertos.
            for (int i = 0; i < puertos.size(); i++) {
            	System.out.println(puertos.get(i));
			}
        } catch (Exception e) {
        	System.err.println(e);
        }
    }
}**/