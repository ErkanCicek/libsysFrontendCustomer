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

public class BookDescPageController{
	ConnectionManager manager = new ConnectionManager();
	@FXML
	public Button goToSimiliarBooks;
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

	public void goBack(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Sökabok.fxml"));
		Parent root = loader.load();
		SearchBookController controller = loader.getController();
		controller.init("book/get/allBooks");
		Scene scene = new Scene(root);
		Stage window = (Stage)this.goBackBtn.getScene().getWindow();
		window.setScene(scene);
		window.setFullScreenExitHint("");
		window.setFullScreen(true);
		window.show();
	}

	public void goToSimilarBooks() throws IOException {
		Genre genre = new Gson().fromJson(manager.sendGetRequest("genre/get/genreByName?value=" + genreLabel.getText()), Genre.class);
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Sökabok.fxml"));
		Parent root = loader.load();
		SearchBookController controller = loader.getController();
		controller.init("book/get/booksByGenreId?id=" + genre.getGenreID());
		controller.searchBookTitleLabel.setText("Liknande böcker");
		Scene scene = new Scene(root);
		Stage window = (Stage)this.goToSimiliarBooks.getScene().getWindow();
		window.setScene(scene);
		window.setFullScreenExitHint("");
		window.setFullScreen(true);
		window.show();
	}
}
