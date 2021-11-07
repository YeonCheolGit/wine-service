package com.example.wineservice.service.crawler.lotte;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RequiredArgsConstructor
@Slf4j
@Service
public class LotteWineCrawlerHelper {
    private final LotteWineModifyImpl lotteWineModifyImpl;

    @Transactional
    public void trimAndAddWines(List<WebElement> names, List<WebElement> prices) {
        log.info("[ trimAndAddWines() ] - start");
        CompletableFuture<List<String>> nameList = lotteWineModifyImpl.wineNameList(names);
        CompletableFuture<List<Integer>> priceList = lotteWineModifyImpl.winePriceList(prices);

        try {
            CompletableFuture.allOf(nameList, priceList).get();
        } catch (InterruptedException | ExecutionException e) {
            log.debug("[ @Async nameList, priceList ] - error");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        List<String> savedNameList = new ArrayList<>(100);
        List<Integer> savedPriceList = new ArrayList<>(100);

        try {
            savedNameList = nameList.get();
            savedPriceList = priceList.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            log.debug("[ @Async nameList, priceList ] - error");
            Thread.currentThread().interrupt();
        }

        lotteWineModifyImpl.saveNameAndPriceList(savedNameList, savedPriceList);
    }
}
