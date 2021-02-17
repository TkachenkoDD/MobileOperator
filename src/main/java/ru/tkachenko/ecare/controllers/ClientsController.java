package ru.tkachenko.ecare.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;

    private final Logger logger = Logger.getLogger(ClientsController.class);

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAllClients(Model model) {
        model.addAttribute("clients", clientService.showAll());
        return "clients/show_all";
    }

    @GetMapping
    public String showByName(Model model){
        model.addAttribute("client", clientService.showByName());
        return "clients/show_by_name";}

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
       public String showClientById(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientService.showById(id));
        return "clients/show_by_id";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newClient(@ModelAttribute("client") ClientDTO clientDTO) {
        return "clients/new";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createClient(@ModelAttribute("client") ClientDTO clientDTO) {
        clientService.save(clientDTO);
        logger.info("Create new Client by " + SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/clients" + clientDTO.getId();
    }

    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientService.showById(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") ClientDTO clientDTO) {
        clientService.update(clientDTO);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteClient(@ModelAttribute("client") ClientDTO clientDTO, @PathVariable("id") int id) {
        clientService.delete(clientDTO, id);
        return "redirect:/clients";
    }
}
