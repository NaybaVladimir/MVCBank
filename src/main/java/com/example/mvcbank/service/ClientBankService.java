package com.example.mvcbank.service;

import com.example.mvcbank.dto.ClientDTO;
import com.example.mvcbank.mapper.ClientBankMapper;
import com.example.mvcbank.model.ClientBankModel;
import com.example.mvcbank.repository.ClientBankRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClientBankService {

    private final ClientBankRepo bankClientRepo;

    private final ClientBankMapper mapper;

    public Map<String, Object> mainTemplateInit(Map<String, Object> response) {
        val qwe = bankClientRepo.findAll();

        val werwe = mapper.getClientDTOFromClientBankModel(qwe);

        response.put("allClient", werwe);
        response.put("newClient", new ClientBankModel());
        return response;
    }

    @Transactional
    public void newClient(ClientDTO newClient){

        val clintModel = mapper.createClientBankModelFromClientDto(newClient);

        bankClientRepo.save(clintModel);
    }
}
