
/**
 * Sean Friedman
 * NID: se972594
 *
 * Ralph Dimayuga
 * NID: Ra597979
 */

public class King {
	private Cell startPosition;
	private Cell currentPosition;

	public King()
    {
	    this.startPosition = new Cell(1,1);
	    this.currentPosition = new Cell(1,1);
    }

    public Cell getStartPosition()
    {
        return this.startPosition;
    }

    public Cell getCurrentPosition()
    {
        return this.currentPosition;
    }

    public void setCurrentPosition(Cell currentPosition)
    {
        this.currentPosition = currentPosition;
    }

    public void setStartPosition(Cell startPosition)
    {
        this.startPosition = startPosition;
    }


}

