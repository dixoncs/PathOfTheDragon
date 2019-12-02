import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * @author Michael Main
 * @version ???
 * 
 */
public abstract class AbstractGame implements Cloneable
{
    /**
     * THREE PRIVATE VARIABLES and THREE PRIVATE METHODS.
     */
    private static Scanner stdin = new Scanner(System.in);
    public int moveNumber = 0;

    /**
     * THREE CONSTANTS (for a possible game outcome).
     * 
     * @author Michael Main
     * @version ???
     */
    public enum Player
    {
        human, nobody, computer
    };

    /**
     * No-args construtor reads input from standard input.
     */
    public AbstractGame()
    {
        stdin = new Scanner(System.in);
    }

    /**
     * The play method plays one game, with the human player moving first and
     * the computer second. The computer uses an alpha-beta look ahead algorithm
     * to select its moves. If play is called a second time, then it displays
     * the final status of the game that was already played and returns its
     * winner.
     * 
     * @param depth
     *            How far ahead the computer looks to make a move.
     * @return the winner of the game (or Player.nobody for a tie)
     */
    public final Player play(int depth)
    {
        while (!isGameOver())
        {
            displayStatus();
            if (nextMover() == Player.human)
            {
                makeHumanMove();
            }
            else
            {
                makeComputerMove(depth);
            }
            moveNumber++;
        }
        displayStatus();
        return winning();
    }

    /**
     * * Play one round of repeatPlay.
     * 
     * @param myConstructor
     *            The constructor for the concrete game class.
     * @param depth
     *            The number of moves to look ahead for the computer player.
     * @return The point won by the human (0 for loss, 1 for tie, 2 for win).
     * @throws IllegalAccessException
     *             if it can not create a new instance
     * @throws InvocationTargetException
     *             if it can not create a new instance of the concrete game
     *             class.
     * @throws InstantiationException
     *             if it can not create a new instance of the concrete game
     *             class.
     */
    private static final int playRound(Constructor<?> myConstructor, int depth)
        throws IllegalAccessException, InvocationTargetException,
        InstantiationException
    {
        AbstractGame round =
                (AbstractGame) myConstructor.newInstance(new Object[] {});
        int score = 0;
        switch (round.play(depth))
        {
            case human:
                System.out.println("You win");
                score = 2;
                break;
            case nobody:
                System.out.println("A draw");
                score = 1;
                break;
            case computer:
                System.out.println("I win");
                break;
            default:
                System.out.println("Unknown player won.");
        }
        return score;
    }

    /**
     * Plays the game multiple times and keeps an ongoing score.
     * 
     * @param name
     *            The name of the class that extends this AbstractGame class.
     * @param depth
     *            How far ahead the computer looks to make a move.
     * @return The overall score for the human player in the games.
     */
    public static final double repeatPlay(String name, int depth)
    {
        Class<?> myClass;
        Constructor<?> myConstructor;

        // 2 for a win; 1 for a tie.
        int humanPoints = 0;
        int gamesPlayed = 0;
        String a;

        try
        {
            myClass = java.lang.Class.forName(name);
            myConstructor = myClass.getConstructor(new Class[] {});

            do
            {
                humanPoints += playRound(myConstructor, depth);
                gamesPlayed++;
                System.out.print("Do you want to play again? Y/N: ");
                a = stdin.nextLine();
            } while (a.length() > 0 && a.charAt(0) == 'Y'
                    || a.charAt(0) == 'y');
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Could not play " + name);
        }
        return humanPoints / gamesPlayed;
    }

    /**
     * FOUR FINAL PROTECTED FUNCTIONS (for extended classes to use if they
     * wish): The movesCompleted method returns the number of moves done so far.
     * 
     * @return the number of moves completed.
     */
    public final int movesCompleted()
    {
        return moveNumber;
    }

    /**
     * The nextMover method returns the next mover (human or computer).
     * 
     * @return The next player to move.
     */
    public final Player nextMover()
    {
        return (moveNumber % 2 == 0 ? Player.human : Player.computer);
    }

    /**
     * FIVE METHODS THAT MAY BE OVERRIDDEN BY AN EXTENDED CLASS: The clone
     * method makes a copy of this game. If the extended class uses any arrays
     * or other objects, then this method must be overridden so that the cloned
     * object does not share objects with the original.
     * 
     * @return a copy of this game.
     */
    protected AbstractGame clone()
    {
        AbstractGame answer;

        try
        {
            answer = (AbstractGame) super.clone();
        }
        catch (Exception e)
        {
            throw new RuntimeException(
                    "This class does not implement Cloneable.");
        }
        return answer;
    }

    /**
     * The displayMessage method writes a message to the user. This version just
     * writes that message to the screen, but an extended class may override for
     * a fancier output.
     * 
     * @param message
     *            The message to display.
     */
    public void displayMessage(String message)
    {
        System.out.println(message);
    }

    /**
     * The getUserMove method asks the user to type his or her move and returns
     * the answer as a string. An extended class may override to achieve a
     * fancier interaction.
     * 
     * @return The users move as a string.
     */
    protected String getUserMove()
    {
        System.out.print("Your move, please: ");
        return stdin.nextLine();
    }

