package gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Program to be run, creates/opens/saves games and plays the open game.
 * @author Mehmet Can Altuntaş
 * Created by admin on 4/28/16.
 */
public class GameProgram extends JFrame
{

    // Swing Properties
    JMenuBar bar; // main menubar
    JMenu file; // file menu
    JMenu openGame; // contains options about opening the saved games, part of file menu
    JMenuItem openEditable; // opens an editable saved game, part of openGame menu
    JMenuItem openPlayable; // opens a playable saved game, part of openGame menu
    JMenu saveGame; // contains save options, part of file menu
    JMenuItem saveEditable; // saves editable games, part of saveGame menu
    JMenuItem savePlayable; // saves playable games, part of saveGame menu
    JMenuItem playGame; // lets user to play the game, part of file menu

    JFileChooser chooser;

    public GameProgram()
    {
        super("Game");

        createMenu();
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createMenu()
    {
        bar = new JMenuBar();

        // file menu
        file = new JMenu("File");

        // create new game
        JMenuItem createNew = new JMenuItem("New");
        file.add(createNew);

        // open a game
        openGame = new JMenu("Open");
        openEditable = new JMenuItem("Open Editable Game");
        openPlayable = new JMenuItem("Open Playable Game");
        openGame.add(openEditable);
        openGame.add(openPlayable);
        file.add(openGame);

        // save menu item
        saveGame = new JMenu("Save");
        saveEditable = new JMenuItem("Create Editable Game");
        savePlayable = new JMenuItem("Create Playable Game");
        saveGame.add(saveEditable);
        saveGame.add(savePlayable);
        file.add(saveGame);

        // play game
        playGame = new JMenuItem("Play");
        file.add(playGame);

        bar.add(file);

        // file menu ends

        setJMenuBar(bar);

    }

    // TODO

    class MenuBarListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == openGame)
            {
                
            }
        }
    }


}
