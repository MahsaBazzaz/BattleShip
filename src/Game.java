import java.security.InvalidParameterException;

/**
 * The game class
 *
 * @author Mahsa Bazzaz 9631405
 * @version 0.0
 */

public class Game {
    private Player[] players;
    private Player p1;
    private Player p2;
    String mode;

    /**
     * constructor of Game.
     */
    public Game(String mode) {
        try {
            if (mode.equals("P")) { //the player mode
                p1 = new Human(1);
                p2 = new Human(2);

            } else if (mode.equals("C")) { //the computer mode
                p1 = new Computer();
                p2 = new Human(2);
            }
            players = new Player[]{p1, p2};
            start();
        } catch (InvalidParameterException e) {
            System.out.println("INVALID!!!");
        }
    }

    /**
     * to Start.
     */
    private void start() {
        int i = 0;
        int j = 1;
        int size = 2;
        Player player = null;

        while (players[0].getLives() > 0 && players[1].getLives() > 0) {
            players[i % size].turnToPlay(players[j % size]);
            player = (players[0].getLives() < players[1].getLives()) ? players[1] : players[0];
            if (!players[i % size].isBonus()) {
                i++;
                j++;
            } else
                players[i % size].setBonus(false);
        }

        System.out.printf("Congrats Player %d, you won!", player.getId());
    }
}