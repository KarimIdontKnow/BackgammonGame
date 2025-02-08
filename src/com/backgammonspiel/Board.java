package com.backgammonspiel;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Field> fields;

    public Board() {
        fields = new ArrayList<>();
        // 24 Felder initialisieren
        for (int i = 0; i < 24; i++) {
            fields.add(new Field(i));
        }
    }

    public Field getField(int position) {
        if (position >= 0 && position < fields.size()) {
            return fields.get(position);
        }
        throw new IllegalArgumentException("Ungültige Feldposition: " + position);
    }

    public void displayBoard() {
        System.out.println("Spielfeldzustand:");
        for (int i = 0; i < fields.size(); i++) {
            System.out.println("Feld " + i + ": " + fields.get(i).getStoneCount() + " Steine");
        }
    }
}
