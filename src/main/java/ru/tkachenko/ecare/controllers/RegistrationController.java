package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.service.ClientService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final ClientService clientService;

    @Autowired
    public RegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String registrationClient(@ModelAttribute("client") ClientDTO clientDTO) {
        return "registration";
    }

    @PostMapping
    public String createRegisteredClient(@ModelAttribute("client") ClientDTO clientDTO) {
        clientService.save(clientDTO);
        return "redirect:/login";
    }
}
