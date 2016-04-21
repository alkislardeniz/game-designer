package gui;

import gamemodel.*;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum ObjectIconView
{
    MEGAMAN ("manLeft.gif", "manRight.gif", "manRightStand.gif", "manLeftStand.gif"),
    HOUSE ("house.png"),
    ROCK  ("rock.png"),
    BG    ("bg.png");

    // need to store icon and movability in ScreenObject

    // is this necessary?
    public static ObjectIconView getIcon(ObjectIcon icon)
    {
        return null;
    }

    ImageIcon[] images; // contains icons to show
    ImageIcon currentImg;
    boolean movable;
    boolean moving;
    // TODO also contain information about size

    private ObjectIconView(String image)
    {
        ImageIcon img = new ImageIcon("pics/" + image);
        // TODO initialize array of icons
        movable = false;

        currentImg = img;
    }

    private ObjectIconView(String left, String right, String rightStand, String leftStand)
    {
        // TODO
        // perhaps store icons in an array or hash table
        currentImg = new ImageIcon("pics/" + rightStand);
        movable = true;
    }

    public ImageIcon getImage()
    {
        return currentImg;
    }

    public void setImage(int dx, int dy)
    {
        // TODO change icon based on direction and moving
    }

    public boolean getMoving() { return moving; }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
        // TODO update icon
    }
}
