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
    public static final int NUM_PLAYERS = 2;
    public static final int NUM_HAND_TILES = 3;
    public static final int HUMAN_INDEX = 0;
    public static final int COMPUTER_INDEX = 1;

    public static GameBoard gameBoard;
    public int[][] playerPositions;
    public Tile[][] playerHands;

    //HUMAN ALWAYS GOES FIRST

    /**
     * Initilializes playerPositions, values are all equal to 8 to
     * symbolize no current placement.
     */
    public GameState() {
        playerPositions = new int[NUM_PLAYERS][NUM_HAND_TILES];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            for (int j = 0; j < NUM_HAND_TILES; j++) {
                playerPositions[i][j] = 8;
            }
        }
        playerHands = new Tile[NUM_PLAYERS][NUM_HAND_TILES];
        gameBoard = new GameBoard();
    }

    /**
     * Main method.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        GameState game = new GameState();
        //game.initBoard();
        game.play(0);
        game.displayStatus();
        System.out.println(game.nextMover().ordinal());
    }

    /**
     * Print all tiles for a player's hand
     */
    public void printPlayerHand() {
        for (int i = 0; i < NUM_HAND_TILES; i++) {
            for (int j = 0; j <= 3; j++) {
                System.out.print("[" + i);
                System.out.println(j + "]");
                playerHands[nextMover().ordinal()][i].rotateTile(j).printTile();
            }
        }
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
     *  @author Courtney Dixon
     *  @return Vector that contains all the legal moves that can currently be
     *      made in the game
     */
    public Vector<String> computeMoves() {
        Vector<String> moves = new Vector<>();
        moves.add("00");
        moves.add("01");
        moves.add("02");
        moves.add("03");        
        moves.add("10");
        moves.add("11");
        moves.add("12");
        moves.add("13"); 
        moves.add("20");
        moves.add("21");
        moves.add("22");
        moves.add("23");
        if (moveNumber == 0 || moveNumber == 1)
        {
            return gameBoard.legalStartPositions();
        }
        return moves;
    }

    /**
     *  Gets a Vector that contains all the legal starting positions that can 
     *  currently be made for the first and second moves of the game. It is 
     *  called only if the game is in the first two moves. If it is the second
     *  move, then the vector no longer has the position chosen in move number
     *  one. These moves are all Java strings.
     *  
     *  @author Courtney Dixon
     *  @return sMoves A vector that contains all the legal starting positions
     */
    public Vector<String> legalStartPositions() {
        Vector<String> sMoves = new Vector<>();
        sMoves.add("");
        sMoves.add("");
        sMoves.add("");
        sMoves.add("");        
        sMoves.add("");
        sMoves.add("");
        sMoves.add("");
        sMoves.add(""); 
        sMoves.add("");
        sMoves.add("");
        sMoves.add("");
        sMoves.add("");
        return sMoves;
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
                if (gameBoard.board[i][j] != null)
                {
                    gameBoard.board[i][j].printTile();
                }
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
       if(playerPositions[0][0] == -1 || playerPositions[1][0] == -1)
       {
           return true;
       }
       else {
           return false;
       }
    }

    /**
     * Determines if the move is legal.
     *
     * @param move
     *      The string representation of the move.
     * @return True, if the move is legal.
     */
    public boolean isLegal(String move)
    {
        if(move.length() == 2) 
        {
            if((move.charAt(0) == '0' || move.charAt(0) == '1' || move.charAt(0) == '2') 
                && (move.charAt(1) == '0' || move.charAt(1) == '1' || move.charAt(1) == '2'
                || move.charAt(1) == '3'))
            {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Takes the game action described by the move string. The move must be
     * valid.
     *
     * @param move
     *      The string representation of the move.
     */
    public void makeMove(String move) {
        if (isLegal(move)) {
            int k = Integer.parseInt(String.valueOf(move.charAt(0)));
            int r = Integer.parseInt(String.valueOf(move.charAt(1)));
            Tile t;
            Player p = nextMover();
            int index;
            
            if (p == Player.human) {
                index = HUMAN_INDEX;
            }
            else {
                index = COMPUTER_INDEX;
            }

            t = playerHands[index][k];
            t.rotateTile(r);
            playerHands[index][k] = new Tile();
            
	        int[] loc = new int[2];

            if (playerPositions[index][2] == 0 || playerPositions[index][2] == 1) {
                loc[0] = playerPositions[index][0] - 1;
                loc[1] = playerPositions[index][1];
            }
            else if (playerPositions[index][2] == 2 || playerPositions[index][2] == 3) {
                loc[0] = playerPositions[index][0];
                loc[1] = playerPositions[index][1] + 1;
            }
            else if (playerPositions[index][2] == 4 || playerPositions[index][2] == 5) {
                loc[0] = playerPositions[index][0] + 1;
                loc[1] = playerPositions[index][1];
            }
            else {
                loc[0] = playerPositions[index][0];
                loc[1] = playerPositions[index][1] - 1;
            }

            gameBoard.board[loc[0]][loc[1]] = t;
        }
    }      
}


