//import java.util.Stack;
import java.util.Vector;

/**
 * GameState class.
 *
 * @author Mikayla Sage
 * @version 10.30.19
 */
public class GameState extends AbstractGame {
    public static final int BOARD_DIMENSION = 6;
    public static final int DISPLAY_DIMENSION = 48;
    public static final int CONVERSION_ROWS = 36;
    public static final int CONVERSION_COLS = 8;
    public static final int NUM_PLAYERS = 2;

    public static GameBoard gameBoard;
    public Player[] players;

    /**
     * Constructor.
     */
    public GameState() {
        players = new Player[2];
        gameBoard = new GameBoard();
    }

    /**
     * Main method.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        GameState game = new GameState();
        game.initBoard();
        game.displayStatus();
    }

    /**
     * Helper method for print testing method to initialize GameBoard 
     * with random tiles.
     */
    public void initBoard() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                gameBoard.board[i][j] = new Tile();
            }
        }
    }


    /**
     *  Gets a Vector that contains all the legal moves that can currently be
     *  made in the game. It is called only if the game is not over, so the
     *  Vector that it returns must contains at least one move. These moves are
     *  all Java strings.
     *
     *  @return Vector that contains all the legal moves that can currently be
     *      made in the game
     */
    public Vector<String> computeMoves() {
        return null;
    }


    /**
     * shows the current status of the game to the user (perhaps by printing to
     * the screen).
     *
     * @author Mikayla Sage
     * @version 10.30.19
     */
    public void displayStatus() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                System.out.println("[" + i + "," + j + "]");
                gameBoard.board[i][j].printTile();
            }
        }

    }

    /**
     * The evaluate function evaluates the current status of the game, returning
     * a negative number if the status seems to favor the computer and a
     * positive number for a human advantage. Zero indicates no advantage to
     * either player; values closer to zero indicate smaller advantages than
     * values far away.
     *
     * @return How much does the game favor the human? Positive numbers favor
     *      the human; negative numbers favor the computer; magnitude
     *      matters.
     */
    public double evaluate() {
        return 0;
    }

    /**
     * Determines if the game is over.
     *
     * @return True, if the game is over.
     */
    public boolean isGameOver() {
        return true;
    }

    /**
     * Determines if the move is legal.
     *
     * @param move
     *      The string representation of the move.
     * @return True, if the move is legal.
     */
    public boolean isLegal(String move) {
        return true;
    }

    /**
     * Takes the game action described by the move string. The move must be
     * valid.
     *
     * @param move
     *      The string representation of the move.
     */
    public void makeMove(String move) {
        
    }
}
