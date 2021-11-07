package com.example.wineservice.controller;

import com.example.wineservice.service.crawler.lotte.LotteWineCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WineController {

    private final LotteWineCrawler lotteWineCrawler;

    @GetMapping("/wine/lotte")
    public String lotteController() {
        lotteWineCrawler.parseLotte();
        return "ok";
    }
}
