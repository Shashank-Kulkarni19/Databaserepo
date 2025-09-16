package com.example.tictactoe.service;

import com.example.tictactoe.model.GameState;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final Map<String, GameState> games = new ConcurrentHashMap<>();

    public GameState createGame() {
        String id = UUID.randomUUID().toString().substring(0, 8);
        GameState game = new GameState(id);
        game.setStatus("WAITING");
        games.put(id, game);
        return game;
    }

    public GameState getGame(String id) {
        return games.get(id);
    }

    public GameState makeMove(String gameId, int index, String player) {
        GameState game = games.get(gameId);
        if (game == null) return null;
        String[] board = game.getBoard();

        if (board[index] == null && game.getCurrentPlayer().equals(player)) {
            board[index] = player;
            if (checkWinner(board, player)) {
                game.setStatus(player + " WINS");
            } else if (isBoardFull(board)) {
                game.setStatus("DRAW");
            } else {
                game.setCurrentPlayer(player.equals("X") ? "O" : "X");
                game.setStatus("IN_PROGRESS");
            }
        }
        return game;
    }

    private boolean checkWinner(String[] board, String p) {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] line : lines) {
            if (p.equals(board[line[0]]) &&
                p.equals(board[line[1]]) &&
                p.equals(board[line[2]])) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull(String[] board) {
        for (String cell : board) if (cell == null) return false;
        return true;
    }
}
