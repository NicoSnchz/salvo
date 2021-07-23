package com.codeoftheweb.salvo.dtos;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Ship;
import com.codeoftheweb.salvo.utilities.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameViewDTO {
    private Long id;
    private LocalDateTime created;
    private String gameState;
    private Set<GamePlayerDTO> gamePlayers;
    private Set<ShipDTO> ships;
    private Set<SalvoesDTO> salvoes;
    private HitsDTO hits;

    public GameViewDTO(GamePlayer gamePlayer) {

        this.id = gamePlayer.getGame().getGameId();
        this.created = gamePlayer.getGame().getGameStartDate();
        this.gameState = Util.getGameState(gamePlayer);
        this.gamePlayers = gamePlayer.getGame().getGamePlayers()
                                                            .stream()
                                                            .map(GamePlayerDTO::new)
                                                            .collect(Collectors.toSet());
        this.ships = gamePlayer.getShips()
                                .stream()
                                .map(ShipDTO::new)
                                .collect(Collectors.toSet());

        if (gamePlayer.getOpponent(gamePlayer).isEmpty()){
            this.salvoes = new HashSet<>();
        }else{
            this.salvoes = gamePlayer.getGame().getGamePlayers()
                    .stream()
                    .flatMap(x -> x.getSalvoes().stream().map(SalvoesDTO::new))
                    .collect(Collectors.toSet());

        }
        this.hits = new HitsDTO(gamePlayer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public Set<GamePlayerDTO> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayerDTO> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Set<ShipDTO> getShips() {
        return ships;
    }

    public void setShips(Set<ShipDTO> ships) {
        this.ships = ships;
    }

    public Set<SalvoesDTO> getSalvoes() {
        return salvoes;
    }

    public void setSalvoes(Set<SalvoesDTO> salvoes) {
        this.salvoes = salvoes;
    }

    public HitsDTO getHits() {
        return hits;
    }

    public void setHits(HitsDTO hits) {
        this.hits = hits;
    }
}