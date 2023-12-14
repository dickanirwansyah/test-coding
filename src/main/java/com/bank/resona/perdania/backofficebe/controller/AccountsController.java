package com.bank.resona.perdania.backofficebe.controller;

import com.bank.resona.perdania.backofficebe.model.*;
import com.bank.resona.perdania.backofficebe.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @PostMapping(value = "/register")
    public ResponseEntity<ValidationResponse> register(@RequestBody RegisterAccounts registerAccounts){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsService.registerAccounts(registerAccounts));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginAccountsResponse> login(@RequestBody LoginAccountsRequest loginAccountsRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsService.login(loginAccountsRequest));
    }
}

