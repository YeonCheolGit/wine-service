package com.example.wineservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class WineReqDTO {
    private String windName;
    private int price;
}
