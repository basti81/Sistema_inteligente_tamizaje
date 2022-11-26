package com.sirere.sistema_registro_renal.biblioteca;


import com.sirere.sistema_registro_renal.entity.Mail;
import com.sirere.sistema_registro_renal.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;


public class SendEmail {

    @Autowired
    private SendMailService sendMailService;

    public boolean mail(String to, String subject, String body){
        Mail mail = new Mail();
        try{
            mail.setFrom("prueba.prueba.prueba782@gmail.com");
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setBody(body);
            sendMailService.sendMail(mail);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }


}
