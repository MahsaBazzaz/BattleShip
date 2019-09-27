import java.awt.*;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * The human class which extends the player class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Human extends Player {
    private Scanner scanner;

    /**
     * the constructor
     *
     * @param id id of the player
     */
    public Human(int id) {
        super(id);
        boolean isShootTypeLegal = false;
        String shoot;
        this.scanner = new Scanner(System.in);
        while (!isShootTypeLegal) {
            try {
                System.out.printf("%n%nPlayer %d,What type of shoot you want? Regular or with error?" +
                        "Enter R for Regular and E for shoot with Error", id);
                System.out.println(" ");
                shoot = scanner.nextLine();
                if (shoot.equals("R") || shoot.equals("C")) {
                    setShootType(shoot);
                    isShootTypeLegal = true;
                } else
                    System.out.println("ERROR::INVALID SHOOT TYPE!! Try again..");
            } catch (InvalidParameterException e) {
                System.out.println("ERROR::INVALID SHOOT TYPE!!");
            }
        }
    }
    //see the java doc comment in the player class
    @Override
    public void turnToPlay(Player opponent) {
        Point point = null;
        boolean isPointLegal = false;
        System.out.println("\t\tYOUR FIELD\t\t\t\t\t\t\t\t\t\t\t\tOPPONENT FIELD");
        System.out.println("-------------------------------------------------------------------------------------------");
        Display d = new Display(getBoard(), opponent.getBoard());
        while (!isPointLegal) {
            System.out.printf("%n%nPlayer %d, Choose coordinates you want to hit (x y) ", getId());
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
                    point = new Point(x, y);
                    isPointLegal = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR::Try another point!!");
                point = new Point(scanner.nextInt(), scanner.nextInt());
            }
        }
        while (getTargetHistory().get(point) != null) {
            System.out.print("This position has already been tried");
            point = new Point(scanner.nextInt(), scanner.nextInt());
        }
        if (getShootType().equals("R")) {
            attack(point, opponent);
        } else if (getShootType().equals("E")) {
            attack(shootWithError(point), opponent);
        }
    }

    /**
     * to shoot with error
     *
     * @param p the shoot point
     * @return the shoot point with error
     */
    private Point shootWithError(Point p) {
        Random rand = new Random();
        Point r = null;
        if (p.getX() != 0 && p.getY() != 0 && p.getX() != 10 && p.getY() != 10) {
            if (rand.nextBoolean())
                r = new Point((int) p.getX() + rand.nextInt(1), (int) p.getY() + rand.nextInt(1));
            else
                r = new Point((int) p.getX() - rand.nextInt(1), (int) p.getY() - rand.nextInt(1));
        } else if (p.getY() == 0 || p.getX() == 0) {
            r = new Point((int) p.getX() + rand.nextInt(1), (int) p.getY() + rand.nextInt(1));
        } else if (p.getX() == 10 || p.getY() == 10) {
            r = new Point((int) p.getX() - rand.nextInt(1), (int) p.getY() - rand.nextInt(1));
        }
        return r;
    }
}
