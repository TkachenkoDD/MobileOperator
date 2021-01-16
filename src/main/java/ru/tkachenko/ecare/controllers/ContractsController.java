package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.service.ContractService;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

    private final ContractService contractService;

    @Autowired
    public ContractsController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/all")
    public String showAllContracts(Model model) {
        model.addAttribute("contracts", contractService.showAll());
        return "contracts/show_all";
    }

    @GetMapping("/{id}")
    public String showContractById(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", contractService.showById(id));
        return "contracts/show_by_id";
    }

    @GetMapping("/new")
    public String newContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        return "contracts/new";
    }

    @PostMapping
    public String createContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        contractService.save(contractDTO);
        return "redirect:/contracts";
    }

    @GetMapping("/{id}/edit")
    public String editContract(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", contractService.showById(id));
        return "contracts/edit";
    }

    @PatchMapping("/{id}")
    public String updateContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        contractService.update(contractDTO);
        return "contracts/show_by_id";
    }

    @DeleteMapping("/{id}")
    public String deleteContract(@ModelAttribute("contract") ContractDTO contractDTO, @PathVariable("id") int id) {
        contractService.delete(contractDTO, id);
        return "redirect:/contracts";
    }

    @GetMapping
    public String searchClientByContract(Model model, @RequestParam(value = "number", required = false) int number){
        model.addAttribute("client", contractService.showClientByNumber(number));
        return "clients/show_by_id";
    }
}
