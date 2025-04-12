package com.svalero.psaa2webflux.controller;

import com.svalero.psaa2webflux.model.TopClanMembers;
import com.svalero.psaa2webflux.service.RankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/top-ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/{locationId}/members")
    public Mono<TopClanMembers> getInfoClanAndMembers(@PathVariable int locationId) {
        return rankingService.getInfoClanAndMembers(locationId);
    }
}
