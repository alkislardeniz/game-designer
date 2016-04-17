package gui;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum ObjectIcon
{
    MEGAMAN ("manRight.gif", "manRight.gif", "manLeft.gif", "manRight.gif", "manRightStand.gif", "manLeftStand.gif"),
    HOUSE ("house.png"),
    ROCK  ("rock.png"),
    BG    ("bg.png");

    // need to store icon and movability in ScreenObject

    // is this necessary?
    public static ObjectIcon getIcon(String iconName)
    {
        return null;
    }

    ImageIcon[] images; // contains icons to show
    ImageIcon currentImg;
    boolean movable;
    boolean moving;

    private ObjectIcon(String image)
    {
        ImageIcon img = new ImageIcon("pics/" + image);
        // TODO initialize array of icons
        movable = false;
    }

    private ObjectIcon(String up, String down, String left, String right, String rightStand, String leftStand)
    {
        // TODO
        // perhaps store icons in an array or hash table
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
