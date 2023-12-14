package com.bank.resona.perdania.backofficebe.service;

import com.bank.resona.perdania.backofficebe.model.AbsensiRequest;
import com.bank.resona.perdania.backofficebe.model.AbsensiResponse;

public interface AbsensiService {
    AbsensiResponse absensi(AbsensiRequest request);
}
