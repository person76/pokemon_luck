package com.ohgiraffers.run;

import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.repository.PokemonRepository;
import com.ohgiraffers.service.HuntingService;
import com.ohgiraffers.service.PlayerService;
import com.ohgiraffers.service.ShopService;

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

        Player player = new Player();
        System.out.println(player);

        PokemonRepository pokemonRepository = new PokemonRepository();
        PlayerService playerService = new PlayerService(player);
        ShopService shopService = new ShopService();
        HuntingService huntingService = new HuntingService(pokemonRepository);


        while(true){
            System.out.println("\n======= POKEMON LUCK 메인화면 =======");
            System.out.println("1. 내 정보 확인");
            System.out.println("2. 상점");
            System.out.println("3. 사냥");
            System.out.println("9. 프로그램 종료");
            System.out.print("행동 선택 : ");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1: playerService.findPlayerInfo();
                    break;
                case 2: shopService.enterShop(player);
                    break;
                case 3: huntingService.selectHuntingGround(player);
                    break;
                case 9:
                    System.out.println("POKEMON LUCK을 종료합니다."); return;
                default:
                    System.out.println("번호를 잘 못 입력했습니다.");
            }
        }



    }
}
