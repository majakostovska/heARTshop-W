package com.example.heartshop.bootstrap;

import com.example.heartshop.model.Artist;
import com.example.heartshop.model.Category;
import com.example.heartshop.model.Painting;
import com.example.heartshop.model.ShoppingCart;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Painting> paintings = new ArrayList<>();
    public static List<Artist> artists = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();


}
