package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.dto.productdto.MainPageProductDTO;
import com.ratethis.publicservice.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/RateThis/public")
@AllArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/mainPage")
    public List<MainPageProductDTO> productsForMainPage(){
        return mainService.getMainPageProducts();
    }
}
