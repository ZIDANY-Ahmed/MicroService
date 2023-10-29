package com.example.microservice.web;

import com.example.microservice.dto.BankAccountRequestDTO;
import com.example.microservice.dto.BankAccountResponseDTO;
import com.example.microservice.entities.BankAccount;
import com.example.microservice.entities.Customer;
import com.example.microservice.repositories.BankAccountRepository;
import com.example.microservice.repositories.CustomerRepository;
import com.example.microservice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    public BankAccountGraphQLController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));

    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }


    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
       bankAccountRepository.deleteById(id);
       return true;
    }
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
}
/*
record BankAccountDTO(Double balance, String type,String currency){
}*/