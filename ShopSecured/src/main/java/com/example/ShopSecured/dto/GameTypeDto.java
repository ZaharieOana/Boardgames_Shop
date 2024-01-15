package com.example.ShopSecured.dto;

import com.example.ShopSecured.model.Game;
import lombok.Builder;

import java.util.List;

@Builder
public record GameTypeDto (
        String name,
        List<Game> games
) {}
