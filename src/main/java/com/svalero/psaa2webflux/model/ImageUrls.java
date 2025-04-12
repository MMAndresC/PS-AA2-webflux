package com.svalero.psaa2webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrls {
    private String small;
    private String medium;
    private String large;
}
