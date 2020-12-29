package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.models.Admin;
import ru.tkachenko.ecare.service.AdminService;

@Controller
@RequestMapping("/admins")
public class AdminsController {

    private AdminService adminService;

    @Autowired
    public AdminsController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String showAllAdmins(Model model) {
        model.addAttribute("admins", adminService.showAll());
        return "admins/show_all";
    }

    @GetMapping("{id}")
    public String showAdmintById(@PathVariable("id") int id, Model model) {
        model.addAttribute("admin", adminService.showById(id));
        return "admins/show_by_id";
    }

    @GetMapping("/new")
    public String newAdmin(@ModelAttribute("admin") Admin admin) {
        return "admins/new";
    }

    @PostMapping
    public String createAdmin(@ModelAttribute("client") Admin admin) {
        adminService.save(admin);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/edit")
    public String editAdmin(Model model, @PathVariable("id") int id) {
        model.addAttribute("admin", adminService.showById(id));
        return "admins/edit";
    }

    @PatchMapping("/{id}")
    public String updateAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.update(admin);
        return "redirect:/admins";
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@ModelAttribute("admin") Admin admin, @PathVariable("id") int id) {
        adminService.delete(admin, id);
        return "redirect:/admins";
    }
}
