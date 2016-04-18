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

        jLabel = new JLabel(label.getText(parent.parent.getPlayer()));
        jLabel.setOpaque(true);
        parent.add(jLabel);
    }

    // draw component on screen
    public void paintComponentOn(Graphics g)
    {
        jLabel.setText(label.getText(parent.parent.getPlayer()));
    }
}
