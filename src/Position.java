import java.awt.*;

/**
 * The position class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Position {
    private Point from;
    private Point to;

    /**
     * constructor of Position.
     *
     * @param from the from
     * @param to   the to
     */
    public Position(Point from, Point to) {
        if (from.getX() > ConstantAmount.BOARD_SIZE || from.getX() < 0
                || from.getY() > ConstantAmount.BOARD_SIZE || from.getY() < 0
                || to.getX() > ConstantAmount.BOARD_SIZE || to.getX() < 0
                || to.getY() > ConstantAmount.BOARD_SIZE || to.getY() < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        this.from = from;
        this.to = to;
    }

    /**
     * to Gets from.
     *
     * @return the from
     */
    public Point getFrom() {
        return from;
    }

    /**
     * to Gets to.
     *
     * @return the to
     */
    public Point getTo() {
        return to;
    }
}