    /**
     * The simulateMove method is called to make a lookahead move in the game.
     * 
     * @param move
     *            The move to make.
     */
    public void simulateMove(String move)
    {
        if (!isLegal(move))
        {
            @SuppressWarnings("unused")
            int k = 5;
        }
        if (!isLegal(move))
        {
            throw new IllegalArgumentException("Illegal move: " + move);
        }
        makeMove(move);
        moveNumber++;
    }

    /**
     * The winning method uses a simple technique to figure out who is winning
     * the game (human, computer, or nobody). An extended class may override
     * this method to provide a more sophisticated analysis.
     * 
     * @return The player that is winning.
     */
    public Player winning()
    {
        double value = evaluate();

        // Evaluate returns positive if things favor the computer:
        if (value > 0)
        {
            return Player.computer;
        }
        else if (value < 0)
        {
            return Player.human;
        }
        else
        {
            return Player.nobody;
        }
    }

    // FIVE ABSTRACT METHODS THAT EVERY EXTENDED CLASS MUST PROVIDE:

    /**
     * Gets a Vector that contains all the legal moves that can currently be
     * made in the game. It is called only if the game is not over, so the
     * Vector that it returns must contain at least one move. These moves are
     * all Java strings.
     * 
     * @return Vector that contains all the legal moves that can currently be
     *         made in the game
     */
    protected abstract Vector<String> computeMoves();

    /**
     * shows the current status of the game to the user (perhaps by printing to
     * the screen).
     */
    protected abstract void displayStatus();

    /**
     * The evaluate function evaluates the current status of the game, returning
     * a negative number if the status seems to favor the computer and a
     * positive number for a human advantage. Zero indicates no advantage to
     * either player; values closer to zero indicate smaller advantages than
     * values far away.
     * 
     * @return How much does the game favor the human? Positive numbers favor
     *         the human; negative numbers favor the computer; magnitude
     *         matters.
     */
    protected abstract double evaluate();

    /**
     * Determines if the game is over.
     * 
     * @return True, if the game is over.
     */
    protected abstract boolean isGameOver();

    /**
     * Determines if the move is legal.
     * 
     * @param move
     *            The string representation of the move.
     * @return True, if the move is legal.
     */
    protected abstract boolean isLegal(String move);

    /**
     * Takes the game action described by the move string. The move must be
     * valid.
     * 
     * @param move
     *            The string representation of the move.
     */
    protected abstract void makeMove(String move);

    /**
     * Helper method for evalWithLookahead.
     * 
     * @return The evaluate method from the opponent's perspective.
     */
    private double baseCase()
    {
        if (nextMover() == Player.human)
        {
            return evaluate();
        }
        else
        {
            return -evaluate();
        }
    }

    /**
     * Evaluate current board position with lookahead. If the current board
     * position can't beat this, then cut it short.
     * 
     * @param lookAhead
     *            How deep the lookahead should go to evaluate // the current
     *            board position.
     * @param beatThis
     *            Value of another board position that we're considering.
     * @return The return value is large if the position is good for the player
     *         who just moved.
     */
    private double evalWithLookahead(int lookAhead, double beatThis)
    {
        // All possible opponent moves
        Vector<String> moves;
        // Value of a board position after opponent moves
        double value;
        // Evaluation of best opponent move
        double bestValue;
        // Ref to a future version of this game
        AbstractGame future;

        // Base case:
        if (lookAhead <= 0 || isGameOver())
        {
            return baseCase();
        }

        // Recursive case:
        // The level is above 0, so try all possible opponent moves. Keep the
        // value of the best of these moves from the opponent's perspective.
        moves = computeMoves();
        bestValue = Double.NEGATIVE_INFINITY;
        do
        {
            future = clone();
            future.simulateMove(moves.lastElement());
            value = future.evalWithLookahead(lookAhead - 1, bestValue);
            if (value > bestValue)
            {
                if (-value <= beatThis)
                {
                    // Alpha-beta pruning
                    return Double.NEGATIVE_INFINITY;
                }
                bestValue = value;
            }
            moves.removeElementAt(moves.size() - 1);
        } while (!moves.isEmpty());

        // The value was calculated from the opponent's perspective.
        // The answer we return should be from player's perspective, so multiply
        // times -1:
        return -bestValue;
    }

    /**
     * Makes the computer move using a look ahead of depth.
     * 
     * @param depth
     *            How many moves the computer should look ahead.
     */
    private void makeComputerMove(int depth)
    {
        Vector<String> moves;
        double value;
        double bestValue;
        String bestMove = null;
        AbstractGame future;

        // Compute all legal moves that the computer could make.
        moves = computeMoves();

        // Evaluate each possible legal move, saving its value in bestValue.
        bestValue = Double.NEGATIVE_INFINITY;
        do
        {
            future = clone();
            future.simulateMove(moves.lastElement());
            value = future.evalWithLookahead(depth, bestValue);
            if (value >= bestValue)
            {
                bestValue = value;
                bestMove = moves.lastElement();
            }
            moves.removeElementAt(moves.size() - 1);
        } while (!moves.isEmpty());

        // Make the best move.
        makeMove(bestMove);
    }

    /**
     * Makes the human move.
     */
    private void makeHumanMove()
    {
        String move;

        move = getUserMove();
        while (!isLegal(move))
        {
            displayMessage("Illegal move.\n");
            move = getUserMove();
        }
        makeMove(move);
    }
}
