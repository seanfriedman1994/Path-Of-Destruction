import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sean Friedman
 * NID: se972594
 *
 * Ralph Dimayuga
 * NID: Ra597979
 */


public class PathOfDestructionExecute {
    private Board checkerBoard;
    private Board visitedBoard;
    private King king;
    private int numCheckers;

    public PathOfDestructionExecute(){
    }

    /**
     * check if the board is valid (checkers must be on the same color)
     */
    public boolean isValidBoard(String boardFile) throws IOException
    {
        File board = null;
        int[] checker1 = new int[2];
        int xMax, yMax;
        boolean evenFlag;


        //open the input file
        try
        {
            board = new File(boardFile);
        }catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

        //read and parse the file for the board specifications
        Scanner sc = new Scanner(board);

        //x and y board coordinates
        xMax = sc.nextInt();
        yMax = sc.nextInt();

        //the number of checkers on the board
        this.numCheckers = sc.nextInt();

        if (this.numCheckers == 0)
        {
            return true;
        }

        //get x and y values for the first checker
        checker1[0] = sc.nextInt();
        checker1[1] = sc.nextInt();

        if (!isCheckerValid(checker1, xMax, yMax)) {
            return false;
        }

        evenFlag = isEven(checker1);

        //keep track of all the checker coordinates
        this.checkerBoard = new Board(xMax, yMax);

        //input the first checker into the checkers array
        if (this.checkerBoard.getCell(checker1[0], checker1[1]) == null) {
            //System.out.println("cell does not exist on board");
        }
        this.checkerBoard.getCell(checker1[0], checker1[1]).setMarked(true);


        //collect the checkers coordinates and place into checkers array
        for(int i = 1; i < this.numCheckers; i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            //checks if cell is out of bounds, otherwise inputs checker onto board
            if (this.checkerBoard.getCell(x, y) == null)
            {
                return false;
            }
            else
            {
                this.checkerBoard.getCell(x, y).setMarked(true);
            }

            if(!isSameColor(x, y, evenFlag))
            {
                return false;
            }
        }

        sc.close();
        //if these checks all pass, the board is valid
        return true;
    }

    /**
     * checks if a path of destruction is possible on the passed board
     */

    public boolean hasPathOfDestruction(String boardFile) throws IOException
    {
        if(!this.isValidBoard(boardFile))
        {
            return false;
        }

        if(this.numCheckers == 0)
        {
            return true;
        }

        int xMax = this.checkerBoard.getxMax();
        int yMax = this.checkerBoard.getyMax();
        int checkerCounter;




        //create a new visited board and a new king
        this.visitedBoard = new Board(xMax,yMax);
        this.king = new King();

        //this loop keeps track of kings start position
        for(int i = 0; i < checkerBoard.getSize(); i++)
        {
            //reset the visited array and checker counter
            this.visitedBoard.clear();
            checkerCounter = this.numCheckers;

            //check to see if the current cell contains a checker. If it does continue since the king cannot start on
            //a space that already has a checker
            if (checkerBoard.getCell(i).getMarked())
            {
                continue;
            }
            //set the start position and the current position of the king
            this.king.setStartPosition(checkerBoard.getCell(i));
            this.king.setCurrentPosition(checkerBoard.getCell(i));

            //mark the starting cell as visited
            this.visitedBoard.getCell(this.king.getStartPosition()).setMarked(true);

            for(int k = 0; k < numCheckers; k++)
            {
                //create a list of the four diagonal cells to check for checkers
                ArrayList<Cell> cellsToCheck = new ArrayList<>();

                //top left diagonal
                cellsToCheck.add(checkerBoard.getCell(this.king.getCurrentPosition().getxCoordinate() - 1,
                        this.king.getCurrentPosition().getyCoordinate() - 1));
                //top right diagonal
                cellsToCheck.add(checkerBoard.getCell(this.king.getCurrentPosition().getxCoordinate() + 1,
                        this.king.getCurrentPosition().getyCoordinate() - 1));
                //bottom left diagonal
                cellsToCheck.add(checkerBoard.getCell(this.king.getCurrentPosition().getxCoordinate() - 1,
                        this.king.getCurrentPosition().getyCoordinate() + 1));
                //bottom right diagonal
                cellsToCheck.add(checkerBoard.getCell(this.king.getCurrentPosition().getxCoordinate() + 1,
                        this.king.getCurrentPosition().getyCoordinate() + 1));

                //checks the four diagonals for checkers
                for (int j = 0; j < cellsToCheck.size(); j++)
                {
                    Cell currentCell = cellsToCheck.get(j);

                    //check that the cell is in the bounds of the board. If not go to the next
                    if (currentCell == null)
                    {
                        continue;
                    }

                    //if the diagonal has a checker and has not been visited already
                    if (checkerBoard.getCell(currentCell).getMarked() &&
                            !visitedBoard.getCell(currentCell).getMarked())
                    {
                        //mark the cell as visited
                        this.visitedBoard.getCell(currentCell).setMarked(true);

                        //find the next position for the king
                        Cell nextPosition;
                        if (j == 0)
                        {
                            nextPosition = new Cell(currentCell.getxCoordinate()
                                    - 1, currentCell.getyCoordinate() - 1);
                        }
                        else if (j == 1)
                        {
                            nextPosition = new Cell(currentCell.getxCoordinate()
                                    + 1, currentCell.getyCoordinate() - 1);
                        }
                        else if (j == 2)
                        {
                            nextPosition = new Cell(currentCell.getxCoordinate()
                                    - 1, currentCell.getyCoordinate() + 1);
                        }else
                        {
                            nextPosition = new Cell(currentCell.getxCoordinate()
                                    + 1, currentCell.getyCoordinate() + 1);
                        }

                        //If the king's next position is in the bounds of the board the space is empty
                        // jump the checker and move king to new position and break out of the loop
                        if (this.checkerBoard.isInBounds(nextPosition))
                        {

                            //if a checker is in this next position, solution cannot be found.
                            if (checkerBoard.getCell(nextPosition).getMarked())
                            {
                                return false;
                            }

                            //the next position is valid, set the king to the next position
                            this.king.setCurrentPosition(nextPosition);
                            //mark the cell with the jumped checker as visited
                            this.visitedBoard.getCell(currentCell).setMarked(true);
                            //decrement the checker counter, once it hits zero, path of destruction has been found.
                            checkerCounter--;
                            if (checkerCounter == 0)
                            {
                                return true;
                            }
                            break;
                        }
                        //If the checker can't be jumped no case will work and return false
                        else
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return false;
    }


    /**
     * check if checker 1 is valid
     */
    private static boolean isCheckerValid(int[] checker1, int xMax, int yMax)
    {
        if(checker1[0] > xMax || checker1[1] > yMax || checker1[0] < 1 || checker1[1] < 1)
        {
            return false;
        }

        return true;
    }

    /**
     * check if "reference checker" is on an even square or odd square
     */
    private static boolean isEven(int[] checker1)
    {
        if((checker1[0] + checker1[1]) % 2 == 0)
        {
            return true;
        }

        return false;
    }

    /**
     *     check if checker color placement is legal
     */
    private static boolean isSameColor(int x, int y, boolean evenFlag)
    {
        if((x + y) % 2 == 0)
        {
            if(!evenFlag)
            {
                return false;
            }
        }
        else if(evenFlag)
        {
            return false;
        }

        return true;
    }

}