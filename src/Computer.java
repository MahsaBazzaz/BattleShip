import java.awt.*;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * The computer class which extends the player class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Computer extends Player {
    private Map<Point, Boolean> targetHistory;
    private Scanner scanner;

    /**
     * constructor of computer
     */
    Computer() {
        super(1);
        setShootType("R");
    }
    //see the java doc comment in the player class
    @Override
    public void turnToPlay(Player opponent) {
        Random random = new Random();
        Point point = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));

        while (getTargetHistory().get(point) != null) {
            point = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));
        }
        attack(point, opponent);
    }
}