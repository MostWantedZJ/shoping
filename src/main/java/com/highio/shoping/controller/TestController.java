package com.highio.shoping.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("/hello")
    public String func(Model model){
        model.addAttribute("name","jian");
        return "testWebPage";
    }

    @RequestMapping("this")
    public String getValue(){
        return "123";
    }


}
