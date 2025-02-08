package com.backgammonspiel;

public class Player {
    private String name;
    private String color; // "weiß" oder "schwarz"
    private int capturedStones;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.capturedStones = 0;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getCapturedStones() {
        return capturedStones;
    }

    public void captureStone() {
        capturedStones++;
    }

    public void releaseStone() {
        if (capturedStones > 0) {
            capturedStones--;
        }
    }
}
