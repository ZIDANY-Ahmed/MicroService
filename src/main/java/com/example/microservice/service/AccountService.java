package com.example.microservice.service;

import com.example.microservice.dto.BankAccountRequestDTO;
import com.example.microservice.dto.BankAccountResponseDTO;
import com.example.microservice.entities.BankAccount;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
