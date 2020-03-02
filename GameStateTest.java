import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

/**
 * GameStateTest.java
 * @author Courtney Dixon
 * @version Fall 2019
 */
public class GameStateTest
{
    /**
     * Tests if GameState class exists.
     * @author Courtney Dixon
     */
    @Test
    public void testClassExists()
    {
        Class c;
        try
        {
            c = Class.forName("GameState");
        }
        catch (Exception ex)
        {
            fail("Class name is not correct" + ex);
        }
    }

    /**
     * Tests constructor.
     * @author Courtney Dixon
     */
    @Test
    public void testConstructor()
    {
        assertTrue((new GameState()) instanceof GameState);
    }
    
    /**
     * Tests the perimeter method.
     * @author Courtney Dixon
     */
    @Test
    public void testPerimeter()
    {
        GameBoard gb = new GameBoard();
        String[] testPerim = {"110", "111", "116", "117", "120", "121", "130", "131", 
                              "140", "141", "150", "151", "160", "161", "162", "163", 
                              "216", "217", "262", "263", "316", "317", "362", "363", 
                              "416", "417", "462", "463", "516", "517", "562", "563", 
                              "616", "617", "614", "615", "624", "625", "634", "635", 
                              "644", "645", "654", "655", "662", "663", "664", "665"};
        for(int i = 0; i < testPerim.length; i++)
        {
            if(!gb.perimeter(testPerim[i]))
            {
                fail("Perimeter list fails at " + testPerim[i]);
            }
        }
    }
    
    /*
     * Tests the getNeighbor method.
     * @author Courtney Dixon
     */
    @Test
    public void testGetNeighbor()
    {
        GameBoard gb = new GameBoard();
        String testNeighbors[] = {"014", "112", "115", "117"};   
        String correctNeighbors[] = {"111", "127", "210", "102"};
        for (int i = 0; i < testNeighbors.length; i++)
        {
            if (!gb.getNeighbor(testNeighbors[i]).equals(correctNeighbors[i]))
            {
                fail("Neighbor is not correct!!\t" + testNeighbors[i]);
            }
    
        }
    }

    /*
     * Tests the computeMoves method.
     * @author Courtney Dixon
     *
    @Test
    public void testComputeMoves()
    {
        GameState gs = new GameState();
        moveNumber = 0;
        Vector<String> moves0 = {};

        moveNumber = 1;


        Vector<String> moves2 = {};
    }*/

    /*
     * Tests the legalStartMoves method.
     * @author Courtney Dixon
     */
    @Test
    public void testLegalStartMoves()
    {
        GameState gs = new GameState();
        Vector<String> legalMoves = gs.legalStartPositions();
        Vector<String> testMoves = new Vector<>();
        testMoves.add("014"); testMoves.add("102"); testMoves.add("402"); testMoves.add("710");
        testMoves.add("015"); testMoves.add("103"); testMoves.add("403"); testMoves.add("711");
        testMoves.add("024"); testMoves.add("176"); testMoves.add("476"); testMoves.add("720");
        testMoves.add("025"); testMoves.add("177"); testMoves.add("477"); testMoves.add("721");
        testMoves.add("034"); testMoves.add("202"); testMoves.add("502"); testMoves.add("730");
        testMoves.add("035"); testMoves.add("203"); testMoves.add("503"); testMoves.add("731");
        testMoves.add("044"); testMoves.add("276"); testMoves.add("576"); testMoves.add("740");
        testMoves.add("045"); testMoves.add("277"); testMoves.add("577"); testMoves.add("741");
        testMoves.add("054"); testMoves.add("302"); testMoves.add("602"); testMoves.add("750");
        testMoves.add("055"); testMoves.add("303"); testMoves.add("603"); testMoves.add("751");
        testMoves.add("064"); testMoves.add("376"); testMoves.add("676"); testMoves.add("760");
        testMoves.add("065"); testMoves.add("377"); testMoves.add("677"); testMoves.add("761");    
        for (String m: testMoves)
        {
            if (!legalMoves.contains(m))
            {
                fail("Not a legal start move: " + m);
            }
        }
    }

    /*
     * Tests the moveBoat method.
     * @author Courtney Dixon
     *
    @Test
    public void testMoveBoat()
    {
        GameState gs = new GameState();
        String currentLocation = "014";
        String newLocation = gs.moveBoat(currentLocation);
        //get the tile at 1,1
        Tile testTile = gs.gameBoard.board[1][1];
        //get the tile points and get connection to 4
        int[] testPoints = testTile.getPoints();
        int testK = testPoints[4];
        //put together the string stuff
        String testLocation = "11" + testK;
        if (!newLocation.equals(testLocation))
        {
            fail("New location is incorrect!");
        }    
    }*/

    /*
     * Tests the abstract game clone method.
     * @author Courtney Dixon
     *
    @Test
    public void testAbstractGameClone()
    {
        
    }*/

    /*
     * Tests the game board clone method.
     * @author Courtney Dixon
     *
    @Test
    public void testGameBoardClone()
    {
        
    }*/

    /*
     * Tests the game state clone method.
     * @author Courtney Dixon
     *
    @Test
    public void testGameStateClone()
    {
        
    }*/

    /*
     * Tests the tile clone method.
     * @author Courtney Dixon
     */
    @Test
    public void testTileClone()
    {
        Tile newTile = new Tile();
        Tile copy = newTile.clone();
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos1));
        newTile.printTile();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String s1 = baos1.toString();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos2));
        copy.printTile();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String s2 = baos2.toString();
        if (!(s2.equals(s1)))
        {
            fail("Unexpected output:\n" + s1 + "\n" + s2 + "\n");
        }
    }

    /**
     * Tests .
     */
    //@Test
    //public void test()
    //{
        
    //}

}
