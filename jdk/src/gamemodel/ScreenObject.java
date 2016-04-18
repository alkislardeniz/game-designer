package gamemodel;

/**
 * Created by admin on 4/3/16.
 */
public class ScreenObject extends ScreenComponent
{
    boolean movable; // there are two types of objects, movable and immovable
    String img;

    public ScreenObject(PlayableScreen par, String nam, String img)
    {
        super(par, nam);
        this.img = img;
        // TODO change dimensions based on img
    }

    // getters, setters

    public String getIcon() { return img; }

    public void setIcon(String name, int height, int width)
    {
        this.img = name;
        this.height = height;
        this.width = width;
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