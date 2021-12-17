package com.example.mvcbank.repository;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findAllByFromBankAccountModel(AccountBankModel accountBankModel);

    TransactionModel findTransactionModelById(Long id);
}
