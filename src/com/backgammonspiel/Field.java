package com.backgammonspiel;

public class Field {
    private int stoneCount;
    private String stoneColor; // "weiß" oder "schwarz"

    public Field(int initialPosition) {
        this.stoneCount = 0;
        this.stoneColor = null;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public String getStoneColor() {
        return stoneColor;
    }

    public void addStones(int count, String color) {
        if (stoneColor == null || stoneColor.equals(color)) {
            stoneCount += count;
            stoneColor = color;
        } else {
            throw new IllegalArgumentException("Kann keine Steine einer anderen Farbe hinzufügen.");
        }
    }

    public void removeStones(int count) {
        if (stoneCount >= count) {
            stoneCount -= count;
            if (stoneCount == 0) {
                stoneColor = null;
            }
        } else {
            throw new IllegalArgumentException("Nicht genügend Steine auf dem Feld.");
        }
    }
}
