package com.example.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

  @GetMapping("/dms")
  public String getHtml(Model model){


    return "dms";
  }

}
