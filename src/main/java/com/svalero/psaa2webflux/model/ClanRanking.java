package com.svalero.psaa2webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClanRanking {

    private String tag;
    private String name;
    private Location location;
    private ImageUrls badgeUrls;
    private int clanLevel;
    private int members;
    private int clanPoints;
    private int rank;
    private int previousRank;
}
