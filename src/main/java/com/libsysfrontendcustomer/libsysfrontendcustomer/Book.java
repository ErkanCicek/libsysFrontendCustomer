package com.libsysfrontendcustomer.libsysfrontendcustomer;

public class Book {
    private String ISBN;
    private String title;
    private String bookDesc;
    private int authorID;
    private int genreID;
    private int amount;
    private boolean isBookAvailable;

    public Book(String ISBN, String title, String bookDesc, int authorID, int genreID, int amount) {
        this.ISBN = ISBN;
        this.title = title;
        this.bookDesc = bookDesc;
        this.authorID = authorID;
        this.genreID = genreID;
        this.amount = amount;
        isBookAvailable = true;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", authorID=" + authorID +
                ", genreID=" + genreID +
                ", amount=" + amount +
                ", isBookAvailable=" + isBookAvailable +
                '}';
    }
}