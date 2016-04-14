package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by admin on 4/12/16.
 */
public class ObjectView extends ComponentView
{
    ScreenObject obj;
    ObjectIcon icon;
    boolean moving;

    public ObjectView(ScreenObject obj)
    {
        super(obj);
        this.obj = obj;
        moving = false;
    }

    public void addComponent(ScreenView scr) {}

    // draw object on screen
    public void paintComponentOn(Graphics g, ScreenView scr)
    {
        // handle the icon work on this class as well
        getImage().paintIcon(scr, g, getX(), getY());
    }

    // try to move object, changing position and icon's current image
    public boolean move(int direction, int jump)
    {
        int x = 0, y = 0;

        if (direction == KeyEvent.VK_UP)
            y = -1;
        if (direction == KeyEvent.VK_DOWN)
            y = 1;
        if (direction == KeyEvent.VK_LEFT)
            x = -1;
        if (direction == KeyEvent.VK_RIGHT)
            x = 1;

        // check compatibility in obj's parent
        if (obj.getParent().canPlaceComponent(obj, x, y))
        {
            // if can move, change coordinates and change currentIcon to direction

            icon.setImage(x, y);
            moving = true;

            return true;
        }
        else
        {
            // otherwise change it to leftStand or rightStand
            moving = false;

            return false;
        }
    }

    public ImageIcon getImage()
    {
        // depends on whether the object is moving or not
        return icon.getImage();
    }
}
