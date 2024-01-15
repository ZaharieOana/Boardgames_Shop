package com.example.ShopSecured.dto;

import com.example.ShopSecured.model.GameType;
import lombok.Builder;

@Builder
public record GameDto (
        String name,
        GameType type,
        Integer price
) {}
