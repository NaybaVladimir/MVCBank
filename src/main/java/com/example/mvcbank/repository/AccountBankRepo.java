package com.example.mvcbank.repository;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.model.ClientBankModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountBankRepo extends JpaRepository<AccountBankModel,Long> {
    List<AccountBankModel> findAllByClientBankModel(ClientBankModel clientBankModel);
    AccountBankModel findAccountBankModelById(Long id);
}
