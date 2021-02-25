package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Contract;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitrii Tkachenko
 */
public interface ContractService {
    /**
     * Find list of contracts from data base
     */
    List<ContractDTO> showAll();

    /**
     * Find contract by Id from data base
     *
     * @param id - contract's Id
     */
    ContractDTO showById(int id);

    /**
     * save new contract in data base
     */
    void save(ContractDTO contractDTO);

    /**
     * update contract
     */
    void update(ContractDTO contractDTO);

    /**
     * Delete contract from data base
     *
     * @param contractDTO - object for deleting
     * @param id          - contract's Id
     */
    void delete(ContractDTO contractDTO, int id);

    /**
     * Find client by phone number
     *
     * @param number - phone number
     * @return - client
     */
    ClientDTO showClientByNumber(String number);

    /**
     * Set block level to contract
     * 0 - unblocked
     * 1 - blocked by user
     * 2 - blocked by admin
     *
     * @param id - block level
     */
    void contractBlock(int id);

    /**
     * Method for converting DTO to entity
     *
     * @param contractDTO - DTO
     */
    Contract toEntity(ContractDTO contractDTO);

    /**
     * Confirm all content of cart and save changes to DB
     *
     * @param contractDTO - contract
     * @param session     - session identification
     */
    void confirmCartContract(ContractDTO contractDTO, HttpSession session);

    /**
     * Delete option from cart by option's Id
     *
     * @param optionId option Id
     * @param session  - session identification
     */
    void deleteOptionFromCart(int optionId, HttpSession session);

    /**
     * Add option to cart and form least available options
     *
     * @param contractId - contract id
     * @param optionId   - option id
     * @param session    - session identification
     */
    void addOptionToCart(int contractId, int optionId, HttpSession session);

    /**
     * Show list of available options
     *
     * @param contractDTO - contract
     * @param session     - - session identification
     * @return - list of options
     */
    Set<OptionDTO> showAvailableOptions(ContractDTO contractDTO, HttpSession session);

    /**
     * Change tariff and set changes to DB
     *
     * @param contractId - contract's id
     * @param tariffId   - tariffs id
     */
    void changeTariffOnContract(int contractId, int tariffId);

    /**
     * Delete options from contract
     *
     * @param contractId - contract's id
     * @param optionId   - option's id
     */
    void deleteOptionFromContract(int contractId, int optionId);
}
