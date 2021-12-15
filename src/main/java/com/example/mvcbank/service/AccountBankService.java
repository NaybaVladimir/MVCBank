package com.example.mvcbank.service;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.repository.AccountBankRepo;
import com.example.mvcbank.repository.ClientBankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AccountBankService {
    @Autowired
    private AccountBankRepo accountBankRepo;
    @Autowired
    private ClientBankRepo clientBankRepo;

    public Map<String, Object> mainAccountBank(String id, Map<String, Object> map) {
        map.put("allAccountBank", accountBankRepo.findAllByClientBankModel(clientBankRepo.findClientBankModelById(Long.parseLong(id))));
        map.put("newAccountBank",new AccountBankModel());
        map.put("id",id);
        return map;
    }
    @Transactional
    public void addNewAccountBank(AccountBankModel newAccountBank){
        accountBankRepo.save(newAccountBank);
    }

}
