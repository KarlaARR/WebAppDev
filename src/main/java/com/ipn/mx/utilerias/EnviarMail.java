/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author karla
 */
public class EnviarMail {
    
    public void enviarCorreo(String destinatario,String asunto, String mensaje){
        try {
            //Propiedades de la conexion
            Properties p = new Properties();
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", "proyectoWAD@gmail.com");
            p.setProperty("mail.smtp.auth", "true");
            
            //Crear la sesion
            Session s = Session.getDefaultInstance(p);
            //Trabajar con el mensaje
            MimeMessage m = new MimeMessage(s);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            m.setSubject(asunto);
            m.setText(mensaje);
            Transport t = s.getTransport("smtp");
            t.connect(p.getProperty("mail.smpt.user"),"1234abcd_");
            t.sendMessage(m, m.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        EnviarMail email = new EnviarMail();
        String destinatario = "proyectoWAD@gmail.com";
        String asunto = "Prueba de WAD";
        String mensaje = "Esto es una prueba";
        
        email.enviarCorreo(destinatario, asunto, mensaje);
    }
    
}
