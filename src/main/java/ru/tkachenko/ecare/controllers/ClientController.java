package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tkachenko.ecare.service.ClientService;
import ru.tkachenko.ecare.models.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String indexClient(Model model){
        model.addAttribute("clients", clientService.showAll());
        return "clients/show_all";
    }

    @GetMapping("{id}")
    public String showClient(@PathVariable("id") int id, Model model){
        model.addAttribute("client", clientService.showById(id));
        return "clients/show_by_id";
    }
}
