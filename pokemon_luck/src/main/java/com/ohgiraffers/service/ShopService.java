package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.aggregate.BallType;

import java.util.Map;
import java.util.Scanner;

public class ShopService {

    public void enterShop(Player player) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= 상점 =========");
            System.out.println("현재 골드: " + player.getGold());
            System.out.println("구매 가능한 포켓몬 볼:");
            for (BallType ballType : BallType.values()) {
                System.out.println(ballType.getName() + " - 가격: " + ballType.getPrice() + " 골드");
            }
            System.out.println("\n구매할 포켓몬 볼의 이름을 입력하세요 (종료하려면 'exit' 입력): ");
            String ballName = scanner.next();

            if (ballName.equalsIgnoreCase("exit")) {
                break;
            }

            BallType selectedBall = BallType.getByName(ballName);
            if (selectedBall == null) {
                System.out.println("잘못된 볼 이름입니다. 다시 입력하세요.");
                continue;
            }

            System.out.print("구매할 개수를 입력하세요: ");
            int quantity = scanner.nextInt();
            int totalCost = selectedBall.getPrice() * quantity;

            if (player.getGold() < totalCost) {
                System.out.println("골드가 부족합니다. 구매할 수 없습니다.");
            } else {
                player.setGold(player.getGold() - totalCost);
                Map<BallType, Integer> playerBall = player.getPlayerBall();
                playerBall.put(selectedBall, playerBall.getOrDefault(selectedBall, 0) + quantity);
                System.out.println(quantity + "개의 " + selectedBall.getName() + "을(를) 구매했습니다.");
            }
        }
    }
}
