package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.service.GenericService;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    private GenericService<Client> genericService;

    @Autowired
    public ClientsController(GenericService<Client> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public String showAllClients(Model model) {
        model.addAttribute("clients", genericService.showAll());
        return "clients/show_all";
    }

    @GetMapping("{id}")
    public String showClientById(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", genericService.showById(id));
        return "clients/show_by_id";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping
    public String createClient(@ModelAttribute("client") Client client) {
        genericService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", genericService.showById(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") Client client) {
        genericService.update(client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@ModelAttribute("client") Client client) {
        genericService.delete(client);
        return "redirect:/clients";
    }
}
