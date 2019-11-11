package Game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{
    private GameMap map;
    private Player player;
    private Ball ball;
    private GameRenderer gameRenderer;
    private GameRules gameRules;

    public GameThread(GameMap map, Player player, Ball ball, GameRenderer gameRenderer, GameRules gameRules) {
        this.map = map;
        this.player = player;
        this.ball = ball;
        this.gameRenderer = gameRenderer;
        this.gameRules = gameRules;
    }
    @Override
    public void run(){
        while(true)
        {
        gameRenderer.drawMap();
        gameRules.moveBall();
        gameRules.addScore();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
