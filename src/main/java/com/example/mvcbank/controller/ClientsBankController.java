package com.example.mvcbank.controller;

import com.example.mvcbank.model.ClientBankModel;
import com.example.mvcbank.service.ClientBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping()
public class ClientsBankController {
    @Autowired
    private ClientBankService clientBankService;

    @GetMapping()
    public String mainClientBank(Map<String, Object> response) {
        response = clientBankService.mainTemplateInit(response);
        return "clientBankMain";
    }

    @PostMapping("/")
    public String newClient(@ModelAttribute("newClient") ClientBankModel newClient) {
        clientBankService.newClient(newClient);
        return "redirect:";
    }
}
