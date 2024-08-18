package com.ohgiraffers.aggregate;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Player implements Serializable {
    private int gold;
    private Map<BallType, Integer> playerBall; //볼 정보, 개수 저장
    private ArrayList<PokemonInfo> playerPokemon;


    public Player() {
        this.gold = 0; // 기본 골드 값
        this.playerBall = new HashMap<>();
        this.playerPokemon = new ArrayList<>();

        // 기본으로 몬스터볼 5개를 보유
        playerBall.put(BallType.MONSTERBALL, 5);
        playerBall.put(BallType.SUPERBALL, 0);
        playerBall.put(BallType.HYPERBALL, 0);
        playerBall.put(BallType.MASTERBALL, 0);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Map<BallType, Integer> getPlayerBall() {
        return playerBall;
    }

    public int getPlayerBallByType(BallType ballType) {
        return playerBall.getOrDefault(ballType, 0);
    }

    public void setPlayerBall(Map<BallType, Integer> playerBall) {
        this.playerBall = playerBall;
    }

    public ArrayList<PokemonInfo> getPlayerPokemon() {
        return playerPokemon;
    }

    public void setPlayerPokemon(ArrayList<PokemonInfo> playerPokemon) {
        this.playerPokemon = playerPokemon;
    }

    public void useBall(BallType ballType) {
        int currentCount = playerBall.getOrDefault(ballType, 0);
        if (currentCount > 0) {
            playerBall.put(ballType, currentCount - 1);
        }
    }

    public void addPokemon(PokemonInfo pokemonInfo) {
        playerPokemon.add(pokemonInfo);
    }

    @Override
    public String toString() {
        return "Player{" +
                "gold=" + gold +
                ", playerBall=" + playerBall +
                ", playerPokemon=" + playerPokemon +
                '}';
    }

}
