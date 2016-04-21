package gamemodel;

/**
 *
 * @author Mehmet Can Altuntaş
 * @version 21 April 2016
 */

public enum ObjectIcon
{
    MEGAMAN (1, 1, true),
    HOUSE   (3, 3, false),
    ROCK    (1, 1, false),
    BG      (21, 21, false);


    //properties
    boolean movable;

    //size of the image
    int width;
    int height;

    private ObjectIcon(int width, int height, boolean isMovable)
    {
        this.width = width;
        this.height = height;

        movable = isMovable;
    }


    //getters and setters
    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setMovable(boolean isMovable)
    {
        movable = isMovable;
    }

}
