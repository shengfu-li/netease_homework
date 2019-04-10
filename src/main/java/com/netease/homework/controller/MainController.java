package com.netease.homework.controller;

import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.UserPO;
import com.netease.homework.service.ProductServcie;
import com.netease.homework.service.ProductServiceImp;
import com.netease.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductServcie productServcie;

    @Autowired
    private UserService userService;

    //通过表单方式进入
    @PostMapping(value = "/")
    public String loginPost(HttpSession session,@RequestParam String username,@RequestParam("password") String hashPassword) {
        List<UserPO> userPOList=userService.getUserByName(username);
        //在本系统中一般一个名字只有一个用户
        if(!userPOList.isEmpty()){
            //用户传过来的密码是经过MD5加密过的，所以在比较前我们需要将数据库中的用户密码进行加密
            String password = userPOList.get(0).getPassword();
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHashPassword = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            if(myHashPassword.equals(hashPassword.toUpperCase())){
                //userType==0 表示买家，userType==1表示卖家
                String userType=userPOList.get(0).getSeller();
                session.setAttribute("username",username);
                session.setAttribute("userType",userType);
                return "redirect:/product";
            }else {
                return "redirect:/login";
            }
        }else {
            return "redirect:/login";
        }
    }

    //通过GET请求进入首页
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, HttpSession session){

//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                //System.out.println(cookie.getName()+cookie.getValue());
//                if (cookie.getName().equals("sessionId")) {
//                    modelAndView.addObject("sessionId", cookie.getValue());
//                    break;
//                }
//            }
//        }

        return "redirect:/product";

    }

    //登出按钮
    @GetMapping(value = "/logout")
    public String logout(HttpSession session,Model model){
        session.removeAttribute("username");
        session.removeAttribute("userType");
       return "redirect:/product";
    }


    //登入
    @GetMapping(value = "/login")
    public String login() {

        return "login";
    }


}
