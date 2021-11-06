package com.example.winesearchservice.service.crawler.lotte;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LotteWineModify {

    CompletableFuture<List<String>> wineNameList(List<WebElement> names);

    CompletableFuture<List<Integer>> winePriceList(List<WebElement> prices);
}
