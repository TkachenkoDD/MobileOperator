package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.models.Admin;
import ru.tkachenko.ecare.service.GenericService;

@Controller
@RequestMapping("/admins")
public class AdminsController {

    private GenericService<Admin> genericService;

    @Autowired
    public AdminsController(GenericService<Admin> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public String showAllAdmins(Model model) {
        model.addAttribute("admins", genericService.showAll());
        return "admins/show_all";
    }

    @GetMapping("{id}")
    public String showAdmintById(@PathVariable("id") int id, Model model) {
        model.addAttribute("admin", genericService.showById(id));
        return "admins/show_by_id";
    }

    @GetMapping("/new")
    public String newAdmin(@ModelAttribute("admin") Admin admin) {
        return "admins/new";
    }

    @PostMapping
    public String createAdmin(@ModelAttribute("client") Admin admin) {
        genericService.save(admin);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/edit")
    public String editAdmin(Model model, @PathVariable("id") int id) {
        model.addAttribute("admin", genericService.showById(id));
        return "admins/edit";
    }

    @PatchMapping("/{id}")
    public String updateAdmin(@ModelAttribute("admin") Admin admin) {
        genericService.update(admin);
        return "redirect:/admins";
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@ModelAttribute("admin") Admin admin) {
        genericService.delete(admin);
        return "redirect:/admins";
    }
}
