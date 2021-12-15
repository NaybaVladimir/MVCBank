package com.example.mvcbank.controller;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.service.AccountBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/bankAccount")
public class AccountBankController {
    @Autowired
    private AccountBankService accountBankService;

    @GetMapping("/{id}")
    public String getBankAccount(@PathVariable("id") String id, Map<String, Object> map) {
        accountBankService.mainAccountBank(id, map);
        return "accountBank";
    }

    @PostMapping("/{id}")
    public String newAccountBank(@PathVariable("id") String id,
                                 @ModelAttribute("newAccountBank") AccountBankModel newAccountBank) {
        accountBankService.addNewAccountBank(newAccountBank);
        return "redirect:/bankAccount/{id}";
    }

}
