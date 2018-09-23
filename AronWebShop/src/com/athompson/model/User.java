package com.athompson.model;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private UserType userType;
	private String password;

	private ArrayList<LocalDateTime> logins;

	private ArrayList<ShoppingHistory> shoppingHistory;

	public ArrayList<LocalDateTime> getLogins() {
		return logins;
	}

	public void setLogins(ArrayList<LocalDateTime> logins) {
		this.logins = logins;
	}

	public ArrayList<ShoppingHistory> getShoppingHistory() {
		return shoppingHistory;
	}

	public void addShoppingCart(ShoppingHistory shoppingHistor) {
		this.shoppingHistory.add(shoppingHistor);
		for (ShoppingHistory sh : shoppingHistory) {
			System.out.println("Shopping history items");
			System.out.println(sh.getCart().totalPrice());

		}
	}

	public void addLogin(LocalDateTime loginTime) {
		logins.add(loginTime);
	}

	public void setShoppingHistory(ArrayList<ShoppingHistory> shoppingHistory) {
		this.shoppingHistory = shoppingHistory;
	}

	public User(String firstName, String lastName, String email, String address, UserType userType, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.userType = userType;
		this.password = password;
		this.logins = new ArrayList<>();
		this.shoppingHistory = new ArrayList<>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
