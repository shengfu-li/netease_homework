package com.netease.homework.controller;

import com.netease.homework.repository.ProductPO;
import com.netease.homework.service.ProductServcie;
import com.netease.homework.service.UserService;
import com.netease.homework.storage.StorageFileNotFoundException;
import com.netease.homework.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/publish")
public class PublishController {

    @Autowired
    ProductServcie productServcie;

    @Autowired
    UserService userService;

    private final StorageService storageService;

    @Autowired
    public PublishController(StorageService storageService) {
        this.storageService = storageService;
    }

//    找到所有已经上传的图片
//    @GetMapping("/all")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(PublishController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "publish";
//    }

    @GetMapping(value = "/add")
    public String publish(HttpSession session){
        String username=(String)session.getAttribute("username");
        if(username == null){//说明用户没有登录
            return "login";
        }else {
            return "publish";
        }

    }

    //value可以是正则表达式，在这个简单系统中用不到
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("productUpload") MultipartFile file,@RequestParam String productName,
    @RequestParam String productIntroduce,@RequestParam String productURL,@RequestParam String productDescribe,@RequestParam BigDecimal productPrice,@RequestParam Integer productNumber) {
        ProductPO productPO=new ProductPO();

        if(!file.isEmpty()){//说明是本地上传的图片,将图片保存到本地,默认文件夹是/static/images
            storageService.store(file);
            productPO.setUrl(file.getOriginalFilename());
        }else{//网络图片不保存在本地
            productPO.setUrl(productURL);
        }
        productPO.setName(productName);
        productPO.setIntroduce(productIntroduce);
        productPO.setDescribe(productDescribe);
        productPO.setPrice(productPrice);
        productPO.setNumbers(productNumber);

        productServcie.insert(productPO);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/product";
    }


    //post方法用来更新商品信息
    @PostMapping(value = "/{id}")
    public String updateProduct(@RequestParam("productUpload") MultipartFile file,@RequestParam String productName,
                                @RequestParam String productIntroduce,@RequestParam String productURL,@RequestParam String productDescribe,
                                @RequestParam BigDecimal productPrice,@RequestParam Integer productNumber,@PathVariable Integer id) {

        ProductPO productPO = new ProductPO();
        if(!file.isEmpty()){//说明是本地上传的图片,将图片保存到本地,默认文件夹是/static/images
            storageService.store(file);
            productPO.setUrl(file.getOriginalFilename());
        }else{//网络图片不保存在本地
            productPO.setUrl(productURL);
        }

        productPO.setId(id);
        productPO.setName(productName);
        productPO.setIntroduce(productIntroduce);
        productPO.setDescribe(productDescribe);
        productPO.setPrice(productPrice);
        productPO.setNumbers(productNumber);

        productServcie.updateProduct(productPO);
        return "redirect:/product";
    }

//    get方法用来查询商品信息
    @GetMapping(value = "/{id}")
    public ModelAndView queryProduct(@PathVariable Integer id){
        ProductPO productPO=productServcie.selectById(id);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("product",productPO);

        //判断该图片是不是网络图片
        String pattern="^https*://.*";
        if(Pattern.matches(pattern,productPO.getUrl())){
            modelAndView.addObject("isHttpURL",1);
        }else{
            modelAndView.addObject("isHttpURL",0);
        }
        modelAndView.setViewName("publish");
        return modelAndView;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
