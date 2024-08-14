package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.PokemonGrade;
import com.ohgiraffers.aggregate.PokemonInfo;

import java.io.*;
import java.util.ArrayList;

public class PokemonRepository {
    private final ArrayList<PokemonInfo> pokemonList = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/com/ohgiraffers/db/pokemonDB.dat";


    public PokemonRepository(){
        File file = new File(FILE_PATH);
// 피카츄 라이츄 파이리 꼬북이 버터풀 야도란 피존투 또가스
        if(!file.exists()){
            ArrayList<PokemonInfo> pokemonInfos = new ArrayList<>();
            pokemonInfos.add(new PokemonInfo("피카츄", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("라이츄", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("파이리", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("꼬북이", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("버터풀", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("야도란", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("피죤투", PokemonGrade.NORMAL));
            pokemonInfos.add(new PokemonInfo("또가스", PokemonGrade.NORMAL));

            pokemonInfos.add(new PokemonInfo("리자몽", PokemonGrade.UNIQUE));
            pokemonInfos.add(new PokemonInfo("이상해풀", PokemonGrade.UNIQUE));
            pokemonInfos.add(new PokemonInfo("망냐뇽", PokemonGrade.UNIQUE));
            pokemonInfos.add(new PokemonInfo("잠만보", PokemonGrade.UNIQUE));
            pokemonInfos.add(new PokemonInfo("롱스톤", PokemonGrade.UNIQUE));
            pokemonInfos.add(new PokemonInfo("뮤", PokemonGrade.UNIQUE));


            pokemonInfos.add(new PokemonInfo("디아루가", PokemonGrade.LEGENDARY));
            pokemonInfos.add(new PokemonInfo("펄기아", PokemonGrade.LEGENDARY));
            pokemonInfos.add(new PokemonInfo("기라티나", PokemonGrade.LEGENDARY));


            pokemonInfos.add(new PokemonInfo("아르세우스", PokemonGrade.GOD));

            savePokemons(file,pokemonInfos);
        }

        loadPokemons(file);
    }
    private void loadPokemons(File file) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            while(true) {
                pokemonList.add((PokemonInfo) ois.readObject());
            }

        } catch(EOFException e) {
            System.out.println("포켓몬 정보를 모두 로딩하였습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void savePokemons(File file, ArrayList<PokemonInfo> pokemonInfos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            for (PokemonInfo pokemonInfo : pokemonInfos) {
                oos.writeObject(pokemonInfo);
            }

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
