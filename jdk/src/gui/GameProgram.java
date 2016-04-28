package gui;

import javax.swing.*;

/**
 * Program to be run, creates/opens/saves games and plays the open game.
 * @author Mehmet Can Altuntaş
 * Created by admin on 4/28/16.
 */
public class GameProgram extends JFrame
{
    public GameProgram()
    {
        createMenu();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMenu()
    {
        JMenuBar bar = new JMenuBar();

        // file menu
        JMenu file = new JMenu("File");
        // create new game
        JMenuItem createNew = new JMenuItem("New");
        file.add(createNew);
        // open a game
        JMenu openGame = new JMenu("Open");
        JMenuItem openEditable = new JMenuItem("Open Editable Game");
        JMenuItem openPlayable = new JMenuItem("Open Playable Game");
        openGame.add(openEditable);
        openGame.add(openPlayable);
        file.add(openGame);
        // save menu item
        JMenu saveGame = new JMenu("Save");
        JMenuItem saveEditable = new JMenuItem("Create Editable Game");
        JMenuItem savePlayable = new JMenuItem("Create Playable Game");
        saveGame.add(saveEditable);
        saveGame.add(savePlayable);
        file.add(saveGame);
        // play game
        JMenuItem playGame = new JMenuItem("Play");
        file.add(playGame);

        bar.add(file);
        // file menu ends

        setJMenuBar(bar);

    }

    // test
    public static void main(String[] args)
    {
        new GameProgram();
    }
}
