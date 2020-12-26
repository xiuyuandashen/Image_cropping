package com.zlf.controller;

import com.zlf.util.BASE64DecodedMultipartFile;
import com.zlf.util.GithubUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(path = {"/","/index"})
    public String index(){
        return "index";
    }

    @Autowired
    GithubUploader githubUploader;



    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(HttpServletRequest request,@RequestParam("dataURL") StringBuilder dataURL) throws Exception{
          Map<String,String> map = new HashMap<>();
//        InputStream in = request.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String str = null;
//        StringBuilder builder = new StringBuilder();
//        while ((str=reader.readLine())!=null){
//            builder.append(str);
//        }
//        System.out.println(builder.toString());
         System.out.println(dataURL.toString());
         MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(dataURL.toString());
         String url = githubUploader.upload(multipartFile);
         map.put("src",url);
         return map;
    }
}
