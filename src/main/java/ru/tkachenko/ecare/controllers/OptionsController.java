package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.service.OptionService;

@Controller
@RequestMapping("/options")
public class OptionsController {

    private final OptionService optionService;

    @Autowired
    public OptionsController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAllOptions(Model model) {
        model.addAttribute("options", optionService.showAll());
        return "options/show_all";
    }

    @GetMapping("/{id}")
    public String showOptionById(@PathVariable("id") int id, Model model) {
        model.addAttribute("option", optionService.showById(id));
        return "options/show_by_id";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newOption(@ModelAttribute("option") OptionDTO optionDTO) {
        return "options/new";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createOption(@ModelAttribute("option") OptionDTO optionDTO) {
        optionService.save(optionDTO);
        return "redirect:/options";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editOption(Model model, @PathVariable("id") int id) {
        model.addAttribute("option", optionService.showById(id));
        return "options/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateOption(@ModelAttribute("option") OptionDTO optionDTO) {
        optionService.update(optionDTO);
        return "redirect:/options/" + optionDTO.getId();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteOption(@ModelAttribute("option") OptionDTO optionDTO, @PathVariable("id") int id) {
        optionService.delete(optionDTO, id);
        return "redirect:/options";
    }
}
