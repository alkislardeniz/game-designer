package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by admin on 4/18/16.
 */
public class EditScreenViewTest
{
    public static void main( String[] args)
    {
        Game game = new Game();
        PlayableScreen screen = new PlayableScreen(game, "a");
        AssignScreen assign = new AssignScreen(game, "b");

        game.addVariable("x");
        game.setVariable("x", "2");

        game.addScreen(screen);
        game.addScreen(assign);
        game.setStartScreen(screen);

        GamePlayer player = new GamePlayer(game);

        ScreenObject object = new ScreenObject(screen, "megaman", ObjectIcon.MEGAMAN);


        object.setPosition(new Point(0, 0));


        screen.addComponent(object);


        screen.setMovable(object);


        screen.addOption("option 1", assign);

        assign.setVariable("x");
        assign.setNewValue("x + 1");
        assign.addOption("option", screen);

        // player.addObserver(new GameObserver(player, object));
        // button.clicked(player);

        ImageIcon logo = new ImageIcon ("pics/logo.png");
        JFrame f = new JFrame ("Dadam - Game Designer");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(520, 620);
        f.add(new ScreenEditController(screen));

        // ScreenPlayController controller = new ScreenPlayController(player, screen);

        f.add(new JLabel(logo), BorderLayout.NORTH);
        f.pack();
        f.setLocationRelativeTo(null);

        f.setVisible(true);
    }


}
