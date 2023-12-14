package com.bank.resona.perdania.backofficebe.controller;

import com.bank.resona.perdania.backofficebe.model.AbsensiRequest;
import com.bank.resona.perdania.backofficebe.model.AbsensiResponse;
import com.bank.resona.perdania.backofficebe.service.AbsensiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AbsensiController {

    private final AbsensiService absensiService;

    @PostMapping(value = "/absensi")
    public ResponseEntity<AbsensiResponse> absensi(@RequestBody AbsensiRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(absensiService.absensi(request));
    }
}

