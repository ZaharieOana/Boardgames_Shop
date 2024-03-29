package org.shop.Shop.controllers;

import jakarta.validation.Valid;
import org.shop.Shop.repos.UserRepository;
import org.shop.Shop.models.CurrentUser;
import org.shop.Shop.models.User;
import org.shop.Shop.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Controller
@RequestMapping("boardgameWorld")
public class ShopController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayHomePage(Model model){
        CurrentUser.setId(-1);
        model.addAttribute("title", "Welcome to Boardgame World");
        return "start";
    }

    @GetMapping("signin")
    public String displaySignInForm(Model model) {
        CurrentUser.setId(-1);
        model.addAttribute("title", "Sign In");
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("signin")
    public String processSignInForm(@ModelAttribute @Valid User newUser,
                                    Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Sign In");
            return "users/create";
        }

        if(!userRepository.findByEmailIs(newUser.getEmail()).isEmpty()) {
            model.addAttribute("title", "Sign In");
            model.addAttribute("errorMsg", "User already exists!");
            return "users/create";
        }

        newUser.setPassword(hashPassword(newUser.getPassword()));
        newUser.setType(UserType.CLIENT);
        userRepository.save(newUser);
        return "redirect:/boardgameWorld";
    }

    @GetMapping("login")
    public String displayLogInForm(Model model) {
        CurrentUser.setId(-1);
        model.addAttribute("title", "Log In");
        return "users/login";
    }

    @PostMapping("login")
    public String processLogInForm(@RequestParam String email,
                                   @RequestParam String password,
                                   Model model) {
        List<User> users = userRepository.findByEmailIs(email);

        password = hashPassword(password);

        if(users.isEmpty() || !(users.get(0).getPassword().equals(password))){
            model.addAttribute("title", "Log In");
            model.addAttribute("errorMsg", "User or Password invalid!");
            return "users/login";
        }

        CurrentUser.setId(users.get(0).getId());

        if(users.get(0).getType().equals(UserType.CLIENT))
            return "redirect:/boardgameWorld/client";

        return "redirect:/boardgameWorld/admin";
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
