package gui;

import filemodule.*;
import gamemodel.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

/**
 * Program to be run, creates/opens/saves games and plays the open game.
 * @author Mehmet Can Altuntaş
 * @author Ata Deniz Aydın
 * @version 30/04/16
 */
public class GameProgram extends JFrame
{
    public static void main(String[] args)
    {
        new GameProgram();
    }

    JPanel panel;
    GameEditController controller;

    public GameProgram()
    {
        super("Dadam Game Designer");

        JLabel label;

        createMenu();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        label = new JLabel(new ImageIcon("pics/welcome.png"));
        label.setSize(1000, 650);

        panel.add(label);
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

        JMenu about;
        JMenuItem aboutUs;


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
        saveEditable = new JMenuItem("Save Editable Game");
        savePlayable = new JMenuItem("Save Playable Game");
        saveEditable.setEnabled(false);
        savePlayable.setEnabled(false);
        saveGame.add(saveEditable);
        saveGame.add(savePlayable);
        file.add(saveGame);

        // play game
        playGame = new JMenuItem("Play");
        playGame.setEnabled(false);
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
                playGame.setEnabled(true);
            }
        });

        openEditable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadam editable file", "editable");
                chooser.setFileFilter(filter);
                int tmp = chooser.showOpenDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    // System.out.println(fileName);
                }

                try
                {
                    Game game = (Game) new In(fileName).deserializeFile();
                    // create new Game object and add its panel to frame
                    panel.removeAll();
                    controller = new GameEditController(game);
                    panel.add(controller);

                    validate();
                    repaint();

                    saveEditable.setEnabled(true);
                    savePlayable.setEnabled(true);
                    playGame.setEnabled(true);
                } catch (Exception ex)
                {
                    // report error
                    ex.printStackTrace();
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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadam playable file", "playable");
                chooser.setFileFilter(filter);
                int tmp = chooser.showOpenDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    // System.out.println(fileName);
                }

                try
                {
                    GamePlayer player = (GamePlayer) new In(fileName).deserializeFile();
                    // create new Game object and add its panel to frame
                    showPlayer(player);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveEditable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadam Editable File", "editable");
                chooser.setFileFilter(filter);
                int tmp = chooser.showSaveDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath() + ".editable";
                    // System.out.println(fileName);
                }

                try
                {
                    assert controller != null;

                    new Out(new File(fileName), controller.game).serializeFile();
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(GameProgram.this, "Something went wrong :(", "Error while opening the file", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        savePlayable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName = "";

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("dadam Playable Game", "playable");
                chooser.setFileFilter(filter);
                int tmp = chooser.showSaveDialog(GameProgram.this);

                if (tmp == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getAbsolutePath() + ".playable";
                    // System.out.println(fileName);
                }

                try
                {
                    new Out(new File(fileName), getPlayer()).serializeFile();
                } catch (Exception ex)
                {
                    ex.printStackTrace();
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

                GamePlayer player = getPlayer();

                if (player == null)
                {
                    // print error message -- game not set up correctly
                    JOptionPane.showMessageDialog(null, "Error: The game is not set up correctly.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                showPlayer(player);
            }
        });

        // file menu ends

        // about menu
        about = new JMenu("About");
        aboutUs = new JMenuItem("About");
        aboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aboutStr = "Dadam - Deniz Ata Demir Akant Mehmet \nLets you design j-rpg games"
                                + "\nThe only thing that restricts you is your imagination"
                                + "\n\nPart of Bilkent's CS 102 course";

                JOptionPane.showMessageDialog(GameProgram.this, aboutStr, "About Us", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        about.add(aboutUs);
        bar.add(about);

        setJMenuBar(bar);

    }

    public GamePlayer getPlayer()
    {
        if (controller.game.valid())
            return new GamePlayer(controller.game);

        return null;
    }

    public void showPlayer(GamePlayer player)
    {
        JFrame frame = new JFrame("Game");

        JPanel playPanel = new GamePlayController(new PlayerWindow() {
            @Override
            public GamePlayer getPlayer()
            {
                return player;
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
}
