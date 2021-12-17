package com.example.mvcbank.controller;

import com.example.mvcbank.model.TransactionModel;
import com.example.mvcbank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * выводит на представление все счета клиента
     * @param id ID клиента для URL
     * @param map данные для представления
     * @param dateFrom - фильтр по дате
     * @param dateTo - фильтр по дате
     * @return - представление
     */
    @GetMapping("/{id}")
    public String transferAllTransaction(@PathVariable("id") String id,
                                         Map<String, Object> map,
                                         @RequestParam(value = "from", required = false, defaultValue = "notDate") String dateFrom,
                                         @RequestParam(value = "to", required = false, defaultValue = "notDate") String dateTo) {
        transactionService.initMainTransaction(map, id, dateFrom, dateTo);
        return "transaction";
    }

    /**
     * Принимает новый объект счета и производит редирект
     * @param idAccountBank - ID счета по которому произвелась транзакция для URL
     * @param newTransaction - Новая транзакция
     * @return -редиректо
     */
    @PostMapping("/{id}")
    public String initNewTransaction(@PathVariable("id") String idAccountBank,
                                     @ModelAttribute("newTransaction") TransactionModel newTransaction) {
        transactionService.addNewTransaction(newTransaction);
        return "redirect:/transaction/{id}";
    }

}


