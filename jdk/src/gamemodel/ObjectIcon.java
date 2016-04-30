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
    BG7     (),
    BG8     (),
    BG9     (),
    TILE    (1, 1, false, true),
    TILE2    (1, 1, false, true),
    TILE3    (1, 1, false, true),
    BLOCK   (1, 1, false),
    WALL    (1, 1, false),
    WALL2   (1, 1, false),

    // houses
    HOUSE   (7, 6, false),
    HOUSE2  (6, 8, false),
    HOUSE3  (6, 8, false),
    HOUSE4  (6, 4, false),
    HOUSE5  (6, 4, false),

    //Non movable indoor objects
    ANTIQUE (2, 2, false),
    BOSSCHAIR (1, 2, false),
    BED     (2, 3, false),
    BED2    (1, 2, false),
    BED3    (3, 2, false),
    BED4    (3, 2, false),
    CARPET  (4, 3, false, true),
    CASE    (1, 2, false),
    CHEST   (1, 2, false),
    COMPUTERDESK (2, 2, false),
    DESK    (2, 2, false),
    FIREPLACE (2, 3, false),
    KITCHEN (4, 2, false),
    LOCKER  (1, 3, false),
    OFFICECHAIR (1, 1, false),
    PIANO   (2, 2, false),
    POT     (1, 3, false),
    POT2    (1, 2, false),
    SWORDS  (2, 1, false),
    SHELF   (2, 3, false),
    SHELF2  (1, 3, false),
    STAIRS (2, 4, false),
    STAIRSTOP (2, 2, false),
    TAP (1, 2, false),
    TABLE   (1, 1, false),
    VASE    (1, 1, false),
    WC      (1, 2, false),

    //Non movable outdoor objects
    BARREL  (1, 1, false),
    BENCH   (2, 1, false),
    BENCH2  (2, 1, false),
    BENCH3  (1, 2, false),
    BENCH4  (1, 2, false),
    BOX     (1, 1, false),
    CAR     (3, 2, false),
    FENCE   (1, 1, false),
    GOLD    (1, 1, false),
    GRAVE   (1, 1, false),
    LAMP    (3, 4, false),
    PANEL   (2, 2, false),
    PHONE   (1, 2, false),
    POST    (1, 2, false),
    ROCK    (1, 1, false),
    SACK    (2, 2, false),
    STATUE  (1, 3, false),
    STONE   (1, 2, false),
    TREE    (1, 2, false),
    TREE2   (2, 2, false),
    TREE3   (2, 2, false),
    TREE4   (2, 2, false),
    TREE5   (2, 2, false),
    TREE6   (2, 2, false),
    TREE7   (2, 2, false),
    TREE8   (2, 2, false);

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
