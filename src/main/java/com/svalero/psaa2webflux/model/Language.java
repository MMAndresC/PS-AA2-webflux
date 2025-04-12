package com.svalero.psaa2webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    private String name;
    private int id;
    private String languageCode;
}
