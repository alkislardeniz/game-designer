package gui;

import java.awt.*;
import gamemodel.*;

import javax.swing.*;

/**
 * Created by admin on 4/18/16.
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
        // TODO fix the font to fit IMAGE_WIDTH and IMAGE_HEIGHT and adjust getPosition() based on text
        g.drawString(label.getText(parent.parent.getPlayer()),
                     (int) label.getPosition().getX() * parent.IMAGE_WIDTH,
                     (int) label.getPosition().getY() * parent.IMAGE_HEIGHT);
    }
}
