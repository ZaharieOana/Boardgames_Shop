package org.shop.Shop.controllers;

import org.shop.Shop.data.GameRepository;
import org.shop.Shop.data.SaleRepository;
import org.shop.Shop.data.UserRepository;
import org.shop.Shop.models.CurrentUser;
import org.shop.Shop.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("boardgameWorld/client")
public class ClientController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping
    public String displayClientPage(Model model){
        model.addAttribute("title", "Hello " + userRepository.findById(CurrentUser.getId()).get().getName());
        return "client/client";
    }

    @GetMapping("personal_data")
    public String displayClientPersonalData(Model model){
        model.addAttribute("title", "Hello " + userRepository.findById(CurrentUser.getId()).get().getName());
        model.addAttribute("user", userRepository.findById(CurrentUser.getId()).get());
        return "client/data";
    }

    @GetMapping("games")
    public String displayGamesTable(Model model){
        model.addAttribute("title", "Hello " + userRepository.findById(CurrentUser.getId()).get().getName());
        model.addAttribute("games", gameRepository.findAll());
        return "client/games";
    }

    @GetMapping("buy")
    public String displayBuyGame(Model model) {
        model.addAttribute("title", "Buy Games");
        model.addAttribute("games", gameRepository.findAll());
        return "client/buy";
    }

    @PostMapping("buy")
    public String processBuyGame(@RequestParam(required = false) int[] gameIds) {
        if(gameIds != null) {
            for(int id : gameIds) {
                saleRepository.save(new Sale(userRepository.findById(CurrentUser.getId()).get().getEmail(), gameRepository.findById(id).get().getName()));
            }
        }

        return "redirect:/boardgameWorld/client";
    }

}
