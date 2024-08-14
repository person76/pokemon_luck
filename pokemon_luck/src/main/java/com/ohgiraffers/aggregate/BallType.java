package com.ohgiraffers.aggregate;

public enum BallType {
    MONSTERBALL ("MonsterBall", 10),
    SUPERBALL("SuperBall", 50),
    HYPERBALL("HyperBall", 100),
    MASTERBALL("MasterBall", 200);

    private final String name;
    private final int price;

    BallType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static BallType getByName(String name) {
        for (BallType ball : values()) {
            if (ball.getName().equalsIgnoreCase(name)) {
                return ball;
            }
        }
        return null;
    }



}
