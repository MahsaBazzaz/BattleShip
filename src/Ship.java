/**
 * The ship class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Ship {
    private int index;
    private int size;
    private int livesLeft;
    private boolean isSunk;
    private Position position;

    /**
     * the constructor
     *
     * @param index the index of the ship
     * @param size  the size of the ship
     */
    public Ship(int index, int size) {
        this.index = index;
        this.size = size;
        this.livesLeft = size;
        this.isSunk = false;
    }

    /**
     * get the index of the ship
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * set the size of the ships
     *
     * @param size the size of the ship
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get the size of the ship
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * get the lives left for the ship
     *
     * @return lives
     */
    public int getLivesLeft() {
        return livesLeft;
    }

    /**
     * decrease the ship's lives
     */
    public void decrementLives() {
        livesLeft--;
    }

    /**
     * Is the ship sunk?
     *
     * @return true or false
     */
    public boolean isSunk() {
        if (livesLeft == 0) {
            isSunk = true;
        } else if (livesLeft != 0) {
            isSunk = false;
        }
        return isSunk;
    }

    /**
     * get the position of the ship
     *
     * @return position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * set the position of the ship
     *
     * @param position the position of the ship
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * funeral of the ship :)))
     */
    public void shipWasHit() {
        if (isSunk()) {
            System.out.println("Congratulations!!! You sunk the Ship " + index);
        }
    }
}