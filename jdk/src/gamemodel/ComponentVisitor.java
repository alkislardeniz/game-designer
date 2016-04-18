package gamemodel;

/**
 * ComponentVisitor
 * Visitor for screen components.
 * @author  Ata Deniz Aydin
 * @version 18/04/16
 */
public interface ComponentVisitor
{
    public void visit(ScreenButton comp);
    public void visit(ScreenLabel comp);
    public void visit(ScreenObject comp);
    public void visit(ScreenTextBox comp);
}
