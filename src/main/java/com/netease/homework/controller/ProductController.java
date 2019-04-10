package com.netease.homework.controller;

import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.ProductPOCustom;
import com.netease.homework.service.ProductServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class ProductController {
    @Autowired
    private ProductServcie productServcie;

    //通过id查看产品
    @GetMapping(value = "/product/{id}")
    public ModelAndView getProductById(@PathVariable Integer id){
        ModelAndView modelAndView=new ModelAndView();
        ProductPO productPO=productServcie.selectById(id);
        modelAndView.addObject("product",productPO);
        //简单判断下该产品的图片是从本地加载，还是从网络上加载的
        String pattern="^https*://.*";
        if(Pattern.matches(pattern,productPO.getUrl())==true){//说明该产品的图片是从网络加载的
            String productHttpURL=productPO.getUrl();
            modelAndView.addObject("productHttpURL",productHttpURL);
        }
        modelAndView.setViewName("product");
        return  modelAndView;
    }

    //查看所有产品
    @GetMapping(value = "/product")
    public ModelAndView getAllProduct(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        String username=(String) session.getAttribute("username");
        //增加一个属性，用于判断该商品是否已经被购买
        List<ProductPO> productPOLists=productServcie.selectAll();
        List<ProductPOCustom> productPOCustomList=new ArrayList<ProductPOCustom>();
        for(ProductPO p:productPOLists){
            //说明用户没有登录，此时所有商品都是未购买状态
            if(username==null){
                ProductPOCustom producCustom=new ProductPOCustom();
                producCustom.setProductPO(p);
                producCustom.setHasBought("0");
                productPOCustomList.add(producCustom);
//          用户已经登录，判断是否已经购买
            }else{
                ProductPOCustom producCustom=new ProductPOCustom();
                if(username.equals("seller"))
                    username="buyer";
                producCustom.setHasBought(productServcie.isBoughtByUser(p,username)==true?"1":"0");
                producCustom.setProductPO(p);
                productPOCustomList.add(producCustom);
            }
        }
        modelAndView.addObject("products",productPOCustomList);

        //如果用户已经登录，增加一栏显示未购买的商品
        if(username!=null){
            List<ProductPO> productsNotBuy=productServcie.getProductsNotBought(username);
            modelAndView.addObject("productsNotBuy",productsNotBuy);
        }
        return  modelAndView;
    }

    @ResponseBody
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Integer id){
//        System.out.println(id);
        productServcie.deleteProduct(id);
        return "success";
    }
}
