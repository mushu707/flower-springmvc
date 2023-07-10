package com.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ssm.mapper.UserMapper;
import com.ssm.util.time.Time;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ssm.mapper.CartMapper;
import com.ssm.entity.Cart;
import com.ssm.service.CartService;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{

    @Resource
    private CartMapper cartMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> cartListData(String token) {
        //根据token获取用户名
        String uname = getUname(token);
        Map<String, Object> map = new HashMap<>();
        map.put("cartList", getCartList(uname));
        return map;
    }

    String getUname(String token){
        String uname = userMapper.getUserInfoByToken(token).getName();
        if (uname != null) return uname;
        else return null;
    }

    /**
     * 获取用户购物车商品信息
     * @param name  商品名
     * @param token token
     */
    public Cart getCartInfoByName(String name,String token){
        String uname = getUname(token);
        return cartMapper.getCartInfoByName(name, uname);
    }

    /**
     * 获取用户购物车列表
     * @param uname 用户名
     */
    public List<Cart> getCartList(String uname){
        return cartMapper.getCartList(uname);
    }

    /**
     * 删除购物车信息
     * @param name  商品名
     * @param token token
     */
    public int deleteToCart(String token,String name) {
        String uname = getUname(token);
        //如果该用户的购物车中没有该商品，则删除商品返回1，否则返回0
        if (getCartInfoByName(name, token) != null){
            return cartMapper.deleteToCart(name, uname);
        }else return 0;
    }

    /**
     * 添加购物车信息
     * @param token token
     * @param cart 购物车信息
     */
    public int addToCart(String token, Cart cart) {
        if (getCartInfoByName(cart.getName(), token) == null){
            String uname = getUname(token);
            Timestamp time = Time.getTimeByDate();
            cart.setUname(uname);
            cart.setCreate_time(time);
            cart.setUpdate_time(time);
            return cartMapper.addToCart(cart);
        }else return 0;
    }

    /**
     * 更新购物车信息
     * @param token  token
     * @param carts  购物车列表
     */
    public int updateCart(String token, List<Cart> carts){
        int result = 1;
        String uname = getUname(token);
        for (int i = 0; i < carts.size(); i++) {
            //更新的商品信息
            Cart newCart = carts.get(i);
            //需要更新的商品信息
            Cart oldCart = cartMapper.getCartInfoByName(newCart.getName(), uname);
            if (oldCart.getBuy_count() != newCart.getBuy_count() || oldCart.getIsChecked() != newCart.getIsChecked()){
                result = cartMapper.updateCart(
                        newCart.getName(),
                        newCart.getBuy_count(),
                        uname,
                        Time.getTimeByDate(),
                        newCart.getIsChecked());
            }
            //更新购物车信息发生错误时退出
            if (result == 0) break;
        }
        return result;
    }
}
