package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class TVBorrowedBooks {

    private int borrowedBooksID;
    private String returnDate;
    private String bookISBN;
    private String borrowerSSN;
    private int bookId;

    public TVBorrowedBooks(int borrowedBooksID, String returnDate, String bookISBN, String borrowerSSN, int bookId) {
        this.borrowedBooksID = borrowedBooksID;
        this.returnDate = returnDate;
        this.bookISBN = bookISBN;
        this.borrowerSSN = borrowerSSN;
        this.bookId = bookId;
    }

    public int getBorrowedBooksID() {
        return borrowedBooksID;
    }

    public void setBorrowedBooksID(int borrowedBooksID) {
        this.borrowedBooksID = borrowedBooksID;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBorrowerSSN() {
        return borrowerSSN;
    }

    public void setBorrowerSSN(String borrowerSSN) {
        this.borrowerSSN = borrowerSSN;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "TVBorrowedBooks{" +
                "borrowedBooksID=" + borrowedBooksID +
                ", returnDate='" + returnDate + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", borrowerSSN='" + borrowerSSN + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}
