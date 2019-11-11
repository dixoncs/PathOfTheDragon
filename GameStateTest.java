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
     * Tests .
     */
    //@Test
    //public void test()
    //{
        
    //}

}
