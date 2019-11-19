import java.util.HashMap;

/**
 * @author Courtney Dixon
 * @author Cameron Small
 */

public class GameBoard {

    public Tile[][] board;
    public HashMap<String, String> tilePaths;

    public GameBoard() {
        board = new Tile[6][6];
        tilePaths = new HashMap<String, String>();
    }

    /**
     * The neighboringPoint() method takes in a point (i, j, k) and returns the neighboring point (i, j, k) of the next
     * tile.
     *
     * @param ijk A string containing the i, j, and k values (i/j for tile coordinate, k for which point we're dealing
     *            with).
     * @return A new ijk string of the neighboring point.
     */
    public static String neighboringPoint(String ijk) {
        String iSub = ijk.substring(0, 1);
        int i = Integer.parseInt(iSub);

        String jSub = ijk.substring(1, 2);
        int j = Integer.parseInt(jSub);

        String kSub = ijk.substring(2);
        int k = Integer.parseInt(kSub);

        // Based on the provided k, determine new I/J coordinates.
        int neighborI = i;
        int neighborJ = j;
        int neighborK = k;

        // Based on the neighborMap, get corresponding K point.
        int[] neighborMap = {5, 4, 7, 6, 1, 0, 3, 2};

        switch (k) {
            case 0:
            case 1:
                if (0 < j) {
                    neighborJ = j;
                    neighborJ--;
                    neighborK = neighborMap[k];
                }
                break;
            case 2:
            case 3:
                if (5 > i) {
                    neighborI = i;
                    neighborI++;
                    neighborK = neighborMap[k];
                }
                break;
            case 4:
            case 5:
                if (5 > j) {
                    neighborJ = j;
                    neighborJ++;
                    neighborK = neighborMap[k];
                }
                break;
            case 6:
            case 7:
                if (0 < i) {
                    neighborI = i;
                    neighborI--;
                    neighborK = neighborMap[k];
                }
                break;
            default:
        }

        // concatenate all parts (i, j, k) into return string.
        String neighborIJK = Integer.toString(neighborI) + Integer.toString(neighborJ) + Integer.toString(neighborK);

        return neighborIJK;
    }    
    
    /**
     * Helper method to tell if a location is off the board.
     *
     * @param loc the row, column, and point of the location in question
     */
    public boolean perimeter(String loc)
    {
        String[] perim = {"000","001","010","011","020","021","030","031","040","041","050","051",
                          "007","006","107","106","207","206","307","306","407","406","507","506",        
                          "052","053","152","153","252","253","352","353","452","453","552","553",
                          "505","504","515","514","525","524","535","534","545","544","555","554"};
                    
        for(int i = 0; i < perim.length; i++)
        {
            if(perim[i].equals(loc))
            {
                return true;
            }
        }
        return false;
    }
}

