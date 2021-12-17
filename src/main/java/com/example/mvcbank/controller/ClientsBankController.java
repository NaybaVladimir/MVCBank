package com.example.mvcbank.controller;

import com.example.mvcbank.model.ClientBankModel;
import com.example.mvcbank.service.ClientBankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Класс-контроллер - отвечает за взаимодействие БЭКа с ФРОНТОМ по части Клиентов банка
 */
@Slf4j
@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ClientsBankController {


    private final ClientBankService clientBankService;

    /**
     * Отдает на представление первую страницу и инициализирует ответ для нее
     * @param map даннеы для  представления
     * @return
     */
    @GetMapping()
    public String getAllClientBank(Map<String, Object> map) {
        clientBankService.mainTemplateInit(map);
        return "clientBankMain";
    }

    /**
     * Принимает объект нового клиента и производит редирект на главную страницу
     * @param newClient - принятый объект с новым клиентом
     * @return
     */
    @PostMapping("/")
    public String initNewClient(@ModelAttribute("newClient") ClientBankModel newClient) {
        clientBankService.newClient(newClient);
        return "redirect:";
    }
}
