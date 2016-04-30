package gamemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/3/16.
 */
public class PlayableScreen extends Screen
{
    List<ScreenComponent> components; // container?
    ScreenObject movable, background;
    // store screen dimensions

    public PlayableScreen()
    {
        playable = true;
    }

    // empty components other than shared objects
    // playable set to true
    public PlayableScreen(Game parent, String name)
    {
        super(parent, name);
        playable = true;
        components = new ArrayList<>();
        background = new ScreenObject(this, "BG0", ObjectIcon.BG0);
    }

    public ScreenObject getBackground()
    {
        return background;
    }

    public void setBackground(ScreenObject bg)
    {
        background = bg;
    }

    public ScreenComponent getComponent(String name)
    {
        for (ScreenComponent comp : components)
            if (comp.name.equals(name))
                return comp;
        return null;
    }

    public int getWidth() { return parent.width; }

    public int getHeight() { return parent.height; }

    public List<ScreenComponent> getComponents()
    {
        return new ArrayList<>(components);
    }

    public boolean addComponent(ScreenComponent comp)
    {
        if (comp instanceof ScreenObject)
        {
            ScreenObject obj = (ScreenObject) comp;

            if (obj.isMovable())
            {
                removeComponent(movable);
                movable = obj;
            }

            if (obj.isBackground())
            {
                // System.out.println(background.getIcon() + ", " + obj.getIcon());
                removeComponent(background);
                background = obj;
            }
        }

        return components.add(comp);
    }

    public ScreenComponent removeComponent(String name)
    {
        for (ScreenComponent comp : components)
            if (comp.name.equals(name))
            {
                components.remove(comp);
                return comp;
            }
        return null;
    }

    public boolean removeComponent(ScreenComponent comp)
    {
        return components.remove(comp);
    }

    public boolean hasComponent(ScreenComponent comp)
    {
        return components.contains(comp);
    }

    // try to place comp on (x,y) on the screen
    public boolean placeComponent(ScreenComponent newComp, int x, int y)
    {
        if (!canPlaceComponent(newComp, x, y))
            return false;

        // assign newComp to its position on the screen
        newComp.getPosition().setLocation(x, y);

        // add it to screen if not already in screen
        if (!components.contains(newComp))
            components.add(newComp);

        return true;
    }

    // return true if all components are compatible with comp
    // also check for boundaries
    // if comp is already in components and not on (x,y) and not movable, return false
    public boolean canPlaceComponent(ScreenComponent newComp, int x, int y)
    {
        // check for boundaries
        if (x < 0 || y < 0 || x + newComp.width > parent.width || y + newComp.height > parent.height)
            return false;

        // check for compatibility
        for (ScreenComponent comp : components)
            if (comp != newComp && !comp.isCompatible(newComp, x, y))
                return false;

        return true;
    }

    public ArrayList<ScreenComponent> findComponentsAt(int x, int y)
    {
        ArrayList<ScreenComponent> res = new ArrayList<>();

        for (ScreenComponent comp : components)
            if (comp.contains(x, y))
                res.add(comp);
        return res;
    }

    public ScreenComponent findFirstComponentAt(int x, int y)
    {
        ArrayList<ScreenComponent> res = findComponentsAt(x, y);

        if (res.isEmpty())
            return null;

        return res.get(res.size() - 1);
    }

    public ScreenObject getMovable()
    {
        return movable;
    }

    public void setMovable(ScreenObject movable)
    {
        if (movable == null || movable.isMovable())
            this.movable = movable;
    }

    // call components in leaving screen
    @Override
    public void toPlayer(GamePlayer player, Option option)
    {
        for (ScreenComponent comp : components)
            comp.leavingScreen(player);
        super.toPlayer(player, option);
    }

    public boolean valid()
    {
        for (ScreenComponent comp : components)
            if (!comp.valid())
                return false;
        return super.valid();
    }

    public void accept(ScreenVisitor visitor)
    {
        visitor.visit(this);
    }
}