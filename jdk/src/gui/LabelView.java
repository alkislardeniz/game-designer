package gui;

import java.awt.*;
import gamemodel.*;

import javax.swing.*;

/**
 * Represents a ScreenLabel graphically.
 * @author  Ata Deniz Aydin
 * @version 18/04/16
 */
public class LabelView extends ComponentView
{
    ScreenLabel label;
    JLabel jLabel;

    public LabelView(ScreenView parent, ScreenLabel label, boolean editing)
    {
        super(parent, label, editing);

        this.label = label;
    }

    // draw component on screen
    public void paintComponentOn(Graphics g)
    {
        g.drawString(label.getText(parent.parent.getPlayer()),
                     (int) label.getPosition().getX() * parent.IMAGE_WIDTH,
                     (int) (label.getPosition().getY() + 1) * parent.IMAGE_HEIGHT);
    }
}
