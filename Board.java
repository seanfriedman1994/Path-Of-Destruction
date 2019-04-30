import java.util.ArrayList;

/**
 * Sean Friedman
 * NID: se972594
 *
 * Ralph Dimayuga
 * NID: Ra597979
 */

public class Board {
    private ArrayList<Cell> board = new ArrayList<>();
    private int xMax, yMax;

    public Board(int xMax, int yMax)
    {
        this.xMax = xMax;
        this.yMax = yMax;
        this.initializeBoard();
    }

    /**
     * creates the board with the dimensions xMax and yMax
     */
    private void initializeBoard()
    {
        for(int i = 1; i <= this.xMax; i++)
        {
            for(int j = 1; j <= this.yMax; j++)
            {
                this.board.add(new Cell(i,j));
            }
        }
    }

    /**
     * checks if a cell is inbounds
     */
    public boolean isInBounds(Cell newPosition)
    {
        if(newPosition.getxCoordinate() > this.xMax || newPosition.getxCoordinate() < 1 ||
                newPosition.getyCoordinate() > this.yMax || newPosition.getyCoordinate() < 1)
        {
            return false;
        }

        return true;
    }
    /**
     * Marks all cell's values on the board to 0
     */
    public void clear()
    {
        for(int i = 0; i < this.board.size(); i++)
        {
            this.board.get(i).setMarked(false);
        }
    }

    /**
     * returns the cell on the board with the same coordinates as the passed reference cell
     */
    public Cell getCell(Cell referenceCell) {
        return getCell(referenceCell.getxCoordinate(), referenceCell.getyCoordinate());
    }

    /**
     * returns the cell on the board with the passed in coordinates. If there is no cell with the given coordinates
     * return null
     */
    public Cell getCell(int x, int y)
    {
        for(int i = 0; i < this.board.size(); i++)
        {
            if(this.board.get(i).getxCoordinate() == x && this.board.get(i).getyCoordinate() == y)
            {
                return this.board.get(i);
            }

        }
        return null;
    }

    /**
     * returns this cell with a given index
     */
    public Cell getCell(int index)
    {
        return this.board.get(index);
    }

    public int getSize()
    {
        return this.board.size();
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }
}
