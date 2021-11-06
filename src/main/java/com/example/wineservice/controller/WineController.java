package com.example.winesearchservice.controller;

import com.example.winesearchservice.service.crawler.lotte.LotteWineCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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
