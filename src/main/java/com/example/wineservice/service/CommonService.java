package com.example.wineservice.service;

import org.springframework.beans.factory.annotation.Value;

public class CommonService {

    @Value("${baseUrl}")
    public String baseUrl;
    @Value("${wineName}")
    public String wineName;
    @Value("${winePrice}")
    public String winePrice;
}
