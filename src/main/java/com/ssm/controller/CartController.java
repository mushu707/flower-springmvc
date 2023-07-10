package com.ssm.controller;
import com.ssm.entity.Cart;
import com.ssm.service.CartService;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 显示顾客购物车
     * @param token token
     */
    @ResponseBody
    @RequestMapping(value = "/getCartList",method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R getCartList(@RequestHeader("token") String token){
        if (token != null && token != " "){
            Map<String, Object> map = cartService.cartListData(token);
            return R.ok().setData(map);
        }else return R.error().setMessage("用户未登录");
    }

    /**
     * 添加商品到购物车
     * @param token token
     * @param cart 购物车信息
     */
    @ResponseBody
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public R addToCart(@RequestHeader("token") String token, @RequestBody Cart cart){
        int result = cartService.addToCart(token, cart);
        if (result == 1) return R.ok();
        else return R.error();
    }

    /**
     * 从购物车删除商品
     * @param cart 商品购物车信息
     * @param token token
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCart",method = RequestMethod.POST)
    public R deleteCart(@RequestHeader("token") String token, @RequestBody Cart cart){
        int result = cartService.deleteToCart(token, cart.getName());
        if (result == 1) return R.ok();
        else return R.error();
    }

    /**
     * 保存购物车信息
     * @param cart 商品购物车信息
     * @param token token
     */
    @ResponseBody
    @RequestMapping(value = "/updateCart",method = RequestMethod.POST)
    public R saveCart(@RequestHeader("token") String token, @RequestBody List<Cart> cart){
        int result = cartService.updateCart(token, cart);
        if (result == 1) return R.ok();
        else return R.error();
    }
}
