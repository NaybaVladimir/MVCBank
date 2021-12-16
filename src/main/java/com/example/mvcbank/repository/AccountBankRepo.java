package com.example.mvcbank.repository;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.model.ClientBankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountBankRepo extends JpaRepository<AccountBankModel,Long> {

    List<AccountBankModel> findByClientBankModel(ClientBankModel clientBankModel);

    AccountBankModel findAccountBankModelById(Long id);
}
