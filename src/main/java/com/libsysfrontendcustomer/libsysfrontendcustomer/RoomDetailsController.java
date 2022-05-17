package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoomDetailsController implements Initializable {

	Room room = new Room();
	public Image image;
	@FXML
	public Label roomNameId;
	@FXML
	public ImageView ImageViewId;
	@FXML
	public ChoiceBox choiseBoxTimeId;
	@FXML
	public Button ReserveBtnId;
	@FXML
	public Button backBtn;

	public void ReserveButton(ActionEvent event) {
		System.out.println("nice");
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}

	public void backToRoomMeny(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bokaRumMeny.fxml")));
			Stage window = (Stage) backBtn.getScene().getWindow();
			window.setScene(new Scene(root));
			window.setFullScreenExitHint("");
			window.setFullScreen(true);
			window.setMaximized(true);
			window.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
