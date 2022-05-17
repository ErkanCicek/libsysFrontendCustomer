package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

import java.util.Arrays;

public class Room {
	public int roomID;
	public String roomName;
	public int[] defaultTimeArray;
	public String imageurl;

	public Room(int roomID, String roomName, int[]defaultTimeArray, String imageurl) {
		this.roomID = roomID;
		this.roomName = roomName;
		this.defaultTimeArray = defaultTimeArray;
		this.imageurl = imageurl;
	}

	public Room() {

	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int[] getDefaultTimeArray() {
		return defaultTimeArray;
	}

	public void setDefaultTimeArray(int[] defaultTimeArray) {
		this.defaultTimeArray = defaultTimeArray;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "Room{" +
				"roomID=" + roomID +
				", roomName='" + roomName + '\'' +
				", defultTimeArray=" + Arrays.toString(defaultTimeArray) +
				", imageurl='" + imageurl + '\'' +
				'}';
	}
}
