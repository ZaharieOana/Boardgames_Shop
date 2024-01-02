package org.shop.Shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boardgameWorld/admin")
public class AdminController {

    @GetMapping
    public String displayClientPage(Model model){
        model.addAttribute("title", "Admin");
        return "admin/admin";
    }

}
