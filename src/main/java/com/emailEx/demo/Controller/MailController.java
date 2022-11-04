package com.emailEx.demo.Controller;

import com.emailEx.demo.ReceiveMail;
import com.emailEx.demo.Service.UserService;
import com.emailEx.demo.models.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.Arrays;
@RestController
public class MailController {

    @Autowired
    UserService userService;
    @Autowired
    ReceiveMail receiveMail;

    @PostMapping("/{mail}")
    public ResponseEntity createUser(@PathVariable String mail){
        try {

//
            String host = "premium18.web-hosting.com";//change accordingly
            String mailStoreType = "pop3";
            String username= "deshanw@mexxar.com";
            String password= "MexxarDw1";//change accordingly
            Message[] oldmessages = receiveMail.receiveEmail(host,mailStoreType,username,password);
            ArrayList oldmessagesArry = (ArrayList) Arrays.asList(Arrays.stream(oldmessages).toArray());
            userService.sendMail(mail);
            Thread.sleep(60000);
            Message[] messages = receiveMail.receiveEmail(host,mailStoreType,username,password);
            ArrayList messagesArry = Arrays.asList(Arrays.stream(messages).toArray());
            System.out.println("def : "+ messagesArry.removeAll(oldmessagesArry));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(mail,"Success","Created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper(mail,"failed",e.getMessage()));
        }
    }
}
