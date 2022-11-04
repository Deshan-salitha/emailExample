package com.emailEx.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import com.sun.mail.pop3.POP3Store;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMail{

 public static Message[] receiveEmail(String pop3Host, String storeType, String user, String password) {
  try {
   Properties properties = new Properties();
   properties.put("mail.pop3.host", pop3Host);
   Session emailSession = Session.getDefaultInstance(properties);

   POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
   emailStore.connect(user, password);

   Folder emailFolder = emailStore.getFolder("INBOX");
   emailFolder.open(Folder.READ_ONLY);

   Message[] messages = emailFolder.getMessages();
//   ArrayList<Message> emails = new ArrayList<>();
   for (int i = 0; i < messages.length; i++) {
	Message message = messages[i];
    if (message.getSubject().equals("Undelivered Mail Returned to Sender")){
//     System.out.println(message);
//     System.out.println("---------------------------------");
//     System.out.println("Email Number " + (i + 1));
//     System.out.println("Subject: " + message.getSubject());
//     System.out.println("From: " + message.getFrom()[0]);
//     System.out.println("Text: " + message.getContent().toString());
//     System.out.println("SentDate: "+message.getSentDate());
//     System.out.println("Flags: "+message.getFlags());
//     emails.add(message);
    }else {

    }

   }

//   System.out.println(Arrays.toString(Arrays.stream(messages).toArray()));

   emailFolder.close(false);
   emailStore.close();
   return messages;
  } catch (NoSuchProviderException e) {e.printStackTrace();} 
  catch (MessagingException e) {e.printStackTrace();}
//  catch (IOException e) {e.printStackTrace();}
  return null;
 }

// public static void main(String[] args) {
//
//  String host = "premium18.web-hosting.com";//change accordingly
//  String mailStoreType = "pop3";
//  String username= "deshanw@mexxar.com";
//  String password= "MexxarDw1";//change accordingly
//
//  receiveEmail(host, mailStoreType, username, password);
//
// }
}