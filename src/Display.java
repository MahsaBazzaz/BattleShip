/**
 * the display class
 *
 * @author Mahsa Bazzaz 96314050
 * @version 0.0
 */
class Display {
    //for the colors of console
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RESET = "\u001B[0m";


    char[][] board, board2;

    /**
     * the constructor
     *
     * @param b1 first board
     * @param b2 second board
     */
    public Display(Board b1, Board b2) {
        this.board = b1.getBoard();
        this.board2 = b2.getBoard();
        //displaying the deader
        for (int i = -1; i < (ConstantAmount.BOARD_SIZE * 2) + 2; i++) {
            if (i == -1) {
                System.out.print("\t");
            } else if (i > -1 && i < 10) {
                System.out.print(ConstantAmount.BOARD_LETTERS[i] + "\t");
            } else if (i == 10) {
                System.out.print("\t\t\t");
            } else if (i > 10 && i < 21)
                System.out.print(ConstantAmount.BOARD_LETTERS[i - 11] + "\t");

        }
        System.out.println();
        //displaying the boards
        int c = 0;
        //Moving in the rows
        for (int i = 0; i <= (ConstantAmount.BOARD_SIZE * 2); i++) {
            //Moving in the columns
            for (int j = -1; j <= (ConstantAmount.BOARD_SIZE * 2) + 1; j++) {
                if (i % 2 == 0) {
                    if (j == -1 || j == 11) {
                        System.out.print("--|");
                    } else if (j == 10) {
                        System.out.print("\t\t\t");
                    } else {
                        System.out.print("---+");
                    }
                } else if (i % 2 != 0 && c < 10) {
                    if (j == -1) {
                        System.out.print(c + " |");
                    }
                    //////// displaying the player's board
                    else if (j > -1 && j < 10) {
                        if (board[c][j] == ConstantAmount.SHIP_ICON) { //@
                            System.out.print(" " + ANSI_BLUE_BACKGROUND + board[c][j] + ANSI_RESET + " |");
                        } else if (board[c][j] == ConstantAmount.SHIP_IS_HIT_ICON) { //#
                            System.out.print(" " + ANSI_RED_BACKGROUND + board[c][j] + ANSI_RESET + " |");
                        } else if (board[c][j] == ConstantAmount.SHOT_MISSED_ICON) {
                            System.out.print(" " + ConstantAmount.BOARD_ICON + " |"); //X
                        } else if (board[c][j] == ConstantAmount.BOARD_ICON) {
                            System.out.print(" " + board[c][j] + " |");
                        }
                    } else if (j == 10) {
                        System.out.print("\t\t\t" + (c) + " |");
                    }
                    //displaying the opponent's board
                    else if (j > 10 && j < 21) {
                        if (board2[c][j - 11] == ConstantAmount.SHIP_ICON) { //@
                            System.out.print(" " + ConstantAmount.BOARD_ICON + " |");
                        } else if (board2[c][j - 11] == ConstantAmount.SHIP_IS_HIT_ICON) {
                            System.out.print(" " + ANSI_GREEN_BACKGROUND + ConstantAmount.SHOT_HIT_ICON + ANSI_RESET + " |"); //&
                        } else if (board2[c][j - 11] == ConstantAmount.SHOT_MISSED_ICON) {
                            System.out.print(" " + ANSI_YELLOW_BACKGROUND + board2[c][j - 11] + ANSI_RESET + " |"); //X
                        } else if (board2[c][j - 11] == ConstantAmount.BOARD_ICON) {
                            System.out.print(" " + board2[c][j - 11] + " |");
                        }
                    }
                }
            }
            if (i % 2 != 0) c++;
            System.out.println();
        }
    }

}
