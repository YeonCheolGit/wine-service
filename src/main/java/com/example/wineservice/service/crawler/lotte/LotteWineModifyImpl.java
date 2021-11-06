package com.example.winesearchservice.service.crawler.lotte;

import com.example.winesearchservice.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RequiredArgsConstructor
@Slf4j
@Service
public class LotteWineModifyImpl implements LotteWineModify {

    private final WineRepository wineRepository;

    @Async
    @Override
    public CompletableFuture<List<String>> wineNameList(List<WebElement> names) {
        log.info("[ wineNameList() ] - start");

        List<String> nameList = new ArrayList<>(100); // 와인 이름 저장 할 배열
        for (WebElement wineName : names) {// 한 페이지씩 와인 이름 가져온 후 배열에 저장
            nameList.add(wineName.getText().trim());
        }

        return CompletableFuture.completedFuture(nameList);
    }

    @Async
    @Override
    public CompletableFuture<List<Integer>> winePriceList(List<WebElement> prices) {
        log.info("[ winePriceList() ] - start");

        List<Integer> priceList = new ArrayList<>(100); // 와인 가격 저장 할 배열
        String price;

        for (WebElement winePrice : prices) { // 한 페이지씩 와인 가격 가져온 후 배열에 저장
            price = winePrice.getText().replaceAll("[^0-9]", ""); // 와인 가격에서 숫자만 가져오기
            priceList.add(Integer.parseInt(price));
        }

        return CompletableFuture.completedFuture(priceList);
    }

    @Transactional
    public void saveNameAndPriceList(List<String> nameList, List<Integer> priceList) {
        log.info("[ saveNameAndPriceList() ] - start");
        int nameListSize = nameList.size();

        for (int i = 0; i < nameListSize; i++) {
            wineRepository.saveWineNameAndPrice(nameList.get(i), priceList.get(i));
        }
    }
}
