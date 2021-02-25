package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.service.TariffService;

import java.util.List;

@Controller
@RequestMapping("/billboard")
public class BillboardController {

    private final TariffService tariffService;

    @Autowired
    public BillboardController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    @ResponseBody
    public List<TariffDTO> tariffsForBillboard() {
       return tariffService.loadTariffs();
    }
}
