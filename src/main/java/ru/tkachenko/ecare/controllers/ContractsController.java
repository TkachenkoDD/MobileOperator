package ru.tkachenko.ecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.service.ClientService;
import ru.tkachenko.ecare.service.ContractService;
import ru.tkachenko.ecare.service.OptionService;
import ru.tkachenko.ecare.service.TariffService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @PreAuthorize("hasAuthority('ADMIN')") //TODO check for error
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
        return "redirect:/contracts/" + contractDTO.getId();
    }

    @GetMapping("/{id}/edit")
    public String editContract(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", contractService.showById(id));
        model.addAttribute("tariffs", tariffService.showAll());
        model.addAttribute("options", optionService.showAll());
        return "contracts/edit";
    }

    @GetMapping("/{id}/cart")
    public String contractToCart(Model model, @PathVariable("id") int id) {
        model.addAttribute("contract", contractService.showById(id));
        model.addAttribute("tariffs", tariffService.showAll());
        model.addAttribute("options", optionService.showAll());
        return "cart";
    }

    @PatchMapping("/{id}/cart")
    public String addToCart(@ModelAttribute("contract") ContractDTO contractDTO,
                            @RequestParam("options") List<Integer> optionList,
                            HttpSession session) {
        contractService.showById(contractDTO.getId());
        contractDTO.setTariffDTO(tariffService.showById(contractDTO.getTariffDTO().getId()));
        Set<OptionDTO> optionDTOSet = new HashSet<>();
        for (Integer x : optionList) {
            if (x != null)
                optionDTOSet.add(optionService.showById(x));
        }
        if (!optionDTOSet.isEmpty())
            contractDTO.setOptionDTOSet(optionDTOSet);
        session.setAttribute("contract", contractDTO);
        return "redirect:/contracts/" + contractDTO.getId();
    }

    @GetMapping("/cart")
    public String cart() {
        return "/cart";
    }

    @PatchMapping("/{id}")
    public String updateContract(@ModelAttribute("contract") ContractDTO contractDTO,
                                 HttpSession session) {
        contractService.update((ContractDTO) session.getAttribute("contract"));
        session.removeAttribute("contract");
        return "redirect:/contracts/" + contractDTO.getId();
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

    @PatchMapping("/{id}/block")
    public String block(@RequestParam("click") boolean click,
                        @PathVariable("id") int id) {
        if (click)
        contractService.contractBlock(id);
        return "redirect:/contracts/" + id;
    }
}