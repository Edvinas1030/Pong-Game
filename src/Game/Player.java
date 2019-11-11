package Game;

public class Player {

    private int playerLength = 3, score = 0, xPosition = 1, yPosition1 = 4, yPosition2 = 6;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition1() {
        return yPosition1;
    }

    public void setyPosition1(int yPosition1) {
        this.yPosition1 = yPosition1;
    }

    public int getyPosition2() {
        return yPosition2;
    }

    public void setyPosition2(int yPosition2) {
        this.yPosition2 = yPosition2;
    }

    public boolean isPlayer(int x, int y) {
        if (xPosition == x && (yPosition1 <= y && yPosition2 >= y)) {
            return true;
        } else {
            return false;
        }
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

}
