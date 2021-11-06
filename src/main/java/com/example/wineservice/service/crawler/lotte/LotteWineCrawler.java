package com.example.winesearchservice.service.crawler.lotte;

import com.example.winesearchservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LotteWineCrawler extends CommonService {

    private final LotteWineCrawlerHelper lotteWineCrawlerHelper;

    List<WebElement> wineNamesElement; // 와인 이름
    List<WebElement> winePricesElement; // 와인 가격

    public void parseLotte() {
        log.info("[ parseLotte() ] - start");
        WebDriver webDriver = new SafariDriver();
        webDriver.get(baseUrl);

        int page = 1;
        while (page < 5) { // 총 와인 페이지
            try {
                log.info("롯데마트 " + page + "페이지 넘어왔습니다.");
                wineNamesElement = webDriver.findElements(By.xpath(wineName)); // 와인 이름
                winePricesElement = webDriver.findElements(By.xpath(winePrice)); // 와인 가격

                lotteWineCrawlerHelper.trimAndAddWines(wineNamesElement, winePricesElement);

                WebDriverWait waitClickable = new WebDriverWait(webDriver, 10); // 웹 드라이버 최대 10초간 기다림
                WebElement nextButton = waitClickable.until(ExpectedConditions.elementToBeClickable(By.className("srchPaginationNext"))); // 다음 페이지 버튼 찾아서 클릭 가능할 때까지 기다림.
                nextButton.sendKeys(Keys.ENTER); // 다음 페이지 버튼 클릭

                page++;

                log.info("롯데마트 " + page + "페이지 넘어가는 중...");
                Thread.sleep(5000);
            } catch (Exception e) { // 다음 버튼 찾을 수 없는 에러 발생 시, 그동안 가지고 온 데이터 저장
                log.debug("[ parseLotte() ] - error");
                log.debug("================ 다음 버튼을 찾을 수 없습니다 ================");
                log.debug(e.getMessage());
                log.debug("===================== lotte 마트 크롤링 끝 =====================");
                webDriver.close();
            }
        }
    }

}
