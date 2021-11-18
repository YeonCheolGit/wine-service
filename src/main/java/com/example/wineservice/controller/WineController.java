package com.example.wineservice.controller;

import com.example.wineservice.response.CommonResult;
import com.example.wineservice.service.crawler.lotte.LotteWineCrawler;
import com.example.wineservice.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WineController {

    private final LotteWineCrawler lotteWineCrawler;
    private final ResponseService responseService;

    @GetMapping("/wine/lotte")
    public ResponseEntity<CommonResult> lotteController() {
        lotteWineCrawler.parseLotte();
        return new ResponseEntity<>(responseService.getSuccessResult(), HttpStatus.OK);
    }

}
