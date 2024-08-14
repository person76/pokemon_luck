package com.ohgiraffers.aggregate;

import java.io.Serializable;

public class PokemonInfo implements Serializable {
    private final String name;
    private final PokemonGrade grade;

    public PokemonInfo(String name, PokemonGrade grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public PokemonGrade getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "PokemonInfo{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
