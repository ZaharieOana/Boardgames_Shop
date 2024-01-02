package org.shop.Shop.controllers;

import jakarta.validation.Valid;
import org.shop.Shop.data.GameRepository;
import org.shop.Shop.data.SaleRepository;
import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.CurrentUser;
import org.shop.Shop.models.Game;
import org.shop.Shop.models.User;
import org.shop.Shop.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boardgameWorld/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SaleRepository saleRepository;

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

        newUser.setType(UserType.ADMIN);
        userRepository.save(newUser);
        return "redirect:/boardgameWorld/admin";
    }

    @GetMapping("add_game")
    public String displayAddGameForm(Model model) {
        model.addAttribute("title", "Add Game");
        model.addAttribute(new Game());
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

}
