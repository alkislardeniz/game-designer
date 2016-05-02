package gamemodel;

/**
 * Represents an object on a playable screen.
 * @author  Ata Deniz Aydin
 * @version 17/04/16
 */
public class ScreenObject extends ScreenComponent
{
    ObjectIcon img;
    boolean collidable;

    public ScreenObject(PlayableScreen par, String nam, ObjectIcon img)
    {
        super(par, nam);
        this.img = img;
        height = img.getHeight();
        width = img.getWidth();
        collidable = img.collidable;
    }

    // copy constructor
    public ScreenObject(ScreenObject other)
    {
        super(other);
        this.img = other.img;
        height = img.getHeight();
        width = img.getWidth();
        collidable = img.collidable;
    }

    // getters, setters

    @Override
    public boolean isCompatible(ScreenComponent other, int x, int y)
    {
        boolean override = collidable;
        
        if (other instanceof ScreenObject)
            override = override || ((ScreenObject) other).collidable;

        return override || super.isCompatible(other, x, y);
    }

    public ScreenComponent copy()
    {
        return new ScreenObject(this);
    }

    public boolean equals(Object other)
    {
        return other != null
                && other instanceof ScreenObject
                && super.equals(other)
                && ((ScreenObject) other).img.equals(img);
    }

    public ObjectIcon getIcon() { return img; }

    public void setIcon(ObjectIcon img)
    {
        this.img = img;
        height = img.getHeight();
        width = img.getWidth();
    }

    public boolean isMovable()
    {
        return img.movable;
    }

    public boolean isBackground() { return img.isBackground; }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    public boolean valid()
    {
        return true;
    }
}