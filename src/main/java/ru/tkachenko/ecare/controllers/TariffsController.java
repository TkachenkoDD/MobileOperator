package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.service.TariffService;
import ru.tkachenko.ecare.models.Tariff;

@Controller
@RequestMapping("/tariffs")
public class TariffsController {

    private TariffService tariffService;

    @Autowired
    public TariffsController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping()
    public String indexTariff(Model model) {
        model.addAttribute("tariffs", tariffService.showAll());
        return "tariffs/show_all";
    }

    @GetMapping("/{id}")
    public String showTariff(@PathVariable("id") int id, Model model) {
        model.addAttribute("tariff", tariffService.showById(id));
        return "tariffs/show_by_id";
    }

    @GetMapping("/new")
    public String newTariff(@ModelAttribute("tariff") Tariff tariff) {
        return "tariffs/new";
    }

    @PostMapping
    public String createTariff(@ModelAttribute("tariff") Tariff tariff) {
        tariffService.save(tariff);
        return "redirect:/tariffs";
    }

    @GetMapping("/{id}/edit")
    public String editTariff(Model model, @PathVariable("id") int id) {
        model.addAttribute("tariff", tariffService.showById(id));
        return "tariffs/edit";
    }

    @PatchMapping("/{id}")
    public String updateTariff(@ModelAttribute("tariff") Tariff tariff) {
        tariffService.update(tariff);
        return "redirect:/tariffs";
    }

    @DeleteMapping("/{id}")
    public String deleteTariff(@ModelAttribute("tariff") Tariff tariff){
        tariffService.delete(tariff);
        return "redirect:/tariffs";
    }
}
