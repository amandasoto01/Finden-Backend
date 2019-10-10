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
import com.Finden.findenBackEnd.models.entity.*;

@Service
public class FacadeGeneralImpl implements FacadeGeneral {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly=true)
	public String Login(User usuario) {
		List<User> u= new ArrayList<User>();
		if(usuario.getEmail()==null) {
			return null;
		}else {
			if(usuario.getPassword()==null) {
				return null;
			}
			else {
				Iterable<User>I;
				usuario.setId(null);
				usuario.setName(null);
				usuario.setType(null);
				usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
				Example<User>userExample=Example.of(usuario);
				I=userDAO.findAll(userExample);
				for(User us:I) {
					u.add(us);
				}
				if(u.size()>0) {
					if(u.get(0).getType()==1 && usuario.getEmail().equals(u.get(0).getEmail())) {
						return "DTI";
					}else if(u.get(0).getType()==2 && usuario.getEmail().equals(u.get(0).getEmail())) {
						return "mesa de servicios";
					}
					else {
						return "contratista";
					}
				}else {
					return null;
				}
			}
		}

	}

	@Override
	@Transactional
	public String Enviar(String correo) {
		if(correo==null) {
			return "No se ingreso un correo";
		}else {
			List<User> u= new ArrayList<User>();
			Iterable<User>I;
			User usuario = new User();
			usuario.setEmail(correo);
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
				u.get(0).setPassword(GenerarCodigo());
				userDAO.save(u.get(0));
				return SendEmail(usuario.getEmail(),u.get(0));
				
			}else {
				return "Correo no existente";
			}
		}
	}

	public String SendEmail(String Correo,User u) {
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
			return "Envio Exitoso";
		} catch (AddressException e) {
			return e.toString();
		} catch (MessagingException e) {
			System.err.println(e);
			return 	e.toString();
		}
	}
	
	public String GenerarCodigo() {
		String codigo="";
		Random r = new Random();
		for (int i = 0; i < 7; i++) {
		        codigo += r.nextInt(10);
		}
		return codigo;
	}

	@Override
	@Transactional
	public String Correguir(Correction nuevo) {
		if(nuevo.getEmail()==null) {
			return "No se ingreso un correo";
		}else if(nuevo.getPassword() ==null) {
			return "No se ingreso contraseña";
		}else if(nuevo.getCode()==null) {
			return "No se ingreso Codigo";
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
				return "Se a cambiado la contraseña con exito";
			}else {
				return"Codigo erroneo";
			}
		}
	}
}
