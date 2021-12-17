package com.example.mvcbank.service;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.repository.AccountBankRepo;
import com.example.mvcbank.repository.ClientBankRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBankService {

    private final AccountBankRepo accountBankRepo;
    private final ClientBankRepo clientBankRepo;

    /**
     * Отдает список счетов по ID клиента, пустой объект счета для инициализации в форме, и id клиента
     *
     * @param id  Идентификатор клиента
     * @param map Ответ для фронта
     */
    public void getMainPageAccountBank(String id, Map<String, Object> map) {
        val listAccountBank = accountBankRepo.findByClientBankModel(
                clientBankRepo.findClientBankModelById(Long.parseLong(id)));
        map.put("allAccountBank", listAccountBank);
        map.put("newAccountBank", new AccountBankModel());
        map.put("id", id);
    }

    /**
     * Сохраняет новый счет в БД
     * @param newAccountBank новый счета
     */
    @Transactional
    public void addNewAccountBank(AccountBankModel newAccountBank) {
        accountBankRepo.save(newAccountBank);
        log.info("Счет для аккаунта: {} добавлен.", newAccountBank.getClientBankModel().getId());
    }

}
