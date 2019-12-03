import java.util.HashMap;

/**
 * @author Courtney Dixon
 * @author Cameron Small
 */

public class GameBoard {

    public Tile[][] board;
    public static HashMap<String, String> tilePaths;

    public GameBoard() {
        board = new Tile[8][8];
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
                if (8 > i) {
                    neighborI = i;
                    neighborI++;
                    neighborK = neighborMap[k];
                }
                break;
            case 4:
            case 5:
                if (8 > j) {
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
        String[] perim = {"110","111","116","117","120","121","130","131",
                          "140","141","150","151","160","161","162","163",
                          "216","217","262","263","316","317","362","363",
                          "416","417","462","463","516","517","562","563",
                          "616","617","614","615","624","625","634","635",
                          "644","645","654","655","662","663","664","665"};
                  
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

            String neighbor = neighboringPoint(from);
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
}

