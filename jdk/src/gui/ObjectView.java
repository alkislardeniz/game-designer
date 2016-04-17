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

    public ObjectView(ScreenObject obj, boolean editing)
    {
        super(obj, editing);
        this.obj = obj;
        icon.moving = false;
    }

    // draw object on screen
    public void paintComponentOn(Graphics g, ScreenView scr)
    {
        // handle the icon work on this class as well
        getImage().paintIcon(scr, g, getX(), getY());
    }

    // try to move object, changing position and icon's current image
    public boolean move(int direction, int xScale, int yScale)
    {
        int x, y, dx, dy;

        dx = dy = 0;

        if (direction == KeyEvent.VK_UP)
            dy--;
        if (direction == KeyEvent.VK_DOWN)
            dy++;
        if (direction == KeyEvent.VK_LEFT)
            dx--;
        if (direction == KeyEvent.VK_RIGHT)
            dx++;

        x = (int) obj.getPosition().getX() + dx;
        y = (int) obj.getPosition().getY() + dy;

        // check compatibility in obj's parent
        if (obj.getParent().placeComponent(obj, x, y))
        {
            // if can move, change coordinates and change currentIcon to direction

            icon.setImage(dx, dy);
            icon.moving = true;

            setX(x * xScale);
            setY(y * yScale);

            return true;
        }
        else
        {
            // otherwise do not move and do not update icon
            icon.moving = false;

            return false;
        }
    }

    public void stopMoving()
    {
        icon.moving = false;
    }

    public ImageIcon getImage()
    {
        // depends on whether the object is moving or not
        return icon.getImage();
    }
}
