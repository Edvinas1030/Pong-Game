package Game;

public class GameRenderer {

    private String solidWall = "-";
    private String background = " ";
    private String playerTile = "|";
    private String ballTile = "*";

    private GameMap map;
    private Player player;
    private Ball ball;

    public GameRenderer(GameMap map, Player player, Ball ball) {
        this.map = map;
        this.player = player;
        this.ball = ball;
    }

    public void drawMap() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.isWall(x, y)) {
                    System.out.print(solidWall);
                } else if (player.isPlayer(x, y)) {
                    System.out.print(playerTile);
                } else if (ball.getBallX() == x && ball.getBallY() == y) {
                    System.out.print(ballTile);
                } else {
                    System.out.print(background);
                }
            }
            System.out.println();
        }
        System.out.println(player.getScore());
    }
}
