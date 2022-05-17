package com.libsysfrontendcustomer.libsysfrontendcustomer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("StartPage.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Kungliga Stadsbiblioteket");
		stage.setScene(scene);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}