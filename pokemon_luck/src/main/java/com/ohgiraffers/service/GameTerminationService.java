package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.BallType;
import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.repository.PlayerRepository;

import java.io.File;
import java.util.Map;

public class GameTerminationService {

    public boolean checkGameOverCondition(Player player, ShopService shopService) {
        // 모든 볼의 개수가 0인지 확인
        boolean allBallsZero = player.getPlayerBall().values().stream().allMatch(count -> count == 0);

        // 가장 저렴한 볼을 구매할 수 없는지 확인
        int cheapestBallPrice = shopService.getCheapestBallPrice();
        boolean canAffordBall = player.getGold() >= cheapestBallPrice;

        return allBallsZero && !canAffordBall;
    }

    public void terminateGame(Player player) {
        int capturedPokemonCount = player.getPlayerPokemon().size();

        // 초기 볼 개수를 가져옴 (PlayerRepository에서 가져옴)
        Map<BallType, Integer> initialBallCounts = PlayerRepository.getInitialBallCounts();

        // 현재 플레이어의 볼 개수
        Map<BallType, Integer> currentBallCounts = player.getPlayerBall();

        // 각 볼의 사용 개수를 계산하고 출력
        for (Map.Entry<BallType, Integer> entry : initialBallCounts.entrySet()) {
            BallType ballType = entry.getKey();
            int initialCount = entry.getValue();
            int currentCount = currentBallCounts.getOrDefault(ballType, 0);  // 남아있는 볼 개수
            int usedCount = initialCount - currentCount;  // 사용된 볼 개수 계산

            System.out.println(ballType + " 사용한 개수: " + usedCount + "개");
        }

        // 포획한 포켓몬 수 출력
        System.out.println("포켓몬을 잡은 횟수: " + capturedPokemonCount);

        // PlayerDB.dat 파일 삭제
        File playerDB = new File("src/main/java/com/ohgiraffers/db/PlayerDB.dat");
        if (playerDB.exists()) {
            if (playerDB.delete()) {
                System.out.println("PlayerDB.dat 파일을 성공적으로 삭제했습니다.");
            } else {
                System.out.println("PlayerDB.dat 파일 삭제에 실패했습니다.");
            }
        }

        // 프로그램 종료
        System.out.println("POKEMON LUCK을 종료합니다.");
        System.exit(0);
    }
}
