package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.util.DateHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {
    Book book;

    public TextField inputField;
    DateHelper dateHelper = new DateHelper();
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
    @FXML
    private Button btnRedirectMinaSidor;
    @FXML
    public Button btnTillbaka4;
    @FXML
    public Text MinaSidorLabel;
    @FXML
    public TextArea TAMinaSidor;


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
        if (Boolean.parseBoolean(connectionManager.sendGetRequest("borrower/get/verifyBorrower?SSN=" + Personnummer + "&password=" + Lösenord))){
            LWarningPN.setText("Konfirmerad!");
            TAMinaSidor.setText(connectionManager.sendGetRequest("borrowedbooks/get/getAllBorrowedBooksBySSN?value=" + Personnummer));
        } else if (TFPN.getText().isBlank() || TFPassword.getText().isBlank()){
            LWarningPN.setText("Vänligen skriv in er personnummer samt lösenord först för att kontollera!");
        } else {
            LWarningPN.setText("Finns ingen person med denna personnummer eller lösenord!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void lånaBokBtnOnAction(ActionEvent event) {
        String ISBNandId = TFISBN.getText();
        String[] inputValues = ISBNandId.split("-");
        String Personnummer = TFPN.getText();
        book = new Gson().fromJson(connectionManager.sendGetRequest("book/get/bookByISBN?value="+ inputValues[0]), Book.class);

        if (TFISBN.getText().isBlank()||TFPN.getText().isBlank()||TFPassword.getText().isBlank()){
            LWarningPN.setText("Vänligen fyll i allt!");
        } else {
            connectionManager.sendGetRequest("borrowedbooks/post/newBorrowedBook?returnDate=" + DateHelper.getReturnDate() + "&ISBN=" + inputValues[0] +"&bookId="+ inputValues[1] +"&SSN=" + Personnummer);
            LWarningPN.setText("Boken har nu lånats");
        }
    }

    public void handleBtnRedirectMinaSidor() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MinaSidor.fxml")));

        Stage window = (Stage) btnRedirectMinaSidor.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }

    public void handleBtnTillbaka4() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
        Stage window = (Stage) btnTillbaka4.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }
}
