package com.bank.resona.perdania.backofficebe.service.impl;

import com.bank.resona.perdania.backofficebe.config.JwtHelper;
import com.bank.resona.perdania.backofficebe.config.PasswordConfig;
import com.bank.resona.perdania.backofficebe.entity.Accounts;
import com.bank.resona.perdania.backofficebe.exception.UnAuthorizeException;
import com.bank.resona.perdania.backofficebe.model.LoginAccountsRequest;
import com.bank.resona.perdania.backofficebe.model.LoginAccountsResponse;
import com.bank.resona.perdania.backofficebe.model.RegisterAccounts;
import com.bank.resona.perdania.backofficebe.model.ValidationResponse;
import com.bank.resona.perdania.backofficebe.repository.AccountsRepository;
import com.bank.resona.perdania.backofficebe.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    private final JwtHelper jwtHelper;

    @Override
    public ValidationResponse registerAccounts(RegisterAccounts registerAccounts) {
        Accounts accounts = new Accounts();
        accounts.setName(registerAccounts.getName());
        accounts.setUsername(registerAccounts.getUsername());
        accounts.setPassword(PasswordConfig.generatePassword(registerAccounts.getPassword()));
        accountsRepository.save(accounts);
        return ValidationResponse.builder()
                .valid(true)
                .build();
    }

    @Override
    public LoginAccountsResponse login(LoginAccountsRequest loginAccountsRequest) {
        return accountsRepository.getAccountsByUsername(loginAccountsRequest.getUsername())
                .map(accounts -> {
                    LoginAccountsResponse loginResponse = new LoginAccountsResponse();
                    PasswordConfig.checkPassword(accounts.getPassword(), loginAccountsRequest.getPassword());
                    loginResponse.setToken("Bearer "+jwtHelper.generateToken(accounts));
                    jwtHelper.getAccountsFromToken(loginResponse.getToken().substring(7));
                    return loginResponse;
                })
                .orElseThrow(() -> new UnAuthorizeException("Sorry username or password is wrong !", HttpStatus.UNAUTHORIZED.value()));
    }
}
