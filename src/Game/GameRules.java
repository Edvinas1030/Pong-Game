package Game;

public class GameRules {

    private GameMap map;
//    private Player player;
//    private Player player2;
    private Ball ball;

    public GameRules(GameMap map, Ball ball) {
        this.map = map;
//        this.player = player;
//        this.player2 = player;
        this.ball = ball;
    }

    public void movePlayer(String direction, Player player) {
        int newY1, newY2;
        int x = player.getxPosition();

        if (direction.equals("up")) {
            newY1 = player.getyPosition1() - 1;
            newY2 = player.getyPosition2() - 1;
        } else {
            newY1 = player.getyPosition1() + 1;
            newY2 = player.getyPosition2() + 1;
        }

        if (map.isWall(x, newY1) || map.isWall(x, newY2)) {
            return;
        }

        player.setyPosition1(newY1);
        player.setyPosition2(newY2);
    }

    public void moveBall(Player player, Player player2) {
//        int oldDX = ball.getBallDX()*ball.getBallSpeed();
//        int oldDY = ball.getBallDY()*ball.getBallSpeed();

        int oldDX = ball.getBallDX();
        int oldDY = ball.getBallDY();
        
        int newX = ball.getBallX() + oldDX;
        int newY = ball.getBallY() + oldDY;

        //int newDX;
        //int newDY;
        //horizintal wall
        if (map.isWall(newX, newY) && ((map.getHeight() - 1) == newY || newY == 0)) {
            //newDY = oldDY * -1;
            ball.setBallDY(oldDY * -1);
        } //vertical wall
        else if (player.isPlayer(newX, newY) || player2.isPlayer(newX, newY)) {
            //newDX = oldDX * -1;
            ball.setBallDX(oldDX * -1);
            ball.setBallSpeed(ball.getBallSpeed()+1);
        } else {
            ball.setBallX(newX);
            ball.setBallY(newY);
        }
    }
    
    public void addScore(Player player, Player player2){
        if(ball.getBallX()<player.getxPosition())
        {
            int score = player.getScore()+1;
            player.setScore(score);
            resetBall();
        }
        else if(ball.getBallX()>player2.getxPosition())
        {
            int score = player2.getScore()+1;
            player2.setScore(score);
            resetBall();
        }
    }
    
    public void resetBall(){
        ball.setBallDX(1);
        ball.setBallDY(-1);
        ball.setBallX(2);
        ball.setBallY(5);
        ball.setBallSpeed(1);
    }
}
