package com.emailEx.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private EmailSender emailSender;

    public void sendMail(String mail){
        try {
            emailSender.sendMail(mail, "Activation Mail" ,"Please User this Link to Verfy Your Account. Link: http://localhost:8080/verify?code=");
        } catch (NoSuchProviderException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
