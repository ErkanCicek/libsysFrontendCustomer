package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class Book {
	private int bookId;
	private String ISBN;
	private String title;
	private String bookDesc;
	private int authorID;
	private int genreID;
	private boolean isBookAvailable;

	public Book(int bookId, String ISBN, String title, String bookDesc, int authorID, int genreID) {
		this.bookId = bookId;
		this.ISBN = ISBN;
		this.title = title;
		this.bookDesc = bookDesc;
		this.authorID = authorID;
		this.genreID = genreID;
		isBookAvailable = true;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public boolean isBookAvailable() {
		return isBookAvailable;
	}

	public void setBookAvailable(boolean bookAvailable) {
		isBookAvailable = bookAvailable;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", ISBN='" + ISBN + '\'' +
				", title='" + title + '\'' +
				", bookDesc='" + bookDesc + '\'' +
				", authorID=" + authorID +
				", genreID=" + genreID +
				", isBookAvailable=" + isBookAvailable +
				'}';
	}
}
