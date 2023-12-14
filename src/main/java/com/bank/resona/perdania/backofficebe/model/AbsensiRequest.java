package com.bank.resona.perdania.backofficebe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbsensiRequest {

    private Long accountId;
    private String type;
}
