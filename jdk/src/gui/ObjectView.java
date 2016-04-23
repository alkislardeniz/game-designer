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
    ObjectIconView icon;

    public ObjectView(ScreenView parent, ScreenObject obj, boolean editing)
    {
        super(parent, obj, editing);
        icon = ObjectIconView.getIcon(obj.getIcon());
        icon.moving = false;

        // copy obj for playing
        if (!editing)
            obj = new ScreenObject(obj); // parent.parent.getPlayer().getSharedObject(obj);
        this.obj = obj;
    }

    // draw object on screen
    public void paintComponentOn(Graphics g)
    {
        // handle the icon work on this class as well
        getImage().paintIcon(parent, g, getX() * parent.IMAGE_WIDTH, getY() * parent.IMAGE_HEIGHT);
    }

    // try to move object, changing position and icon's current image
    public boolean move(int direction)
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

        x = getX() + dx;
        y = getY() + dy;

        // check compatibility in obj's parent
        if (obj.getParent().placeComponent(obj, x, y))
        {
            // if can move, change coordinates and change currentIcon to direction

            // check if the movable object goes in front of a button (problem: putting button on top of object)
            if (parent.parent.getPlayer() != null)
            {
                for (ScreenComponent comp : parent.screen.getComponents())
                    if (comp instanceof ScreenButton && !((ScreenButton) comp).getVisible() && comp.contains(x, y))
                        ((ScreenButton) comp).clicked(parent.parent.getPlayer());
            }

            icon.moving = true;
            icon.setImage(dx, dy);

            setX(x);
            setY(y);

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

    // change coordinates for movable objects

    @Override
    public void setX(int x)
    {
        super.setX(x);
        if (icon.movable)
            obj.getPosition().setLocation(x, getY());
    }

    @Override
    public void setY(int y)
    {
        super.setY(y);
        if (icon.movable)
            obj.getPosition().setLocation(getX(), y);
    }
}
