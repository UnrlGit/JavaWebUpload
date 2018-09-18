package com.athompson.repo;

import com.athompson.model.Product;
import com.athompson.model.User;

import java.util.ArrayList;

public interface ShopRepo
{

    public ArrayList<Product> getItem(String filter);
    public User getUser(String email, String password);

}
