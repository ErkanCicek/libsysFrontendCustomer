package com.libsysfrontendcustomer.libsysfrontendcustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartPageController {

    @FXML
    private Button btnSBok;
    @FXML
    private Button btnLBok;
    @FXML
    private Button btnBG;
    @FXML
    private Button btnRedirectMinaSidor;


    public void handleBtnSBok() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sökabok.fxml"));
        Parent root = loader.load();
        SearchBookController controller = loader.getController();
        controller.init("book/get/allBooks");
        Scene scene = new Scene(root);
        Stage window = (Stage)this.btnSBok.getScene().getWindow();
        window.setScene(scene);
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

    public void handleBtnRedirectMinaSidor() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MinaSidor.fxml")));

        Stage window = (Stage) btnRedirectMinaSidor.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }


    public void goToPopularBook() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sökabok.fxml"));
        Parent root = loader.load();
        SearchBookController controller = loader.getController();
        controller.init("book/get/mostPopularAvailableBook");
        controller.searchBookTitleLabel.setText("Vår Topplista");
        Scene scene = new Scene(root);
        Stage window = (Stage)this.btnSBok.getScene().getWindow();
        window.setScene(scene);
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.show();
    }
}
