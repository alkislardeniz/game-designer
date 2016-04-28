package filemodule.tests;

import filemodule.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class DeserializeTest extends JFrame {

    public static void main(String[] args) {
        filemodule.tests.SerializeTest test = new filemodule.tests.SerializeTest();
        In in = new In("");

    }

    JPanel testPanel;

    public DeserializeTest() {
        super("Test");
        testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(400, 500));
        add(testPanel);

        pack();
        setVisible(true);
    }
}
