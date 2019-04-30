import java.io.IOException;

/**
 * Sean Friedman
 * NID: se972594
 *
 * Ralph Dimayuga
 * NID: Ra597979
 */

/**
 * All five class files must be compiled to run the program:
 * King.java
 * Cell.java
 * Board.java
 * PathOfDestructionExecute.java
 * PathOfDestruction.java
 *
 * Then PathOfDestruction.java can be ran with any input file and produce the expected results.
 */


public class PathOfDestruction {

    public static boolean isValidBoard(String boardFile) throws IOException
    {
        PathOfDestructionExecute execute = new PathOfDestructionExecute();
        return execute.isValidBoard(boardFile);
    }

    public static boolean hasPathOfDestruction(String boardFile) throws IOException
    {
        PathOfDestructionExecute executePath = new PathOfDestructionExecute();
        return executePath.hasPathOfDestruction(boardFile);
    }

    public static void main(String[] args) throws IOException
    {
        if(isValidBoard(args[0]))
        {
            System.out.println("valid board");
        }
        else
        {
            System.out.println("Invalid board");
        }

        if(hasPathOfDestruction(args[0]))
        {
            System.out.println("Path of destruction found");
        }
        else
        {
            System.out.println("No Path Found");
        }
    }
}