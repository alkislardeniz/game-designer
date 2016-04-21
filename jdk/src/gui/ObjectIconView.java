package gui;

import gamemodel.*;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum ObjectIconView
{
    MEGAMAN (ObjectIcon.MEGAMAN, "manLeft.gif", "manRight.gif", "manLeftStand.gif", "manRightStand.gif"),
    HOUSE   (ObjectIcon.HOUSE, "house.png"),
    ROCK    (ObjectIcon.ROCK, "rock.png"),
    BG      (ObjectIcon.BG, "bg.png");

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
        images[1][0] = new ImageIcon("pics/" + image);

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
        if (movable)
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
        else
            directionIndex = 1;
    }
}
