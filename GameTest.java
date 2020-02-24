public class GameTest{
    public static GameState gs;
    public static void main(String[] args)
    {
        gs = new GameState();
        gs.initBoard();
        gs.displayStatus();
        gs.playerPositions[0][0] = 1;
        gs.playerPositions[0][1] = 1;
        gs.playerPositions[0][2] = 3;

        for (int i = 0; i < 3; i++) 
        {
            System.out.print(gs.playerPositions[0][i]);
        }

        System.out.println("");
        while (gs.playerPositions[0][0] >= 0) {
            
            updatePlayerPosition(0);
        }
        
    }
    //int player - which player is playing
    public static void updatePlayerPosition(int player)
    {
        int playerI = gs.playerPositions[player][0];
        int playerJ = gs.playerPositions[player][1];
        int currPlayerPoint = gs.playerPositions[player][2];
        String ijk = Integer.toString(playerI) + Integer.toString(playerJ) + Integer.toString(currPlayerPoint);
        String new_ijk = gs.gameBoard.getNeighbor(ijk);
        
        int newPlayerI = Character.getNumericValue(new_ijk.charAt(0));
        int newPlayerJ = Character.getNumericValue(new_ijk.charAt(1));
        int newPlayerK = Character.getNumericValue(new_ijk.charAt(2));

        if (newPlayerI == 0 || newPlayerI == 7 || newPlayerJ == 0 || newPlayerJ == 7) {
            newPlayerI = -1;
            newPlayerJ = -1;
            newPlayerK = -1;
        }
        else {
        
            Tile tile = gs.gameBoard.board[newPlayerI][newPlayerJ];
            int[] points = tile.getPoints();

            newPlayerK = points[newPlayerK];
        }

        gs.playerPositions[player][0] = newPlayerI;
        gs.playerPositions[player][1] = newPlayerJ;
        gs.playerPositions[player][2] = newPlayerK;
        
        for (int i = 0; i < 3; i++) 
        {
            System.out.print(gs.playerPositions[0][i]);
        }

        
        System.out.println("");
    }
}
