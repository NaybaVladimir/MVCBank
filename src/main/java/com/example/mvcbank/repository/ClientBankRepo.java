package com.example.mvcbank.repository;

import com.example.mvcbank.model.ClientBankModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientBankRepo extends JpaRepository<ClientBankModel,Long> {
    ClientBankModel findClientBankModelById(Long id);



}
