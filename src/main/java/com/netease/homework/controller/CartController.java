package com.netease.homework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.homework.repository.CartPO;
import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.UserPO;
import com.netease.homework.service.CartService;
import com.netease.homework.service.ProductServcie;
import com.netease.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shoppingCart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductServcie productServcie;

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public ModelAndView getAllCart(HttpSession session) {
        String username = (String) session.getAttribute("username");
        //如果用户没有登录，则跳转到登录页面
        if (username == null)
            return new ModelAndView("login");
        //从数据库中将该用户购物车中的所有产品读取出来
        List<CartPO> cartList = cartService.selectByUsername(username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartList", cartList);
        modelAndView.setViewName("shoppingCart");
        return modelAndView;
    }

//    一般来说get请求应该具有幂等性，不应该用来执行添加操作，应该用来执行查找操作
//    @GetMapping()
//    public ModelAndView getCart(@RequestParam Integer number, @RequestParam Integer productId, HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        //如果用户没有登录，则跳转到登录页面
//        if (username == null)
//            return new ModelAndView("login");
//
//        CartPO cartPO = cartService.selectByUsername(username, productId);
//        //如果该用户从来没有买过这个物品怎么办？
//        //用户之前买过该商品
//        if (cartPO != null) {
//            cartPO.setNumber(cartPO.getNumber() + number);
//            cartService.updateByUsername(cartPO, username);
//        } else {//说明该用户之前没有买过这个产品
//            cartPO = new CartPO();
//            cartPO.setUserId(userService.getUserByName(username).get(0).getId());
//            cartPO.setProductId(productId);
//            cartPO.setNumber(number);
//            ProductPO productPO = productServcie.selectById(productId);
//            cartPO.setProductName(productPO.getName());
//            cartPO.setPrice(productPO.getPrice());
//            cartService.insert(cartPO);
//        }
//
//        //从数据库中将该用户购物车中的所有产品读取出来
//        List<CartPO> cartList = cartService.selectByUsername(username);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("cartList", cartList);
//        modelAndView.setViewName("shoppingCart");
//        return modelAndView;
//    }

    //把要购买的产品添加到购物车中
    @ResponseBody
    @PostMapping()
    public String addProductInCart(@RequestBody SearchCriteria criteria, HttpSession session) {
        String username = (String) session.getAttribute("username");
        //如果用户没有登录，则跳转到登录页面
        if (username == null)
            return "user not login";

        Integer productId = criteria.getProductId();
        Integer number = criteria.getProductNum();

        CartPO cartPO = cartService.selectByUsername(username, productId);
        //如果该用户从来没有买过这个物品怎么办？
        //用户之前买过该商品
        if (cartPO != null) {
            cartPO.setNumber(cartPO.getNumber() + number);
            cartService.updateByUsername(cartPO, username);
        } else {//说明该用户之前没有买过这个产品
            cartPO = new CartPO();
            cartPO.setUserId(userService.getUserByName(username).get(0).getId());
            cartPO.setProductId(productId);
            cartPO.setNumber(number);
            ProductPO productPO = productServcie.selectById(productId);
            cartPO.setProductName(productPO.getName());
            cartPO.setPrice(productPO.getPrice());
            cartService.insert(cartPO);
        }
        return "success";
    }

    //从购物车中删除数据
    @ResponseBody
    @DeleteMapping()
    public String deleteProductInCart(@RequestBody String productsIdStr, HttpSession session) {
        String username = (String) session.getAttribute("username");
        //如果用户没有登录，则跳转到登录页面
        if (username == null)
            return new String("user must login!");

        JSONObject object = (JSONObject) JSONObject.parse(productsIdStr);
        String ids = object.getString("productsId");
        JSONArray arrays = JSONArray.parseArray(ids);

        for(Object productId:arrays){
            CartPO cartPO = cartService.selectByUsername(username,Integer.parseInt((String)productId));
            //如果该用户从来没有买过这个物品怎么办？
            //用户之前买过该商品
            if (cartPO != null) {
                cartService.deleteById(cartPO.getId());
            }
        }

        return "success";
    }
}



//用来接收post请求发送过来的数据
class SearchCriteria {
    Integer productId;
    Integer productNum;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }


}
