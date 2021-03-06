package gui.tests;
import gamemodel.*;
import gamemodel.Game;
import gamemodel.GamePlayer;
import gamemodel.PlayableScreen;
import gui.GameEditController;
import gui.GameView;

import javax.swing.*;
import java.awt.*;

/**
 * Mehmet was here uga buga luga duga muga suga kuga aga daga dega waga maga taga daga baga laga raga lagu ragu
 */
public class GameViewTest
{
    public static void main (String[] args)
    {
        Game game = new Game();

        game.addVariable("x");

        AssignScreen screen = new AssignScreen(game, "a");
        CondScreen screen2 = new CondScreen(game, "b");

        screen.addOption( "option", screen );
        screen2.addOption( "option 2",screen);
        screen.setVariable("x");
        screen2.setPred("true");

        screen.setNewValue("x+1+2+3+4+5+6");

        GamePlayer player = new GamePlayer(game);

        game.addScreen(screen);
        game.addScreen(screen2);

        GameView gameViewTest = new GameView( new GameEditController(game) );
        JFrame f = new JFrame ("GameViewTest");

        f.add(gameViewTest);
        // f.add(new ScreenEditController(screen));

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }
}
