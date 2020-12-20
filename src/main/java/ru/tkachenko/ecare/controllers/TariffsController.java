package ru.tkachenko.ecare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.models.Tariff;

@Controller
@RequestMapping("/tariffs")
public class TariffsController {

    private TariffDAO tariffDAO;

    public TariffsController(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @GetMapping()
    public String indexTariff(Model model) {
        model.addAttribute("tariffs", tariffDAO.showAll());
        return "tariffs/index";
    }

    @GetMapping("/{id}")
    public String showTariff(@PathVariable("id") int id, Model model) {
        model.addAttribute("tariff", tariffDAO.showId(id));
        return "tariffs/show";
    }

    @GetMapping("/new")
    public String newTariff(@ModelAttribute("tariff") Tariff tariff) {
        return "tariffs/new";
    }

    @PostMapping
    public String createTariff(@ModelAttribute("tariff") Tariff tariff) {
        tariffDAO.save(tariff);
        return "redirect:/tariffs";
    }

    @GetMapping("/{id}/edit")
    public String editTariff(Model model, @PathVariable("id") int id) {
        model.addAttribute("tariff", tariffDAO.showId(id));
        return "tariffs/edit";
    }

    @PatchMapping("/{id}")
    public String updateTariff(@ModelAttribute("tariff") Tariff tariff) {
        tariffDAO.update(tariff);
        return "redirect:/tariffs";
    }

    @DeleteMapping("/{id}")
    public String deleteTariff(@ModelAttribute("tariff") Tariff tariff){
        tariffDAO.delete(tariff);
        return "redirect:/tariffs";
    }
}
