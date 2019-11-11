/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author mazas
 */
public class UI extends Application {

    private int width = 800;
    private int height = 800;
    private int playerSize = 15;
    private int wallSize = 15;
    private double ballR = 15;
    private boolean isGameStarted = false;
    private String gamePause = "START GAME"; 

    @Override
    public void start(Stage primaryStage) {
        GameMap map = new GameMap();
        Player player = new Player();
        Player player2 = new Player();
        player2.setxPosition(map.getWidth() - 2);
        Ball ball = new Ball();
        GameRules gameRules = new GameRules(map, ball);

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e -> drawShapes(gc, map, player, player2, ball)));
        t1.setCycleCount(Timeline.INDEFINITE);
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200), e -> runGame(gameRules, player, player2)));
        t2.setCycleCount(Timeline.INDEFINITE);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                gameRules.movePlayer("up", player2);
            }
            if (key.getCode() == KeyCode.DOWN) {
                gameRules.movePlayer("down", player2);
            }
            if (key.getCode() == KeyCode.W) {
                gameRules.movePlayer("up", player);
            }
            if (key.getCode() == KeyCode.S) {
                gameRules.movePlayer("down", player);
            }
            if (key.getCode() == KeyCode.ENTER) {
                isGameStarted = true;
            }
        });

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        //drawShapes(gc, map, player, ball);
        primaryStage.setTitle("Pong Game");

        primaryStage.setScene(scene);
        primaryStage.show();

        t1.play();
        t2.play();
    }

    private void drawShapes(GraphicsContext gc, GameMap map, Player player, Player player2, Ball ball) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setFill(Color.WHITE);
        if (!isGameStarted) {
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2.0);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText(gamePause, width / 2, height / 2);
        } else if (player.getScore() == 5 || player2.getScore() == 5) {
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2.0);
            gc.setTextAlign(TextAlignment.CENTER);
            if (player.getScore() == 5) {
                gamePause="PLAYER 1 WON, PRESS ENTER";
                isGameStarted = false;
                player.setScore(0);
                player2.setScore(0);
            } else {
                gamePause="PLAYER 2 WON, PRESS ENTER";
                isGameStarted = false;
                player.setScore(0);
                player2.setScore(0);
            }
        } else {
            for (int y = 0; y < map.getHeight(); y++) {
                for (int x = 0; x < map.getWidth(); x++) {
                    if (map.isWall(x, y)) {
                        gc.fillRect(10 + x * 20, 10 + y * 20, wallSize, wallSize);
                    } else if (player.isPlayer(x, y)) {
                        gc.fillRect(10 + x * 20, 10 + y * 20, playerSize, playerSize);
                    } else if (player2.isPlayer(x, y)) {
                        gc.fillRect(10 + x * 20, 10 + y * 20, playerSize, playerSize);
                    } else if (ball.getBallX() == x && ball.getBallY() == y) {
                        gc.fillOval(10 + x * 20, 10 + y * 20, ballR, ballR);
                    }
                }
            }
            gc.setTextAlign(TextAlignment.LEFT);
            gc.strokeText("SCORE: " + player2.getScore() + " " + player.getScore(), 10, height / 3);
        }
    }

    public void runGame(GameRules gameRules, Player player, Player player2) {
        gameRules.moveBall(player, player2);
        gameRules.addScore(player, player2);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
