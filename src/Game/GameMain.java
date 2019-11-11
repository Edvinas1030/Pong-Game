package Game;

import java.util.Scanner;

public class GameMain{

    public static void main(String[] args) {
        Player player = new Player();
        GameMap map = new GameMap();
        Ball ball = new Ball();
        GameRenderer gameRenderer = new GameRenderer(map, player, ball);
        GameRules gameRules = new GameRules(map, player, ball);
        Scanner scan = new Scanner(System.in);
        GameThread thread = new GameThread(map, player, ball, gameRenderer, gameRules);
        thread.start();
        while (true) {
            //gameRenderer.drawMap();
            //gameRules.moveBall();
            String input = scan.nextLine().trim();
            switch (input) {
                case "w":
                    gameRules.movePlayer("up");
                    break;
                case "s":
                    gameRules.movePlayer("down");
                    break;
            }
           // gameRules.addScore();
        }
    }
}
    
  
    