import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * the player class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public abstract class Player {
    private int id;
    private int lives;
    private Board board;
    private Map<Point, Boolean> targetHistory;
    private Scanner scanner;
    private String shootType;
    private boolean bonus;

    /**
     * constructor of Player.
     *
     * @param playerID the id
     */
    public Player(int playerID) {
        id = playerID;
        if (this instanceof Human)
            System.out.printf("Setting up things for player %d\n", id);
        targetHistory = new HashMap<>();
        board = new Board(this);
        bonus = false;
    }

    /**
     * to set the shoot type
     *
     * @param shootType the shoot type
     */
    public void setShootType(String shootType) {
        this.shootType = shootType;
    }

    /**
     * to get the shoot type
     *
     * @return shoot type
     */
    public String getShootType() {
        return shootType;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * to Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * to Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * to Decrement live by one.
     */
    public void decrementLiveByOne() {
        lives--;
    }

    /**
     * Turn to play.
     * this method overrides in the human class and computer class
     *
     * @param opponent the opponent
     */
    public void turnToPlay(Player opponent) {

    }

    /**
     * is there a bonus?
     *
     * @return if there is a a bonus or not
     */
    public boolean isBonus() {
        return bonus;
    }


    /**
     * to get the board
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * to declare if there is a bonus or not
     * @param bonus true or false
     */
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    /**
     * to get the target history
     *
     * @return target history
     */
    public Map<Point, Boolean> getTargetHistory() {
        return targetHistory;
    }

    /**
     * to Attack
     *
     * @param point    the point to attack
     * @param opponent the opponent
     */

    public void attack(Point point, Player opponent) {
        Ship ship = opponent.getBoard().targetShip(point);
        boolean isShipHit = (ship != null) ? true : false;

        if (isShipHit) {
            ship.decrementLives();
            ship.shipWasHit();
            opponent.decrementLiveByOne();
            bonus = true; //if one player hits the ship there is a bonus for it
        }
        targetHistory.put(point, isShipHit);
        if (this instanceof Human) {
            System.out.printf("Player %d, targets (%d, %d)", id, (int) point.getX(), (int) point.getY());
            System.out.println("...and " + ((isShipHit) ? "IT HITS THE SHIP!!" : "It misses..."));
        } else if (this instanceof Computer) {
            System.out.printf("Computer, targets (%d, %d)", (int) point.getX(), (int) point.getY());
            System.out.println("...and " + ((isShipHit) ? "IT HITS THE SHIP!!" : "It misses..."));
        }
    }

}