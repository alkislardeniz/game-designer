package gamemodel;

/**
 *
 * @author Mehmet Can Altuntaş
 * @version 21 April 2016
 */

public enum ObjectIcon
{
    //Movable objects
    MEGAMAN (1, 1, true),
    MARIO   (1, 1, true),
    COP     (1, 1, true),

    //Non movable backgrounds
    BG0     (),
    BG      (),
    BG2     (),
    BG3     (),
    BG4     (),
    BG5     (),
    BG6     (),

    //Non movable objects
    BLOCK   (1, 1, false),
    ROCK    (1, 1, false),
    STONE   (1, 2, false),
    TREE    (1, 2, false),
    TREE2   (2, 2, false),
    TREE3   (2, 2, false),
    TREE4   (2, 2, false),
    TREE5   (2, 2, false),

    HOUSE   (7, 6, false),
    ANTIQUE   (2, 2, false),
    BED   (2, 3, false),
    BED2   (1, 2, false),
    BENCH   (2, 1, false),
    BENCH2   (2, 1, false),
    CASE  (1, 2, false),
    CHEST  (1, 2, false),
    DESK (2, 2, false),
    LAMP   (3, 4, false),
    LOCKER   (1, 3, false),
    PHONE   (1, 2, false),
    POST   (1, 2, false),
    SACK  (2, 2, false),
    STATUE  (1, 3, false),
    SWORDS  (2, 1, false),
    TABLETENNIS  (5, 3, false),
    WC (1, 2, false),
    FENCE   (1, 1, false),
    HOUSE2  (6, 8, false),
    HOUSE3  (6, 8, false),
    HOUSE4  (6, 4, false),
    HOUSE5  (6, 4, false),
    CAR     (3, 2, false),

    CHAIR   (2, 1, false),
    SHELF   (1, 2, false),
    SHELF2  (1, 2, false),
    SOFA    (3, 1, false),
    SOFA2   (3, 1, false),
    TABLE   (1, 1, false),
    TABLE2  (1, 1, false);

    //properties
    boolean movable;
    boolean collidable;
    boolean isBackground;

    //size of the image
    int width;
    int height;

    private ObjectIcon(int width, int height, boolean movable)
    {
        this.width   = width;
        this.height  = height;
        this.movable = movable;
        collidable = false;
    }

    private ObjectIcon(int width, int height, boolean movable, boolean collidable)
    {
        this.width      = width;
        this.height     = height;
        this.movable    = movable;
        this.collidable = collidable;
    }

    // for backgrounds only
    private ObjectIcon()
    {
        width = 21;
        height = 21;
        movable = false;
        collidable = true;
        isBackground = true;
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

    public boolean isBackground() { return isBackground; }
}
