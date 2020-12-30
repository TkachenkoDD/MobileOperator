package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.AdminDTO;
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
    public String newAdmin(@ModelAttribute("admin") AdminDTO adminDTO) {
        return "admins/new";
    }

    @PostMapping
    public String createAdmin(@ModelAttribute("client") AdminDTO adminDTO) {
        adminService.save(adminDTO);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/edit")
    public String editAdmin(Model model, @PathVariable("id") int id) {
        model.addAttribute("admin", adminService.showById(id));
        return "admins/edit";
    }

    @PatchMapping("/{id}")
    public String updateAdmin(@ModelAttribute("admin") AdminDTO adminDTO) {
        adminService.update(adminDTO);
        return "redirect:/admins";
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@ModelAttribute("admin") AdminDTO adminDTO, @PathVariable("id") int id) {
        adminService.delete(adminDTO, id);
        return "redirect:/admins";
    }
}
