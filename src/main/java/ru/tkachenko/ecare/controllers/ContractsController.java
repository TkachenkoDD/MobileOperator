package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.service.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

    private final ContractService contractService;
    private final ClientService clientService;
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public ContractsController(ContractService contractService, ClientService clientService,
                               TariffService tariffService, OptionService optionService) {
        this.contractService = contractService;
        this.clientService = clientService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newContract(@ModelAttribute("contract") ContractDTO contractDTO,
                              @PathVariable("id") int id) {
        contractDTO.setClientDTO(clientService.showById(id));
        return "contracts/new";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createContract(@ModelAttribute("contract") ContractDTO contractDTO) {
        contractService.save(contractDTO);
        return "redirect:/contracts/all";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteContract(@ModelAttribute("contract") ContractDTO contractDTO, @PathVariable("id") int id) {
        contractService.delete(contractDTO, id);
        return "redirect:/contracts/all";
    }

    @GetMapping("/{id}/cart")
    public String contractToCart(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", contractService.showById(id));
        model.addAttribute("tariffs", tariffService.showAll());
        model.addAttribute("options", optionService.showAll());
        return "cart";
    }

    @GetMapping("/{id}/available_tariffs")
    public String showAvailableTariffs(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", contractService.showById(id));
        model.addAttribute("tariffs", tariffService.showAll());
        return "contracts/available_tariffs";
    }

    @PatchMapping("/{id}/change_tariff")
    public String changeTariffOnContract(@PathVariable("id") int id,
                                  @RequestParam("tariff") int tariffId) {
        ContractDTO contractDTO = contractService.showById(id);
        contractDTO.setTariffDTO(tariffService.showById(tariffId));
        contractDTO.getOptionDTOSet().clear();
        contractService.update(contractDTO);
        return "redirect:/contracts/" + id;
    }

    @GetMapping("/{id}/available_options")
    public String showAvailableOptions(@PathVariable("id") int id, Model model, HttpSession session) {
        ContractDTO contractDTO = contractService.showById(id);
        model.addAttribute("contract", contractDTO);
        model.addAttribute("options", contractService.showAvailableOptions(contractDTO, session));
        return "contracts/available_options";
    }

    @PatchMapping("/{id}/options_to_cart")
    public String addOptionToCart(@PathVariable("id") int id,
                                  @RequestParam("option") int optionId,
                                  HttpSession session) {
        contractService.addOptionToCart(id, optionId, session);
        return "redirect:/contracts/" + id + "/available_options";
    }

    @DeleteMapping("/delete_from_cart")
    public String deleteOptionFromCart (@RequestParam("option") int optionId,
                                        HttpSession session) {
        contractService.deleteOptionFromCart(optionId, session);
        return "redirect:/contracts/cart";
    }

    @DeleteMapping("/{id}/delete_option")
    public String deleteOptionFromContract(@PathVariable("id") int id,
                                           @RequestParam("option") int optionId) {
        ContractDTO contractDTO = contractService.showById(id);
        contractDTO.getOptionDTOSet().remove(optionService.showById(optionId));
        contractService.update(contractDTO);
        return "redirect:/contracts/" + id + "/available_options";
    }

    @GetMapping("/cart")
    public String cart() {
        return "/cart";
    }

    @PatchMapping("/confirm/{id}")
    public String confirmCartContract(@ModelAttribute("contract") ContractDTO contractDTO,
                                 HttpSession session) {
        contractService.confirmCartContract(contractDTO, session);
        session.removeAttribute("contract");
        return "redirect:/contracts/" + contractDTO.getId();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String searchClientByContract(Model model, @RequestParam(value = "number", required = false) int number) {
        model.addAttribute("client", contractService.showClientByNumber(number));
        return "clients/show_by_id";
    }

    @PatchMapping("/{id}/block")
    public String block(@RequestParam("click") boolean click,
                        @PathVariable("id") int id) {
        if (click)
            contractService.contractBlock(id);
        return "redirect:/contracts/" + id;
    }
}