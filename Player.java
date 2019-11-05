/**
 * Player class.
 *
 * @author Courtney Dixoon
 * @version 28.10.2019
 */
public class Player
{
    public int[] boatLocation;
    public Tile[] playerHand;

    /**
     *
     */
    public Player()
    {
        boatLocation = new int[3];
        playerHand = new Tile[3];
    }

    /**
     *
     */
    public Player(int[] loc)
    {
        boatLocation = loc;
        playerHand = new Tile[3];
    }

    /**
     *
     */
    public Player(Tile[] hand)
    {
        boatLocation = new int[3];
        playerHand = hand;
    }

    /**
     *
     */
    public Player(int[] l, Tile[] h)
    {
        boatLocation = l;
        playerHand = h;
    }

    /**
     *
     */
    public int[] getBoatLocation()
    {
        return boatLocation;
    }

    /**
     *
     */
    public Tile[] getPlayerHand()
    {
        return playerHand;
    }

    /**
     *
     */
    public void setGetBoatLocation(int[] b)
    {
        boatLocation = b;
    }
    
    /**
     *
     */
    public void setPlayerHand(Tile[] p)
    {
        playerHand = p;
    }
}
