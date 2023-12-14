package com.bank.resona.perdania.backofficebe.service;

import com.bank.resona.perdania.backofficebe.model.LoginAccountsRequest;
import com.bank.resona.perdania.backofficebe.model.LoginAccountsResponse;
import com.bank.resona.perdania.backofficebe.model.RegisterAccounts;
import com.bank.resona.perdania.backofficebe.model.ValidationResponse;

public interface AccountsService {

    ValidationResponse registerAccounts(RegisterAccounts registerAccounts);

    LoginAccountsResponse login(LoginAccountsRequest loginAccountsRequest);

}
