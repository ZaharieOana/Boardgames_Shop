package org.shop.Shop.controllers;

import jakarta.validation.Valid;
import org.shop.Shop.data.GameCatagoryRepository;
import org.shop.Shop.data.GameRepository;
import org.shop.Shop.data.SaleRepository;
import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Controller
@RequestMapping("boardgameWorld/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private GameCatagoryRepository typeRepository;

    @GetMapping
    public String displayAdminPage(Model model){
        model.addAttribute("title", "Hello " + userRepository.findById(CurrentUser.getId()).get().getName());
        return "admin/admin";
    }

    @GetMapping("users")
    public String dispayAllUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("games")
    public String displayGamesTable(Model model){
        model.addAttribute("title", "All Games");
        model.addAttribute("games", gameRepository.findAll());
        return "client/games";
    }

    @GetMapping("sales")
    public String displaySalesTable(Model model){
        model.addAttribute("title", "All Sales");
        model.addAttribute("sales", saleRepository.findAll());
        return "admin/sales";
    }

    @GetMapping("add_admin")
    public String displayAddAdminForm(Model model) {
        model.addAttribute("title", "Add Admin");
        model.addAttribute(new User());
        return "admin/addAdmin";
    }

    @PostMapping("add_admin")
    public String processAddAdminForm(@ModelAttribute @Valid User newUser,
                                    Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Admin");
            return "admin/addAdmin";
        }

        if(!userRepository.findByEmailIs(newUser.getEmail()).isEmpty()) {
            model.addAttribute("title", "Add Admin");
            model.addAttribute("errorMsg", "User already exists!");
            return "admin/addAdmin";
        }

        newUser.setPassword(hashPassword(newUser.getPassword()));
        newUser.setType(UserType.ADMIN);
        userRepository.save(newUser);
        return "redirect:/boardgameWorld/admin";
    }

    @GetMapping("add_game")
    public String displayAddGameForm(Model model) {
        model.addAttribute("title", "Add Game");
        model.addAttribute(new Game());
        model.addAttribute("types", typeRepository.findAll());
        return "admin/games";
    }

    @PostMapping("add_game")
    public String processAddGameForm(@ModelAttribute @Valid Game newGame,
                                      Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "admin/games";
        }

        gameRepository.save(newGame);
        return "redirect:/boardgameWorld/admin";
    }

    @GetMapping("add_type")
    public String displayAddTypeForm(Model model) {
        model.addAttribute("title", "Add Game Type");
        model.addAttribute(new GameType());
        return "admin/type";
    }

    @PostMapping("add_type")
    public String processAddTypeForm(@ModelAttribute @Valid GameType newType,
                                     Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Game Type");
            model.addAttribute("errorMsg", "Name is required");
            return "admin/type";
        }

        typeRepository.save(newType);
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
