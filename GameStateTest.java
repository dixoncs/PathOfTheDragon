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
        GameState gs = new GameState();
        String[] testPerim = {"000","001","010","011","020","021","030","031","040","041","050","051",
                              "007","006","107","106","207","206","307","306","407","406","507","506",       
                              "052","053","152","153","252","253","352","353","452","453","552","553",
                              "505","504","515","514","525","524","535","534","545","544","555","554"};

        for(int i = 0; i < testPerim.length; i++)
        {
            if(!gs.perimeter(testPerim[i]))
            {
                fail("Perimeter list fails at " + testPerim[i]);
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
