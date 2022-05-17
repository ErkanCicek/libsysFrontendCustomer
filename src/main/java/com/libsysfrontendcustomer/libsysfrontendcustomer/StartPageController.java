package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Book;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TableViewBookSearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

public class StartPageController implements Initializable {

    public TextField inputField;
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

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sökabok.fxml")));

        Stage window = (Stage) btnSBok.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();

    }
    public void handleBtnLBok() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Lånabok.fxml")));

        Stage window = (Stage) btnLBok.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }
    public void handleBtnBG() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bokaRumMeny.fxml")));

        Stage window = (Stage) btnBG.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }

    public void handleBtnTillbaka2() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
        Stage window = (Stage) btnTillbaka2.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }

    public void confirmISBNOnAction(ActionEvent event){
        String ISBN = TFISBN.getText();

        if (connectionManager.sendGetRequest("book/get/bookByISBN?value=" + ISBN).equals("boken finns ej")){
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
        if (Boolean.parseBoolean(connectionManager.sendGetRequest("borrower/get/verifyBorrower?SSN=" + Personnummer + "&password=" + lösenord))){
            LWarningPN.setText("Konfirmerad!");
        } else if (TFPN.getText().isBlank()){
            LWarningPN.setText("Vänligen skriv in er personnummer först för att kontollera!");
        } else {
            LWarningPN.setText("Finns ingen person med denna personnummer eller lösenord!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
