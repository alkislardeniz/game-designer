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
    COP    (ObjectIcon.COP, "copLeft.gif", "copRight.gif", "copLeftStand.gif", "copRightStand.gif"),

    //Non movable backgrounds
    BG      (ObjectIcon.BG, "bg.png"),
    BG2     (ObjectIcon.BG2, "bg.png"),
    BG3     (ObjectIcon.BG3, "bg.png"),
    BG4     (ObjectIcon.BG4, "bg.png"),
    BG5     (ObjectIcon.BG5, "bg.png"),

    //Non movable objectS
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
    CAR     (ObjectIcon.CAR, "car.png"),
    FENCE   (ObjectIcon.FENCE, "fence.png")

    CHAIR     (ObjectIcon.CHAIR, "chair.png"),
    SHELF     (ObjectIcon.SHELF, "shelf.png"),
    SHELF2    (ObjectIcon.SHELF2, "shelf2.png"),
    SOFA      (ObjectIcon.SOFA, "sofa.png"),
    SOFA2     (ObjectIcon.SOFA2, "sofa2.png"),
    TABLE     (ObjectIcon.TABLE, "table.png"),
    TABLE2    (ObjectIcon.TABLE, "table.png");

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
    boolean moving;
    int directionIndex;

    private ObjectIconView(ObjectIcon icon, String image)
    {
        this.icon = icon;

        images = new ImageIcon[2][2];
        images[0][0] = images[0][1] = images[1][0] = images[1][1] = new ImageIcon("pics/" + image);

        directionIndex = 1;
        moving = false;
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
        moving = false;
        movable = true;
    }

    public ImageIcon getImage()
    {
        return images[directionIndex][getMovingIndex()];
    }

    public void setImage(int dx, int dy)
    {
        // turn left if dx < 0, right otherwise
        setDirectionIndex(dx);
    }

    public boolean getMoving() { return moving; }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    // return 0 if standing, 1 if moving
    private int getMovingIndex()
    {
        if (moving)
            return 1;
        return 0;
    }

    // return 0 if left, 1 if right
    private void setDirectionIndex(int dx)
    {
        if (dx < 0)
            directionIndex = 0;
        directionIndex = 1;
    }
}
