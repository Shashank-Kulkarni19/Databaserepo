package com.example.tictactoe.model;

import java.util.Arrays;

public class GameState {
    private String gameId;
    private String[] board = new String[9];
    private String currentPlayer = "X";
    private String status = "WAITING";

    public GameState(String gameId) {
        this.gameId = gameId;
        Arrays.fill(board, null);
    }

    public String getGameId() { return gameId; }
    public String[] getBoard() { return board; }
    public void setBoard(String[] board) { this.board = board; }
    public String getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
