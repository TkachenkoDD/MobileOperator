package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.service.MessageService;
import ru.tkachenko.ecare.service.OptionService;
import ru.tkachenko.ecare.service.TariffService;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.List;

@Controller
@RequestMapping("/tariffs")
public class TariffsController {

    private final TariffService tariffService;
    private final OptionService optionService;
    private final MessageService messageService;

    @Autowired
    public TariffsController(TariffService tariffService, OptionService optionService, MessageService messageService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.messageService = messageService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newTariff(@ModelAttribute("tariff") TariffDTO tariffDTO) {
        return "tariffs/new";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createTariff(@ModelAttribute("tariff") TariffDTO tariffDTO) throws JMSException, NamingException {
        tariffService.save(tariffDTO);
        messageService.sendMessage("Banzai!");
        return "redirect:/tariffs/all";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editTariff(Model model, @PathVariable("id") int id) {
        model.addAttribute("tariff", tariffService.showById(id));
        model.addAttribute("options", optionService.showAll());
        return "tariffs/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateTariff(@ModelAttribute("tariff") TariffDTO tariffDTO,
                               @RequestParam("options") List<Integer> optionList) throws JMSException, NamingException {
        tariffService.update(tariffDTO, optionList);
        messageService.sendMessage("Banzai!");
        return "redirect:/tariffs/" + tariffDTO.getId();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteTariff(@ModelAttribute("tariff") TariffDTO tariffDTO, @PathVariable("id") int id) throws JMSException, NamingException {
        tariffService.delete(tariffDTO, id);
        messageService.sendMessage("Banzai!");
        return "redirect:/tariffs/all";
    }
}
