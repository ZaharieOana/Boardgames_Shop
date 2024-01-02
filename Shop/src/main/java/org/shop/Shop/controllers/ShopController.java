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

import java.util.ArrayList;
import java.util.List;

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

        if(!userRepository.findByEmailIs(newUser.getEmail()).isEmpty()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "User already exists!");
            return "users/create";
        }

        newUser.setType(UserType.CLIENT);
        userRepository.save(newUser);
        return "redirect:/boardgameWorld";
    }

    @GetMapping("login")
    public String displayLogInForm(Model model) {
        model.addAttribute("title", "Log In");
        return "users/login";
    }

    @PostMapping("login")
    public String processLogInForm(@RequestParam String email,
                                   @RequestParam String password,
                                   Model model) {
        List<User> users = userRepository.findByEmailIs(email);
        if(users.isEmpty() || !(users.get(0).getPassword().equals(password))){
            model.addAttribute("title", "Log In");
            model.addAttribute("errorMsg", "User or Password invalid!");
            return "users/login";
        }

        if(users.get(0).getType().equals(UserType.CLIENT))
            return "redirect:/boardgameWorld/client";

        return "redirect:/boardgameWorld/admin";
    }


}
