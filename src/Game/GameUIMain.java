/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static java.awt.Event.DOWN;
import static java.awt.Event.UP;
import static java.awt.FlowLayout.LEFT;
import static java.awt.FlowLayout.RIGHT;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.input.KeyCode.SHIFT;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author mazas
 */
public class GameUIMain extends Application {
    
    private static final int width = 800;
	private static final int height = 800;
	private static final int PLAYER_HEIGHT = 100;
	private static final int PLAYER_WIDTH = 15;
        private static final int WALL_SIZE = 80;
	private static final double BALL_R = 15;
        
    @Override
    public void start(Stage primaryStage) {
        GameMap map=new GameMap();
        Player player = new Player();
        Ball ball = new Ball();
        GameRules gameRules = new GameRules(map, player, ball);
        
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc, map, player, ball);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Pong Game");
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case UP:
                    gameRules.movePlayer("up");
                    drawShapes(gc, map, player, ball);
                    System.out.println("bepis");
                    break;
                case DOWN:
                    gameRules.movePlayer("down");
                    drawShapes(gc, map, player, ball);
                    break;
            }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void drawShapes(GraphicsContext gc, GameMap map, Player player, Ball ball){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
                            gc.setFill(Color.WHITE);
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.isWall(x, y)) {
                    gc.fillRect(x*80, y*80, WALL_SIZE, WALL_SIZE);
                } else if (player.isPlayer(x, y)) {
                    gc.fillRect(player.getxPosition()*80, player.getyPosition2()*80, PLAYER_WIDTH, PLAYER_HEIGHT);
                } else if (ball.getBallX() == x && ball.getBallY() == y) {
                    gc.fillOval(ball.getBallX()*80, ball.getBallY()*80, 30, 30);

              }
//
//                    System.out.print(background);
//                }
            }
            //System.out.println();
        }
        //System.out.println(player.getScore());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
