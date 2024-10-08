package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.BallType;
import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.aggregate.PokemonInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {

    private ArrayList<Player> playerList = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/com/ohgiraffers/db/PlayerDB.dat";

    // 모든 볼의 초기 개수를 설정하는 맵 (외부에서 접근 가능하도록 설정)
    private static final Map<BallType, Integer> initialBallCounts = new HashMap<>();

    // static 초기화 블록을 사용해 초기 볼 개수 설정
    static {
        initialBallCounts.put(BallType.MONSTERBALL, 5);  // 몬스터볼 5개
    }

    public PlayerRepository() {
        File file = new File(FILE_PATH);

        // 파일이 존재할 경우 데이터를 로드
        if (file.exists()) {
            loadPlayerInfo(file);
        } else {
            // 파일이 없을 경우 새로운 데이터를 생성하고 저장
            ArrayList<Player> playerInfos = new ArrayList<>();

            // 초기 볼의 개수를 설정
            Map<BallType, Integer> initialBalls = new HashMap<>(initialBallCounts);

            // 포켓몬 리스트를 빈 리스트로 초기화
            ArrayList<PokemonInfo> initialPokemonList = new ArrayList<>();

            // 골드는 0으로 초기화
            int initialGold = 0;

            // Player 객체 생성
            Player newPlayer = new Player(initialGold, initialBalls, initialPokemonList);
            playerInfos.add(newPlayer);

            // 파일에 Player 정보 저장
            savePlayerInfo(file, playerInfos);

            // playerList에 추가
            playerList.add(newPlayer);
        }
    }

    // 초기 볼 개수를 반환하는 메서드 (외부에서 사용할 수 있도록 제공)
    public static Map<BallType, Integer> getInitialBallCounts() {
        return new HashMap<>(initialBallCounts); // 원본 보호를 위해 복사본 반환
    }

    private void loadPlayerInfo(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            playerList = (ArrayList<Player>) ois.readObject(); // 파일에서 전체 리스트를 불러옴
            System.out.println("플레이어 정보를 성공적으로 로드했습니다.");
        } catch (EOFException e) {
            System.out.println("플레이어 정보를 전부 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePlayerInfo(File file, ArrayList<Player> playerInfos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(playerInfos);  // 전체 플레이어 리스트를 파일에 저장
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayer() {
        if (playerList.isEmpty()) {
            // playerList가 비어 있으면 새로운 플레이어 생성
            Map<BallType, Integer> initialBalls = new HashMap<>(initialBallCounts);

            ArrayList<PokemonInfo> initialPokemonList = new ArrayList<>();
            int initialGold = 0;

            Player newPlayer = new Player(initialGold, initialBalls, initialPokemonList);
            playerList.add(newPlayer);

            return newPlayer;
        } else {
            // 파일에서 로드된 첫 번째 플레이어 반환
            return playerList.get(0);
        }
    }

    public void savePlayer(Player player) {
        // 특정 플레이어 객체를 업데이트하고 저장
        if (!playerList.contains(player)) {
            playerList.add(player);
        }
        savePlayerInfo(new File(FILE_PATH), playerList);
    }
}
