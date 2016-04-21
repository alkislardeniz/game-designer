package gamemodel;

/**
 * Created by admin on 4/3/16.
 */
public class ScreenObject extends ScreenComponent
{
    ObjectIcon img;

    public ScreenObject(PlayableScreen par, String nam, ObjectIcon img)
    {
        super(par, nam);
        this.img = img;
    }

    // copy constructor
    public ScreenObject(ScreenObject other)
    {
        super(other);
    }

    // getters, setters

    public ObjectIcon getIcon() { return img; }

    public void setIcon(ObjectIcon img)
    {
        this.img = img;
    }

    public boolean isMovable()
    {
        return img.movable;
    }

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