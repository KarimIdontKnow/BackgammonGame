package com.backgammonspiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private JFrame frame;
    private JPanel boardPanel;
    private JLabel playerTurnLabel;
    private JButton rollDiceButton;
    private JLabel diceResultLabel;

    private Game game;
    private Player currentPlayer;

    public GameGUI(Game game) {
        this.game = game;
        this.currentPlayer = game.getPlayer1();

        // GUI initialisieren
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Backgammon Spiel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Spielfeld-Panel
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(2, 12));  // 2 Reihen, 12 Felder pro Reihe
        frame.add(boardPanel, BorderLayout.CENTER);

        // Spielfeld grafisch anzeigen
        for (int i = 0; i < 24; i++) {
            JButton fieldButton = new JButton("Feld " + i);
            fieldButton.setBackground(Color.LIGHT_GRAY);
            boardPanel.add(fieldButton);
        }

        // Statusbereich (unten)
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());

        playerTurnLabel = new JLabel("Am Zug: " + currentPlayer.getName());
        rollDiceButton = new JButton("Würfeln");
        diceResultLabel = new JLabel("Ergebnis: -");

        // Button-ActionListener zum Würfeln
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = game.rollDice();
                diceResultLabel.setText("Ergebnis: " + diceRoll);
                JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " hat " + diceRoll + " gewürfelt.");

                // Wechsel zum nächsten Spieler (für den Anfang simpel)
                togglePlayer();
                playerTurnLabel.setText("Am Zug: " + currentPlayer.getName());
            }
        });

        statusPanel.add(playerTurnLabel);
        statusPanel.add(rollDiceButton);
        statusPanel.add(diceResultLabel);
        frame.add(statusPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
    }

    public static void main(String[] args) {
        Game game = new Game();  // Spiel initialisieren
        new GameGUI(game);       // GUI starten
    }
}
