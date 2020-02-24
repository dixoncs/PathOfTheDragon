import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * GameStateTest.java
 * @author Courtney Dixon, Mikayla Sage, Diana Martinez, Cameron Small
 * @version Fall 2019
 */
public class GameStateTest
{
    /**
     * Tests if GameState class exists
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
     */
    @Test
    public void testConstructor()
    {
        assertTrue((new GameState()) instanceof GameState);
    }
    
    /**
     * Tests the perimeter method.
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

    /**
     * Tests .
     */
    //@Test
    //public void test()
    //{
        
    //}

}
