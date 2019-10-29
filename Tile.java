import java.util.Random;
import java.util.Stack;

public class Tile {

    // I know I don't need this for the class. It's just to help me test.
    public static void main(String[] args) throws Exception {
        Tile test = new Tile();
        test.printTile();
        test.rotateTile(-3).printTile();
        test.rotateTile(-2).printTile();
        test.rotateTile(-1).printTile();
        test.rotateTile(1).printTile();
        test.rotateTile(2).printTile();
        test.rotateTile(3).printTile();
    }

    // Fields
    private int[] points;

    public Tile() {
        points = shuffleValues();
    }

    public Tile(int[] values) {
        points = values;
    }

    private static int[] shuffleValues() {
        Stack<Integer> possibleValues = new Stack<>();          // create a stack to store possible values in
        for (int i = 0; i < 8; i++) {                           // populate the stack with values 0 - 7
            possibleValues.push(i);
        }

        int[] output = new int[8];                              // create int array for output
        Random rand = new Random();

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

    public void printTile() {
        StringBuilder output = new StringBuilder("[0] [1] [2] [3] [4] [5] [6] [7]\n");
        for (int location : points) {
            output.append(" ").append(location).append("  ");
        }
        System.out.println(output + "\n");
    }

    public Tile rotateTile(int cwRotations) throws Exception {

        if (cwRotations < -3 || cwRotations == 0 || cwRotations > 3) {
            throw new Exception("The cwRotation must be an integer between -3 and 3, and not 0.");
        }

        int[] legend = new int[8];
        int[] newTile = new int[8];

        switch(cwRotations) {
            case -3: legend = new int[] {2, 3, 4, 5, 6, 7, 0, 1};
                break;
            case -2: legend = new int[] {4, 5, 6, 7, 0, 1, 2, 3};
                break;
            case -1: legend = new int[] {6, 7, 0, 1, 2, 3, 4, 5};
                break;
            case 1: legend = new int[] {2, 3, 4, 5, 6, 7, 0, 1};
                    break;
            case 2: legend = new int[] {4, 5, 6, 7, 0, 1, 2, 3};
                    break;
            case 3: legend = new int[] {6, 7, 0, 1, 2, 3, 4, 5};
                    break;
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

}
