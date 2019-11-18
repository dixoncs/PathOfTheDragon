/**
 * @author Courtney Dixon
 * @author Cameron Small
 */

public class GameBoard {

    public Tile[][] board;

    public GameBoard() {
        board = new Tile[6][6];
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
}

