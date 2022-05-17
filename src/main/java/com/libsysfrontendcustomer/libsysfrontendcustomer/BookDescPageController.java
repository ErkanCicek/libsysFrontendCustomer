package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Author;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Book;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookDescPageController implements Initializable{
	ConnectionManager manager = new ConnectionManager();

	@FXML
	public Label amountLabel;
	@FXML
	public Label titleLabel;
	@FXML
	public Label authorLabel;
	@FXML
	public Text bookDescLabel;
	@FXML
	public Label genreLabel;
	@FXML
	public Button goBackBtn;

	public void goBack(ActionEvent event) {
		try{
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SökaBok.fxml")));
			Stage window = (Stage) goBackBtn.getScene().getWindow();
			window.setScene(new Scene(root));
			window.setMaximized(true);
			window.setFullScreenExitHint("");
			window.setFullScreen(true);
			window.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
