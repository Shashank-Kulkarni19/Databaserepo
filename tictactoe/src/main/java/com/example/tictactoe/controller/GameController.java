package com.example.tictactoe.controller;

import com.example.tictactoe.model.GameState;
import com.example.tictactoe.model.Move;
import com.example.tictactoe.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/create")
    public GameState createGame() {
        return gameService.createGame();
    }

    @GetMapping("/{gameId}")
    public GameState getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    @MessageMapping("/move")
    public void makeMove(@Payload Move move) {
        GameState updated = gameService.makeMove(move.getGameId(), move.getIndex(), move.getPlayer());
        if (updated != null) {
            messagingTemplate.convertAndSend("/topic/game." + move.getGameId(), updated);
        }
    }
}
