package com.example.mvcbank.mapper;

import com.example.mvcbank.dto.ClientDTO;
import com.example.mvcbank.model.ClientBankModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper(imports = {Instant.class})
public interface ClientBankMapper {


    @Mapping(target = "name", source = "name")
    ClientBankModel createClientBankModelFromClientDto(ClientDTO src);

    ClientDTO getClientDTOFromClientBankModel(ClientBankModel src);

    List<ClientDTO> getClientDTOFromClientBankModel(List<ClientBankModel> src);


}
