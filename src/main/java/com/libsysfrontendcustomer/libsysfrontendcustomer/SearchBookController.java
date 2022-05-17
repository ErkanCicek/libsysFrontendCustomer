package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Author;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Book;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Genre;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.TableViewBookSearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchBookController implements Initializable {
	ConnectionManager manager = new ConnectionManager();
	public TableView<TableViewBookSearchModel> bookTableView;
	public TableColumn<TableViewBookSearchModel, String>titleCol;
	public TableColumn <TableViewBookSearchModel, String>authorCol;
	public TableColumn<TableViewBookSearchModel, String> genreCol;
	public ObservableList<TableViewBookSearchModel> observableList = FXCollections.observableArrayList();

	public TextField inputField;
	public Button goBackToStart;
	public Label LWarningNoBook;
	public Button chooseBook;


	public void goBackBtn(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
			Stage window = (Stage) goBackToStart.getScene().getWindow();
			window.setScene(new Scene(root));
			window.setFullScreenExitHint("");
			window.setFullScreen(true);
			window.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Book[]books = new Gson().fromJson(manager.sendGetRequest("book/get/allBooks"), Book[].class);
		for (Book book : books) {
			Author tempAuthor = new Gson().fromJson(manager.sendGetRequest("author/get/authorById?id=" + book.getAuthorID()), Author.class);
			Genre tempGenre = new Gson().fromJson(manager.sendGetRequest("genre/get/genreById?id=" + book.getGenreID()), Genre.class);
			observableList.add(new TableViewBookSearchModel(
					book.getTitle(), tempAuthor.getAuthorName(), tempGenre.getGenreName(), book.getISBN(), book.getAuthorID(), book.getGenreID())
			);
		}
		FilteredList<TableViewBookSearchModel>filteredList = new FilteredList<>(observableList, p -> true);
		inputField.textProperty().addListener(((observableValue, s, t1) -> {
			filteredList.setPredicate(tableViewBookSearchModel -> {
				if (t1 == null || t1.isEmpty()){
					return true;
				}

				String lowercase = t1.toLowerCase();

				if (tableViewBookSearchModel.getTitle().toLowerCase().contains(lowercase)){
					return true;
				}else if(tableViewBookSearchModel.getAuthor().toLowerCase().contains(lowercase)){
					return true;
				}else return tableViewBookSearchModel.getGenre().toLowerCase().contains(lowercase);
			});
		}));

		SortedList<TableViewBookSearchModel> sortedList = new SortedList<>(filteredList);

		sortedList.comparatorProperty().bind(bookTableView.comparatorProperty());

		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

		bookTableView.setItems(sortedList);

	}

	public void goToBookDetails() {
		TableViewBookSearchModel temp = bookTableView.getSelectionModel().getSelectedItem();
		Book book = new Gson().fromJson(manager.sendGetRequest("book/get/bookByISBN?bookIsbn=" + temp.getIsbn()), Book.class);
		Author author = new Gson().fromJson(manager.sendGetRequest("author/get/authorById?id=" + temp.getAuthorId()), Author.class);
		Genre genre = new Gson().fromJson(manager.sendGetRequest("genre/get/genreById?id=" + temp.getGenreId()), Genre.class);
		String amount = manager.sendGetRequest("book/get/countBook?bookIsbn=" + book.getISBN());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDescPage.fxml"));
		try {
			Parent root = loader.load();
			BookDescPageController bookDescPageController = loader.getController();
			bookDescPageController.titleLabel.setText(book.getTitle());
			bookDescPageController.authorLabel.setText(author.getAuthorName());
			bookDescPageController.genreLabel.setText(genre.getGenreName());
			bookDescPageController.bookDescLabel.setText(book.getBookDesc());
			bookDescPageController.amountLabel.setText(amount);
			Stage stage = (Stage) chooseBook.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
