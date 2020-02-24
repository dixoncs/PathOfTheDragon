//import java.util.Stack;
import java.util.Vector;

/**
 * GameState class.
 *
 * @author Mikayla Sage, Courtney Dixon
 * @version 02.03.20
 */
public class GameState extends AbstractGame
{
    public static final int BOARD_DIMENSION = 6;
    public static final int NUM_PLAYERS = 3;
    //there is no third player, it is place holder for nobody from the player enum type abstract class
    //do not change the number of players, there are still two players, you have no friends
    public static final int NUM_HAND_TILES = 3;
    public static final int HUMAN_INDEX = 0;
    public static final int COMPUTER_INDEX = 1;

    public static GameBoard gameBoard;
    public int[][] playerPositions;
    public Tile[][] playerHands;
    public Vector<String> startPositions; 
    public Vector<String> validMoves;

    //HUMAN ALWAYS GOES FIRST

    /**
     * Initilializes playerPositions, values are all equal to 8 to
     * symbolize no current placement.
     */
    public GameState() 
    {
        playerPositions = new int[NUM_PLAYERS][NUM_HAND_TILES];
        for (int i = 0; i < NUM_PLAYERS; i++)
        {
            for (int j = 0; j < NUM_HAND_TILES; j++)
            {
                playerPositions[i][j] = -1;
            }
        }
        playerHands = new Tile[NUM_PLAYERS][NUM_HAND_TILES];
        for (int m = 0; m < NUM_PLAYERS; m++)
        {
            for (int n = 0; n < NUM_HAND_TILES; n++)
            {
                playerHands[m][n] = new Tile();
            }
        }
        gameBoard = new GameBoard();
        
        startPositions = legalStartPositions();
        validMoves = new Vector<>();
        validMoves.add("00");
        validMoves.add("01");
        validMoves.add("02");
        validMoves.add("03");        
        validMoves.add("10");
        validMoves.add("11");
        validMoves.add("12");
        validMoves.add("13"); 
        validMoves.add("20");
        validMoves.add("21");
        validMoves.add("22");
        validMoves.add("23");
    }

