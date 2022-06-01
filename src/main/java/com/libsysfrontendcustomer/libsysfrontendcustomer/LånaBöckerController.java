package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.util.DateHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class LånaBöckerController {
    Book book;
    ConnectionManager connectionManager = new ConnectionManager();
    @FXML
    private TextField TFISBN;
    @FXML
    private Label LWarningISBN;
    @FXML
    private TextField TFPN;
    @FXML
    private TextField TFPassword;
    @FXML
    private Label LWarningPN;
    @FXML
    private Button btnTillbaka2;

    public void handleBtnTillbaka2() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
        Stage window = (Stage) btnTillbaka2.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }

    public void confirmISBNOnAction(ActionEvent event){
        String ISBNandId = TFISBN.getText();
        String[] inputValues = ISBNandId.split("-");

        if (connectionManager.sendGetRequest("book/get/bookByIsbnAndId?value=" + inputValues[0] + "&bookId=" + Integer.parseInt(inputValues[1])).equals("boken finns ej")){
            LWarningISBN.setText("Boken finns ej!");
        } else if (TFISBN.getText().isEmpty()){
            LWarningISBN.setText("Vänligen skriv in bokens ISBN!");
        } else {
            LWarningISBN.setText("Boken finns!");
        }
    }

    public void confirmBtnSSNAndPassAction(ActionEvent event){
        String Personnummer = TFPN.getText();
        String Lösenord = TFPassword.getText();
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> verifyLoginOutput = new Gson().fromJson(connectionManager.sendGetRequest("borrower/get/verifyBorrower?SSN=" + Personnummer + "&password=" + Lösenord), mapType);
        boolean isVerified = (boolean) verifyLoginOutput.get("booleanvalue");
        if (isVerified){
            LWarningPN.setText("Konfirmerad!");
        } else if (TFPN.getText().isBlank() || TFPassword.getText().isBlank()){
            LWarningPN.setText("Vänligen skriv in er personnummer samt lösenord först för att kontollera!");
        } else {
            LWarningPN.setText("Finns ingen person med denna personnummer eller lösenord!");
        }
    }

    public void lånaBokBtnOnAction(ActionEvent event) {
        String ISBNandId = TFISBN.getText();
        String[] inputValues = ISBNandId.split("-");
        String Personnummer = TFPN.getText();
        book = new Gson().fromJson(connectionManager.sendGetRequest("book/get/bookByIsbnAndId?value="+ inputValues[0]+ "&bookId=" + inputValues[1]), Book.class);
        if (TFISBN.getText().isBlank()||TFPN.getText().isBlank()||TFPassword.getText().isBlank()){
            LWarningPN.setText("Vänligen fyll i allt!");
        } else {
            connectionManager.sendGetRequest("borrowedbooks/post/newBorrowedBook?returnDate=" + DateHelper.getReturnDate() + "&ISBN=" + inputValues[0] +"&bookId="+ inputValues[1] +"&SSN=" + Personnummer);
            LWarningPN.setText("Boken har nu lånats");
            book.setBookAvailable(false);
            connectionManager.sendPutRequest("book/put/reserveBook?bookId=" + inputValues[1] + "&isbn=" + inputValues[0] +"&Available=" + book.isBookAvailable());
        }
    }
    //Test
}
