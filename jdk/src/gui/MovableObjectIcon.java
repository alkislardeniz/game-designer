package gui;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum MovableObjectIcon
{
    MEGAMAN ("manRight.gif", "manRight.gif", "manLeft.gif", "manRight.gif", "manRightStand.gif", "manLeftStand.gif");

    ImageIcon currentIcon;

    private MovableObjectIcon(String up, String down, String left, String right, String rightStand, String leftStand)
    {
        // TODO
    }

    public ImageIcon getImage() { return currentIcon; }

    public ImageIcon getIcon(int direction) { return null; }

    public void setImage(int direction)
    {
        setImage(direction, false);
    }

    // assign image based on
    public void setImage(int direction, boolean moving)
    {
        // TODO
    }
}
