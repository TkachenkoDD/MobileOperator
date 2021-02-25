package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tkachenko.ecare.service.TariffService;

@Controller
@RequestMapping("/ecare")
public class HomepageController {

    private final TariffService tariffService;

    @Autowired
    public HomepageController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    public String homepageController(Model model){
        model.addAttribute("tariffs", tariffService.loadTariffs());
        return "homepage";
    }
}
