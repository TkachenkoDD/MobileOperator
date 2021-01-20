package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.service.ClientService;
import ru.tkachenko.ecare.service.ContractService;
import ru.tkachenko.ecare.service.TariffService;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

    private final ContractService contractService;
    private final ClientService clientService;
    private final TariffService tariffService;

    @Autowired
    public ContractsController(ContractService contractService, ClientService clientService, TariffService tariffService) {
        this.contractService = contractService;
        this.clientService = clientService;
        this.tariffService = tariffService;
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

    @GetMapping("/{id}/new")
    public String newContract(@ModelAttribute("contract") ContractDTO contractDTO,
                              @PathVariable("id") int id) {
        contractDTO.setClientDTO(clientService.showById(id));
        return "contracts/new";
    }

    @PostMapping
    public String createContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        contractService.save(contractDTO);
        return "redirect:/contracts/all";
    }

    @GetMapping("/{id}/edit")
    public String editContract(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", contractService.showById(id));
        model.addAttribute("tariffs", tariffService.showAll());
        return "contracts/edit";
    }

    @PatchMapping("/{id}")
    public String updateContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        contractService.update(contractDTO);
        return "redirect:/contracts/all";
    }

    @DeleteMapping("/{id}")
    public String deleteContract(@ModelAttribute("contract") ContractDTO contractDTO, @PathVariable("id") int id) {
        contractService.delete(contractDTO, id);
        return "redirect:/contracts/all";
    }

    @GetMapping
    public String searchClientByContract(Model model, @RequestParam(value = "number", required = false) int number) {
        model.addAttribute("client", contractService.showClientByNumber(number));
        return "clients/show_by_id";
    }
}