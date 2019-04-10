package com.netease.homework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.homework.repository.*;
import com.netease.homework.service.CartService;
import com.netease.homework.service.ProductServcie;
import com.netease.homework.service.SettleService;
import com.netease.homework.service.UserService;
import java.text.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//已经购买的商品
@Controller
@RequestMapping(value = "/settle")
public class SettleController {
    @Autowired
    SettleService settleService;
    @Autowired
    UserService userService;
    @Autowired
    ProductServcie productServcie;
    @Autowired
    CartService cartService;

    @PostMapping(value = "/add")
    @ResponseBody
//    public String addProduct(@RequestParam(value = "productId") Integer productId, @RequestParam(value = "productNum") Integer number, HttpSession session) {
    public String addProduct(@RequestBody String products, HttpSession session) {
        String username = (String) session.getAttribute("username");
        UserPO userPO=userService.getUserByName(username).get(0);

        JSONObject productJSON=JSON.parseObject(products);
        JSONArray productsId=productJSON.getJSONArray("productsId");
        JSONArray productsNum=productJSON.getJSONArray("productsNum");

        for(int i=0;i<productsId.size();i++){
            ProductPO productPO =productServcie.selectById(Integer.parseInt((String)productsId.get(i)));
            SettlePO settlePO=new SettlePO();
            settlePO.setNumber(Integer.parseInt((String)productsNum.get(i)));
            settlePO.setPrice(productPO.getPrice());
            settlePO.setProductId(Integer.parseInt((String)productsId.get(i)));
            settlePO.setUserId(userPO.getId());
            settlePO.setGmtCreate(new Date());
            settlePO.setGmtModified(new Date());
            settleService.insert(settlePO);
            //别忘了将购物车中对应的记录删除,还要减去对应的库存数量
            cartService.deleteByUserIdAndProductId(userPO.getId(),Integer.parseInt((String)productsId.get(i)));
            productPO.setNumbers(productPO.getNumbers()-Integer.parseInt((String)productsNum.get(i)));
            productServcie.updateProduct(productPO);
        }
        return "insert record successfully!";
    }

    @GetMapping(value = "/all")
    public ModelAndView getAllProducts(HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username==null)
            return new ModelAndView("/login");
        ModelAndView modelAndView=new ModelAndView();
        username=(String)session.getAttribute("username");
        List<SettlePO> settlePOList=settleService.getByUserName(username);
        List<SettlePOCustom> settlePOCustomList=new ArrayList<>();

        for(int i=0;i<settlePOList.size();i++){
            SettlePOCustom settlePOCustom=new SettlePOCustom();
            settlePOCustom.setSettlePO(settlePOList.get(i));
            ProductPO productPO=productServcie.selectById(settlePOList.get(i).getProductId());
            String imageURL=productPO.getUrl();
            //判断该图片是否属于网络图片
            if(imageURL.matches("^https*://.*")){
                settlePOCustom.setImageHttpURL(imageURL);
            }else{
                settlePOCustom.setImageURL(imageURL);
            }
            String productName=productPO.getName();
            settlePOCustom.setProductName(productName);
            settlePOCustom.setDateTime(DateFormat.getDateTimeInstance().format(settlePOList.get(i).getGmtCreate()));
            settlePOCustomList.add(settlePOCustom);
        }

        modelAndView.setViewName("settlement");
        modelAndView.addObject("settlePOCustomList",settlePOCustomList);
        return modelAndView;
    }
}
