import java.util.LinkedList;
import java.util.HashMap;

/**
 * GameBoard class.
 *
 * @author Courtney Dixon
 * @author Diana Martinez
 * @version Spring 2020
 */

public class GameBoard
{

    public Tile[][] board;
    public static HashMap<String, String> tilePaths;
	public LinkedList[] tilePaths1;

	/**
	 * No argument constructor.
	 */
    public GameBoard()
    {
        board = new Tile[8][8];
        tilePaths = new HashMap<String, String>();
		tilePaths1 = new LinkedList[48];
    }

    /**
     * The getNeighbor() method takes in a point (i, j, k)
     * and returns the neighboring point (i, j, k) of the next tile;
     * i = row, j = column, k = position on tile.
     *
     * @param ijk A string containing the i, j, and k values
     * @return the neighboring point.
     */
    public static String getNeighbor(String ijk)
    {
		String neighbor = "";
        int i = Character.getNumericValue(ijk.charAt(0));
        int j = Character.getNumericValue(ijk.charAt(1));
        int k = Character.getNumericValue(ijk.charAt(2));
        int [] neighborPairs = {5, 4, 7, 6, 1, 0, 3, 2};
		switch (k)
        {
			//upper neighbor
			case 0:
			case 1:
				neighbor += Integer.toString(i-1);
            	neighbor += Integer.toString(j);
            	neighbor += Integer.toString(neighborPairs[k]);
        		break;
			 //right neighbor
        	case 2:
			case 3:
            	neighbor += Integer.toString(i);
            	neighbor += Integer.toString(j+1);
            	neighbor += Integer.toString(neighborPairs[k]);
            	break;
		 	//lower neighbor
        	case 4:
			case 5:
            	neighbor += Integer.toString(i+1);
            	neighbor += Integer.toString(j);
            	neighbor += Integer.toString(neighborPairs[k]);
            	break;
			//left neighbor
        	case 6:
			case 7:
            	neighbor += Integer.toString(i);
            	neighbor += Integer.toString(j-1);
            	neighbor += Integer.toString(neighborPairs[k]);
            	break;
			default:
				break;
		}
		return neighbor;
    }    
    
    /**
     * Helper method to tell if a location is off the board.
     *
     * @param loc the row, column, and point of the location in question
     */
    public boolean perimeter(String loc)
    {
        String[] perim = {"110", "111", "116", "117", "120", "121", "130", "131", 
                          "140", "141", "150", "151", "160", "161", "162", "163", 
                          "216", "217", "262", "263", "316", "317", "362", "363", 
                          "416", "417", "462", "463", "516", "517", "562", "563",
                          "616", "617", "614", "615", "624", "625", "634", "635", 
                          "644", "645", "654", "655", "662", "663", "664", "665"};
                  
        for(int i = 0; i < perim.length; i++)
        {
            if(perim[i].equals(loc))
            {
                return true;
            }
        }
        return false;
    }

    public static void updatePaths(int i, int j, Tile t)
    {
        for (int index = 0; index < t.getPoints().length; index++)
        {
            String from = "" + i + j + index;
            String to = "" + i + j + t.getPoints()[index];
            tilePaths.put(from, to);

            String neighbor = getNeighbor(from);
            if (tilePaths.containsKey(neighbor))
            {
                tilePaths.put(from, neighbor);
            }
            else if (tilePaths.containsValue(neighbor))
            {
                tilePaths.put(neighbor, from);
            }
        }
    }

    /*public void updatePaths(String ijk, Tile t)
    {
        int i = Character.getNumericValue(ijk.charAt(0));
        int j = Character.getNumericValue(ijk.charAt(1));
        int k = Character.getNumericValue(ijk.charAt(2));
    }*/
}

