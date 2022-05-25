package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libsysfrontendcustomer.libsysfrontendcustomer.models.Borrower;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class ReserveStudyRoomConfirmation {
	ConnectionManager connectionManager = new ConnectionManager();
	private final Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
	Properties properties = new Properties();

	@FXML
	public TextField ssnInput;
	@FXML
	public PasswordField passwordInput;
	@FXML
	public Button confirmationButton;
	@FXML
	public Label info;
	@FXML
	public Button backBtn;
	@FXML
	public Button finishButton;

	public void onlynum(){
		passwordInput.textProperty().addListener((observableValue, s, newValue) -> {
			if (!newValue.matches("\\d*")) {
				passwordInput.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
	}
	public void confirm() {
		try {
			Map<String, Object> verifyLoginOutput = new Gson().fromJson(connectionManager.sendGetRequest("borrower/get/verifyBorrower?SSN=" + ssnInput.getText() + "&password=" + passwordInput.getText()), mapType);
			boolean isVerified = (boolean) verifyLoginOutput.get("booleanvalue");
			if (isVerified){
				Integer roomResId = (Integer) properties.get("roomResId");
				Integer roomId = (Integer) properties.get("roomId");
				String time = properties.getProperty("selectedTime").substring(0,2);
				String roomname = properties.getProperty("roomName");
				Borrower borrower = new Gson().fromJson(connectionManager.sendGetRequest("borrower/get/borrowerBySSN?value=" + ssnInput.getText()), Borrower.class);
				connectionManager.sendPostRequest("roomRes/post/reserveRoom?roomResId=" + roomResId + "&time=" + time + "&borrowerId=" + borrower.getBorrowerId() + "&roomId=" + roomId);
				info.setText("DU HAR BOKAT GRUPPRUM: " + roomname + " | KLOCKAN: " + time+ ":00 - " + (Integer.parseInt(time) + 2) + ":00" + " | on: " + properties.get("dateReserved"));
				confirmationButton.setVisible(false);
				backBtn.setVisible(false);
				finishButton.setVisible(true);
			}else{
				info.setText("Fel användarnamn eller lösenord. Kolla gärna med kassa");
			}
		}catch (NullPointerException e){
			info.setText("Fel användarnamn eller lösenord. Kolla gärna med kassa");
		}
	}

	public void init(Properties properties){
		onlynum();
		this.properties = properties;
	}

	public void goBack() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetails.fxml"));
		try {
			Parent root = loader.load();
			RoomDetailsController roomDetailsController = loader.getController();
			var room = new Gson().fromJson(connectionManager.sendGetRequest("room/get/roomById?id=" + properties.get("roomId")), HashMap.class);
			Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream((String) room.get("imageUrl"))));
			roomDetailsController.ImageViewId.setImage(image);
			roomDetailsController.roomNameId.setText((String) room.get("roomName"));
			roomDetailsController.init(1);
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void goHome() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
		Stage window = (Stage) finishButton.getScene().getWindow();
		window.setScene(new Scene(root));
		window.setFullScreenExitHint("");
		window.setFullScreen(true);
		window.show();
	}
}
