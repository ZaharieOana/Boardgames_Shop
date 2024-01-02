package org.shop.Shop.controllers;

import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boardgameWorld/client")
public class ClientController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayClientPage(Model model){
        model.addAttribute("title", "Hello " + userRepository.findById(CurrentUser.getId()).get().getName());
        return "client/client";
    }

}
