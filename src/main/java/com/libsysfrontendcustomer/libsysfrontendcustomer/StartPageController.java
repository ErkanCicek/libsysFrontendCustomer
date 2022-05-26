package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.BorrowedBooks;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.RoomResDD;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TVBorrowedBooks;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TVReservedRooms;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.util.DateHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class StartPageController {
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

    @FXML
    public TableView<TVBorrowedBooks> TVLB;
    @FXML
    public TableColumn<TVBorrowedBooks, Integer> CID;
    @FXML
    public TableColumn<TVBorrowedBooks, String> CÅB;
    @FXML
    public TableColumn<TVBorrowedBooks, String> CISBN;
    @FXML
    public TableColumn<TVBorrowedBooks, String> CPersonnummer;
    @FXML
    public TableColumn<TVBorrowedBooks, Integer> CBID;

    public ObservableList<TVBorrowedBooks> observableList = FXCollections.observableArrayList();

    @FXML
    public TableView<TVReservedRooms> TVRR;
    @FXML
    public TableColumn<TVReservedRooms, String> CDatum;
    @FXML
    public TableColumn<TVReservedRooms, String> CTid;
    @FXML
    public TableColumn<TVReservedRooms, Integer> CLID;
    @FXML
    public TableColumn<TVReservedRooms, Integer> CRID;

    public ObservableList<TVReservedRooms> observableList1 = FXCollections.observableArrayList();

    @FXML
    private Button btnValidate;

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
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> verifyLoginOutput = new Gson().fromJson(connectionManager.sendGetRequest("borrower/get/verifyBorrower?SSN=" + Personnummer + "&password=" + Lösenord), mapType);
        boolean isVerified = (boolean) verifyLoginOutput.get("booleanvalue");
        if (isVerified){
            LWarningPN.setText("Konfirmerad!");

            BorrowedBooks[] borrowedBooks = new Gson().fromJson(connectionManager.sendGetRequest("borrowedbooks/get/getAllBorrowedBooksBySSN?value=" + Personnummer), BorrowedBooks[].class);
            for (BorrowedBooks borrowedBooks1 : borrowedBooks){
                observableList.add(new TVBorrowedBooks(
                        borrowedBooks1.getBorrowedBooksID(), borrowedBooks1.getReturnDate(), borrowedBooks1.getBookISBN(), borrowedBooks1.getBorrowerSSN(), borrowedBooks1.getBookId())
                );

                CID.setCellValueFactory(new PropertyValueFactory<>("borrowedBooksID"));
                CÅB.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
                CISBN.setCellValueFactory(new PropertyValueFactory<>("bookISBN"));
                CPersonnummer.setCellValueFactory(new PropertyValueFactory<>("borrowerSSN"));
                CBID.setCellValueFactory(new PropertyValueFactory<>("bookId"));

                TVLB.setItems(observableList);
            }

            RoomResDD[] roomResDDS = new Gson().fromJson(connectionManager.sendGetRequest("roomRes/get/getAllReservationsBySSN?value=" + Personnummer), RoomResDD[].class);
            for (RoomResDD roomResDD : roomResDDS){
                observableList1.add(new TVReservedRooms(
                        roomResDD.getDateValue(), roomResDD.getTime() + ": 00 - " + (roomResDD.getRoomId() + 2) + " : 00" , roomResDD.getBorrowerId(), roomResDD.getRoomId())
                );
                CDatum.setCellValueFactory(new PropertyValueFactory<>("dateValue"));
                CTid.setCellValueFactory(new PropertyValueFactory<>("time"));
                CLID.setCellValueFactory(new PropertyValueFactory<>("borrowerId"));
                CRID.setCellValueFactory(new PropertyValueFactory<>("roomId"));

                TVRR.setItems(observableList1);
            }

            btnValidate.setVisible(false);
            btnTillbaka4.setText("Logga ut!");

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
