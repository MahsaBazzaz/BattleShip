import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/**
 * The board class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */
public class Board {
    private static final Ship[] ships = new Ship[ConstantAmount.NUMBER_OF_SHIPS];
    private char[][] board;


    /**
     * Constructor
     */
    public Board(Player p) {
        board = new char[ConstantAmount.BOARD_SIZE][ConstantAmount.BOARD_SIZE];

        for (int i = 0; i < ConstantAmount.BOARD_SIZE; i++) {
            for (int j = 0; j < ConstantAmount.BOARD_SIZE; j++) {
                board[i][j] = ConstantAmount.BOARD_ICON;
            }
        }
        if (p instanceof Human) {
            getTheShips(p);
            placeShipsOnBoard(p);
        } else if (p instanceof Computer) {
            makeRandomShips(p);
            randomlyPlacement();
        }
    }

    /**
     * What are the ships? (also player lives)
     *
     * @param p the player
     */
    private void getTheShips(Player p) {
        int lives = 0;
        int size;
        boolean isShipSizeLegal = false;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < ConstantAmount.NUMBER_OF_SHIPS; i++) {
            while (!isShipSizeLegal) {
                try {
                    System.out.printf("Player %d Enter the size of Ship %d- Size should be equal" +
                                    "or larger than %d and equal or smaller than %d",
                            p.getId(), i + 1, ConstantAmount.SHIP_MIN_LENGTH, ConstantAmount.SHIP_MAX_LENGTH);
                    size = scanner.nextInt();
                    if (size >= ConstantAmount.SHIP_MIN_LENGTH && size <= ConstantAmount.SHIP_MAX_LENGTH) {
                        ships[i] = new Ship(i + 1, size);
                        isShipSizeLegal = true;
                        lives += ships[i].getSize();
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ERROR::INVALID SHIP SIZE!! Try again");
                }

            }
            isShipSizeLegal = false;
        }
        p.setLives(lives);
    }

    /**
     * to make random Ships for computer
     *
     * @param p the player
     */
    private void makeRandomShips(Player p) {
        int lives = 0;
        Random random = new Random();
        for (int i = 0; i <= ConstantAmount.NUMBER_OF_SHIPS - 1; i++) {
            ships[i] = new Ship(i + 1,
                    random.nextInt((ConstantAmount.SHIP_MAX_LENGTH - ConstantAmount.SHIP_MIN_LENGTH) + 1) + ConstantAmount.SHIP_MIN_LENGTH);
            lives += ships[i].getSize();
        }
        p.setLives(lives);
    }

    /**
     * get the board in array form
     *
     * @return board[][]
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Place ships on board.
     */

    private void placeShipsOnBoard(Player player) {
        System.out.printf("Alright - Player %d Time to place out your ships%n%n", player.getId());
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            boolean isShipPlacementLegal = false;

            System.out.printf("%nEnter position of Ship %d (length  %d): ", ship.getIndex(), ship.getSize());

            while (!isShipPlacementLegal) {
                try {
                    Point from = new Point(s.nextInt(), s.nextInt());
                    Point to = new Point(s.nextInt(), s.nextInt());

                    while (ship.getSize() - 1 != Mathematics.distanceBetweenPoints(from, to)) {
                        System.out.printf("The ship currently being placed on the board is of length: %d. Change your coordinates and try again",
                                ship.getSize());

                        from = new Point(s.nextInt(), s.nextInt());
                        to = new Point(s.nextInt(), s.nextInt());
                    }
                    Position position = new Position(from, to);

                    if (!isPositionOccupied(position)) {
                        drawShipOnBoard(position);
                        ship.setPosition(position);
                        isShipPlacementLegal = true;
                    } else {
                        System.out.println("A ship in that position already exists - try again");
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid coordinates - Outside board");
                }
            }
        }
    }

    /**
     * Target ship ship.
     *
     * @param point the point
     * @return ship
     */
    public Ship targetShip(Point point) {
        boolean isHit = false;
        Ship hitShip = null;
        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            if (ship.getPosition() != null) {
                if (Mathematics.isPointBetween(point, ship.getPosition())) {
                    isHit = true;
                    hitShip = ship;
                    break;
                }

            }
        }
        final char result = isHit ? ConstantAmount.SHIP_IS_HIT_ICON : ConstantAmount.SHOT_MISSED_ICON;
        updateShipOnBoard(point, result);
        return (isHit) ? hitShip : null;
    }

    /**
     * randomly place the ships (for computer)
     */
    private void randomlyPlacement() {

        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            boolean isShipPlacementLegal = false;
            Random random = new Random();
            while (!isShipPlacementLegal) {
                Point from = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));
                Point to = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));
                while (ship.getSize() != Mathematics.distanceBetweenPoints(from, to)) {
                    from = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));
                    to = new Point(random.nextInt(ConstantAmount.BOARD_SIZE), random.nextInt(ConstantAmount.BOARD_SIZE));
                }
                Position position = new Position(from, to);
                if (!isPositionOccupied(position)) {
                    drawShipOnBoard(position);
                    ship.setPosition(position);
                    isShipPlacementLegal = true;
                }
            }
        }
    }

    /**
     * is the position occupied?
     *
     * @param position the position
     * @return true or false
     */
    private boolean isPositionOccupied(Position position) {
        boolean isOccupied = false;
        Point from = position.getFrom();
        Point to = position.getTo();
        outer:
        for (int i = 0; i < ConstantAmount.BOARD_SIZE; i++) {
            for (int j = 0; j < ConstantAmount.BOARD_SIZE; j++) {
                if (i >= from.getX() && i <= to.getX() && j >= from.getX() && j <= to.getY() && board[i][j] == ConstantAmount.SHIP_ICON) {
                    isOccupied = true;
                    break outer;
                }
            }
        }
        return isOccupied;
    }

    /**
     * to update ships on board (change the icon)
     *
     * @param point  the point
     * @param result the new icon
     */
    private void updateShipOnBoard(Point point, final char result) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        board[y][x] = result;
    }


    /**
     * draw the ships on the board
     *
     * @param position the position of the board
     */
    private void drawShipOnBoard(Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        for (int i = 0; i < ConstantAmount.BOARD_SIZE; i++) {
            for (int j = 0; j < ConstantAmount.BOARD_SIZE; j++) {
                if (j >= from.getX() && j <= to.getX() && i >= from.getY() && i <= to.getY()) {
                    board[i][j] = ConstantAmount.SHIP_ICON;
                }
            }
        }
    }
}