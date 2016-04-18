package gui;

import gamemodel.*;
import java.awt.*;

/**
 * Created by admin on 4/18/16.
 */
public class TextBoxView extends ComponentView
{
    ScreenTextBox textBox;

    public TextBoxView(ScreenView parent, ScreenTextBox textBox, boolean editing)
    {
        super(parent, textBox, editing);

        // TODO add text field
    }

    // draw component on screen
    public void paintComponentOn(Graphics g)
    {
        // TODO update properties based on state of parent
    }
}
