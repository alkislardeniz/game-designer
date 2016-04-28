package filemodule.tests;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import filemodule.*;


public class SerializeTest extends JFrame {

    public static void main(String[] args) {
        SerializeTest test = new SerializeTest();
        Out out = new Out("", test);
        try {
            out.serializeFile();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JPanel testPanel;

    public SerializeTest() {
        super("Test");
        testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(400, 500));
        add(testPanel);

        pack();
        setVisible(true);
    }
}
