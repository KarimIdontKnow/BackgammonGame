package com.backgammonspiel;

import java.util.Scanner;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private boolean isGameRunning;

    public Game() {
        // Spieler initialisieren
        player1 = new Player("Spieler 1", "weiß");
        player2 = new Player("Spieler 2", "schwarz");

        // Spielfeld initialisieren
        board = new Board();

        isGameRunning = true;
    }

    public void start() {
        System.out.println("Willkommen zu Backgammon!");
        while (isGameRunning) {
            takeTurn(player1);
            if (checkWinCondition(player1)) {
                System.out.println(player1.getName() + " hat gewonnen!");
                break;
            }

            takeTurn(player2);
            if (checkWinCondition(player2)) {
                System.out.println(player2.getName() + " hat gewonnen!");
                break;
            }
        }
        System.out.println("Spiel beendet.");
    }

    private void takeTurn(Player player) {
        System.out.println("\n" + player.getName() + " ist am Zug.");
        int diceRoll = rollDice();
        System.out.println(player.getName() + " hat " + diceRoll + " gewürfelt.");

        // Logik für das Ziehen des Spielers
        makeMove(player, diceRoll);

        board.displayBoard(); // Board-Zustand anzeigen
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }


    private void makeMove(Player player, int diceRoll) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wähle ein Feld (0-23) zum Bewegen: ");
        int fromField = scanner.nextInt();
        int toField = fromField + diceRoll;

        if (toField < 24) {
            Field currentField = board.getField(fromField);
            Field targetField = board.getField(toField);

            // Prüfen, ob der Spieler das Feld besitzt
            if (currentField.getStoneColor() != null && currentField.getStoneColor().equals(player.getColor())) {
                // Prüfen, ob das Zielfeld leer oder durch den Spieler besetzt ist
                if (targetField.getStoneColor() == null || targetField.getStoneColor().equals(player.getColor())) {
                    currentField.removeStones(1);
                    targetField.addStones(1, player.getColor());
                    System.out.println("Stein bewegt von Feld " + fromField + " zu Feld " + toField);
                }
                // Prüfen, ob das Zielfeld nur einen gegnerischen Stein hat (schlagen)
                else if (targetField.getStoneCount() == 1) {
                    System.out.println("Ein gegnerischer Stein wurde geschlagen!");
                    targetField.removeStones(1); // Gegnerischen Stein entfernen
                    player.captureStone(); // Auf die Bar legen
                    targetField.addStones(1, player.getColor()); // Spieler setzt seinen Stein
                    currentField.removeStones(1);
                } else {
                    System.out.println("Das Zielfeld ist blockiert. Ungültiger Zug.");
                }
            } else {
                System.out.println("Du kannst nur deine eigenen Steine bewegen.");
            }
        } else {
            System.out.println("Zug außerhalb des Spielfelds.");
        }
    }


    private boolean checkWinCondition(Player player) {
        int homeStart = 18;
        int totalStonesInHome = 0;

        for (int i = homeStart; i < 24; i++) {
            Field field = board.getField(i);
            if (field.getStoneColor() != null && field.getStoneColor().equals(player.getColor())) {
                totalStonesInHome += field.getStoneCount();
            }
        }

        // Siegbedingung: 15 Steine im Home-Bereich
        return totalStonesInHome == 15;
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
