package com.ohgiraffers.run;

import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.repository.PlayerRepository;
import com.ohgiraffers.repository.PokemonRepository;
import com.ohgiraffers.service.GameTerminationService;
import com.ohgiraffers.service.HuntingService;
import com.ohgiraffers.service.PlayerService;
import com.ohgiraffers.service.ShopService;

import java.io.File;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String art =  " ________  ________  ___  __    _______   _____ ______   ________  ________           ___       ___  ___  ________  ___  __       \n" +
                "|\\   __  \\|\\   __  \\|\\  \\|\\  \\ |\\  ___ \\ |\\   _ \\  _   \\|\\   __  \\|\\   ___  \\        |\\  \\     |\\  \\|\\  \\|\\   ____\\|\\  \\|\\  \\     \n" +
                "\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\/  /|\\ \\   __/|\\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\       \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\___|\\ \\  \\/  /|_   \n" +
                " \\ \\   ____\\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/_\\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\       \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\    \\ \\   ___  \\  \n" +
                "  \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\       \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\____\\ \\  \\\\ \\  \\ \n" +
                "   \\ \\__\\    \\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ \\__\\       \\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\\n" +
                "    \\|__|     \\|_______|\\|__| \\|__|\\|_______|\\|__|     \\|__|\\|_______|\\|__| \\|__|        \\|_______|\\|_______|\\|_______|\\|__| \\|__|\n" +
                "                                                                                                                                  \n" +
                "                                                                                                                                  \n" +
                "                                                                                                                                  ";
        System.out.println(art);

        // PlayerRepository에서 Player 객체를 불러옴
        PlayerRepository playerRepository = new PlayerRepository();
        Player player = playerRepository.getPlayer();

        PokemonRepository pokemonRepository = new PokemonRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        ShopService shopService = new ShopService();
        HuntingService huntingService = new HuntingService(pokemonRepository);
        GameTerminationService gameTerminationService = new GameTerminationService();

        while(true) {
            if (gameTerminationService.checkGameOverCondition(player, shopService)) {
                gameTerminationService.terminateGame(player);
            }

            System.out.println("\n======= POKEMON LUCK 메인화면 =======");
            System.out.println("1. 내 정보 확인");
            System.out.println("2. 상점");
            System.out.println("3. 사냥");
            System.out.println("9. 프로그램 종료");
            System.out.print("행동 선택 : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playerService.findPlayerInfo(player);
                    break;
                case 2:
                    shopService.enterShop(player);
                    break;
                case 3:
                    huntingService.selectHuntingGround(player);
                    break;
                case 9:
                    System.out.println("POKEMON LUCK을 종료합니다.");
                    playerRepository.savePlayer(player);
                    return;
                default:
                    System.out.println("번호를 잘못 입력했습니다.");
            }
        }
    }

    // 게임 종료 조건을 확인하는 메서드
    private static boolean checkGameOverCondition(Player player, ShopService shopService) {
        // 모든 볼의 개수가 0인지 확인
        boolean allBallsZero = player.getPlayerBall().values().stream().allMatch(count -> count == 0);

        // 가장 저렴한 볼을 구매할 수 없는지 확인
        int cheapestBallPrice = shopService.getCheapestBallPrice();
        boolean canAffordBall = player.getGold() >= cheapestBallPrice;

        return allBallsZero && !canAffordBall;
    }
}
