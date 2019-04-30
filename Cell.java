/**
 * Sean Friedman
 * NID: se972594
 *
 * Ralph Dimayuga
 * NID: Ra597979
 */

public class Cell {
    private int xCoordinate;
    private int yCoordinate;
    private boolean marked;

    public Cell(int x, int y)
    {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.marked = false;
    }

    public int getxCoordinate() {
        return this.xCoordinate;
    }

    public void setxCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate()
    {
        return this.yCoordinate;
    }

    public void setyCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }

    public boolean getMarked()
    {
        return this.marked;
    }

    public void setMarked(boolean marked)
    {
        this.marked = marked;
    }
}
