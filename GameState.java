//import java.util.Stack;
import java.util.Vector;
import java.util.Collections;

/**
 * GameState class.
 *
 * @author Mikayla Sage, Courtney Dixon, Diana Martinez, Emma Allen
 * @version 02.03.20
 */
public class GameState extends AbstractGame implements Cloneable
{
    public static final char HUMAN_CHAR = '@';   //'\u263A';
    public static final char COMPUTER_CHAR = '#';   //'\u2639';
    public static final int BOARD_DIMENSION = 6;
    public static final int NUM_PLAYERS = 3;
    //there is no third player, it is place holder for nobody from the player enum type abstract class
    //do not change the number of players, there are still two players, you have no friends
    public static final int NUM_HAND_TILES = 3;
    public static final int HUMAN_INDEX = 0;
    public static final int COMPUTER_INDEX = 2;
    public static final int NOONE_INDEX = 1;

    public GameBoard gameBoard;
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
        // GameState game = new GameState();
        // game.initBoard();
        // game.play(0);
	    AbstractGame.repeatPlay("GameState", 1);
    }

    /**
     * Print all tiles for a player's hand.
     */
    public void printPlayerHand(int player)
    {
        if ( player == 0)
	    {
	        System.out.println("\nHuman Hand");
	    }
	    else if ( player == 2)
	    {
	        System.out.println("\nComputer Hand");
        }
        for (int i = 0; i < NUM_HAND_TILES; i++)
        {
            for (int y = 0; y <= 3; y++)
            {
                 System.out.print("[" + i);
                 System.out.print(y + "] ");
            }
            System.out.println();
            String[] result = playerHands[player][i].getRows();
            for (int j = 1; j < 4; j++)
            {
                String[] nextResult = playerHands[player][i].rotateTile(j).getRows();
                for (int k = 0; k < result.length; k++)
                {
                    result[k] += " " + nextResult[k];
                }
                //String[] empty = {"", "", "", ""};
                //playerHands[player][i].rotateTile(j).printTile();
            }
	        System.out.println(String.join("\n", result));
        }
	    System.out.println();
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
        //System.out.println("Move number: " + moveNumber);
        // if the first or second move of the game
        if (moveNumber < 2)
        {
            moves = (Vector<String>)startPositions.clone();
        }
        // all moves after the first two in the game
        else
        {
            /*for (String m : moves)
            {
                System.out.println(m);
            }*/
            moves = (Vector<String>)validMoves.clone();
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
        Collections.reverse(sMoves);       
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
        
        System.out.println("COMPUTER:");
        System.out.println("Current position: [" + playerPositions[COMPUTER_INDEX][0] + playerPositions[COMPUTER_INDEX][1]
            + playerPositions[COMPUTER_INDEX][2] + "]");
        printPlayerHand(COMPUTER_INDEX);
        for (int a = 0; a < 8; a++)
        {
            for (int b = 0; b < 4; b++)
            {
                System.out.print("-");
            }
            System.out.print(" ");
        }
        System.out.println();
        System.out.println(gameBoard);
        /*for (int a = 0; a < 8; a++)
        {
            for (int b = 0; b < 4; b++)
            {
                System.out.print(" " + "\u035E");
            }
            System.out.print(" ");
       }                                                                                                 
        System.out.println();*/
	
	System.out.println("HUMAN:");
        System.out.println("Current position: [" + playerPositions[HUMAN_INDEX][0] + playerPositions[HUMAN_INDEX][1] 
            + playerPositions[HUMAN_INDEX][2] + "]");
        printPlayerHand(HUMAN_INDEX);
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
        String humanPos = Integer.toString(playerPositions[HUMAN_INDEX][0]);
        String compPos = Integer.toString(playerPositions[COMPUTER_INDEX][0]);
        humanPos += Integer.toString(playerPositions[HUMAN_INDEX][1]);
        humanPos += Integer.toString(playerPositions[HUMAN_INDEX][2]);
        compPos += Integer.toString(playerPositions[COMPUTER_INDEX][1]);
        compPos += Integer.toString(playerPositions[COMPUTER_INDEX][2]);
        if (gameBoard.perimeter(humanPos) && !gameBoard.perimeter(compPos))
        {
            //return -1;
            return 1;
        }
        else if (!gameBoard.perimeter(humanPos) && gameBoard.perimeter(compPos))
        {
            //return 1;
            return -1;
        }
        return 0;
    }

    /**
     * Determines if the game is over.
     *
     * @return True, if the game is over.
     */
    public boolean isGameOver()
    {
        String humanPos = Integer.toString(playerPositions[HUMAN_INDEX][0]);
        String compPos = Integer.toString(playerPositions[COMPUTER_INDEX][0]);
        humanPos += Integer.toString(playerPositions[HUMAN_INDEX][1]);
        humanPos += Integer.toString(playerPositions[HUMAN_INDEX][2]);
        compPos += Integer.toString(playerPositions[COMPUTER_INDEX][1]);
        compPos += Integer.toString(playerPositions[COMPUTER_INDEX][2]);
        
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
        //System.out.println(move);
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
        //System.out.println(nextMover().ordinal());
        //System.out.println("move --> " + move);
        //move = forceMoves();
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
            tile = tile.rotateTile(numRotations);
            playerHands[player][tileIndex] = new Tile();
            int[] newTileLocation = new int[2];          

            String tilePlacement = gameBoard.getNeighbor("" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2]);
            //System.out.println("'" + tilePlacement + "'");
            //System.out.println("Here"); 
            //System.out.println(tilePlacement.charAt(0));
            //System.out.println(tilePlacement.charAt(1)); 
            newTileLocation[0] = Integer.parseInt(String.valueOf(tilePlacement.charAt(0)));
            newTileLocation[1] = Integer.parseInt(String.valueOf(tilePlacement.charAt(1)));
            
            gameBoard.board[newTileLocation[0]][newTileLocation[1]] = tile;
            
            // String currLocation = "" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2];
            
            if (gameBoard.board[playerPositions[HUMAN_INDEX][0]][playerPositions[HUMAN_INDEX][1]] != null)
            {
                gameBoard.board[playerPositions[HUMAN_INDEX][0]][playerPositions[HUMAN_INDEX][1]].tileToChars();
            }
            moveBoat(HUMAN_INDEX);
            if (gameBoard.board[playerPositions[COMPUTER_INDEX][0]][playerPositions[COMPUTER_INDEX][1]] != null)
            {
                gameBoard.board[playerPositions[COMPUTER_INDEX][0]][playerPositions[COMPUTER_INDEX][1]].tileToChars();
            }
	        moveBoat(COMPUTER_INDEX);
           
            if (gameBoard.board[playerPositions[HUMAN_INDEX][0]][playerPositions[HUMAN_INDEX][1]] != null)
            {
                gameBoard.board[playerPositions[HUMAN_INDEX][0]][playerPositions[HUMAN_INDEX][1]].setBoatLocation(playerPositions[HUMAN_INDEX][2], HUMAN_CHAR);
            }
            if (gameBoard.board[playerPositions[COMPUTER_INDEX][0]][playerPositions[COMPUTER_INDEX][1]] != null)
            {
                gameBoard.board[playerPositions[COMPUTER_INDEX][0]][playerPositions[COMPUTER_INDEX][1]].setBoatLocation(playerPositions[COMPUTER_INDEX][2], COMPUTER_CHAR);
            }
        }
        
        //System.out.println("Player position " + playerPositions[nextMover().ordinal()][0] + playerPositions[nextMover().ordinal()][1] + playerPositions[nextMover().ordinal()][2]);
    }

    public void moveBoat(int player)
    {
        //for (int player = 0; player <= 2; player += 2)
	    //{
            // Gets the current location of the player as a string
            String currentLocation = "" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2]; 
            // Gets the neighboring point of the player's current location as a string
            String neighborLoc = gameBoard.getNeighbor(currentLocation);
            int row = Integer.parseInt(String.valueOf(neighborLoc.charAt(0)));
            int column = Integer.parseInt(String.valueOf(neighborLoc.charAt(1)));
            // Uses the row and column from the neighbor location to index into the 2-D array, gameBoard.board, of type Tile to get the adjacent Tile 
            Tile tile = gameBoard.board[Integer.parseInt(String.valueOf(neighborLoc.charAt(0)))][Integer.parseInt(String.valueOf(neighborLoc.charAt(1)))];
            //Tile tile = gameBoard.board[row][column];
            // Gets the point on the adjavcent Tile that our boat would move to
            int currentK = Integer.parseInt(String.valueOf(neighborLoc.charAt(2)));
            // if there is no Tile there, then we cannot move across it, so if there is a Tile there
            if (tile != null)
            {
                // Get the points of the Tile to determine paths across a singular Tile 
                int[] tilePoints = tile.getPoints();
                // Determine what new k on the Tile we must move our boat
                int newK = tilePoints[currentK];
                // make new player location string
                String newPlayerPosition = "" + neighborLoc.charAt(0) + neighborLoc.charAt(1) + newK;
                // Set the players new position
                playerPositions[player][0] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(0)));
                playerPositions[player][1] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(1)));
                playerPositions[player][2] = Integer.parseInt(String.valueOf(newPlayerPosition.charAt(2)));
                //System.out.println("The new location of " + player + " is  " + newPlayerPosition);
                moveBoat(player);
            }
            String currentLocationEnd = "" + playerPositions[player][0] + playerPositions[player][1] + playerPositions[player][2];
            //System.out.println("The final location of " + player + " is  " + currentLocationEnd);
        //}
    }
        
    /*
    public String moveBoat(String currentLocation)
    {
        String neighborLoc = gameBoard.getNeighbor(currentLocation);
        //Check Tile Paths
        Tile tile = gameBoard.board[Integer.parseInt(String.valueOf(neighborLoc.charAt(0)))][Integer.parseInt(String.valueOf(neighborLoc.charAt(1)))];
        int currentK = Integer.parseInt(String.valueOf(neighborLoc.charAt(2)));
        int[] tilePoints = tile.getPoints();
        int newK = tilePoints[currentK];
        String newPlayerLocation = "" + neighborLoc.charAt(0) + neighborLoc.charAt(1) + newK;
        return newPlayerLocation;
    }
    */
    
    
    /*@SuppressWarnings("unchecked")
    public GameState clone()
    {
        GameState gameStateCopy;
        try
        {
                gameStateCopy = (GameState) super.clone();
        }
        catch (CloneNotSupportedException e)
        {   
            e.printStackTrace();
            throw new RuntimeException("Booooooooo!!!");
        }
        gameStateCopy.playerHands = deepCopy1(playerHands);
        gameStateCopy.playerPositions = deepCopy(playerPositions);
        gameStateCopy.startPositions = (Vector<String>) startPositions.clone();
        gameStateCopy.validMoves = (Vector<String>) validMoves.clone();
        gameStateCopy.gameBoard = gameBoard.clone();
       
        
        return gameStateCopy;
        
    }*/
	
	@SuppressWarnings("unchecked")
    public GameState clone()
    {
        GameState gameStateCopy;
        gameStateCopy = (GameState) super.clone();
        
        gameStateCopy.playerHands = deepCopy1(playerHands);
        gameStateCopy.playerPositions = deepCopy(playerPositions);
        gameStateCopy.startPositions = (Vector<String>) startPositions.clone();
        gameStateCopy.validMoves = (Vector<String>) validMoves.clone();
        gameStateCopy.gameBoard = gameBoard.clone();
       
        
        return gameStateCopy;
        
    }

    public static int[][] deepCopy(int[][] playerPositions) {
        if (playerPositions == null) {
            return null;
        }

        int[][] playerPositionsCopy = new int[playerPositions.length][playerPositions[0].length];
        for (int i = 0; i < playerPositions.length; i++) 
        {
           for (int j = 0; j < playerPositions[i].length; j++)
           {
               playerPositionsCopy[i][j] = playerPositions[i][j];
           }
       }
        
        return playerPositionsCopy;
    }


    public static Tile[][] deepCopy1(Tile[][] playerHands)
    {
        if (playerHands == null)
        {
            return null;
        }

        Tile[][] playerHandsCopy = new Tile[playerHands.length][];
        for (int i = 0; i < playerHands.length; i++) 
        {
            playerHandsCopy[i] = new Tile[playerHands[i].length];
            
            for (int j = 0; j < playerHandsCopy[i].length; j++)
            {
                if (playerHands[i][j] == null)
                {
                    playerHandsCopy[i][j] = null;
                }
                else 
                {
                    Tile tile = playerHands[i][j];
                    playerHandsCopy[i][j] = tile.clone();
                }
            }
        }
        
        return playerHandsCopy;
    }
    
    /**
     * This method was for testing purposes for the case when boats must cross multiple tiles in one turn
     * 13.4.2020
     */    
    public String forceMoves()
    {
        if (moveNumber == 0)
        {
            return "202";
        }
        else if (moveNumber == 1)
        {
            return "015";
        }
        else if (moveNumber == 2)
        {
            return "00";
        }
        else if (moveNumber == 3)
        {
            return "00";
        }
        return "";
    }
}


