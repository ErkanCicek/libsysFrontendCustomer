package com.libsysfrontendcustomer.libsysfrontendcustomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RoomDetailsController{

	private final ConnectionManager connectionManager = new ConnectionManager();
	public int roomid;
	private int roomResId;

	@FXML
	public DatePicker datePicker;
	@FXML
	public ComboBox<String> comboBoxId;
	@FXML
	public Label roomNameId;
	@FXML
	public ImageView ImageViewId;
	@FXML
	public Button ReserveBtnId;
	@FXML
	public Button backBtn;
	@FXML
	public Label emptyChoiceBoxWarning;

	public void ReserveButton() {
		Properties properties;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomReservationConfirmation.fxml"));
		if (comboBoxId.getSelectionModel().isEmpty()){
			emptyChoiceBoxWarning.setVisible(true);
		}
		try {
			properties = new Properties();
			properties.put("roomId", roomid);
			properties.put("roomResId", roomResId);
			properties.put("selectedTime", comboBoxId.getSelectionModel().getSelectedItem());
			properties.put("roomName", roomNameId.getText());
			properties.put("dateReserved", String.valueOf(datePicker.getValue()));
			Parent root = loader.load();
			ReserveStudyRoomConfirmation reserveStudyRoomConfirmation = loader.getController();
			reserveStudyRoomConfirmation.init(properties);
			Stage stage = (Stage) ReserveBtnId.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setFullScreenExitHint("");
			stage.setFullScreen(true);
			stage.show();
		}catch (IOException e){
			e.printStackTrace();
		}catch (NullPointerException ignore){
		}
	}

	public void init(Integer roomid){
		this.datePicker.setShowWeekNumbers(false);
		this.datePicker.setDayCellFactory(datePicker -> new DateCell(){
			public void updateItem(LocalDate date, boolean empty){
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || (date.compareTo(today) < 0) || (date.compareTo(today.plus(2, ChronoUnit.WEEKS)) > 0));
			}
		});
		this.roomid = roomid;
	}

	public void dataGetter(String valueDate){
		this.roomResId = Integer.parseInt(this.connectionManager.sendGetRequest("roomRes/get/roomResIdByDate?value=" + valueDate));
		if (this.roomResId == 0){
			this.connectionManager.sendGetRequest("roomRes/post/insertIntoRoomResDate?value=" + valueDate);
		}
		this.roomResId = Integer.parseInt(this.connectionManager.sendGetRequest("roomRes/get/roomResIdByDate?value=" + valueDate));
		Type integerArrayType = new TypeToken<Integer[]>(){}.getType();
		Integer[]getReservedTimes = new Gson().fromJson(connectionManager.sendGetRequest("roomRes/get/freeTimesByIds?roomresid=" + roomResId + "&roomid=" + this.roomid),integerArrayType);
		System.out.println(Arrays.toString(getReservedTimes));
		List<Integer> timesAvaliable = new ArrayList<>(Arrays.asList(10,12,14,16,18));
		for (Integer reservedTime : getReservedTimes) {
			timesAvaliable.removeIf(value -> Objects.equals(reservedTime, value));
		}

		ObservableList<String>observableListTime = FXCollections.observableArrayList();

		for (Integer integer : timesAvaliable) {
			observableListTime.add(integer + ":00 - " + (integer + 2) + ":00");
		}
		comboBoxId.setItems(observableListTime);
	}

	public void backToRoomMeny() {
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

	public void datePickerAction() {

		dataGetter(String.valueOf(this.datePicker.getValue()));
	}


}
