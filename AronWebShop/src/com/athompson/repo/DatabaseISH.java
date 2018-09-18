package com.athompson.repo;

import com.athompson.model.Cart;
import com.athompson.model.PaymentType;
import com.athompson.model.Product;
import com.athompson.model.ShoppingHistory;
import com.athompson.model.User;
import com.athompson.model.UserType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseISH implements ShopRepo {
	private final User[] users;
	private final Product[] items;

	private DatabaseISH() {

		users = new User[] { new User("Miro", "Miric", "miro@mirich.com", "Ilica 242", UserType.ADMIN, "pass123"),
				new User("Buha", "Buhic", "buha@buhic.com", "Trnje 1", UserType.USER, "heck123"),
				new User("Loop", "Cat", "loop@cat.com", "Negdje 21", UserType.USER, "tummy123") };

		items = new Product[] { new Product("pictures/products/alliance_ashbringer.jpg", "Alliance Ashbringer", 50.00, "Figures", "1"),
				new Product("pictures/products/alliance_helmet.jpg", "Alliance Helmet", 250.00, "Figures", "2"),
				new Product("pictures/products/neutral_sword.jpg", "Neutral Sword", 15.00, "Other", "3"),
				new Product("pictures/products/neutral_warglaive.jpg", "Warglaive of Azzinoth", 15.00, "Other", "4"),
				new Product("pictures/products/horde_dagger.jpg", "Horde Dagger", 30.00, "Plushies", "5"),
				//new Product("pictures/products/bottle_256.jpg", "Water bottle", 40.00, "Other", "6"),
				new Product("pictures/products/horde_cake.jpg", "Horde Cake", 40.00, "Plushies", "7"),
				new Product("pictures/products/alliance_shield.jpg", "Alliance Shield", 50.00, "Figures", "8"),
				new Product("pictures/products/horde_sword.jpg", "Horde Sword", 25.00, "Plushies", "9"),
				new Product("pictures/products/alliance_sword.jpg", "Alliance Sword", 80.00, "Figures", "10"),
				//new Product("pictures/products/sombra_256.jpg", "Sombra nendoroid", 50.00, "Figures", "11"),
				new Product("pictures/products/horde_axe.jpg", "Straps set", 10.00, "Plushies", "12"),
				//new Product("pictures/products/vader_256.jpg", "Vader statue", 300.00, "Figures", "13") 
				};

		// for (User user : users) {
		// Cart cart1 = new Cart();
		// cart1.addProduct(items[0]);
		// cart1.addProduct(items[0]);
		// cart1.addProduct(items[0]);
		// cart1.addProduct(items[4]);
		// LocalDateTime localDateTime = LocalDateTime.of(2000, 2, 2, 4, 14);
		/// ShoppingHistory shoppingHistory = new ShoppingHistory(cart1, localDateTime,
		// PaymentType.CASH);
		// LocalDateTime randomLoginTime = LocalDateTime.of(1999, 2, 2, 4, 14);
		// user.addShoppingCart(shoppingHistory);
		// user.addLogin(randomLoginTime);
		// }

	};

	public Product getProduct(String id) {
		Product temp = null;

		for (Product product : items) {
			if (product.getId().equals(id)) {
				temp = product;
			}
		}

		return temp;
	}

	private static DatabaseISH instance;

	public static DatabaseISH getInstance() {
		if (instance == null) {
			instance = new DatabaseISH();
		}

		return instance;
	}

	@Override
	public ArrayList<Product> getItem(String filter) {
		ArrayList<Product> filteredItems = new ArrayList<>();

		if (filter.equals("All")) {
			filteredItems.addAll(Arrays.asList(items));
		}

		for (int i = 0; i < items.length; i++) {
			if (items[i].getCategory().equals(filter)) {
				filteredItems.add(items[i]);
			}
		}

		return filteredItems;
	}

	@Override
	public User getUser(String email, String password) {
		User user = null;
		User temp;

		for (int i = 0; i < users.length; i++) {

			temp = users[i];

			if (temp.getEmail().equals(email) && temp.getPassword().equals(password)) {
				user = temp;
				break;
			}

		}

		return user;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		User temp;

		for (int i = 0; i < users.length; i++) {

			temp = users[i];

			if (temp.getEmail().equals(email)) {
				user = temp;
				break;
			}

		}

		return user;
	}

	public void addShopping(User user, ShoppingHistory newShopping) {
		for (User u : users) {
			if (user.getEmail().equals(u.getEmail())) {
				u.addShoppingCart(newShopping);
			}
		}
	}
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> usersList = new ArrayList<>();
		for (User user : users) {
			if (user.getUserType() == UserType.USER) {
				usersList.add(user);
			}
		}
		return usersList;
	}

}
