package com.ohgiraffers.repository;

import com.ohgiraffers.aggregate.Player;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerRepository {

    private ArrayList<Player> playerList = new ArrayList<>();

    private static final String FILE_PATH = "src/main/java/com/ohgiraffers/db/PlayerDB.dat";

    private void loadPlayerInfo(File file){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            
            while (true){
                playerList.add((Player)ois.readObject());
            }

        }catch (EOFException e){
            System.out.println("플레이어 정보를 전부 로딩했습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
