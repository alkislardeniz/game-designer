package gamemodel;

/**
 * Visitor interface for different subclasses of screens.
 * @author  Ata Deniz Aydin
 * @version 12/04/16
 */
public interface ScreenVisitor
{
    public void visit(PlayableScreen screen);
    public void visit(AssignScreen screen);
    public void visit(CondScreen screen);
}
