package com.ohgiraffers.service;

import com.ohgiraffers.aggregate.BallType;
import com.ohgiraffers.aggregate.Player;
import com.ohgiraffers.aggregate.PokemonGrade;
import com.ohgiraffers.repository.PokemonRepository;
import com.ohgiraffers.aggregate.PokemonInfo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.ohgiraffers.aggregate.BallType.*;

public class HuntingService {

    private final PokemonRepository pokemonRepository;
    public HuntingService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public void selectHuntingGround(Player player){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n======= 사냥터 입구 =======");
            System.out.println("입장하실 사냥터를 선택해주세요.");
            System.out.println("1. 초급 사냥터 \n2. 중급 사냥터 \n3. 고급 사냥터");
            System.out.println("입장할 사냥터 번호: ");
            int huntPlaceNum = scanner.nextInt();
            if(huntPlaceNum==1 || huntPlaceNum == 2 || huntPlaceNum ==3){
                startHunting(player,huntPlaceNum);
                break;
            }
            else{
                System.out.println("사냥터 번호가 유효하지 않습니다. 1,2,3 중 하나를 입력해주세요");
            }
        }
    }
    // 사냥터 선택 화면. 선택 후 enterHuntingGround 호출

    private void startHunting(Player player, int HuntingPlaceGrade)  {
        String HuntingPlaceName = " ";
        if(HuntingPlaceGrade==1){
            HuntingPlaceName = "초급";
        }
        else if(HuntingPlaceGrade==2){
            HuntingPlaceName = "중급";
        }
        else {
            HuntingPlaceName = "고급";
        }
        System.out.println( HuntingPlaceName + "사냥터에 입장합니다.");
        try {
            System.out.print("포켓몬을 탐색중입니다");
            for (int i = 0; i < 3; i++) {  // 점 3개 출력
                Thread.sleep(500);  // 0.5초 대기
                System.out.print(".");
            }
            Thread.sleep(500);  // 추가 대기
            System.out.println();

            PokemonInfo foundPokemon = findPokemon(HuntingPlaceGrade);


            System.out.println("야생의 " + foundPokemon.getName() + "(이)가 나타났다!");
            System.out.println("포켓몬 등급: "+foundPokemon.getGrade());

            System.out.println("행동 선택");
            System.out.println("1. 도망가기");
            System.out.println("2. 사냥하기");

            System.out.println("번호 입력: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.print("사냥터를 빠져나오는 중입니다");
                    for (int i = 0; i < 3; i++) {  // 점 3개 출력
                        Thread.sleep(500);  // 0.5초 대기
                        System.out.print(".");
                    }
                    return;
                case 2:
                    hunting(foundPokemon,player);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //선택한 등급의 사냥터에서 포켓몬 탐색(findPokemon 호출) 후 사냥 진행(huntingGround 호출) or return.

    private void hunting(PokemonInfo pokemonInfo, Player player){
        while (true) {
            if(player.getPlayerBall()==null){
                System.out.println("몬스터볼을 모두 소진하였습니다. 메인화면으로 돌아갑니다.");
                return;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("사냥에 사용할 볼을 선택해주세요.");
            System.out.println("1. 몬스터볼 \n2. 슈퍼볼\n3.하이퍼볼\n4.마스터볼");
            System.out.println("=== 보유한 볼 정보 ===");
            System.out.println(player.getPlayerBall());
            System.out.println("번호 입력: ");
            int choiceBall = scanner.nextInt();
            int cnt = 0;
            BallType selectBallType = null;
            while (true){
                switch (choiceBall) {
                    case 1:
                        cnt = player.getPlayerBallByType(MONSTERBALL);
                        selectBallType = MONSTERBALL;
                        break;
                    case 2:
                        cnt = player.getPlayerBallByType(SUPERBALL);
                        selectBallType = SUPERBALL;
                        break;
                    case 3:
                        cnt = player.getPlayerBallByType(HYPERBALL);
                        selectBallType = HYPERBALL;
                        break;
                    case 4:
                        cnt = player.getPlayerBallByType(MASTERBALL);
                        selectBallType = MONSTERBALL;
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 1, 2, 3, 4 중에서 선택해주세요.");
                }

                if(cnt==0){
                    System.out.println("해당 볼을 보유하고 있지 않습니다. 다른 볼을 선택해주세요.");
                    break;
                }
                else {
                    boolean huntSuccess = isHuntingSuccessful(pokemonInfo.getGrade(), selectBallType);
                    player.useBall(selectBallType);
                    if (huntSuccess) {
                        System.out.println(pokemonInfo.getName() + "을(를) 포획했습니다!");
                        System.out.println(pokemonInfo.getGrade().getPokemonGold() + "G를 획득합니다.");

                        player.addPokemon(pokemonInfo);
                        player.setGold(player.getGold() + pokemonInfo.getGrade().getPokemonGold());
                        System.out.println("메인화면으로 돌아갑니다.");
                        return;

                    } else {
                        System.out.println(pokemonInfo.getName() + "을(를) 포획하지 못했습니다.");
                        while(true) {
                            System.out.println("다시 시도하겠습니까?(Y/N): ");
                            scanner = new Scanner(System.in);
                            char ch = scanner.next().charAt(0);
                            if (ch == 'Y') {
                                break;
                            } else if (ch == 'N') {
                                System.out.println("메인화면으로 돌아갑니다.");
                                return;
                            } else {
                                System.out.println("Y 또는 N 으로만 입력해 주세요.");
                            }
                        }
                    }
                }


            }

        }

    }
    // 사냥 진행. hunting 호출해서 성공여부 반환받아 player 소지금 및 소지 포켓몬볼 정보 갱신

    private boolean isHuntingSuccessful(PokemonGrade pokemonGrade, BallType ballType){
        boolean isSuccess;
        Random random = new Random();
        int chance = random.nextInt(10000) + 1;
        /*
        * # 일반 포켓몬 - 몬스터볼 : 50퍼 - 슈퍼볼 : 80퍼 - 하이퍼볼 : 100퍼 - 마스터볼 : 100퍼
        * # 유니크 포켓몬 - 몬스터볼 : 10퍼 - 슈퍼볼 : 50퍼 - 하이퍼볼 : 75퍼 - 마스터볼 : 100퍼
        * # 전설 포켓몬- 몬스터볼 : 3퍼 - 슈퍼볼 : 15퍼 - 하이퍼볼 : 40퍼 - 마스터볼 : 70퍼
        * # 신 포켓몬 - 몬스터볼 : 0.01퍼 - 슈퍼볼 : 0.1퍼 - 하이퍼볼 : 1퍼 - 마스터볼 : 5퍼
        * */
        if(pokemonGrade.equals(PokemonGrade.NORMAL)) {
            if(ballType.equals(MONSTERBALL)) {
                if(chance<=5000) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(SUPERBALL)) {
                if(chance<=7000) isSuccess =true;
                else isSuccess =false;
            }

            else  {
                isSuccess = true;
            }
        }

        else if(pokemonGrade.equals(PokemonGrade.UNIQUE)) {
            if(ballType.equals(MONSTERBALL)) {
                if(chance<=1000) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(SUPERBALL)) {
                if(chance<=5000) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(HYPERBALL)) {
                if(chance<=7500) isSuccess =true;
                else isSuccess =false;
            }

            else  {
                isSuccess = true;
            }

        }

        else if(pokemonGrade.equals(PokemonGrade.LEGENDARY)) {
            if(ballType.equals(MONSTERBALL)) {
                if(chance<=300) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(SUPERBALL)) {
                if(chance<=1500) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(HYPERBALL)) {
                if(chance<=4000) isSuccess =true;
                else isSuccess =false;
            }

            else  {
                if(chance<=7000) isSuccess =true;
                else isSuccess =false;
            }

        }

        else {
            if(ballType.equals(MONSTERBALL)) {
                if(chance<=1) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(SUPERBALL)) {
                if(chance<=10) isSuccess =true;
                else isSuccess =false;
            }

            else if(ballType.equals(HYPERBALL)) {
                if(chance<=100) isSuccess =true;
                else isSuccess =false;
            }

            else  {
                if(chance<=1000) isSuccess =true;
                else isSuccess =false;
            }

        }

        return isSuccess;
    }
    // 포켓몬 등급, 볼 종류 별 확률에 맞게 포획 진행 후 성공여부 반환.

    private PokemonInfo findPokemon(int HuntingPlaceGrade){
        PokemonInfo foundPokemon = null;
        Random random = new Random();
        int chance = random.nextInt(100) + 1;  // 1부터 100 사이의 랜덤 숫자

        /* 초급 사냥터 : 일반 70퍼 유니크 29퍼 전설 1퍼
        중급 사냥터 : 일반 10퍼 유니크 50퍼 전설 39퍼 신 1퍼
	    고급 사냥터 : 일반 1퍼 유니크 10퍼 전설 60퍼 신 29퍼 */
        if(HuntingPlaceGrade==1){
            if (chance <= 70) {
                foundPokemon = getRandomPokemonByGrade("NORMAL");
            } else if (chance <= 99) {
                foundPokemon = getRandomPokemonByGrade("UNIQUE");
            } else {
                foundPokemon = getRandomPokemonByGrade("LEGENDARY");
            }
        }

        else if(HuntingPlaceGrade==2){
            if (chance <= 10) {
                foundPokemon = getRandomPokemonByGrade("NORMAL");
            } else if (chance <= 60) {
                foundPokemon = getRandomPokemonByGrade("UNIQUE");
            } else if (chance <= 99 ){
                foundPokemon = getRandomPokemonByGrade("LEGENDARY");
            }
            else{
                foundPokemon = getRandomPokemonByGrade("GOD");
            }
        }
        else{
            if (chance <= 1) {
                foundPokemon = getRandomPokemonByGrade("NORMAL");
            } else if (chance <= 11) {
                foundPokemon = getRandomPokemonByGrade("UNIQUE");
            } else if (chance <= 71 ){
                foundPokemon = getRandomPokemonByGrade("LEGENDARY");
            }
            else{
                foundPokemon = getRandomPokemonByGrade("GOD");
            }
        }

        return foundPokemon;
    }
    // 사냥터 등급에 따라 확률에 맞춰 탐색한 포켓몬 (huntingGround에) 반환.

    private PokemonInfo getRandomPokemonByGrade(String grade) {
        List<PokemonInfo> pokemons = pokemonRepository.getPokemonListByGrade(grade);

            if (!pokemons.isEmpty()) {
                Random random = new Random();
                return pokemons.get(random.nextInt(pokemons.size()));
            }
            return null;
    }

}
