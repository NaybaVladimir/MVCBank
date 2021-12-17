package com.example.mvcbank.service;

import com.example.mvcbank.model.ClientBankModel;
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
public class ClientBankService {

    private final ClientBankRepo bankClientRepo;

    /**
     * Иницилизирует ответ для представления
     * @param map данные для представления
     */
    public void mainTemplateInit(Map<String, Object> map) {
        val allClient = bankClientRepo.findAll();

        map.put("allClient", allClient);
        map.put("newClient", new ClientBankModel());

    }

    /**
     * Сохраняет нового клиента в БД
     * @param newClient
     */
    @Transactional
    public void newClient(ClientBankModel newClient) {
        bankClientRepo.save(newClient);

    }
}
