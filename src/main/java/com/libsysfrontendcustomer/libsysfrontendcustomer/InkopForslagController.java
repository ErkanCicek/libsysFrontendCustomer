package com.libsysfrontendcustomer.libsysfrontendcustomer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class InkopForslagController {


    @FXML
    public Button TillbakaBtn;
    @FXML
    public Button SkickaBtn;
    @FXML
    public TextField IfInputField;
    @FXML
    public TextField TFSSNInput;
    @FXML
    public Label LWarning;

    public void goBackBtn(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sökabok.fxml")));
            Stage window = (Stage) TillbakaBtn.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setFullScreenExitHint("");
            window.setFullScreen(true);
            window.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void skickaInköpsLista(ActionEvent actionEvent) throws MessagingException {
        String Förslag = IfInputField.getText();
        String SSN = TFSSNInput.getText();

        if (Förslag.isEmpty() || SSN.isEmpty()){
            LWarning.setText("Vänligen skriv in både personnummer samt förslag för att skicka!");
        } else {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.transport.protocl", "smtp");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kiosk.ksb1@gmail.com", "TyPo123C1");
                }
            });

            Message message = new MimeMessage(session);
            message.setSubject("Önskelista #" + SSN);
            message.setContent("<h1> "+ Förslag + "</h1>", "text/html");


            Address address = new InternetAddress("kundservice.ksb1@gmail.com");
            message.setRecipient(Message.RecipientType.TO, address);

            Transport.send(message);
            LWarning.setText("Ditt förslag har skickats!");
        }


    }
}