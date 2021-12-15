package com.example.mvcbank.service;

import com.example.mvcbank.model.ClientBankModel;
import com.example.mvcbank.repository.ClientBankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ClientBankService {
    @Autowired
    private ClientBankRepo bankClientRepo;
    public Map<String, Object> mainTemplateInit(Map<String, Object> response) {
        response.put("allClient", bankClientRepo.findAll());
        response.put("newClient", new ClientBankModel());
        return response;
    }
    @Transactional
    public void newClient(ClientBankModel newClient){
        bankClientRepo.save(newClient);
    }
}
