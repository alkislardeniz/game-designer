package gui;

import gamemodel.*;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum ObjectIconView
{
    //Movable objects
    MEGAMAN (ObjectIcon.MEGAMAN, "manLeft.gif", "manRight.gif", "manLeftStand.gif", "manRightStand.gif"),
    MARIO   (ObjectIcon.MARIO, "marioLeft.gif", "marioRight.gif", "marioLeftStand.gif", "marioRightStand.gif"),
    COP     (ObjectIcon.COP, "copLeft.gif", "copRight.gif", "copLeftStand.gif", "copRightStand.gif"),

    //Non movable backgrounds
    BG0     (ObjectIcon.BG0, "bg0.png"),
    BG      (ObjectIcon.BG, "bg.png"),
    BG2     (ObjectIcon.BG2, "bg2.png"),
    BG3     (ObjectIcon.BG3, "bg3.png"),
    BG4     (ObjectIcon.BG4, "bg4.png"),
    BG5     (ObjectIcon.BG5, "bg5.png"),
    BG6     (ObjectIcon.BG6, "bg6.png"),
    BG7     (ObjectIcon.BG7, "bg7.png"),
    BG8     (ObjectIcon.BG8, "bg8.png"),
    BG9     (ObjectIcon.BG9, "bg9.png"),
    TILE    (ObjectIcon.TILE, "tile.png"),
    TILE2    (ObjectIcon.TILE2, "tile2.png"),
    TILE3    (ObjectIcon.TILE3, "tile3.png"),
    TILE4    (ObjectIcon.TILE4, "tile4.png"),
    TILE5    (ObjectIcon.TILE5, "tile5.png"),
    BLOCK   (ObjectIcon.BLOCK, "block.png"),
    WALL    (ObjectIcon.WALL, "wall.png"),
    WALL2   (ObjectIcon.WALL2, "wall2.png"),

    // houses
    HOUSE   (ObjectIcon.HOUSE, "house.png"),
    HOUSE2  (ObjectIcon.HOUSE2, "house2.png"),
    HOUSE3  (ObjectIcon.HOUSE3, "house3.png"),
    HOUSE4  (ObjectIcon.HOUSE4, "house4.png"),
    HOUSE5  (ObjectIcon.HOUSE5, "house5.png"),

    //Non movable indoor objects
    ANTIQUE (ObjectIcon.ANTIQUE, "antique.png"),
    BOSSCHAIR (ObjectIcon.BOSSCHAIR, "bosschair.png"),
    BED     (ObjectIcon.BED, "bed.png"),
    BED2    (ObjectIcon.BED2, "bed2.png"),
    BED3    (ObjectIcon.BED3, "bed3.png"),
    BED4    (ObjectIcon.BED4, "bed4.png"),
    CARPET  (ObjectIcon.CARPET, "carpet.png"),
    CASE    (ObjectIcon.CASE, "case.png"),
    CHEST   (ObjectIcon.CHEST, "chest.png"),
    COMPUTERDESK (ObjectIcon.COMPUTERDESK, "computerdesk.png"),
    DESK    (ObjectIcon.DESK, "desk.png"),
    FIREPLACE (ObjectIcon.FIREPLACE, "fireplace.png"),
    KITCHEN (ObjectIcon.KITCHEN, "kitchen.png"),
    LOCKER  (ObjectIcon.LOCKER, "locker.png"),
    OFFICECHAIR (ObjectIcon.OFFICECHAIR, "officechair.png"),
    PIANO   (ObjectIcon.PIANO, "piano.png"),
    POT     (ObjectIcon.POT, "pot.png"),
    POT2    (ObjectIcon.POT2, "pot2.png"),
    SWORDS  (ObjectIcon.SWORDS, "swords.png"),
    SHELF   (ObjectIcon.SHELF, "shelf.png"),
    SHELF2  (ObjectIcon.SHELF2, "shelf2.png"),
    STAIRS  (ObjectIcon.STAIRS, "stairs.png"),
    STAIRSTOP (ObjectIcon.STAIRSTOP, "stairstop.png"),
    TAP (ObjectIcon.TAP, "tap.png"),
    TABLE   (ObjectIcon.TABLE, "table.png"),
    VASE    (ObjectIcon.VASE, "vase.png"),
    WC      (ObjectIcon.WC, "wc.png"),

    //Non movable outdoor objects
    BARREL  (ObjectIcon.BARREL, "barrel.png"),
    BENCH   (ObjectIcon.BENCH, "bench.png"),
    BENCH2  (ObjectIcon.BENCH2, "bench2.png"),
    BENCH3  (ObjectIcon.BENCH3, "bench3.png"),
    BENCH4  (ObjectIcon.BENCH4, "bench4.png"),
    BOX     (ObjectIcon.BOX, "box.png"),
    CAR     (ObjectIcon.CAR, "car.png"),
    FENCE   (ObjectIcon.FENCE, "fence.png"),
    GOLD    (ObjectIcon.GOLD, "gold.png"),
    GRAVE   (ObjectIcon.GRAVE, "grave.png"),
    LAMP    (ObjectIcon.LAMP, "lamp.png"),
    PANEL   (ObjectIcon.PANEL, "panel.png"),
    PHONE   (ObjectIcon.PHONE, "phone.png"),
    POST    (ObjectIcon.POST, "post.png"),
    ROCK    (ObjectIcon.ROCK, "rock.png"),
    SACK    (ObjectIcon.SACK, "sack.png"),
    STATUE  (ObjectIcon.STATUE, "statue.png"),
    STONE   (ObjectIcon.STONE, "stone.png"),
    TREE    (ObjectIcon.TREE, "tree.png"),
    TREE2   (ObjectIcon.TREE2, "tree2.png"),
    TREE3   (ObjectIcon.TREE3, "tree3.png"),
    TREE4   (ObjectIcon.TREE4, "tree4.png"),
    TREE5   (ObjectIcon.TREE5, "tree5.png"),
    TREE6   (ObjectIcon.TREE6, "tree6.png"),
    TREE7   (ObjectIcon.TREE7, "tree7.png"),
    TREE8   (ObjectIcon.TREE8, "tree8.png");

    // need to store icon and movability in ScreenObject

    // is this necessary?
    public static ObjectIconView getIcon(ObjectIcon icon)
    {
        for (ObjectIconView view : values())
        {
            if (view.icon == icon)
                return view;
        }
        return null;
    }

    ObjectIcon icon;
    ImageIcon[][] images; // contains icons to show
    boolean movable;
    ImageIcon defaultImage;

    private ObjectIconView(ObjectIcon icon, String image)
    {
        this.icon = icon;
        //C:/Users/user/IdeaProjects/game-designer/jdk/src/pics\
        images = new ImageIcon[2][2];
        images[1][0] = new ImageIcon("pics/" + image);

        defaultImage = images[1][0];
        movable = false;
    }

    private ObjectIconView(ObjectIcon icon, String left, String right, String leftStand, String rightStand)
    {
        this.icon = icon;

        images = new ImageIcon[2][2];

        images[0][0] = new ImageIcon("pics/" + leftStand);
        images[1][0] = new ImageIcon("pics/" + rightStand);
        images[0][1] = new ImageIcon("pics/" + left);
        images[1][1] = new ImageIcon("pics/" + right);

        // perhaps store icons in an array or hash table
        defaultImage = images[1][0];
        movable = true;
    }

    public ImageIcon getImage(ObjectView view)
    {
        if (!movable)
            return defaultImage;
        return images[view.directionIndex][view.movingIndex];
    }

    public void setImage(ObjectView view, int dx, int dy)
    {
        // turn left if dx < 0, right otherwise
        setDirectionIndex(view, dx);
    }

    public boolean getMoving(ObjectView view) { return view.movingIndex != 0; }

    public void setMoving(ObjectView view, boolean moving)
    {
        if (moving)
            view.movingIndex = 1;
        else
            view.movingIndex = 0;
    }

    // return 0 if standing, 1 if moving
    private int getMovingIndex(ObjectView view)
    {
        return view.movingIndex;
    }

    // return 0 if left, 1 if right
    private void setDirectionIndex(ObjectView view, int dx)
    {
        if (dx < 0)
            view.directionIndex = 0;
        else
            view.directionIndex = 1;
    }
}
