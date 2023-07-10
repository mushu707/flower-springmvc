package com.ssm.service;
import com.ssm.entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartService{

    Map<String, Object> cartListData(String token);

    Cart getCartInfoByName(String name, String token);

    List<Cart> getCartList(String uname);

    int addToCart(String token, Cart cart);

    int deleteToCart(String token, String name);

    int updateCart(String token, List<Cart> carts);

}
