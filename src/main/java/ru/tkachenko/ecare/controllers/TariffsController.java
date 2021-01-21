package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.service.OptionService;
import ru.tkachenko.ecare.service.TariffService;

@Controller
@RequestMapping("/tariffs")
public class TariffsController {

    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public TariffsController(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @GetMapping("/all")
    public String showAllTariffs(Model model) {
        model.addAttribute("tariffs", tariffService.showAll());
        return "tariffs/show_all";
    }

    @GetMapping("/{id}")
    public String showTariffById(@PathVariable("id") int id, Model model) {
        model.addAttribute("tariff", tariffService.showById(id));
        return "tariffs/show_by_id";
    }

    @GetMapping("/new")
    public String newTariff(@ModelAttribute("tariff") TariffDTO tariffDTO) {
        return "tariffs/new";
    }

    @PostMapping
    public String createTariff(@ModelAttribute("tariff") TariffDTO tariffDTO) {
        tariffService.save(tariffDTO);
        return "redirect:/tariffs";
    }

    @GetMapping("/{id}/edit")
    public String editTariff(Model model, @PathVariable("id") int id) {
        model.addAttribute("tariff", tariffService.showById(id));
        model.addAttribute("options", optionService.showAll());
        return "tariffs/edit";
    }

    @PatchMapping("/{id}")
    public String updateTariff(@ModelAttribute("tariff") TariffDTO tariffDTO) {
        tariffService.update(tariffDTO);
        return "redirect:/tariffs/all";
    }

    @DeleteMapping("/{id}")
    public String deleteTariff(@ModelAttribute("tariff") TariffDTO tariffDTO, @PathVariable("id") int id) {
        tariffService.delete(tariffDTO, id);
        return "redirect:/tariffs/all";
    }
}
