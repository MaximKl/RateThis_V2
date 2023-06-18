package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.dto.developerdto.UniqueDeveloperDTO;
import com.ratethis.publicservice.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/RateThis/public")
@AllArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping("/developer/{id}")
    public UniqueDeveloperDTO getDeveloper(@PathVariable(name = "id") String id){
        return developerService.getDeveloper(id);
    }

}
