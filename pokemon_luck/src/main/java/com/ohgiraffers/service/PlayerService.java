package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.BallType;
import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.aggregate.PokemonInfo;
import com.ohgiraffers.repository.PlayerRepository;

import java.util.Map;

public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void findPlayerInfo(Player player) {

        System.out.println("\n======== Player 정보 =========");
        // 플레이어의 골드 출력
        System.out.println("현재 골드: " + player.getGold());

        // 플레이어가 소유한 볼의 종류 및 개수를 출력
        System.out.println("보유한 포켓몬 볼 종류 및 개수:");
        Map<BallType, Integer> playerBall = player.getPlayerBall();
        for (Map.Entry<BallType, Integer> entry : playerBall.entrySet()) {
            BallType ballType = entry.getKey();
            Integer count = entry.getValue();
            System.out.println(ballType + ": " + count + "개");
        }

        System.out.println("포획한 포켓몬 기록: ");
        if (player.getPlayerPokemon().isEmpty()) {
            System.out.println("포획한 포켓몬이 없습니다.");
        } else {
            for (PokemonInfo pokemon : player.getPlayerPokemon()) {
                System.out.println("포켓몬 이름: " + pokemon.getName() + ", 등급: " + pokemon.getGrade());
            }
        }
    }
}
