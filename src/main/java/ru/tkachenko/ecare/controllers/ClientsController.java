package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    private ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showAllClients(Model model) {
        model.addAttribute("clients", clientService.showAll());
        return "clients/show_all";
    }

    @GetMapping("{id}")
    public String showClientById(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientService.showById(id));
        return "clients/show_by_id";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping
    public String createClient(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientService.showById(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") Client client) {
        clientService.update(client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@ModelAttribute("client") Client client) {
        clientService.delete(client);
        return "redirect:/clients";
    }
}
