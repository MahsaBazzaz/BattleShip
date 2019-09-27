import java.awt.Point;

/**
 * the class for doing mathematical thing :))
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Mathematics {
    /**
     * actually no constructor was needed :)))
     */
    private Mathematics() {

    }

    /**
     * Is point between boolean.
     *
     * @param point    the point
     * @param position the position
     * @return the boolean
     */
    public static boolean isPointBetween(Point point, Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        if (from.getY() <= point.getY()
                && to.getY() >= point.getY()
                && from.getX() <= point.getX()
                && to.getX() >= point.getX()) {
            return true;
        } else
            return false;
    }

    /**
     * Distance between points double.
     *
     * @param from the from
     * @param to   the to
     * @return the double
     */
    public static double distanceBetweenPoints(Point from, Point to) {
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();

        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
    }


}