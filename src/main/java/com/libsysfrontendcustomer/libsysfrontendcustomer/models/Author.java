package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class Author {
	private int authorID;
	private String authorName;

	public Author(int authorID, String authorName) {
		this.authorID = authorID;
		this.authorName = authorName;
	}

	public Author(){}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author{" +
				"authorID=" + authorID +
				", authorName='" + authorName + '\'' +
				'}';
	}
}