    /**
     * Main method.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        GameState game = new GameState();
        //game.initBoard();
        game.play(0);
        game.displayStatus();
        System.out.println(game.nextMover().ordinal());
    }

    /**
     * Print all tiles for a player's hand.
     */
    public void printPlayerHand()
    {
        for (int i = 0; i < NUM_HAND_TILES; i++)
        {
            for (int j = 0; j <= 3; j++)
            {
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
    public void initBoard()
    {
        for (int i = 0; i < BOARD_DIMENSION; i++)
        {
            for (int j = 0; j < BOARD_DIMENSION; j++)
            {
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
    public Vector<String> computeMoves()
    {
        Vector<String> moves = null;
        // if the first or second move of the game
        if (moveNumber == 0 || moveNumber == 1)
        {
            moves = startPositions;
        }
        // all moves after the first two in the game
        else
        {
            /*for (String m : moves)
            {
                System.out.println(m);
            }*/
            moves = validMoves;
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
    public Vector<String> legalStartPositions()
    {
        Vector<String> sMoves = new Vector<>();
        sMoves.add("014"); sMoves.add("102"); sMoves.add("402"); sMoves.add("710");
        sMoves.add("015"); sMoves.add("103"); sMoves.add("403"); sMoves.add("711");
        sMoves.add("024"); sMoves.add("176"); sMoves.add("476"); sMoves.add("720");
        sMoves.add("025"); sMoves.add("177"); sMoves.add("477"); sMoves.add("721");
        sMoves.add("034"); sMoves.add("202"); sMoves.add("502"); sMoves.add("730");
        sMoves.add("035"); sMoves.add("203"); sMoves.add("503"); sMoves.add("731");
        sMoves.add("044"); sMoves.add("276"); sMoves.add("576"); sMoves.add("740");
        sMoves.add("045"); sMoves.add("277"); sMoves.add("577"); sMoves.add("741");
        sMoves.add("054"); sMoves.add("302"); sMoves.add("602"); sMoves.add("750");
        sMoves.add("055"); sMoves.add("303"); sMoves.add("603"); sMoves.add("751");
        sMoves.add("064"); sMoves.add("376"); sMoves.add("676"); sMoves.add("760");
        sMoves.add("065"); sMoves.add("377"); sMoves.add("677"); sMoves.add("761");
        /*for (String m : sMoves)
        {
            System.out.println(m);
        }*/ 
        return sMoves;
    }


    /**
     * shows the current status of the game to the user (perhaps by printing to
     * the screen).
     *
     * @author Mikayla Sage
     * @version 10.30.19
     */
    public void displayStatus()
    {
        for (int i = 0; i < BOARD_DIMENSION; i++)
        {
            for (int j = 0; j < BOARD_DIMENSION; j++)
            {
                //System.out.println("[" + i + "," + j + "]");
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
    public double evaluate()
    {
        return 0;
    }

    /**
     * Determines if the game is over.
     *
     * @return True, if the game is over.
     */
    public boolean isGameOver()
    {
        String humanPos = Integer.toString(playerPositions[0][0]);
        String compPos = Integer.toString(playerPositions[1][0]);
        humanPos += Integer.toString(playerPositions[0][1]);
        humanPos += Integer.toString(playerPositions[0][2]);
        compPos += Integer.toString(playerPositions[1][1]);
        compPos += Integer.toString(playerPositions[1][2]);
        
        if (gameBoard.perimeter(humanPos) || gameBoard.perimeter(compPos)
            && (moveNumber != 0 || moveNumber != 1))
        {
            return true;
        }
        return false;
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
        System.out.println(move);
        return computeMoves().contains(move);
    }

    /**
     * Takes the game action described by the move string. The move must be
     * valid.
     *
     * @param move
     *      The string representation of the move.
     */
    public void makeMove(String move)
    {
        if (isLegal(move))
        {
            int player = nextMover().ordinal();
            if (moveNumber < 2) 
            {
                //System.out.println(player);
                playerPositions[player][0] = Integer.parseInt(String.valueOf(move.charAt(0)));
                playerPositions[player][1] = Integer.parseInt(String.valueOf(move.charAt(1)));
                playerPositions[player][2] = Integer.parseInt(String.valueOf(move.charAt(2)));
                if(startPositions.contains(move))
                {
                    startPositions.remove(move);
                }
                else
                {
                    throw new RuntimeException("Did not enter a legal start position");
                }
                return;
            }
                
            int tileIndex = Integer.parseInt(String.valueOf(move.charAt(0)));
            int numRotations = Integer.parseInt(String.valueOf(move.charAt(1)));
            Tile tile;

            tile = playerHands[player][tileIndex];
            tile.rotateTile(numRotations);
            playerHands[player][tileIndex] = new Tile();
            int[] newTileLocation = new int[2];
            
            String tilePlacement = gameboard.getNeighbor("" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2]);
            
            newTileLocation[0] = Integer.parseInt(String.valueOf(tilePlacement.charAt(0)));
            newTileLocation[1] = Integer.parseInt(String.valueOf(tilePlacement.charAt(1)));
            
            gameBoard.board[newTileLocation[0]][newTileLocation[1]] = tile;
            
            String currLocation = "" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2];
            
            String newPlayerPosition = moveBoat(currLocation);
            
            playerPositions[player][0] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(0)));
            playerPositions[player][1] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(1)));
            playerPositions[player][2] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(2)));
            
            //if (playerPositions[player][2] == 0 || playerPositions[player][2] == 1) 
            //{
            //    newTileLocation[0] = playerPositions[player][0] - 1;
            //    newTileLocation[1] = playerPositions[player][1];
            //}
            //else if (playerPositions[player][2] == 2 || playerPositions[player][2] == 3) 
            //{
            //    newTileLocation[0] = playerPositions[player][0];
            //    newTileLocation[1] = playerPositions[player][1] + 1;
            //}
            //else if (playerPositions[player][2] == 4 || playerPositions[player][2] == 5) 
            //{
            //    newTileLocation[0] = playerPositions[player][0] + 1;
            //    newTileLocation[1] = playerPositions[player][1];
            //}
            //else 
            //{
            //    newTileLocation[0] = playerPositions[player][0];
            //    newTileLocation[1] = playerPositions[player][1] - 1;
            //}
          
            //GameBoard.updatePaths(newTileLocation[0], newTileLocation[1], tile);

            //updatePlayerPositions();

            // get current ijk of player. Check if it's a key in the hashtable
            //String playerPosition = "";
            //playerPosition += playerPositions[player][0]; 
            //playerPosition += playerPositions[player][1];
            //playerPosition += playerPositions[player][2];

            // while the current player position has a value,
            // it's mapped to get that value and keep looking.
            //while (GameBoard.tilePaths.containsKey(playerPosition))
            //{
            //    playerPosition = GameBoard.tilePaths.get(playerPosition);
            //}

            // update playerPositions array with point gotten from hashtable
            //playerPositions[player][0] = playerPosition.charAt(0);
            //playerPositions[player][1] = playerPosition.charAt(1);
            //playerPositions[player][2] = playerPosition.charAt(2);
        }
        
        public String moveBoat(String currentLocation)
        {
            String neighborLoc = gameboard.getNeighbor(currentLocation)
            //Check Tile Paths
            Tile tile = gameboard.board[Integer.parseInt(String.valueOf(neighborLoc.charAt(0)))][Integer.parseInt(String.valueOf(neighborLoc.charAt(1)))];
            int currentK = Integer.parseInt(String.valueOf(neighborLoc.charAt(2)));
            int[] tilePoints = tile.getPoints();
            int newK = tilePoints[currentK];
            String newPlayerLocation = "" + neighborLoc.charAt(0) + neighborLoc.charAt(1) + newK;
            return newPlayerLocation;
        }
        
    }    
}


