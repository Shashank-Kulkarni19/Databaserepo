package com.example.tictactoe.model;

public class Move {
    private String gameId;
    private int index;
    private String player;

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }
}