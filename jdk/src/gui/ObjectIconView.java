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

    //Non movable objects
    ANTIQUE (ObjectIcon.ANTIQUE, "antique.png"),
    BED     (ObjectIcon.BED, "bed.png"),
    BED2    (ObjectIcon.BED2, "bed2.png"),
    BENCH   (ObjectIcon.BENCH, "bench.png"),
    BENCH2  (ObjectIcon.BENCH2, "bench2.png"),
    CASE    (ObjectIcon.CASE, "case.png"),
    CHEST   (ObjectIcon.CHEST, "chest.png"),
    DESK    (ObjectIcon.DESK, "desk.png"),
    LAMP    (ObjectIcon.LAMP, "lamp.png"),
    LOCKER  (ObjectIcon.LOCKER, "locker.png"),
    PHONE   (ObjectIcon.PHONE, "phone.png"),
    POST    (ObjectIcon.POST, "post.png"),
    SACK    (ObjectIcon.SACK, "sack.png"),
    STATUE  (ObjectIcon.STATUE, "statue.png"),
    SWORDS  (ObjectIcon.SWORDS, "swords.png"),
    TABLETENNIS  (ObjectIcon.TABLETENNIS, "tabletennis.png"),
    WC      (ObjectIcon.WC, "wc.png"),

    BLOCK   (ObjectIcon.BLOCK, "block.png"),
    ROCK    (ObjectIcon.ROCK, "rock.png"),
    STONE   (ObjectIcon.STONE, "stone.png"),
    TREE    (ObjectIcon.TREE, "tree.png"),
    TREE2   (ObjectIcon.TREE2, "tree2.png"),
    TREE3   (ObjectIcon.TREE3, "tree3.png"),
    TREE4   (ObjectIcon.TREE4, "tree4.png"),
    TREE5   (ObjectIcon.TREE5, "tree5.png"),

    HOUSE   (ObjectIcon.HOUSE, "house.png"),
    HOUSE2  (ObjectIcon.HOUSE2, "house2.png"),
    HOUSE3  (ObjectIcon.HOUSE3, "house3.png"),
    HOUSE4  (ObjectIcon.HOUSE4, "house4.png"),
    HOUSE5  (ObjectIcon.HOUSE5, "house5.png"),
    CAR     (ObjectIcon.CAR, "car.png"),
    FENCE   (ObjectIcon.FENCE, "fence.png"),

    CHAIR   (ObjectIcon.CHAIR, "chair.png"),
    SHELF   (ObjectIcon.SHELF, "shelf.png"),
    SHELF2  (ObjectIcon.SHELF2, "shelf2.png"),
    SOFA    (ObjectIcon.SOFA, "sofa.png"),
    SOFA2   (ObjectIcon.SOFA2, "sofa2.png"),
    TABLE   (ObjectIcon.TABLE, "table.png"),
    TABLE2  (ObjectIcon.TABLE2, "table2.png");

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
    int movingIndex;
    int directionIndex;

    private ObjectIconView(ObjectIcon icon, String image)
    {
        this.icon = icon;
        //C:/Users/user/IdeaProjects/game-designer/jdk/src/pics\
        images = new ImageIcon[2][2];
        images[1][0] = new ImageIcon("pics/" + image);

        directionIndex = 1;
        movingIndex = 0;
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
        directionIndex = 1;
        movingIndex = 0;
        movable = true;
    }

    public ImageIcon getImage()
    {
        return images[directionIndex][movingIndex];
    }

    public void setImage(int dx, int dy)
    {
        // turn left if dx < 0, right otherwise
        setDirectionIndex(dx);
    }

    public boolean getMoving() { return movingIndex != 0; }

    public void setMoving(boolean moving)
    {
        if (moving)
            movingIndex = 1;
        else
            movingIndex = 0;
    }

    // return 0 if standing, 1 if moving
    private int getMovingIndex()
    {
        return movingIndex;
    }

    // return 0 if left, 1 if right
    private void setDirectionIndex(int dx)
    {
        if (dx < 0)
            directionIndex = 0;
        else
            directionIndex = 1;
    }
}
