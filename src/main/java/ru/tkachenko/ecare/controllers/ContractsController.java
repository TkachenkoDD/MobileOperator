package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.models.Contract;
import ru.tkachenko.ecare.service.GenericService;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

    private GenericService<Contract> genericService;

    @Autowired
    public ContractsController(GenericService<Contract> genericService) {
        this.genericService = genericService;
    }

    @GetMapping()
    public String showAllContracts(Model model) {
        model.addAttribute("contracts", genericService.showAll());
        return "contracts/show_all";
    }

    @GetMapping("/{id}")
    public String showContractById(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", genericService.showById(id));
        return "contracts/show_by_id";
    }

    @GetMapping("/new")
    public String newContract(@ModelAttribute("contract") Contract contract) {
        return "contracts/new";
    }

    @PostMapping
    public String createContract(@ModelAttribute("contract") Contract contract) {
        genericService.save(contract);
        return "redirect:/contracts";
    }

    @GetMapping("/{id}/edit")
    public String editContract(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", genericService.showById(id));
        return "contracts/edit";
    }

    @PatchMapping("/{id}")
    public String updateContract(@ModelAttribute("contract") Contract contract) {
        genericService.update(contract);
        return "redirect:/contracts";
    }

    @DeleteMapping("/{id}")
    public String deleteContract(@ModelAttribute("contract") Contract contract) {
        genericService.delete(contract);
        return "redirect:/contracts";
    }
}
