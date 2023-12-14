package com.bank.resona.perdania.backofficebe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbsensiResponse {

    private Long id;
    private String username;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
}
