package gui;

import gamemodel.*;
import javax.swing.*;

/**
 * Displays preview image for screens, to be used inside GameEditController.
 * Created by admin on 4/26/16.
 */
public abstract class ScreenPreview extends JPanel
{
    Screen screen;

    // for assignment screens, show the screen's name and assignment
    // for conditional screens, show its name and predicate
    // for playable screens, show its name

    // use clicks and double clicks to open a dialog to edit properties or the corresponding ScreenEditController
}
