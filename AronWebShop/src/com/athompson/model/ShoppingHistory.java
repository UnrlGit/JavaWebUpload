package com.athompson.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShoppingHistory {
	private Cart cart;
	private LocalDateTime shoppingDateTime;
	private PaymentType paymentType;
	private ArrayList<String> itemsAndAmount;
	private double price;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public LocalDateTime getShoppingDateTime() {
		return shoppingDateTime;
	}

	public void setShoppingDateTime(LocalDateTime shoppingDateTime) {
		this.shoppingDateTime = shoppingDateTime;
	}

	public ShoppingHistory(Cart cart, LocalDateTime shoppingDateTime, PaymentType paymentType) {
		super();
		this.cart = cart;
		this.shoppingDateTime = shoppingDateTime;
		this.paymentType = paymentType;
		this.price = 0;
		this.itemsAndAmount = new ArrayList<>();
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public ArrayList<String> getItemsAndAmount() {
		return itemsAndAmount;
	}

	public void setItemsAndAmount(ArrayList<String> itemsAndAmount) {
		this.itemsAndAmount = itemsAndAmount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void addItemAndAmount(String itemAndAmount) {
		itemsAndAmount.add(itemAndAmount);
	}
}
