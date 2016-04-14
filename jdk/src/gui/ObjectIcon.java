package gui;

import javax.swing.*;

/**
 * Created by admin on 4/12/16.
 */
public enum ObjectIcon
{
    HOUSE ("house.png"),
    ROCK  ("rock.png"),
    BG    ("bg.png");

    // need to store icon and movability in ScreenObject

    // TODO unify this with MovableObjectIcon, based on whether a given icon is movable or not

    public static ObjectIcon getIcon(String iconName)
    {
        return null;
    }

    ImageIcon image;

    private ObjectIcon(String image)
    {
        this.image = new ImageIcon(image);
    }

    public ImageIcon getImage()
    {
        return image;
    }

    public void setImage(int x, int y)
    {
        // TODO
    }
}
