import java.util.Random;
import java.util.Stack;


/**
 *
 * @author Mikayla Sage, Diana Martinez, Emma Allen, Courtney Dixon
 *
 */
public class Tile implements Cloneable {
    public static Random rand = new Random(12);

    // Fields
    /**
    * Array of integers that express the points/paths for each tile.
    */
    private int[] points;
    private char[] characters;


    /**
     * Default constructor.
     *
     * Calls the shuffleValues() method and returns a list of points/paths.
     */
    public Tile() {
        points = shuffleValues();
        tileToChars();
    }

    /**
     * Parameterized constructor.
     *
     * @param values
     *          An array of integers that indicate chosen points/paths.
     */
    public Tile(int[] values) {
        points = values;
        tileToChars();
    }

    /**
     * The shuffleValues() method creates a random, valid collection of points to instantiate tiles with.
     *
     * @return a random array of ints that represent points/paths for a tile.
     */
    private static int[] shuffleValues() {
        Stack<Integer> possibleValues = new Stack<>();          // create a stack to store possible values in
        for (int i = 0; i < 8; i++) {                           // populate the stack with values 0 - 7
            possibleValues.push(i);
        }

        int[] output = new int[8];                              // create int array for output

        for (int i = 0; i < 4; i++) {
            int a = possibleValues.get(rand.nextInt(possibleValues.size()));    // get random element
            possibleValues.removeElement(a);                                    // remove from list
            int b = possibleValues.get(rand.nextInt(possibleValues.size()));    // get pair element
            possibleValues.removeElement(b);                                    // remove from list
            output[a] = b;                                                      // assign b to a index
            output[b] = a;                                                      // assign a to b index
        }

        return output;
    }

    /**
     * The printTile() method is a basic way to print out a tile in terms of points.
     */
    public void printTile()
    {
        //StringBuilder output = new StringBuilder("[0] [1] [2] [3] [4] [5] [6] [7]\n");
        System.out.print("[ ");
        for (int location : points)
        {
            //output.append(" ").append(location).append("  ");
            System.out.printf("%d ", location);
        }
        //System.out.println(output + "\n");
        System.out.print("]  ");
    }

    /**
     * The rotateTile method allows for a tile to be rotated a number of rotations and a new Tile object to be returned.
     * It will be rotated between 0 and 3 times clockwise.
     *
     * @param cwRotations
     *              Specifies how many times to rotate the tile (0 to 3 times clockwise)
     * @return A new Tile object with the updated orientation.
     * @throws Exception
     *              Thrown if the input (number of rotations) is invalid (below 0 or above 3).
     */
    public Tile rotateTile(int cwRotations) {

        if (cwRotations < 0 || cwRotations > 3) {
            return null;
        }

        int[] legend = new int[8];
        int[] newTile = new int[8];

        switch(cwRotations) {
            case 1: legend = new int[] {2, 3, 4, 5, 6, 7, 0, 1};
                break;
            case 2: legend = new int[] {4, 5, 6, 7, 0, 1, 2, 3};
                break;
            case 3: legend = new int[] {6, 7, 0, 1, 2, 3, 4, 5};
                break;
            default:
                return this;
        }

        for (int i = 0; i < 8; i++) {
            int ogValue = this.getPoints()[i];                  // OG mapping
            int newI = legend[i];                               // new i for rotation
            int newVal = legend[ogValue];
            newTile[newI] = newVal;
        }

        return new Tile(newTile);
    }

    public int[] getPoints() {
        return points;
    }
    
    
     public Tile clone()
    {
        Tile tileCopy;
        
        try
        {
            tileCopy = (Tile) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
            ("Does not implement cloneable.");
        }
         tileCopy.points = points.clone();
         tileCopy.characters = characters.clone();
         return tileCopy;
        
    }
    
    public void tileToChars()
    {
        String possibilities = "abcd";
        int ptr = 0;
        characters = new char[points.length];
        for (int i = 0; i < points.length; i++)
        {
            if (characters[i] == 0)
            {
                characters[i] = characters[points[i]] = possibilities.charAt(ptr);
                ptr++;
            }
        }
    }

    /**
     * 20.4.2020
     */
    public String[] getRows()
    {
        String[] rows = new String[4];
        rows[0] = " " + characters[0] + characters[1] + " ";
        rows[1] = characters[7] + " " + " " + characters[2];
        rows[2] = characters[6] + " " + " " + characters[3];
        rows[3] = " " + characters[5] + characters[4] + " ";
        //System.out.println(rows[0]);
        //System.out.println(rows[1]);
        //System.out.println(rows[2]);
        //System.out.println(rows[3]);
        return rows;
    }

    /**
     * 20.4.2020
     */
    public String toString()
    {
        return String.join("\n", this.getRows());
    }

    /**
     * Sets the current location of a boat to the proper character.
     * @param k point on tile that needs boat character
     * @param c character representing human or computer boat
     */
    public void setBoatLocation(int k, char c)
    {
        characters[k] = c;
    }

    /**
     *
     *
    public String getRow(int r)
    {
        switch (r)
        {
            case 0:
                return " " + characters[0] + characters[1] + " ";
            case 1:
                return characters[7] + " " + " " + characters[2];
            case 2:
                return characters[6] + " " + " " + characters[3];
            case 3:
                return " " + characters[5] + characters[4] + " ";
            default:
                break;
        }
    }*/

}


