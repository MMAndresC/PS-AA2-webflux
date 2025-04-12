package com.svalero.psaa2webflux.service;

import com.svalero.psaa2webflux.constants.Constants;
import com.svalero.psaa2webflux.env.ApiKey;
import com.svalero.psaa2webflux.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RankingService {

    private final WebClient webClient;

    public RankingService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(Constants.BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, ApiKey.TOKEN)
                .build();
    }

    public Mono<ClanRanking> getTopClanByLocation(int locationId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/locations/{locationId}/rankings/clans")
                        .queryParam("limit", 1)
                        .build(locationId)
                )
                .retrieve()
                .bodyToMono(ApiResponseClanRanking.class)
                .map(response -> {
                    if(response.getItems() != null && !response.getItems().isEmpty()) {
                        return response.getItems().getFirst(); //The top clan
                    }
                    return new ClanRanking();
                });
    }

    public Flux<Member> getMembersTopClan(String tag){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/clans/{tag}/members")
                        .build(tag)
                )
                .retrieve()
                .bodyToMono(ApiResponseMembers.class)
                .flatMapMany(response -> {
                    List<Member> members = response.getItems();
                    return members != null ? Flux.fromIterable(members) : Flux.empty();
                });
    }

    public Mono<TopClanMembers> getInfoClanAndMembers(int locationId) {
        return getTopClanByLocation(locationId)
                .flatMap(topClan -> {
                    String tag = topClan.getTag();
                    // Url encode # to %23, api requirement
                    tag = tag.replaceAll("#", "%23");
                    return getMembersTopClan(tag)
                            .collectList() // convert Flux<Member> to Mono<List<Member>>
                            .map(members -> {
                                String badgeUrl = "";
                                if(topClan.getBadgeUrls() != null && topClan.getBadgeUrls().getSmall() != null)
                                    badgeUrl = topClan.getBadgeUrls().getSmall();
                                return new TopClanMembers(
                                        topClan.getName(),
                                        topClan.getLocation() != null ? topClan.getLocation().getName() : "",
                                        badgeUrl,
                                        topClan.getRank(),
                                        topClan.getPreviousRank(),
                                        members
                                );
                            });
                });
    }
}
