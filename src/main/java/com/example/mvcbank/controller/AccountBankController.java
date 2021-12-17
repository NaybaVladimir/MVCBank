package com.example.mvcbank.controller;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.service.AccountBankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/bankAccount")
@RequiredArgsConstructor
public class AccountBankController {

    private final AccountBankService accountBankService;

    /**
     * Принимает ID конкретного клиента, отдает на представление страницу счетов
     *
     * @param id  - уникальный идентификатор клиента
     * @param map - по ключу отдает список клиентов и пустой объект для формы
     * @return - возвращает представление
     */
    @GetMapping("/{id}")
    public String transferAllBankAccount(@PathVariable("id") String id, Map<String, Object> map) {
        accountBankService.getMainPageAccountBank(id, map);
        log.info("Отдал accountBank.jsp");
        return "accountBank";
    }


    /**
     * Принимает Новый банковский счет, инициализирует и производит редирект на страницу счетов клиента
     *
     * @param id             - УИН клиента банка(Владельца счета)
     * @param newAccountBank - проинициализированный объект счета
     * @return
     */
    @PostMapping("/{id}")
    public String initNewAccountBank(@PathVariable("id") String id,
                                     @ModelAttribute("newAccountBank") AccountBankModel newAccountBank) {
        accountBankService.addNewAccountBank(newAccountBank);
        log.info("Редирект accountBank.jsp");
        return "redirect:/bankAccount/{id}";
    }

}
