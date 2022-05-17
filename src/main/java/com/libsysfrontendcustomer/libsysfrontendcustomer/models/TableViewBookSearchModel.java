package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class TableViewBookSearchModel {
	private String title;
	private String author;
	private String genre;
	private String isbn;
	private int authorId;
	private int genreId;

	public TableViewBookSearchModel(String title, String author, String genre, String isbn, int authorId, int genreId) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isbn = isbn;
		this.authorId = authorId;
		this.genreId = genreId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
}
