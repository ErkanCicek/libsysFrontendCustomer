package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class Genre {
	private int genreID;
	private String genreName;
	private String genreDesc;

	public Genre(int genreID, String genreName, String genreDesc) {
		this.genreID = genreID;
		this.genreName = genreName;
		this.genreDesc = genreDesc;
	}

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreDesc() {
		return genreDesc;
	}

	public void setGenreDesc(String genreDesc) {
		this.genreDesc = genreDesc;
	}

	@Override
	public String toString() {
		return "Genre{" +
				"genreID=" + genreID +
				", genreName='" + genreName + '\'' +
				", genreDesc='" + genreDesc + '\'' +
				'}';
	}
}
