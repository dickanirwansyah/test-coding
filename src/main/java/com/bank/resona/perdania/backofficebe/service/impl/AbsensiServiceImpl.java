package com.bank.resona.perdania.backofficebe.service.impl;

import com.bank.resona.perdania.backofficebe.entity.Absensi;
import com.bank.resona.perdania.backofficebe.model.AbsensiRequest;
import com.bank.resona.perdania.backofficebe.model.AbsensiResponse;
import com.bank.resona.perdania.backofficebe.repository.AbsensiRepository;
import com.bank.resona.perdania.backofficebe.repository.AccountsRepository;
import com.bank.resona.perdania.backofficebe.service.AbsensiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbsensiServiceImpl implements AbsensiService {

    private final AbsensiRepository absensiRepository;

    private final AccountsRepository accountsRepository;

    private final String dateIn = "DATE_IN";
    private final String dateOut = "DATE_OUT";

    @Override
    public AbsensiResponse absensi(AbsensiRequest request) {
        AtomicReference<Absensi> responseAbsensi = new AtomicReference<>(new Absensi());
        accountsRepository.findById(request.getAccountId())
                .ifPresentOrElse(accounts -> {
                    if (request.getType().equals(dateIn)){
                        Absensi dateInAbsensi = new Absensi();
                        dateInAbsensi.setAccountsId(accounts.getId());
                        dateInAbsensi.setDateIn(LocalDateTime.now());
                        responseAbsensi.set(absensiRepository.save(dateInAbsensi));
                    }
                    if (request.getType().equals(dateOut)){
                        absensiRepository.getAbsensiByAccounts(request.getAccountId())
                                .ifPresent(absensi -> {
                                    absensi.setDateOut(LocalDateTime.now());
                                });
                    }
                }, ()-> {
                    log.error("data not found");
                    throw new RuntimeException(String.format("Accounts id %s not found",request.getAccountId()));
                });

        return AbsensiResponse.builder()
                .id(responseAbsensi.get().getId())
                .dateIn(responseAbsensi.get().getDateIn())
                .dateOut(responseAbsensi.get().getDateOut())
                .build();
    }
}
