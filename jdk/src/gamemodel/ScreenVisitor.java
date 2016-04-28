package gamemodel;

/**
 * Created by admin on 4/28/16.
 */
public interface ScreenVisitor
{
    public void visit(PlayableScreen screen);
    public void visit(AssignScreen screen);
    public void visit(CondScreen screen);
}
