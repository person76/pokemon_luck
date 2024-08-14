package com.ohgiraffers.aggregate;

public enum PokemonGrade {
    NORMAL ("Normal", 20),
    UNIQUE("Unique", 80),
    LEGENDARY("Legendary", 250),
    GOD ("GOD", 9999999);

    private final String gradeName;
    private final int pokemonGold;

    PokemonGrade(String gradeName, int pokemonGold) {
        this.gradeName = gradeName;
        this.pokemonGold = pokemonGold;
    }

    public String getGradeName() {
        return gradeName;
    }

    public int getPokemonGold() {
        return pokemonGold;
    }

}
