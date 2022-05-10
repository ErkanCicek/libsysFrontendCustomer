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

public class StartController {

    ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private Button btnSBok;
    @FXML
    private Button btnLBok;
    @FXML
    private Button btnBG;
    @FXML
    private Button btnTillbaka1;
    @FXML
    private Button btnTillbaka2;
    @FXML
    private Button btnTillbaka3;
    @FXML
    private Button btnSB;
    @FXML
    private Label LWarningNoBook;
    @FXML
    private TextField TFSBok;
    @FXML
    private Label LWarningISBN;
    @FXML
    private Label LWarningPN;
    @FXML
    private TextField TFISBN;
    @FXML
    private TextField TFPN;
    @FXML
    private TextField TFPassword;

    public void handleBtnSBok() throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Sökabok.fxml"));

        Stage window = (Stage) btnSBok.getScene().getWindow();
        window.setScene(new Scene(root));
        //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();

    }

    public void handleBtnLBok() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Lånabok.fxml"));

        Stage window = (Stage) btnLBok.getScene().getWindow();
        window.setScene(new Scene(root));
        //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();
    }

    public void handleBtnBG() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BokaRum.fxml"));

        Stage window = (Stage) btnBG.getScene().getWindow();
        window.setScene(new Scene(root));
        //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();
    }


    public void handleBtnTillbaka() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Stage window = (Stage) btnTillbaka1.getScene().getWindow();
        window.setScene(new Scene(root));
        //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();
    }

    public void handleBtnTillbaka2() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Stage window = (Stage) btnTillbaka2.getScene().getWindow();
        window.setScene(new Scene(root));
       //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();
    }

    public void handleBtnTillbaka3() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Stage window = (Stage) btnTillbaka3.getScene().getWindow();
        window.setScene(new Scene(root));
        //window.setFullScreen(true);
        window.setMaximized(true);
        window.show();
    }

    public void searchBookOnAction(ActionEvent event){
        if (TFSBok.getText().isBlank()){
            LWarningNoBook.setText("Vänligen skriv in en bok för att kunna söka");
        }
    }

    public void confirmISBNOnAction(ActionEvent event){
        String ISBN = TFISBN.getText();

        if (connectionManager.sendRequest("book/get/bookByISBN?value=" + ISBN).equals("boken finns ej")){
            LWarningISBN.setText("Boken finns ej!");
        } else if (TFISBN.getText().isEmpty()){
            LWarningISBN.setText("Vänligen skriv in bokens ISBN!");
        } else {
            LWarningISBN.setText("Boken finns!");
        }
    }

    public void confirmBtnSSNAndPassAction(ActionEvent event){
        String Personnummer = TFPN.getText();
        String lösenord = TFPassword.getText();
        System.out.println(Personnummer);
        System.out.println(lösenord);
        if (Boolean.parseBoolean(connectionManager.sendRequest("borrower/get/verifyBorrower?SSN=" + Personnummer + "&password=" + lösenord))){
            LWarningPN.setText("Konfirmerad!");
        } else if (TFPN.getText().isBlank()){
            LWarningPN.setText("Vänligen skriv in er personnummer först för att kontollera!");
        } else {
            LWarningPN.setText("Finns ingen person med denna personnummer eller lösenord!");
        }
    }
}
