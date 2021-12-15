package com.example.mvcbank.service;

import com.example.mvcbank.model.TransactionModel;
import com.example.mvcbank.repository.AccountBankRepo;
import com.example.mvcbank.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private AccountBankRepo accountBankRepo;


    public Map<String, Object> initMainTransaction(Map<String, Object> map, String id) {
        map.put("allTransaction", transactionRepo.findAllByFromBankAccountModel(accountBankRepo.findAccountBankModelById(Long.parseLong(id))));
        map.put("idAccountBank", id);
        map.put("newTransaction", new TransactionModel());
        return map;
    }

    @Transactional
    public void addNewTransaction(TransactionModel transaction) {
        transactionRepo.save(transaction);
    }
}
