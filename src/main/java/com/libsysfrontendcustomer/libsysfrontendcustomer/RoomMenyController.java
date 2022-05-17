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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoomMenyController implements Initializable {

	ConnectionManager manager = new ConnectionManager();
	Room room;
	private Image image;

	@FXML
	public Button backBtn;
	@FXML
	public Button roomOneBtn;
	@FXML
	public Button roomTwoBtn;
	@FXML
	public Button roomThreeBtn;
	@FXML
	public Button roomFourBtn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}

	public void BackToStart() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		try {
			Parent root = loader.load();
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void reserveRoomOne() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetails.fxml"));
		try {
			Parent root = loader.load();
			RoomDetailsController roomDetailsController = loader.getController();
			var room = new Gson().fromJson(manager.sendGetRequest("room/get/roomById?roomId=1"), HashMap.class);
			image = new Image(Objects.requireNonNull(getClass().getResourceAsStream((String) room.get("imageUrl"))));
			roomDetailsController.ImageViewId.setImage(image);
			roomDetailsController.roomNameId.setText((String) room.get("roomName"));
			Stage stage = (Stage) roomOneBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void ReserveRoomTwo() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetails.fxml"));
		try {
			Parent root = loader.load();
			RoomDetailsController roomDetailsController = loader.getController();
			var room = new Gson().fromJson(manager.sendGetRequest("room/get/roomById?roomId=2"), HashMap.class);
			image = new Image(Objects.requireNonNull(getClass().getResourceAsStream((String) room.get("imageUrl"))));
			roomDetailsController.ImageViewId.setImage(image);
			roomDetailsController.roomNameId.setText((String) room.get("roomName"));
			Stage stage = (Stage) roomTwoBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void ReserveRoomThree() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetails.fxml"));
		try {
			Parent root = loader.load();
			RoomDetailsController roomDetailsController = loader.getController();
			var room = new Gson().fromJson(manager.sendGetRequest("room/get/roomById?roomId=3"), HashMap.class);
			image = new Image(Objects.requireNonNull(getClass().getResourceAsStream((String) room.get("imageUrl"))));
			roomDetailsController.ImageViewId.setImage(image);
			roomDetailsController.roomNameId.setText((String) room.get("roomName"));
			Stage stage = (Stage) roomThreeBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void ReserveRoomFour(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetails.fxml"));
		try {
			Parent root = loader.load();
			RoomDetailsController roomDetailsController = loader.getController();
			var room = new Gson().fromJson(manager.sendGetRequest("room/get/roomById?roomId=4"), HashMap.class);
			image = new Image(Objects.requireNonNull(getClass().getResourceAsStream((String) room.get("imageUrl"))));
			roomDetailsController.ImageViewId.setImage(image);
			roomDetailsController.roomNameId.setText((String) room.get("roomName"));
			Stage stage = (Stage) roomFourBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
