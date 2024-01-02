package org.shop.Shop.controllers;

import org.shop.Shop.data.GameRepository;
import org.shop.Shop.data.SaleRepository;
import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
