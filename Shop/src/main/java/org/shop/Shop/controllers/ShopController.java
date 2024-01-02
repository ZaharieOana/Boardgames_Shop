package org.shop.Shop.controllers;

import jakarta.validation.Valid;
import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.User;
import org.shop.Shop.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("boardgameWorld")
public class ShopController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayHomePage(Model model){
        model.addAttribute("title", "Welcome to Boardgame World");
        return "start";
    }

    @GetMapping("view")
    public String dispayAllUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "users/view";
    }

    @GetMapping("signin")
    public String displaySignInForm(Model model) {
        model.addAttribute("title", "Sign In");
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("signin")
    public String processSignInForm(@ModelAttribute @Valid User newUser,
                                    Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "users/create";
        }

        newUser.setType(UserType.CLIENT);
        userRepository.save(newUser);
        return "redirect:/boardgameWorld";
    }

    @GetMapping("login")
    public String displayLogInForm(Model model) {
        model.addAttribute("title", "Log In");
        return "users/create";
    }

    @PostMapping("login")
    public String processLogInForm(@RequestParam String email,
                                   @RequestParam String password) {

        return "redirect:/boardgameWorld/client";
    }


}
