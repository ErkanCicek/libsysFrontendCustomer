package com.libsysfrontendcustomer.libsysfrontendcustomer.models;

public class Borrower {
	private int borrowerId;
	private String surname;
	private String lastname;
	private String SSN;
	private String email;
	private String tel;
	private String role;
	private String password;

	public Borrower(int borrowerId, String surname, String lastname, String SSN, String email, String tel, String role, String password) {
		this.borrowerId = borrowerId;
		this.surname = surname;
		this.lastname = lastname;
		this.SSN = SSN;
		this.email = email;
		this.tel = tel;
		this.role = role;
		this.password = password;
	}

	public Borrower(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Borrower{" +
				"borrowerId=" + borrowerId +
				", surname='" + surname + '\'' +
				", lastname='" + lastname + '\'' +
				", SSN='" + SSN + '\'' +
				", email='" + email + '\'' +
				", tel='" + tel + '\'' +
				", role='" + role + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
