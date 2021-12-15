package com.example.mvcbank.controller;

import com.example.mvcbank.model.TransactionModel;
import com.example.mvcbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public String mainTransaction(@PathVariable("id") String id, Map<String, Object> map) {
        map = transactionService.initMainTransaction(map, id);
        return "transaction";
    }

    @PostMapping("/{idAccountBank}")
    public String newTransaction(@PathVariable("idAccountBank") String idAccountBank,
                                 @ModelAttribute("newTransaction") TransactionModel newTransaction) {

        transactionService.addNewTransaction(newTransaction);

        return "redirect:/transaction/{idAccountBank}";
    }

}
