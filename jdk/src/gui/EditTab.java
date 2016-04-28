package gui;

/**
 * Created by sev on 26.04.2016.
 */
import gamemodel.GamePlayer;
import gamemodel.PlayableScreen;
import gamemodel.ScreenButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * Creating and using TabComponentsDemo example
 */
public class EditTab extends JPanel {

    private final int tabNumber = 5;
    private final JTabbedPane pane = new JTabbedPane();

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                JFrame frame = new JFrame();
                EditTab tab = new EditTab("TEST");
                tab.runTestWithDadam();
                frame.add(tab);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }

    public EditTab(String title) {
        add(pane);

    }


    private void addPlus() {

        String title = "+";
        pane.add(title,new JLabel(title));
    }

    public void runTest() {
        pane.removeAll();
        addPlus();
        for (int i = 0; i < tabNumber; i++) {
            String title = "Tab " + i;
            pane.add(title, new JLabel(title));
            initTabComponent(i);
        }
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(400, 200));
        setVisible(true);
    }

    gamemodel.Game game;
    private void runTestWithDadam() {
        pane.removeAll();
        game = new gamemodel.Game();
        GamePlayer player = new gamemodel.GamePlayer(game);
        PlayableScreen screen = new gamemodel.PlayableScreen(game, "Screen 1");
        PlayableScreen screen2 = new gamemodel.PlayableScreen(game, "Screen 2");
        ScreenButton button = new ScreenButton(screen, "hello");
        screen.addComponent(button);
        for(int i = 0;i < 3;i++) {
            game.addScreen(screen);
            game.addScreen(screen2);
        }
        ArrayList<gamemodel.Screen> screenList = (ArrayList) game.getScreens();





        //Getting each Screen from Game and creating a screenview for them then adds it to TABPane
        for (int i = 0; i < screenList.size();i++) {
            String title = "Screen " + i;
            ScreenEditController controller = new ScreenEditController((gamemodel.PlayableScreen) screenList.get(i));
            pane.addTab(title,controller);
            initTabComponent(i);
        }


        pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setSize(new Dimension(400, 200));
        setVisible(true);




    }

    private void initTabComponent(int i) {
        pane.setTabComponentAt(i, new ButtonTabComponent(pane));
    }


    class ScreenAddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //TODO Need to get a game instance for every method in one place.
            game.addScreen(new gamemodel.PlayableScreen(game, "Screen 1"));
        }


    }

    private JButton addButton() {

        JButton addScreen = new JButton("Add");
        addScreen.addActionListener(new ScreenAddListener());
        return addScreen;
    }

    public gamemodel.Game getGame(){
        return this.game;
    }



    //Setting menu

}



