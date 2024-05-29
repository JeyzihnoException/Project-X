package com.social.social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {


    @GetMapping("/main")
    public ModelAndView getMainPage() {
        return new ModelAndView("template/main-page.html");
    }



}
