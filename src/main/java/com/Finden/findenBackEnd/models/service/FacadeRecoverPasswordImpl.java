package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Correction;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la logica de negocio cambio de contraseña
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeRecoverPasswordImpl implements FacadeRecoverPassword{
	
	@Autowired
	private UserDAO userDAO;
	/**
	 * Método para generar un codigo con el cual se genera el cambio de la contraseña
	 * @param user correo de quien esta haciendo la acción
	 * @return si el servicio funciono o no
	 */
	@Override
	@Transactional
	public Request Send(User user) {
		Request res = new Request();
		if(user.getEmail()==null) {
			res.setRequest(false);
			res.setRes("No se ingreso un correo");
			return res;
		}else {
			List<User> u= new ArrayList<User>();
			Iterable<User>I;
			User usuario = new User();
			usuario.setEmail(user.getEmail());
			usuario.setId(null);
			usuario.setName(null);
			usuario.setType(null);
			usuario.setPassword(null);
			Example<User>userExample=Example.of(usuario);
			I=userDAO.findAll(userExample);
			for(User us:I) {
				u.add(us);
			}
			if(u.size()!=0) {
				u.get(0).setPassword(CreatCode());
				userDAO.save(u.get(0));
				return SendEmail(usuario.getEmail(),u.get(0));
				
			}else {
				res.setRequest(false);
				res.setRes("Correo no existente");
				return res;
			}
		}
	}
	/**
	 * Método para hacer el cambio de contraseña
	 * @param user información para la generación de la nueva contraseña 
	 * @return si el servicio funciono o no
	 */
	@Override
	@Transactional
	public Request CorrectPassword(Correction nuevo) {
		Request res = new Request();
		if(nuevo.getEmail()==null) {
			res.setRequest(false);
			res.setRes("No se ingreso un correo");
			return res;
		}else if(nuevo.getPassword() ==null) {
			res.setRequest(false);
			res.setRes("No se ingreso contraseña");
			return res;
		}else if(nuevo.getCode()==null) {
			res.setRequest(false);
			res.setRes("No se ingreso Codigo");
			return res;
		}else {
			List<User> u= new ArrayList<User>();
			Iterable<User>I;
			User usuario = new User();
			usuario.setEmail(nuevo.getEmail());
			usuario.setId(null);
			usuario.setName(null);
			usuario.setType(null);
			usuario.setPassword(nuevo.getCode());
			Example<User>userExample=Example.of(usuario);
			I=userDAO.findAll(userExample);
			for(User us:I) {
				u.add(us);
			}
			if(u.size()!=0) {
				u.get(0).setPassword(DigestUtils.sha1Hex(nuevo.getPassword()));
				userDAO.save(u.get(0));
				res.setRequest(true);
				res.setRes("Se a cambiado la contraseña con exito");
				return res;
			}else {
				res.setRequest(false);
				res.setRes("Codigo erroneo");
				return res;
			}
		}
	}
	/**
	 * Método para generar un codigo con el cual se genera el cambio de la contraseña
	 * @return codigo para la recuperación de la contraseña
	 */
	public String CreatCode() {
		String codigo="";
		Random r = new Random();
		for (int i = 0; i < 7; i++) {
		        codigo += r.nextInt(10);
		}
		return codigo;
	}
	/**
	 * Método para enviar un correo con el codigo de recuperación
	 * @param Correo dirección de correo a la que se va enviar el mail
	 * @param u información del usuario como nombre y codigo de recuperación
	 * @return si el servicio funciono o no
	 */
	public Request SendEmail(String Correo,User u) {
		Request res = new Request();
		Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(props);
        String CorreoEnvia="findenPUJ@gmail.com";
        String Contrasena="finden2019";
        String destinatario=Correo;
        String asunto="Recuperación contraseña Finden";
        String mensaje= "Estimado "+u.getName()+"\n Deacuerdo con su solicitud de recuperación de contraseña, se ha generado el siguiente código:\n"
        				+u.getPassword()+
        				"\n para que pueda generar un cambio en la contraseña.\n"
        				+ "Si no ha solicitado esta acción porfavor comunicarse con la DTI javeriana\n"
        				+ "No-replay\n";
        MimeMessage mail= new MimeMessage(session);
        
        try {
			mail.setFrom(new InternetAddress(CorreoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mail.setSubject(asunto);
			mail.setText(mensaje);
			
			Transport transporte=session.getTransport("smtp");
			transporte.connect(CorreoEnvia,Contrasena);
			transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transporte.close();
			res.setRequest(true);
			res.setRes("Envio Exitoso");
			return res;
		} catch (AddressException e) {
			System.out.println(e.toString());
			res.setRequest(false);
			res.setRes(e.toString());
			return res;
		} catch (MessagingException e) {
			System.out.println(e.toString());
			res.setRequest(false);
			res.setRes(e.toString());
			return res;
		}
	}

}
