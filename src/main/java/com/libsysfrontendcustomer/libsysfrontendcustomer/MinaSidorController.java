package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.BorrowedBooks;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.RoomResDD;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TVBorrowedBooks;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TVReservedRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class MinaSidorController {
    ConnectionManager connectionManager = new ConnectionManager();
    @FXML
    private TextField TFPN;
    @FXML
    private TextField TFPassword;
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
    public Button btnTillbaka4;
    @FXML
    private Label LWarningPN;
    @FXML
    private Button btnValidate;

    public void handleBtnTillbaka4() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
        Stage window = (Stage) btnTillbaka4.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }

    public void confirmBtnSSNAndPassMSAction(ActionEvent event){
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
}
