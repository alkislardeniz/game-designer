package gui;

import filemodule.*;
import gamemodel.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Program to be run, creates/opens/saves games and plays the open game.
 * @author Mehmet Can Altuntaş
 * Created by admin on 4/28/16.
 */
public class GameProgram extends JFrame
{
    JPanel panel;
    GameController controller;

    public GameProgram()
    {
        super("Game");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        createMenu();
        panel.add(Box.createRigidArea(new Dimension(1000, 650)));
        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createMenu()
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

        createNew.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller = new GameEditController(new Game());

                // create new Game object and add its panel to frame
                panel.removeAll();
                panel.add((GameEditController) controller);

                validate();
                repaint();
                pack();

                saveEditable.setEnabled(true);
                savePlayable.setEnabled(true);
            }
        });

        openEditable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadamsave File", "dadamsave");
                chooser.setFileFilter(filter);
                int tmp = chooser.showOpenDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println(fileName);
                }

                try
                {
                    Game game = (Game) new In(fileName).deserializeFile();
                    // create new Game object and add its panel to frame
                    panel.removeAll();
                    panel.add(new GameEditController(game));

                    validate();
                    repaint();

                    saveEditable.setEnabled(true);
                    savePlayable.setEnabled(true);
                    playGame.setEnabled(true);
                }
                catch (Exception ex)
                {
                    // report error
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        openPlayable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadamsave File", "dadamsave");
                chooser.setFileFilter(filter);
                int tmp = chooser.showOpenDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println(fileName);
                }

                try
                {
                    GamePlayer player = (GamePlayer) new In(fileName).deserializeFile();
                    // create new Game object and add its panel to frame
                    panel.removeAll();
                    panel.add(new GamePlayController(player));

                    validate();
                    repaint();
                    pack();

                    saveEditable.setEnabled(false);
                    savePlayable.setEnabled(true);
                    playGame.setEnabled(true);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveEditable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadamsave File", "dadamsave");
                chooser.setFileFilter(filter);
                int tmp = chooser.showSaveDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println(fileName);
                }

                try
                {
                    assert controller != null && controller instanceof GameEditController;
                    new Out(new File(fileName), ((GameEditController) controller).game).serializeFile();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        savePlayable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadamsave File", "dadamsave");
                chooser.setFileFilter(filter);
                int tmp = chooser.showSaveDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println(fileName);
                }

                try
                {
                    assert controller != null && controller instanceof GamePlayController;
                    new Out(new File(fileName), ((GamePlayController) controller).player).serializeFile();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        playGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                assert controller != null;

                JFrame frame = new JFrame("Game");

                JPanel playPanel = new GamePlayController(new PlayerWindow() {
                    @Override
                    public GamePlayer getPlayer()
                    {
                        if (controller == null)
                            return null;

                        if (controller instanceof GamePlayController)
                            return ((GamePlayController) controller).player;

                        return new GamePlayer(((GameEditController) controller).game);
                    }

                    // close controller if game over
                    @Override
                    public void gameOver()
                    {
                        // close frame
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });

                frame.add(playPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        // file menu ends

        setJMenuBar(bar);

    }

    public static void main(String[] args)
    {
        new GameProgram();
    }
}
