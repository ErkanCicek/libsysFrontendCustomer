package com.libsysfrontendcustomer.libsysfrontendcustomer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InkopForslagController {


    @FXML
    public Button TillbakaBtn;

    public void goBackBtn(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
            Stage window = (Stage) TillbakaBtn.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setFullScreenExitHint("");
            window.setFullScreen(true);
            window.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }








}
